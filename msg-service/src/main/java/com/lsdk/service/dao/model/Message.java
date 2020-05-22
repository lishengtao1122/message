package com.lsdk.service.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lesent
 * @since 2020-05-19
 */
@TableName("t_message")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 幂等键
     */
    @TableField("Idempotent_key")
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

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 完成时间
     */
    private Date finishDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdempotentKey() {
        return idempotentKey;
    }

    public void setIdempotentKey(String idempotentKey) {
        this.idempotentKey = idempotentKey;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getTrySum() {
        return trySum;
    }

    public void setTrySum(Integer trySum) {
        this.trySum = trySum;
    }

    public Integer getSts() {
        return sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "Message{" +
        "id=" + id +
        ", idempotentKey=" + idempotentKey +
        ", task=" + task +
        ", param=" + param +
        ", trySum=" + trySum +
        ", sts=" + sts +
        ", remark=" + remark +
        ", createDate=" + createDate +
        ", finishDate=" + finishDate +
        "}";
    }
}
