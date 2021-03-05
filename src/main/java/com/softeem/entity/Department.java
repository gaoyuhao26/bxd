package com.softeem.entity;

import java.io.Serializable;

/**
 * 部门表(Department)实体类
 *
 * @author 高玉好
 * @since 2021-01-25 06:35:23
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 828719744514539454L;
    /**
     * 部门id
     */
    private Integer depId;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 办公地点
     */
    private String address;
    /**
     * 状态 1：启动 0：停用
     */
    private Integer status;

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

    @Override
    public String toString() {
        return "Department{" +
                "depId=" + depId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}