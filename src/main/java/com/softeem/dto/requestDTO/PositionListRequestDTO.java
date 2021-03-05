package com.softeem.dto.requestDTO;

import com.softeem.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 高玉好
 * @ClassName PositionListRequestDTO
 * @since 2021/2/11 23:19
 */

@SuppressWarnings("AlibabaPojoMustOverrideToString")
@ApiModel(description = "获取职位列表RequestDTO")
public class PositionListRequestDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 6446483332213019949L;
    /**
     * 职位名
     */
    @ApiModelProperty(value = "职位名称", example = "")
    private String positionName;

    /**
     * 状态 1：启动 0：停用
     */
    @ApiModelProperty(value = "状态1:启动0:停用", example = "")
    private Integer status;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
