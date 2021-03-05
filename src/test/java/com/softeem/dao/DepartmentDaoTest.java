package com.softeem.dao;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softeem.dto.requestDTO.DepartmentListRequestDTO;
import com.softeem.entity.Department;
import com.softeem.entity.Employee;
import com.softeem.utils.RedisTemplateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml",
        "classpath:spring-redis.xml"})
public class DepartmentDaoTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis(){
       /* String test = (String) redisTemplate.opsForValue().get("test");*/
      //  ValueOperations valueOperations = redisTemplate.opsForValue();
        RedisTemplateUtil  redisTemplateUtil =new RedisTemplateUtil(redisTemplate);
        String test = (String) redisTemplateUtil.get("userName");
        System.out.println(test);
        redisTemplateUtil.set("userName","哈哈");
       /* redisTemplateUtil.set("test2","哈哈");
        System.out.println(redisTemplateUtil.get("test2"));
        Employee employee = new Employee();
        employee.setPassword("12345");
        employee.setLoginName("haha");
        redisTemplateUtil.set("employee", JSON.toJSONString(employee));
        String employee1 = (String) redisTemplateUtil.get("employee");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Employee employee2 = objectMapper.readValue(employee1, Employee.class);
            System.out.println(employee2.getPassword());
            System.out.println(employee2.getPassword().equals("12345"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(employee1);*/

    }

    @Test
    public void queryPageList() {
        DepartmentListRequestDTO department = new DepartmentListRequestDTO();
        department.setName("开发部");
        List<Department> departments = departmentDao.queryPageList(department);
         departments.stream().forEach(System.out::println);
    }

    @Test
    public void insert() {
        for(int i = 8 ; i < 18; i++){
            Department department = new Department();
            department.setName("销售部"+i);
            department.setAddress("武汉智慧园16栋1楼");
            department.setStatus(1);
            int insert = departmentDao.insert(department);
            System.out.println(insert);
        }
    }


    @Test
    public void deleteById() {
        for(int i = 0; i < 500 ; i++){
            if(i>9){
                departmentDao.deleteById(i);
            }

        }
    }
}