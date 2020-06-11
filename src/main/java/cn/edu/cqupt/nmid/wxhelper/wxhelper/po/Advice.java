package cn.edu.cqupt.nmid.wxhelper.wxhelper.po;

import java.util.Date;

public class Advice {
    private Long id;
    private String userId;
    private String advice;
    private Date publishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", advice='" + advice + '\'' +
                ", publishTime=" + publishTime +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
