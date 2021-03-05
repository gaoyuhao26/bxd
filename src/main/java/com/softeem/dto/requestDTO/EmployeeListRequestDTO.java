package com.softeem.dto.requestDTO;

import com.softeem.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
@ApiModel(description = "获取员工列表RequestDTO")
public class EmployeeListRequestDTO extends PageDTO implements Serializable {


    private static final long serialVersionUID = 1752343203128376700L;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名",example = "")
    private String name;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id",example = "1")
    private Integer depId;
    /**
     * 职位id
     */
    @ApiModelProperty(value = "职位id",example = "1")
    private Integer positionId;
    /**
     * 状态(0:离职 1:在职)
     */
    @ApiModelProperty(value = "状态(0:离职 1:在职)",example = "1")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeeListRequestDTO{" +
                "name='" + name + '\'' +
                ", depId=" + depId +
                ", positionId=" + positionId +
                ", status=" + status +
                '}';
    }
}
