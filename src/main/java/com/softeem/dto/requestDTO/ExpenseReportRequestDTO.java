package com.softeem.dto.requestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softeem.dto.PageDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 高玉好
 * @ClassName ExpenseReportRequestDTO
 * @since 2021/2/28 11:05
 */
public class ExpenseReportRequestDTO extends PageDTO implements Serializable {

    private static final long serialVersionUID = -1006047579039326710L;
    /**
     * 报销类型
     */
    private String cause;
    /**
     * 创建人
     */
    private String emName;
    /**
     * begin创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTimeDown;
    /**
     * end创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTimeUp;
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

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public Date getCreateTimeDown() {
        return createTimeDown;
    }

    public void setCreateTimeDown(Date createTimeDown) {
        this.createTimeDown = createTimeDown;
    }

    public Date getCreateTimeUp() {
        return createTimeUp;
    }

    public void setCreateTimeUp(Date createTimeUp) {
        this.createTimeUp = createTimeUp;
    }

    public String getNextDealEmName() {
        return nextDealEmName;
    }

    public void setNextDealEmName(String nextDealEmName) {
        this.nextDealEmName = nextDealEmName;
    }

    @Override
    public String toString() {
        return "ExpenseReportRequestDTO{" +
                "cause='" + cause + '\'' +
                ", emName='" + emName + '\'' +
                ", createTimeDown=" + createTimeDown +
                ", createTimeUp=" + createTimeUp +
                ", nextDealEmName='" + nextDealEmName + '\'' +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
