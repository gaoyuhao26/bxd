package com.softeem.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 报销单表(ExpenseReport)实体类
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:42
 */
public class ExpenseReport implements Serializable {
    private static final long serialVersionUID = -54025919444258115L;
    /**
    * 报销单id
    */
    private Integer expenseId;
    /**
    * 报销类型
    */
    private String cause;
    /**
    * 创建人
    */
    private Integer emId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 待处理人
    */
    private Integer nextDealEm;
    /**
    * 报销总金额
    */
    private Double totalAmount;
    /**
    * 状态
    */
    private String status;



    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getNextDealEm() {
        return nextDealEm;
    }

    public void setNextDealEm(Integer nextDealEm) {
        this.nextDealEm = nextDealEm;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExpenseReport{" +
                "expenseId=" + expenseId +
                ", cause='" + cause + '\'' +
                ", emId=" + emId +
                ", createTime=" + createTime +
                ", nextDealEm=" + nextDealEm +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}