package com.softeem.dto.requestDTO;

import com.softeem.dto.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author 高玉好
 * @ClassName DepartmentListRequestDTO
 * @since 2021/2/3 22:20
 */
@SuppressWarnings("AlibabaPojoMustOverrideToString")
@ApiModel(description = "获取部门列表RequestDTO")
public class DepartmentListRequestDTO extends PageDTO implements Serializable {
    private static final long serialVersionUID = 2131699323094381064L;
    @ApiModelProperty(value = "部门名称",example = "")
    private String name;
    /**
     * 办公地点
     */
    @ApiModelProperty(value = "办公地点",example = "")
    private String address;
    /**
     * 状态1:启动0:停用
     */
    @ApiModelProperty(value = "状态1:启动0:停用",example = "")
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
