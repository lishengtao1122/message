package com.lsdk.client.req;

import com.lsdk.client.enums.QueryOpEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageListReq implements Serializable {

    /**
     * 幂等键
     */
    private String idempotentKey;

    /**
     * 任务名称
     */
    private String task;

    /**
     * 1：小于
     */
    private QueryOpEnum trySumOp;

    /**
     * 尝试次数
     */
    private Integer trySum;

    /**
     * 状态
     */
    private Integer sts;

    private QueryOpEnum stsOp;


}
