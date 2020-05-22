package com.lsdk.client.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lsdk.client.ResEntity;
import com.lsdk.client.req.FailureMessageReq;
import com.lsdk.client.req.MessageListReq;
import com.lsdk.client.req.MessageReq;
import com.lsdk.client.res.MessageRes;
import com.lsdk.client.utils.HttpUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class MessageServiceUtils {

    @Value("${message.server.host}")
    private String messageServerHost;

    private static final String Prefix = "/message/rabbit/api/v1";

    private static final String messageList = "/message/list";
    private static final String sendMsg = "/send";
    private static final String sendOk = "/sendOk";
    private static final String finish = "/finish";
    private static final String addFailure = "/addFailure";

    //消息列表
    public ResEntity<List<MessageRes>> messageList(MessageListReq req){
        String json = JSONObject.toJSONString(req);
        String result = HttpUtils.sendPostForJSONString(messageServerHost+Prefix+messageList,json);
        ResEntity<List<MessageRes>> resEntity = JSONObject.parseObject(result,ResEntity.class);
        String data = JSONObject.parseObject(result).getObject("data",String.class);
        List<MessageRes> messageResList = JSONObject.parseArray(data,MessageRes.class);
        resEntity.setData(messageResList);
        return resEntity;
    }

    //发送消息
    public ResEntity<Boolean> sendMsg(MessageReq req){
        String json = JSONObject.toJSONString(req);
        String result = HttpUtils.sendPostForJSONString(messageServerHost+Prefix+sendMsg,json);
        ResEntity<Boolean> resEntity = JSONObject.parseObject(result,new TypeReference<ResEntity<Boolean>>(){});
        return resEntity;
    }

    //发送消息确认
    public ResEntity<Boolean> sendOk(String idempotentKey){
        String result = HttpUtils.sendGet(messageServerHost+Prefix+sendOk+"/"+idempotentKey,null);
        ResEntity<Boolean> resEntity = JSONObject.parseObject(result,new TypeReference<ResEntity<Boolean>>(){});
        return resEntity;
    }

    //完成消息
    public ResEntity finish(String idempotentKey){
        String result = HttpUtils.sendGet(messageServerHost+Prefix+finish+"/"+idempotentKey,null);
        ResEntity resEntity = JSONObject.parseObject(result,ResEntity.class);
        return resEntity;
    }

    //消费失败
    public ResEntity<List<MessageRes>> addFailure(FailureMessageReq req){
        String json = JSONObject.toJSONString(req);
        String result = HttpUtils.sendPostForJSONString(messageServerHost+Prefix+addFailure,json);
        ResEntity resEntity = JSONObject.parseObject(result,ResEntity.class);
        return resEntity;
    }

    public static void main(String[] args){
        /*String result = "{\"code\":\"200\",\"msg\":\"成功\",\"data\":[{\"taskId\":\"682826\",\"approver\":\"190\",\"busiKey\":\"举证\",\"commonts\":[\"举证\"],\"dueDate\":\"2020-05-15 18:32:59\"},{\"taskId\":\"682831\",\"approver\":\"66\",\"busiKey\":\"分案\",\"commonts\":[\"分案\"],\"dueDate\":\"2020-05-15 18:34:27\"},{\"taskId\":\"682840\",\"approver\":\"190\",\"busiKey\":\"待审核\",\"commonts\":[\"审核通过\"],\"dueDate\":\"2020-05-15 18:35:33\"},{\"taskId\":\"682846\",\"approver\":\"190\",\"busiKey\":\"待报案\",\"commonts\":null,\"dueDate\":\"2020-05-15 18:38:32\"},{\"taskId\":\"682852\",\"approver\":\"190\",\"busiKey\":\"待开庭\",\"commonts\":null,\"dueDate\":\"2020-05-15 18:44:17\"},{\"taskId\":\"682858\",\"approver\":\"190\",\"busiKey\":\"撤诉\",\"commonts\":[\"撤诉失败\"],\"dueDate\":\"2020-05-19 14:17:17\"},{\"taskId\":\"683017\",\"approver\":\"190\",\"busiKey\":\"待执行\",\"commonts\":null,\"dueDate\":\"2020-05-19 14:19:32\"}],\"success\":true}";
        ResEntity<List<HistoryTaskResVO>> resEntity = JSONObject.parseObject(result,ResEntity.class);
        String dataStr = JSONObject.parseObject(result).getString("data");
        if(!StringUtils.isEmpty(dataStr)){
            List<HistoryTaskResVO> list = JSONObject.parseArray(dataStr,  HistoryTaskResVO.class);
            resEntity.setData(list);
        }*/
    }

}
