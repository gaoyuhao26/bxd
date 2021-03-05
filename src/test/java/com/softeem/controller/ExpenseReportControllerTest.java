package com.softeem.controller;

import com.github.pagehelper.PageInfo;
import com.softeem.dto.requestDTO.ExpenseReportRequestDTO;
import com.softeem.dto.responseDTO.ExpenseReportListResponseDTO;
import com.softeem.service.ExpenseReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-mvc.xml"})
public class ExpenseReportControllerTest {
    private ExpenseReportService expenseReportService;
    @Test
    public void getExpenseReportList() {
        ExpenseReportRequestDTO expenseReportRequestDTO = new ExpenseReportRequestDTO();
        PageInfo<ExpenseReportListResponseDTO> list = null;
        try {
          list = expenseReportService.getexpenseReportList(expenseReportRequestDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list.toString());
        System.out.println("-----------------------------------------");
    }
}