package com.softeem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.softeem.dto.ExpenseReportDTO;
import com.softeem.dto.Result;
import com.softeem.dto.requestDTO.ExpenseReportRequestDTO;
import com.softeem.dto.responseDTO.ExpenseReportListResponseDTO;
import com.softeem.entity.ExpenseReport;
import com.softeem.service.ExpenseReportDetailService;
import com.softeem.service.ExpenseReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 高玉好
 * @ClassName ExpenseReportController
 * @since 2021/2/26 17:43
 */
@Api(tags = "报销单管理模块")
@Controller
@RequestMapping("/expenseReport")
public class ExpenseReportController extends BaseController {
    @Resource
    private ExpenseReportService expenseReportService;


    @ApiOperation(value = "跳转到填写报销单路由")
    @GetMapping("/toexpenseReportAdd")
    public String toEmployee() {
        request.getSession().setAttribute("pageName", "填写报销单");
        return "expenseReportAdd";
    }
    @ApiOperation(value = "填写报销单")
    @PostMapping("/expenseReportAdd")
    @ResponseBody
    public Result expenseReportAdd(@RequestParam String expenseReportStr) {
        try {
           /* ObjectMapper objectMapper = new ObjectMapper();
           ExpenseReportDTO expenseReportDTO;
            try {
                expenseReportDTO = objectMapper.readValue(expenseReportStr, ExpenseReportDTO.class);
                if(expenseReportDTO == null){
                    return new Result(false, "报销单填写不能为空");
                }
            } catch (JsonProcessingException je) {
                return new Result(false, je.getMessage());
            }*/
            ExpenseReportDTO expenseReportDTO = JSONObject.parseObject(expenseReportStr, ExpenseReportDTO.class);
            expenseReportService.insert(expenseReportDTO);
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }



    @ApiOperation(value = "跳转到查看待处理报销单列表路由")
    @GetMapping("/toExpenseReportListPending")
    public String ExpenseReportListPending() {
        request.getSession().setAttribute("pageName", "待处理报销单列表");
        return "expenseReportListPending";
    }


    @ApiOperation(value = "查看待处理报销单列表")
    @PostMapping("/getExpenseReportList")
    @ResponseBody
    public Result getExpenseReportList(@RequestBody ExpenseReportRequestDTO expenseReportRequestDTO) {
        System.out.println(expenseReportRequestDTO);
        PageInfo<ExpenseReportListResponseDTO> list = null;
        try {
             list = expenseReportService.getexpenseReportList(expenseReportRequestDTO);
        }catch (Exception e){
            return new Result(false,e.getMessage());
        }
        return new Result(true,list);
    }

    @ApiOperation(value = "根据expenseId跳转到审批待处理报销单路由")
    @GetMapping("/goExpenseReportApproval")
    public String goExpenseReportApproval() {
        request.getSession().setAttribute("pageName", "审批待处理报销单");
        return "expenseReportApproval";
    }


    @PostMapping("/ExpenseReportNextDealEmChange")
    @ResponseBody
    public Result ExpenseReportNextDealEmChange(Integer expenseId) {
        try {
            expenseReportService.ExpenseReportNextDealEmChange(expenseId);
        }catch (Exception e){
            return new Result(false,e.getMessage());
        }
        return new Result(true);
    }


    @ApiOperation(value = "切换状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name="expenseId",value="报销单id",required=true,dataType="Integer"),
            @ApiImplicitParam(name="status",value="报销单状态",required=true,dataType="String"),
    })
    @PostMapping("/toggleExpenseReportStatus")
    @ResponseBody
    public Result toggleExpenseReportStatus(@RequestParam Integer expenseId,@RequestParam String status) {
        try {
          expenseReportService.toggleExpenseReportStatus(expenseId,status);
        }catch (Exception e){
            return new Result(false,e.getMessage());
        }
        return new Result(true);
    }






    @ApiOperation(value = "根据expenseId跳转到查看待处理报销单路由")
    @GetMapping("/goExpenseReportInfo")
    public String goExpenseReportInfo() {
        request.getSession().setAttribute("pageName", "查看待处理报销单");
        return "expenseReportInfo";
    }



    @ApiOperation(value = "根据创建人跳转到个人报销单列表路由")
    @GetMapping("/toPersonalExpenseReportList")
    public String toPersonalExpenseReportList() {
        request.getSession().setAttribute("pageName", "个人报销单列表");
        return "personalExpenseReportList";
    }

 /*   @ApiOperation(value = "跳转到修改个人报销单路由")
    @GetMapping("/goPersonalExpenseReportEdit")
    public String goPersonalExpenseReportEdit() {
        request.getSession().setAttribute("pageName", "修改个人报销单");
        return "personalExpenseReportEdit";
    }*/


    @ApiOperation(value = "跳转到查看个人报销单路由")
    @GetMapping("/goPersonalExpenseReportInfo")
    public String goPersonalExpenseInfoReportInfo() {
        request.getSession().setAttribute("pageName", "查看个人报销单");
        return "personalExpenseReportInfo";
    }

    @ApiOperation(value = "通过expenseId查看个人报销单")
    @PostMapping("/queryPersonalExpenseReport")
    @ResponseBody
    public Result queryPersonalExpenseReport(@RequestParam Integer expenseId) {
        try {
            ExpenseReport expenseReport =  expenseReportService.queryById(expenseId);
            return new Result(true,expenseReport);
        }catch (Exception e){
            return new Result(false,e.getMessage());
        }

    }

    /*@ApiOperation(value = "通过expenseId删除个人报销单")
    @PostMapping("/delPersonalExpenseReport")
    @ResponseBody
    public Result delPersonalExpenseReport(@RequestParam Integer expenseId) {
        try {
           expenseReportService.deleteById(expenseId);
        }catch (Exception e){
            return new Result(false,e.getMessage());
        }
        return new Result(true);
    }*/

}
