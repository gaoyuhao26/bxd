package com.softeem.dto.responseDTO;

import java.io.Serializable;

/**
 * @author 高玉好
 * @ClassName EmployeeActiveListResponseDTO
 * @since 2021/2/28 3:08
 */
public class EmployeeActiveListResponseDTO implements Serializable {
    private static final long serialVersionUID = 1713777694990234470L;

    /**
     * 员工id
     */
    private Integer emId;
    /**
     * 姓名
     */
    private String name;

    public Integer getEmId() {
        return emId;
    }

    public void setEmId(Integer emId) {
        this.emId = emId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmployeeActiveListResponseDTO{" +
                "emId=" + emId +
                ", name='" + name + '\'' +
                '}';
    }
}
