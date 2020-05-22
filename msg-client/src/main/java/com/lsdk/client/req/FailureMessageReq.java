package com.lsdk.client.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailureMessageReq implements Serializable {

    /**
     * 幂等键
     */
    private String idempotentKey;

    //失败原因
    private String failureMsg;

}
