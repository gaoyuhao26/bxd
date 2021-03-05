package com.softeem.service;

import com.github.pagehelper.PageInfo;
import com.softeem.dto.ExpenseReportDTO;
import com.softeem.dto.requestDTO.ExpenseReportRequestDTO;
import com.softeem.dto.responseDTO.ExpenseReportListResponseDTO;
import com.softeem.entity.ExpenseReport;

import java.util.List;

/**
 * 报销单表(ExpenseReport)表服务接口
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:42
 */
public interface ExpenseReportService {

    /**
     * 通过ID查询单条数据
     *
     * @param expenseId 主键
     * @return 实例对象
     */
    ExpenseReport queryById(Integer expenseId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExpenseReport> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param expenseReportDTO 实例对象
     * @return 实例对象
     */
    boolean insert(ExpenseReportDTO expenseReportDTO) throws Exception;

    /**
     * 修改数据
     *
     * @param expenseReport 实例对象
     * @return 实例对象
     */
    ExpenseReport update(ExpenseReport expenseReport);

    /**
     * 通过主键删除数据
     *
     * @param expenseId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer expenseId);


    PageInfo<ExpenseReportListResponseDTO> getexpenseReportList(ExpenseReportRequestDTO expenseReportRequestDTO) throws Exception;

    void queryPersonalExpenseReport(Integer expenseId);

    void ExpenseReportNextDealEmChange(Integer expenseId);

    void toggleExpenseReportStatus(Integer expenseId, String status) throws Exception;
}