package com.lsdk.service.business.service.impl;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lsdk.client.ResEntity;
import com.lsdk.client.ResEntityUtils;
import com.lsdk.client.enums.MessageStsEnum;
import com.lsdk.client.enums.RestStsEnum;
import com.lsdk.client.req.FailureMessageReq;
import com.lsdk.client.req.MessageListReq;
import com.lsdk.client.req.MessageReq;
import com.lsdk.client.res.MessageRes;
import com.lsdk.service.business.service.RabbitBusinessService;
import com.lsdk.service.config.Constans;
import com.lsdk.service.dao.model.Message;
import com.lsdk.service.dao.model.MessageLog;
import com.lsdk.service.exception.InsBusiException;
import com.lsdk.service.service.impl.MessageLogService;
import com.lsdk.service.service.impl.MessageService;
import com.lsdk.service.utils.JSONSerializer;
import com.lsdk.service.utils.RedisKeyGenerator;
import com.lsdk.service.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RabbitBusinessServiceImpl implements RabbitBusinessService {

    private static Logger logger = LoggerFactory.getLogger(RabbitBusinessServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageLogService messageLogService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResEntity<List<MessageRes>> queryMessage(MessageListReq req){
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        if(req != null){
            if(StringUtils.isNotBlank(req.getIdempotentKey()))
                wrapper.eq("Idempotent_key",req.getIdempotentKey());
            if(StringUtils.isNotBlank(req.getTask()))
                wrapper.eq("task",req.getTask());
            if(req.getSts() != null)
                wrapper.eq("sts",req.getSts());
            if(req.getTrySum() != null)
                wrapper.ge("try_sum",req.getTrySum());
        }
        List<Message> messageList = messageService.list(wrapper);
        if(CollectionUtils.isNotEmpty(messageList)){
            return ResEntityUtils.success(
                    messageList.stream()
                    .map(message -> {
                        MessageRes messageRes = new MessageRes();
                        BeanUtils.copyProperties(message,messageRes);
                        return messageRes;
                    }).collect(Collectors.toList()));
        }
        return ResEntityUtils.error(RestStsEnum.MESSAGE_IS_NOT_EXIST); //不存在
    }

    @Override
    public ResEntity<Boolean> sendMsg(MessageReq message) {
        if(existKey(message.getIdempotentKey())){
            logger.info(" >>> idempotentKey:{}  message content :{} exists! <<< ",message.getIdempotentKey(),message);
            return ResEntityUtils.success();
        }
        //插入数据库
        Message o = new Message();
        BeanUtils.copyProperties(message,o);
        o.setCreateDate(new Date());
        try{
            if(!messageService.save(o)){
                return ResEntityUtils.success(false);
            }
            rabbitTemplate.convertAndSend(Constans.MSG_ACTIVITI_ROUTING_KEY,Constans.MSG_ACTIVITI_ROUTING_KEY,o, new CorrelationData(message.getIdempotentKey()));
        }catch (Exception e){
            throw new InsBusiException(RestStsEnum.FAILURE);
        }

        if(!rabbitTemplate.waitForConfirms(2000)){
            throw new InsBusiException(RestStsEnum.SEND_RABBIT_MQ_FAILUER);
        }

        cacheKey(Constans.IDEMPOTENT_KEY,o);
        return ResEntityUtils.success(true);
    }

    @Override
    public ResEntity<Boolean> sendOk(String idempotentKey) {
        return ResEntityUtils.success(existKey(idempotentKey));
    }

    @Override
    public ResEntity finish(String idempotentKey) {
        Message message = new Message();
        message.setFinishDate(new Date());
        message.setSts(MessageStsEnum.FINISHED.ordinal());
        if(!messageService.update(message,new QueryWrapper<Message>().eq("Idempotent_key",idempotentKey))){
            //todo 失败
        }
        return ResEntityUtils.success();
    }

    @Transactional
    @Override
    public ResEntity addFailure(@Valid FailureMessageReq req) {
        Message message = messageService.getOne(new QueryWrapper<Message>().eq("Idempotent_key",req.getIdempotentKey()));
        if(message == null){
            logger.error(" >>> idemportentKey:{} is not exist failure msg:{}! <<< ",req.getIdempotentKey(),req.getFailureMsg());
            return ResEntityUtils.error(RestStsEnum.MESSAGE_IS_NOT_EXIST);
        }
        Message newMessage = new Message();
        newMessage.setId(message.getId());
        newMessage.setTrySum(message.getTrySum() + 1);
        messageService.updateById(newMessage);

        MessageLog messageLog = new MessageLog();
        messageLog.setmId(message.getId());
        messageLog.setMsg(req.getFailureMsg());
        messageLog.setCreateDate(new Date());
        messageLogService.save(messageLog);
        return ResEntityUtils.success();
    }

    private void cacheKey(String idempotentKey, Message message) {
        String key = RedisKeyGenerator.keyGenerator(Constans.IDEMPOTENT_KEY,idempotentKey);
        if(!redisUtil.set(key, JSONSerializer.serialize(message),5*60*1000)){
            //检查问题 重试

        }
    }

    //查询key
    private boolean existKey(String idemportentKey){
        String key = RedisKeyGenerator.keyGenerator(Constans.IDEMPOTENT_KEY,idemportentKey);
        return redisUtil.hasKey(key);
    }
}
