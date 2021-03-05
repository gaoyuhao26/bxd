package com.softeem.dto.responseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "有效的职位列表,下拉框使用")
public class PositionActiveListResponseDTO implements Serializable {

    private static final long serialVersionUID = -4930161945713702356L;

    @ApiModelProperty(value = "职位id")
    private Integer positionId;

    @ApiModelProperty(value = "职位名称")
    private String positionName;

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
}
