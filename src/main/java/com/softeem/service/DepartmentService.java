package com.softeem.service;

import com.github.pagehelper.PageInfo;
import com.softeem.dto.Result;
import com.softeem.dto.requestDTO.DepartmentListRequestDTO;
import com.softeem.dto.responseDTO.DepartmentActiveListResponseDTO;
import com.softeem.entity.Department;

import java.util.List;

/**
 * 部门表(Department)表服务接口
 *
 * @author 高玉好
 * @since 2021-01-25 06:35:25
 */
public interface DepartmentService {

    /**
     * 通过ID查询单条数据
     *
     * @param depId 主键
     * @return 实例对象
     */
    Department queryById(Integer depId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Department> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    Department insert(Department department);

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    Department update(Department department);

    /**
     * 通过主键删除数据
     *
     * @param depId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer depId);


    /**
     * 单个部门状态切换
     *
     * @param department
     */
    void toggleStatus(Department department);

    PageInfo<Department> queryPageList(DepartmentListRequestDTO departmentListRequestDTO);

    List<DepartmentActiveListResponseDTO> queryDepartmentList();
}