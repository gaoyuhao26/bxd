package com.softeem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softeem.dao.DepartmentDao;
import com.softeem.dto.Result;
import com.softeem.dto.requestDTO.DepartmentListRequestDTO;
import com.softeem.dto.responseDTO.DepartmentActiveListResponseDTO;
import com.softeem.entity.Department;
import com.softeem.service.DepartmentService;
import com.softeem.utils.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门表(Department)表服务实现类
 *
 * @author 高玉好
 * @since 2021-01-25 06:35:26
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;
//    @Autowired
//   private RedisTemplate redisTemplate;
    /**
     * 通过ID查询单条数据
     *
     * @param depId 主键
     * @return 实例对象
     */
    @Override
    public Department queryById(Integer depId) {
        return this.departmentDao.queryById(depId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Department> queryAllByLimit(int offset, int limit) {
        return this.departmentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department insert(Department department) {
        this.departmentDao.insert(department);
        return department;
    }

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department update(Department department) {
        this.departmentDao.update(department);
        return this.queryById(department.getDepId());
    }

    /**
     * 通过主键删除数据
     *
     * @param depId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer depId) {
        return this.departmentDao.deleteById(depId) > 0;
    }

    /**
     * 单个部门状态切换
     *
     * @param department
     */
    @Override
    public void toggleStatus(Department department) {
        Integer status = department.getStatus();
        if(1 == status){
            //状态改为失效
            departmentDao.updateFailureStatusById(department.getDepId());
        }else {
            departmentDao.updateSuccessStatusById(department.getDepId());
        }

    }

    @Override
    public PageInfo<Department> queryPageList(DepartmentListRequestDTO departmentListRequestDTO) {
            PageHelper.startPage(departmentListRequestDTO.getPageNum(),departmentListRequestDTO.getPageSize());
            List<Department> departments = departmentDao.queryPageList(departmentListRequestDTO);
            return new PageInfo<Department>(departments);
    }

    @Override
    public List<DepartmentActiveListResponseDTO> queryDepartmentList() {
        List<DepartmentActiveListResponseDTO> departmentList = departmentDao.queryAllActiveList();
        return departmentList;
    }
}