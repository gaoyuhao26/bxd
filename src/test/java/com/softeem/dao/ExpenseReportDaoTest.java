package com.softeem.dao;

import com.softeem.dto.ExpenseReportDTO;
import com.softeem.dto.requestDTO.ExpenseReportRequestDTO;
import com.softeem.dto.responseDTO.ExpenseReportListResponseDTO;
import com.softeem.entity.ExpenseReport;
import com.softeem.entity.ExpenseReportDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ExpenseReportDaoTest {

    @Autowired
    ExpenseReportDao expenseReportDao;
    @Autowired
    ExpenseReportDetailDao expenseReportDetailDao;

    @Test
    public void insert() {
        ExpenseReport expenseReport = new ExpenseReport();
        expenseReport.setCause("差旅费");
        expenseReport.setCreateTime(new Date());
        expenseReport.setEmId(1);
        expenseReport.setNextDealEm(2);
        expenseReport.setStatus("未处理");
        expenseReport.setTotalAmount((double) 2000);
        int insert = expenseReportDao.insert(expenseReport);
        System.out.println(insert);
        System.out.println(expenseReport.getExpenseId());

    }


    @Test
    public void insertList(){
        ArrayList<ExpenseReportDetail> arrayList = new ArrayList();
        for(int i = 0 ; i < 20 ;i++){
            ExpenseReportDetail expenseReportDetail = new ExpenseReportDetail();
            expenseReportDetail.setExpensiveId(i);
            expenseReportDetail.setItem("酒店住房消费");
            expenseReportDetail.setAmount(2000D);
            expenseReportDetail.setComment("在北京家和酒店，总消费2500元，打折后2000元");
            arrayList.add(expenseReportDetail);
        }
        boolean b = expenseReportDetailDao.insertList(arrayList);
    }

    @Test
    public void getexpenseReportList() {
        ExpenseReportRequestDTO expenseReportDTO = new ExpenseReportRequestDTO();

        List<ExpenseReportListResponseDTO> expenseReportDTOS = expenseReportDao.getexpenseReportList(expenseReportDTO);
        expenseReportDTOS.forEach(System.out::println);
    }
}