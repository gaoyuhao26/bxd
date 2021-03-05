package com.softeem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.softeem.dto.EmployeeDTO;
import com.softeem.dto.Result;
import com.softeem.dto.requestDTO.EmployeeListRequestDTO;
import com.softeem.dto.responseDTO.EmployeeActiveListResponseDTO;
import com.softeem.entity.Employee;
import com.softeem.service.EmployeeService;
import com.softeem.utils.RedisTemplateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author 高玉好
 * @ClassName EmployeeController
 * @since 2021/2/22 16:14
 */
@Api(tags = "员工管理模块")
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

    @Resource
    private EmployeeService employeeService;


    /**
     * 跳转到员工列表路由
     *
     * @return
     */
    @ApiOperation(value = "跳转到员工列表路由")
    @GetMapping("/toEmployee")
    public String toEmployee() {
        request.getSession().setAttribute("pageName", "员工列表");
        return "employeeList";
    }

    @ApiOperation(value = "查询员工列表：1分页查询 2.模糊查询")
    @PostMapping("/getList")
    @ResponseBody
    public Result getList(@RequestBody EmployeeListRequestDTO employeeListRequestDTO) {
        try {
            PageInfo<EmployeeDTO> list = employeeService.queryPageList(employeeListRequestDTO);
            return new Result(true, list);
        }catch (Exception e){
            return new Result(false,e.getMessage());
        }
    }

    @ApiOperation(value = "切换状态")
    @PostMapping("/toggleEmployeeStatus")
    @ResponseBody
    public Result togglePositionStatus(@RequestBody Employee employee) {
        try {
            employeeService.toggleStatus(employee);
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }

    @ApiOperation(value = "跳转添加员工路由")
    @GetMapping("/toAddEmployee")
    public String toAddEmployee() {
        request.getSession().setAttribute("pageName", "添加员工");
        return "employeeAdd";
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/insertEmployee")
    @ResponseBody
    public Result insertEmploye(@RequestParam String employeeStr) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Employee employee;
            try {
                employee = mapper.readValue(employeeStr, Employee.class);
                if (null == employee.getLoginName()) {
                    throw new Exception("登录名不能为空");
                }
            } catch (JsonProcessingException je) {
                return new Result(false, je.getMessage());
            }
            employeeService.insert(employee);
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }

    @ApiOperation(value = "跳转查看员工路由")
    @GetMapping("/goEmployee")
    public String goEmployee() {
        request.getSession().setAttribute("pageName", "查看员工");
        return "employeeInfo";
    }

    @PostMapping("/queryEmployeeById")
    @ResponseBody
    public Result<EmployeeDTO> queryEmployeeById(Integer emId) {
        EmployeeDTO employee = employeeService.queryEmployeeById(emId);
        return new Result(true, employee);
    }

    @ApiOperation(value = "跳转修改员工路由")
    @GetMapping("/goEmployeeEdit")
    public String goEmployeeEdit() {
        request.getSession().setAttribute("pageName", "修改员工");
        return "employeeEdit";
    }

    @ApiOperation(value = "修改员工")
    @PostMapping("/editEmployee")
    @ResponseBody
    public Result editEmployee(@RequestParam String employeeStr, @RequestParam String oldLoginName) {
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
    }

    @ApiOperation(value = "获取有效员工列表(下拉列表用)")
    @PostMapping("/getEmployeeInfo")
    @ResponseBody
    public Result getEmployeeInfo(){
        try {
            List<EmployeeActiveListResponseDTO> employees = employeeService.queryEmployeeList();
            return new Result(true,employees);
        }catch (Exception exception){
            return new Result(false,"获取员工下拉列表失败");
        }

    }



    @ApiOperation(value = "获取待处理人下拉列表")
    @PostMapping("/getnextDealEm")
    @ResponseBody
    public Result getnextDealEm(){
        try {
            List<EmployeeActiveListResponseDTO> employees = employeeService.queryFounderList();
            return new Result(true,employees);
        }catch (Exception exception){
            return new Result(false,"获取员工下拉列表失败");
        }

    }
}
