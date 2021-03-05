package com.softeem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softeem.dto.EmployeeDTO;
import com.softeem.dto.Result;
import com.softeem.entity.Employee;
import com.softeem.service.EmployeeService;
import com.softeem.utils.RedisTemplateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 高玉好
 * @ClassName TopbarController
 * @since 2021/2/26 14:54
 */
@Api(tags = "个人管理模块")
@Controller
@RequestMapping("/personal")
public class PersonalController extends BaseController{
    @Resource
    private EmployeeService employeeService;

    @ApiOperation(value = "跳转查看个人信息路由")
    @GetMapping("/gopersonalInformation")
    public String gopersonalInformation() {
        request.getSession().setAttribute("pageName", "个人信息");
        return "personalInformation";
    }

    @ApiOperation(value = "修改头像")
    @PostMapping("/upload")
    @ResponseBody
    public Result fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
/*        EmployeeDTO employeeDTO = null;
        RedisTemplateUtil redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        String user = (String) redisTemplateUtil.get("user");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            employeeDTO = objectMapper.readValue(user, EmployeeDTO.class);
        } catch (JsonProcessingException e) {
            return new Result(false,e.getMessage());
        }
        System.out.println(employeeDTO.toString());*/
        EmployeeDTO user = (EmployeeDTO) request.getSession().getAttribute("user");
        //判断是否有存储上传文件的文件夹
        String realPath = request.getSession().getServletContext().getRealPath("/images/uploads/");
        File filePath = new File(realPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        //获取文件名
        String filename = file.getOriginalFilename();
        //新的文件名
        filename = UUID.randomUUID().toString().replace("-", "") + filename;
        file.transferTo(new File(realPath, filename));
        user.setHeadPortrait(filename);
        boolean isUpload = employeeService.fileUpload(user);
        if (isUpload) {
            return new Result(true);
        }
        return new Result(false, "修改失败");
    }


   /* @ApiOperation(value = "修改个人信息")
    @PostMapping("/editPersonalInformation")
    @ResponseBody
    public Result editPersonalInformation(@RequestParam String employeeStr, @RequestParam String oldLoginName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Employee employee;
            try {
                employee = objectMapper.readValue(employeeStr, Employee.class);
                oldLoginName = objectMapper.readValue(oldLoginName, String.class);
                if (null == employee.getLoginName()) {
                    throw new Exception("登录名不能为空");
                }
            } catch (JsonProcessingException je) {
                return new Result(false, je.getMessage());
            }
            employeeService.update(employee, oldLoginName);
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }*/

    @ApiOperation(value = "跳转修改密码路由")
    @GetMapping("/goUpdatePassword")
    public String goUpdatePassword() {
        request.getSession().setAttribute("pageName", "修改密码");
        return "updatePassword";
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/updatePassword")
    @ResponseBody
    public Result updatePassword(Employee employee) {
        System.out.println(employee);
        boolean b = employeeService.updatePassword(employee);
        if(b){
           return new Result(true);
        }
        return new Result(false,"修改失败");
    }

    @ApiOperation(value = "修改密码")
    @GetMapping("/flushCache")
    public String flushCache() {
        request.getSession().invalidate();
        RedisTemplateUtil redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        redisTemplateUtil.flushAll();
        return "main";
    }
}
