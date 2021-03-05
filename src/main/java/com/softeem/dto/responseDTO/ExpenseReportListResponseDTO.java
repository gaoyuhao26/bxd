package com.softeem.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softeem.dto.PageDTO;
import com.softeem.entity.ExpenseReportDetail;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 高玉好
 * @ClassName ExpenseReportListResponseDTO
 * @since 2021/3/1 14:07
 */
public class ExpenseReportListResponseDTO implements Serializable {

    private static final long serialVersionUID = 1499458442076297402L;
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
    private String emName;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**
     * 待处理人
     */
    private String nextDealEmName;
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

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNextDealEmName() {
        return nextDealEmName;
    }

    public void setNextDealEmName(String nextDealEmName) {
        this.nextDealEmName = nextDealEmName;
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
        return "ExpenseReportListResponseDTO{" +
                "expenseId=" + expenseId +
                ", cause='" + cause + '\'' +
                ", emName='" + emName + '\'' +
                ", createTime=" + createTime +
                ", nextDealEmName='" + nextDealEmName + '\'' +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", expenseReportDetailList=" + expenseReportDetailList +
                '}';
    }
}
