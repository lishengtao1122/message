package com.lsdk.service.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lsdk.client.ResEntity;
import com.lsdk.client.req.FailureMessageReq;
import com.lsdk.client.req.MessageListReq;
import com.lsdk.client.req.MessageReq;
import com.lsdk.service.business.service.RabbitBusinessService;
import com.lsdk.service.dao.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/rabbit/api/v1")
public class RabbitController {

    @Autowired
    private RabbitBusinessService rabbitBusinessService;

    //查询
    @RequestMapping("/message/list")
    ResEntity messageList(@RequestBody @Valid MessageListReq req){
        return rabbitBusinessService.queryMessage(req);
    }

    @RequestMapping("/send")
    ResEntity sendMessage(@RequestBody @Valid MessageReq req){
        return rabbitBusinessService.sendMsg(req);
    }

    @RequestMapping("/sendOk/{idempotentKey}")
    ResEntity sendMessage(@PathVariable("idempotentKey") String idempotentKey){
        return rabbitBusinessService.sendOk(idempotentKey);
    }

    //完成消息状态
    @RequestMapping("/finish/{idempotentKey}")
    ResEntity finish(@PathVariable("idempotentKey") String idempotentKey){
        return rabbitBusinessService.finish(idempotentKey);
    }

    //更新消息状态
    @RequestMapping("/addFailure")
    ResEntity addFailure(@RequestBody @Valid FailureMessageReq req){
        return rabbitBusinessService.addFailure(req);
    }


}
