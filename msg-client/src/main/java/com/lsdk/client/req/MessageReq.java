package com.lsdk.client.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageReq implements Serializable {

    /**
     * 幂等键
     */
    private String idempotentKey;

    /**
     * 任务名称
     */
    private String task;

    /**
     * 参数
     */
    private String param;

    /**
     * 尝试次数
     */
    private Integer trySum;

    /**
     * 状态
     */
    private Integer sts;

    /**
     * 备注
     */
    private String remark;

}
