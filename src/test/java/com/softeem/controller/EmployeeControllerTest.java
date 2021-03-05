package com.softeem.controller;

import com.github.pagehelper.PageInfo;
import com.softeem.dto.EmployeeDTO;
import com.softeem.dto.requestDTO.EmployeeListRequestDTO;
import com.softeem.entity.Employee;
import com.softeem.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class EmployeeControllerTest {

//BeanUtils测试
    @Test
    public void togglePositionStatus() {
        EmployeeListRequestDTO employeeListRequestDTO = new EmployeeListRequestDTO();
        employeeListRequestDTO.setDepId(1);
        employeeListRequestDTO.setName("1111");
        employeeListRequestDTO.setPositionId(1);
        employeeListRequestDTO.setStatus(1);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeListRequestDTO,employee);
        System.out.println(employee.toString());
        EmployeeListRequestDTO employeeListRequestDTO1 = new EmployeeListRequestDTO();
        BeanUtils.copyProperties(employeeListRequestDTO,employeeListRequestDTO1);
        System.out.println(employeeListRequestDTO1.toString());
        EmployeeListRequestDTO employeeListRequestDTO2 = new EmployeeListRequestDTO();employee.setName("hahah");
        BeanUtils.copyProperties(employee,employeeListRequestDTO2);
    }

    @Autowired
    private EmployeeService employeeService;
    @Test
    public void getList() {
        EmployeeListRequestDTO employeeListRequestDTO = new EmployeeListRequestDTO();
        PageInfo<EmployeeDTO> list = null;
        try {
            list = employeeService.queryPageList(employeeListRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list.getList());
    }

    @Test
    public void queryEmployeeById() {
        EmployeeDTO employeeDTO = employeeService.queryEmployeeById(1);
        System.out.println(employeeDTO);

    }
}