package com.softeem.dto;

import com.softeem.entity.Department;
import com.softeem.entity.Position;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
@ApiModel(description = "用户列表DTO")
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = 7028515937091490879L;
    /**
     * 员工id
     */
    @ApiModelProperty(value = "员工id",example = "")
    private Integer emId;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",example = "")
    private String password;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名",example = "")
    private String name;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",example = "")
    private String loginName;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id",example = "")
    private Integer depId;
    /**
     * 职位id
     */
    @ApiModelProperty(value = "职位id",example = "")
    private Integer positionId;
    /**
     * 状态(0:离职 1:在职)
     */
    @ApiModelProperty(value = "状态(0:离职 1:在职)",example = "")
    private Integer status;

    /**
     * 关联的部门信息
     */
    @ApiModelProperty(value = "关联的部门信息",example = "")
    private Department department;

    /**
     * 关联的职位信息
     */
    @ApiModelProperty(value = "关联的职位信息",example = "")
    private Position position;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径",example = "")
    private String headPortrait;

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "emId=" + emId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", depId=" + depId +
                ", positionId=" + positionId +
                ", status=" + status +
                ", department=" + department +
                ", position=" + position +
                ", headPortrait='" + headPortrait + '\'' +
                '}';
    }
}
