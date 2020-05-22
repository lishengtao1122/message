package com.lsdk.service.exception;

import com.lsdk.client.enums.RestStsEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * ins 异常：业务异常
 */
@Data
public class InsBusiException extends RuntimeException implements Serializable{

    private String code; //业务码

    private String msg; //说明

    public InsBusiException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public InsBusiException(RestStsEnum restStsEnum){
        super(restStsEnum.msg);
        this.code = restStsEnum.code;
        this.msg = restStsEnum.msg;
    }

}
