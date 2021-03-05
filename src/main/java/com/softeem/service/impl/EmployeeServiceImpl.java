package com.softeem.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softeem.dao.EmployeeDao;
import com.softeem.dto.EmployeeDTO;
import com.softeem.dto.requestDTO.EmployeeListRequestDTO;
import com.softeem.dto.responseDTO.EmployeeActiveListResponseDTO;
import com.softeem.entity.Employee;
import com.softeem.service.EmployeeService;
import com.softeem.utils.MD5Util;
import com.softeem.utils.RedisTemplateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工表(Employee)表服务实现类
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:40
 */
@Service("employeeService")
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {
    @Resource
    private EmployeeDao employeeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param emId 主键
     * @return 实例对象
     */
    @Override
    public Employee queryById(Integer emId) {
        return this.employeeDao.queryById(emId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Employee> queryAllByLimit(int offset, int limit) {
        return this.employeeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee insert(Employee employee) throws Exception {
        //查重,登录名(手机号)查重
        int count = employeeDao.selectCountByLoginName(employee.getLoginName());
        if (count != 0) {
            throw new Exception("登录名(手机号)已经注册过");
        }
        //设置默认密码123456
        employee.setPassword(MD5Util.getMd5("123456"));
        System.out.println(employee.toString());
        this.employeeDao.insert(employee);
        return employee;
    }

    /**
     * 修改数据
     *
     * @param employee 实例对象
     * @return 实例对象
     */
    @Override
    public Employee update(Employee employee,String oldLoginName) throws Exception {

        if(!oldLoginName.equals(employee.getLoginName())){
            int count = employeeDao.selectCountByLoginName(employee.getLoginName());
            if (count != 0) {
                throw new Exception("登录名(手机号)已经注册过");
            }
        }
        this.employeeDao.update(employee);
        return this.queryById(employee.getEmId());
    }

    /**
     * 通过主键删除数据
     *
     * @param emId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer emId) {
        return this.employeeDao.deleteById(emId) > 0;
    }

    /**
     * 获取用户名和密码
     * @param userName
     * @param password
     * @return
     */
    @Override
    public EmployeeDTO getEmInfoByUsernameAndPassword(String userName, String password) {
        EmployeeDTO employeeDTO = employeeDao.queryEmInfoByUsernameAndPassword(userName, MD5Util.getMd5(password));
        RedisTemplateUtil redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        redisTemplateUtil.set("user",JSON.toJSONString(employeeDTO));
        redisTemplateUtil.set("userName",userName);
        redisTemplateUtil.set("password",password);
        return employeeDTO;
    }

    @Override
    public PageInfo<EmployeeDTO> queryPageList(EmployeeListRequestDTO employeeListRequestDTO){
        PageHelper.startPage(employeeListRequestDTO.getPageNum(),employeeListRequestDTO.getPageSize());
            List<EmployeeDTO> list = employeeDao.queryPageList(employeeListRequestDTO);
            return new PageInfo<EmployeeDTO>(list);
    }

    @Override
    public void toggleStatus(Employee employee) {
        Integer status = employee.getStatus();
        System.out.println(status);
        if(status == 1){
            employeeDao.updateFailureStatusById(employee.getEmId());
        }else {
            employeeDao.updateSuccessStatusById(employee.getEmId());
        }
    }

    @Override
    public EmployeeDTO queryEmployeeById(Integer employeeId) {
        return employeeDao.queryEmployeeById(employeeId);
    }

    @Override
    public boolean fileUpload(EmployeeDTO user) {
        RedisTemplateUtil redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        //当传入用户头像不为null时，才修改
        if(user.getHeadPortrait() != null) {
            if(employeeDao.fileUpload(user)){
                redisTemplateUtil.set("user", JSON.toJSONString(user));
                return true;
            }
            return false;
        }
        return true;
    }


    @Override
    public boolean updatePassword(Employee employee) {
        if(employee.getPassword() != null){
            employee.setPassword(MD5Util.getMd5(employee.getPassword()));
            int update = employeeDao.update(employee);
            if(update != 0){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<EmployeeActiveListResponseDTO> queryEmployeeList() {
        List<EmployeeActiveListResponseDTO> list = employeeDao.queryEmployeeList();
        return list;
    }

    @Override
    public List<EmployeeActiveListResponseDTO> queryFounderList() {
        List<EmployeeActiveListResponseDTO> list = employeeDao.queryFounderList();
        return list;
    }
}