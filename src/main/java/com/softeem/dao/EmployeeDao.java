package com.softeem.dao;

import com.softeem.dto.EmployeeDTO;
import com.softeem.dto.requestDTO.EmployeeListRequestDTO;
import com.softeem.dto.responseDTO.EmployeeActiveListResponseDTO;
import com.softeem.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 员工表(Employee)表数据库访问层
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:40
 */
public interface EmployeeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param emId 主键
     * @return 实例对象
     */
    Employee queryById(Integer emId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Employee> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param employee 实例对象
     * @return 对象列表
     */
    List<Employee> queryAll(Employee employee);

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int insert(Employee employee);

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 影响行数
     */
    int update(Employee employee);

    /**
     * 通过主键删除数据
     *
     * @param emId 主键
     * @return 影响行数
     */
    int deleteById(Integer emId);

    /**
     * 获取用户名和密码
     *
     * @param userName
     * @param password
     * @return
     */
    EmployeeDTO queryEmInfoByUsernameAndPassword(@Param("userName") String userName, @Param("password") String password);

    List<EmployeeDTO> queryPageList(EmployeeListRequestDTO employeeListRequestDTO);

    void updateFailureStatusById(Integer depId);

    void updateSuccessStatusById(Integer depId);

    int selectCountByLoginName(@Param("loginName") String loginName);

    EmployeeDTO queryEmployeeById(@Param("employeeId") Integer employeeId);

    @Select("SELECT login_name from employee WHERE login_name = #{loginName}")
    List<String> selectLoginNameByLoginName(@Param("loginName") String loginName);

      @Update("update employee set  head_portrait = #{headPortrait} where employee.em_id = #{emId}")
   /* @Update({"<script> " +
            "update employee" +
            "<set>" +
            "<if test='head_portrait != null and head_portrait != '''> head_portrait = #{headPortrait}</if>" +
            "</set>" +
            "where expense_id = #{expenseId}" +
            "</script>"})*/
    boolean fileUpload(EmployeeDTO user);

    @Select("SELECT em_id, name FROM employee where status = 1")
    List<EmployeeActiveListResponseDTO> queryEmployeeList();
    @Select("SELECT employee.em_id, employee.`name` FROM employee LEFT JOIN position on position.position_id =  employee.em_id " +
            "where  position_name LIKE CONCAT('%','经理','%') and employee.`status` = 1 AND position.`status` = 1")
    List<EmployeeActiveListResponseDTO> queryFounderList();
}