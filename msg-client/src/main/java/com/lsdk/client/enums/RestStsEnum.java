package com.lsdk.client.enums;

//响应码
public enum RestStsEnum {

    SUCCESS("200","SUCCESS") , //成功
    FAILURE("500","FAILURE"), //系统错误
    SEND_RABBIT_MQ_FAILUER("001","发送mq错误"), //发送mq错误

    MESSAGE_IS_NOT_EXIST("002","消息不存在"), //不存在

    ;

    public final String code; //业务码

    public final String msg;  //业务码说明

    RestStsEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
