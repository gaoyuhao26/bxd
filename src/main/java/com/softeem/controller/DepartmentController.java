package com.softeem.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softeem.dto.Result;
import com.softeem.dto.requestDTO.DepartmentListRequestDTO;
import com.softeem.dto.responseDTO.DepartmentActiveListResponseDTO;
import com.softeem.entity.Department;
import com.softeem.service.DepartmentService;
import com.softeem.utils.HttpServletRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 高玉好
 * @ClassName DepartmentController
 * @since 2021/1/29 14:20
 */
@Api(tags = "部门管理模块")
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController{
    @Resource
    private DepartmentService departmentService;


    /**
     * 跳转到部门列表的路由
     *
     * @return
     */
    @ApiOperation(value = "跳转到部门列表的路由")
    @GetMapping("/toList")
    public String toList() {
        request.getSession().setAttribute("pageName", "部门列表");
        return "departmentList";
    }


    /**
     * 查询部门列表：1分页查询 2.模糊查询
     *
     * @param
     * @return
     */
    @ApiOperation(value = "查询部门列表：1分页查询 2.模糊查询")
    @PostMapping("/getList")
    @ResponseBody
    public Result getList(@RequestBody DepartmentListRequestDTO departmentListRequestDTO) {
        Result<Object> result = new Result<>();
        try {
            result.setFlag(true);
            result.setData(departmentService.queryPageList(departmentListRequestDTO));
        } catch (Exception e) {
            result.setFlag(false);
            result.setMsg(e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 跳转到新增部门的路由
     *
     * @return
     */
    @ApiOperation(value = "跳转新增部门路由")
    @GetMapping("/toAddDepartment")
    public String toAddDepartment(){
        request.getSession().setAttribute("pageName", "新增部门");
        return "departmentAdd";
    }

    /**
     * 新增部门信息
     * @param
     * @return
     */
    @ApiOperation(value = "新增部门")
    @PostMapping("/insertDepartment")
    @ResponseBody
    public Map<String, Object> insertDepartment(@RequestParam String departmentStr) {
        Map<String, Object> map = new HashMap<>();
        //调用service
        try {
            ObjectMapper mapper = new ObjectMapper();
            Department department;
            try {
                department = mapper.readValue(departmentStr, Department.class);
            } catch (JsonProcessingException je) {
                map.put("success", false);
                map.put("errMsg", je.getMessage());
                return map;
            }
            departmentService.insert(department);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        return map;
    }


    /**
     * 单个部门状态切换
     *
     * @param department
     * @return
     */
    @ApiOperation(value = "单个部门状态切换")
    @PostMapping("/toggleDepartmentStatus")
    @ResponseBody
    public Result toggleDepartmentStatus(@RequestBody Department department) {
        Result<Object> result = new Result<>();
        try {
            departmentService.toggleStatus(department);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMsg(e.getMessage());
            return result;
        }
        result.setFlag(true);
        return result;
    }

    @ApiOperation(value = "跳转到部门修改页面")
    @GetMapping("/goDepartmentEdit")
    public String goDepartmentEdit(){
        request.getSession().setAttribute("pageName", "修改部门");
        return "departmentEdit";
    }

    @ApiOperation(value = "修改部门")
    @PostMapping(value = "/editDepartment",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> editDepartment(@RequestParam String departmentStr) {
        Map<String, Object> map = new HashMap<>();
        //调用service
        try {
            ObjectMapper mapper = new ObjectMapper();
            Department department;
            try {
                department = mapper.readValue(departmentStr, Department.class);
            } catch (JsonProcessingException je) {
                map.put("success", false);
                map.put("errMsg", je.getMessage());
                return map;
            }
            departmentService.update(department);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        return map;
    }

    @ApiOperation(value = "跳转到指定部门详情页面")
    @GetMapping("/goDepartment")
    public String goDepartment(){
        request.getSession().setAttribute("pageName", "部门详情");
        return "departmentInfo";
    }

    /**
     * 查询单个部门信息
     *
     * @param departmentId
     * @return
     */
    @ApiOperation(value = "查询单个部门信息")
    @ApiImplicitParam(name = "departmentId",value ="部门ID",dataType = "Integer")
    @PostMapping(value = "/queryDepartmentById",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> queryDepartmentById(@RequestParam Integer departmentId) {
        Map<String, Object> map = new HashMap<>();
        Department department = null;
        try {
            department = departmentService.queryById(departmentId);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        map.put("data", department);
        return map;
    }

    @ApiOperation(value = "获取有效部门列表(下拉列表用)")
    @PostMapping("/getDepartmentInfo")
    @ResponseBody
    public Result getDepartmentInfo(){
        List<DepartmentActiveListResponseDTO> list = departmentService.queryDepartmentList();
        return new Result(true,list);
    }

}
