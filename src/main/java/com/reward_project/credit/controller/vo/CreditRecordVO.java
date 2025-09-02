package com.reward_project.credit.controller.vo;

import java.util.Date;

public class CreditRecordVO {
    private int id;
    private int userId;
    private String creditType;
    private long amount;
    private String status;
    // outBizNo用来做幂等 下游发奖也需要保证每个阶段只能发一次 上游插入数据记录也需要保证每个阶段只能发一次
    // 上下游约定做幂等的字段为outBizNo
    private String outBizNo;
    // 上游不同的业务场景都可能会调用此服务发credit
    private String theme;
    private Date prizeTime;
    private Date createTime;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOutBizNo() {
        return outBizNo;
    }

    public void setOutBizNo(String outBizNo) {
        this.outBizNo = outBizNo;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getPrizeTime() {
        return prizeTime;
    }

    public void setPrizeTime(Date prizeTime) {
        this.prizeTime = prizeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public CreditRecordVO(int id, int userId, String creditType, long amount, String status, String outBizNo, String theme, Date prizeTime, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.creditType = creditType;
        this.amount = amount;
        this.status = status;
        this.outBizNo = outBizNo;
        this.theme = theme;
        this.prizeTime = prizeTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public CreditRecordVO() {
    }

    @Override
    public String toString() {
        return "CreditRecordVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", creditType='" + creditType + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", outBizNo='" + outBizNo + '\'' +
                ", theme='" + theme + '\'' +
                ", prizeTime=" + prizeTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
