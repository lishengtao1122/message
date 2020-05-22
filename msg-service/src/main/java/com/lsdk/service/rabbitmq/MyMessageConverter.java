package com.lsdk.service.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class MyMessageConverter implements MessageConverter {
    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        com.lsdk.service.dao.model.Message message = (com.lsdk.service.dao.model.Message) o;
        Message m = new Message(JSONObject.toJSONBytes(message, SerializerFeature.WriteNullStringAsEmpty),messageProperties);
        return m;
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        byte[] data = message.getBody();
        com.lsdk.service.dao.model.Message mo = JSONObject.parseObject(data,com.lsdk.service.dao.model.Message.class);
        return mo;
    }
}
