package com.lsdk.service.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("t_message_log")
public class MessageLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息Id
     */
    private Integer mId;

    /**
     * 任务名称
     */
    private String msg;

    /**
     * 创建时间
     */
    private Date createDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "MessageLog{" +
        "id=" + id +
        ", mId=" + mId +
        ", msg=" + msg +
        ", createDate=" + createDate +
        "}";
    }
}
