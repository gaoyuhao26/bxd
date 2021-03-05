package com.softeem.dao;

import com.softeem.dto.EmployeeDTO;

import com.softeem.utils.MD5Util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void queryEmInfoByUsernameAndPassword() {
        EmployeeDTO employeeDTO = employeeDao.queryEmInfoByUsernameAndPassword("13100000001", MD5Util.getMd5("123456"));
        System.out.println(employeeDTO);
    }
    @Test
    public void equalsTest(){
        String a = "123456";
            System.out.println(!a.equals("123456"));
    }

    @Test
    public void fileUpload() {
       /* EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmId(1);
        employeeDTO.setHeadPortrait("sishen.jpg");
        boolean b = employeeDao.fileUpload(employeeDTO);*/
        int b =1;
        System.out.println(b);
    }
}