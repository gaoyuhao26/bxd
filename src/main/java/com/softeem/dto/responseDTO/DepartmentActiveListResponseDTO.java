package com.softeem.dto.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "有效的部门列表,下拉框使用")
public class DepartmentActiveListResponseDTO implements Serializable {

    private static final long serialVersionUID = 8563207431292949603L;

    @ApiModelProperty(value = "部门id")
    private Integer depId;

    @ApiModelProperty(value = "部门名称")
    private String name;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
