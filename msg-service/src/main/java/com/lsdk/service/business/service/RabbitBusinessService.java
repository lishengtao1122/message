package com.lsdk.service.business.service;

import com.lsdk.client.ResEntity;
import com.lsdk.client.req.FailureMessageReq;
import com.lsdk.client.req.MessageListReq;
import com.lsdk.client.req.MessageReq;
import com.lsdk.client.res.MessageRes;
import com.lsdk.service.dao.model.Message;

import javax.validation.Valid;
import java.util.List;

public interface RabbitBusinessService {

    /**
     * 按照idempotentKey查询message
     * @param req
     * @return
     */
    ResEntity<List<MessageRes>> queryMessage(MessageListReq req);

    /**
     * 发送消息
     * @param message
     * @return
     */
    ResEntity<Boolean> sendMsg(MessageReq message);


    /**
     * 确认发送消息
     * @param idempotent
     * @return
     */
    ResEntity<Boolean> sendOk(String idempotent);

    //完成状态
    ResEntity finish(String idempotentKey);

    //消费消息失败状态
    ResEntity addFailure(@Valid FailureMessageReq req);
}
