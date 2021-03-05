package com.softeem.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位表(Position)实体类
 *
 * @author 高玉好
 * @since 2021-02-11 23:13:58
 */
@ApiModel(description = "传入职位信息")
public class Position implements Serializable {
    private static final long serialVersionUID = 390541371563497180L;
    /**
     * 职位表id
     */
    @ApiModelProperty(value = "职位表id",example = "")
    private Integer positionId;
    /**
     * 职位名
     */
    @ApiModelProperty(value = "职位名",example = "")
    private String positionName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",example = "")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 状态 1：启动 0：停用
     */
    @ApiModelProperty(value = "状态",example = "1：启动 0：停用")
    private Integer status;


    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}