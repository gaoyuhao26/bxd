package com.softeem.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softeem.dao.EmployeeDao;
import com.softeem.dto.EmployeeDTO;
import com.softeem.dto.requestDTO.EmployeeListRequestDTO;
import com.softeem.dto.responseDTO.EmployeeActiveListResponseDTO;
import com.softeem.entity.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class EmployeeServiceImplTest {
    @Resource
    private EmployeeDao employeeDao;
    @Test
    public void queryPageList() throws Exception {
        System.out.println("/____");
        EmployeeListRequestDTO employeeListRequestDTO = new EmployeeListRequestDTO();
        employeeListRequestDTO.setDepId(2);
        employeeListRequestDTO.setPositionId(1);
        PageHelper.startPage(employeeListRequestDTO.getPageNum(),employeeListRequestDTO.getPageSize());
        try{
            List<EmployeeDTO> list = employeeDao.queryPageList(employeeListRequestDTO);
           System.out.println(new PageInfo<EmployeeDTO>(list).toString());
        }catch (Exception e){
            throw new Exception("没找到");
        }


    }

     @Test
     public void a(){
         try {
             queryPageList();
         } catch (Exception e) {
             e.getMessage();
         }
     }
    @Test
    public void update() {
       /* Employee employee = new Employee();
        employee.setLoginName("13100000001");
        List<String> loginNames = employeeDao.selectLoginNameByLoginName(employee.getLoginName());
        long count = loginNames.stream().filter(loginName -> loginName.equals(employee.getLoginName())).count();
        System.out.println(count==1);*/


    }


    @Test
    public void fileUpload() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmId(1);
        System.out.println(employeeDao.queryById(employeeDTO.getEmId()).getHeadPortrait());
    }


    @Test
    public void queryFounderList() {
        List<EmployeeActiveListResponseDTO> list = employeeDao.queryFounderList();
        list.forEach(System.out::println);
    }
}