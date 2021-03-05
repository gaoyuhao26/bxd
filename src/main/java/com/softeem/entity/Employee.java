package com.softeem.entity;

import java.io.Serializable;

/**
 * 员工表(Employee)实体类
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:40
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 424913128042308128L;
    /**
    * 员工id
    */
    private Integer emId;
    /**
    * 密码
    */
    private String password;
    /**
    * 姓名
    */
    private String name;
    /**
    * 用户名
    */
    private String loginName;
    /**
    * 部门id
    */
    private Integer depId;
    /**
    * 职位id
    */
    private Integer positionId;
    /**
    * 状态(0:离职 1:在职)
    */
    private Integer status;
    /**
     * 图片路径
     */
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

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emId=" + emId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", depId=" + depId +
                ", positionId=" + positionId +
                ", status=" + status +
                ", headPortrait='" + headPortrait + '\'' +
                '}';
    }
}