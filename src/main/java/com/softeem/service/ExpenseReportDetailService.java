package com.softeem.service;

import com.softeem.entity.ExpenseReportDetail;

import java.util.List;

/**
 * 报销单细节表(ExpenseReportDetail)表服务接口
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:47
 */
public interface ExpenseReportDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param expensiveDetailId 主键
     * @return 实例对象
     */
    ExpenseReportDetail queryById(Integer expensiveDetailId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExpenseReportDetail> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param expenseReportDetail 实例对象
     * @return 实例对象
     */
    ExpenseReportDetail insert(ExpenseReportDetail expenseReportDetail);

    /**
     * 修改数据
     *
     * @param expenseReportDetail 实例对象
     * @return 实例对象
     */
    ExpenseReportDetail update(ExpenseReportDetail expenseReportDetail);

    /**
     * 通过主键删除数据
     *
     * @param expensiveDetailId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer expensiveDetailId);

    List<ExpenseReportDetail> queryListById(Integer expenseId);
}