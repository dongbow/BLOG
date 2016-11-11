package cn.ifxcode.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Message {
    private Integer messageid;

    private Integer send_user_name;

    private Integer receive_user_name;

    private String message;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date viewtime;

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public Integer getSend_user_name() {
        return send_user_name;
    }

    public void setSend_user_name(Integer send_user_name) {
        this.send_user_name = send_user_name;
    }

    public Integer getReceive_user_name() {
        return receive_user_name;
    }

    public void setReceive_user_name(Integer receive_user_name) {
        this.receive_user_name = receive_user_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getViewtime() {
        return viewtime;
    }

    public void setViewtime(Date viewtime) {
        this.viewtime = viewtime;
    }
}