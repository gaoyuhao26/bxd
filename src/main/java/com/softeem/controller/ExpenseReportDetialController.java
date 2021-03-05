package com.softeem.controller;

import com.softeem.dto.Result;
import com.softeem.entity.ExpenseReport;
import com.softeem.entity.ExpenseReportDetail;
import com.softeem.service.ExpenseReportDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 高玉好
 * @ClassName ExpenseReportDetialController
 * @since 2021/3/1 16:47
 */
@Api(tags = "报销单细节管理模块")
@Controller
@RequestMapping("/expenseReportDetial")
public class ExpenseReportDetialController extends BaseController {
    @Resource
    private ExpenseReportDetailService expenseReportDetailService;

    @ApiOperation(value = "通过expenseId查看个人报销单")
    @PostMapping("/queryPersonalExpenseReportDetial")
    @ResponseBody
    public Result queryPersonalExpenseReportDetial(@RequestParam Integer expenseId) {
        try {
            List<ExpenseReportDetail> list = expenseReportDetailService.queryListById(expenseId);
            return new Result(true,list);
        }catch (Exception e){
            return new Result(false,e.getMessage());
        }
    }

    @ApiOperation(value = "通过expenseId删除个人报销单")
    @RequestMapping(value = "/perdByexpensiveDetailId",method = RequestMethod.GET)
    @ResponseBody
    public Result perdByexpensiveDetailId(@RequestParam Integer expensiveDetailId) {
        try {
            expenseReportDetailService.deleteById(expensiveDetailId);
            return new Result(true);
        }catch (Exception e){
            return new Result(false,e.getMessage());
        }
    }
}
