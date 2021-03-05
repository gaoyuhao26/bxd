package com.softeem.service;

import com.github.pagehelper.PageInfo;
import com.softeem.dto.EmployeeDTO;
import com.softeem.dto.requestDTO.EmployeeListRequestDTO;
import com.softeem.dto.responseDTO.EmployeeActiveListResponseDTO;
import com.softeem.entity.Employee;

import java.util.List;

/**
 * 员工表(Employee)表服务接口
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:40
 */
public interface EmployeeService {

    /**
     * 通过ID查询单条数据
     *
     * @param emId 主键
     * @return 实例对象
     */
    Employee queryById(Integer emId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Employee> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee insert(Employee employee) throws Exception;

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    Employee update(Employee employee,String oldLoginName) throws Exception;

    /**
     * 通过主键删除数据
     *
     * @param emId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer emId);

    /**
     * 获取用户名和密码
     * @param userName
     * @param password
     * @return
     */
    EmployeeDTO getEmInfoByUsernameAndPassword(String userName, String password);

    PageInfo<EmployeeDTO> queryPageList(EmployeeListRequestDTO employeeListRequestDTO) throws Exception;

    void toggleStatus(Employee employee);

    EmployeeDTO queryEmployeeById(Integer employeeId);

    boolean fileUpload(EmployeeDTO user);



    boolean updatePassword(Employee password);

    List<EmployeeActiveListResponseDTO> queryEmployeeList();

    List<EmployeeActiveListResponseDTO> queryFounderList();
}