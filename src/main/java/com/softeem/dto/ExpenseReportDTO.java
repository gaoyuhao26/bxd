package com.softeem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softeem.entity.ExpenseReportDetail;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 高玉好
 * @ClassName ExpenseReportDTO
 * @since 2021/2/26 18:39
 */
@ApiModel(description = "报销单DTO")
public class ExpenseReportDTO implements Serializable {

    private static final long serialVersionUID = 5036275342430036309L;
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

    /**
     * 报销单细节List
     */
    private List<ExpenseReportDetail> expenseReportDetailList;

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

    public List<ExpenseReportDetail> getExpenseReportDetailList() {
        return expenseReportDetailList;
    }

    public void setExpenseReportDetailList(List<ExpenseReportDetail> expenseReportDetailList) {
        this.expenseReportDetailList = expenseReportDetailList;
    }

    @Override
    public String toString() {
        return "ExpenseReportDTO{" +
                "expenseId=" + expenseId +
                ", cause='" + cause + '\'' +
                ", emId=" + emId +
                ", createTime=" + createTime +
                ", nextDealEm=" + nextDealEm +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", expenseReportDetailList=" + expenseReportDetailList +
                '}';
    }
}
