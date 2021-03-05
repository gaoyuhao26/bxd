package com.softeem.service.impl;

import com.softeem.dao.ExpenseReportDetailDao;
import com.softeem.entity.ExpenseReportDetail;
import com.softeem.service.ExpenseReportDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 报销单细节表(ExpenseReportDetail)表服务实现类
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:47
 */
@Service("expenseReportDetailService")
public class ExpenseReportDetailServiceImpl implements ExpenseReportDetailService {
    @Resource
    private ExpenseReportDetailDao expenseReportDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param expensiveDetailId 主键
     * @return 实例对象
     */
    @Override
    public ExpenseReportDetail queryById(Integer expensiveDetailId) {
        return this.expenseReportDetailDao.queryById(expensiveDetailId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ExpenseReportDetail> queryAllByLimit(int offset, int limit) {
        return this.expenseReportDetailDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param expenseReportDetail 实例对象
     * @return 实例对象
     */
    @Override
    public ExpenseReportDetail insert(ExpenseReportDetail expenseReportDetail) {
        this.expenseReportDetailDao.insert(expenseReportDetail);
        return expenseReportDetail;
    }

    /**
     * 修改数据
     *
     * @param expenseReportDetail 实例对象
     * @return 实例对象
     */
    @Override
    public ExpenseReportDetail update(ExpenseReportDetail expenseReportDetail) {
        this.expenseReportDetailDao.update(expenseReportDetail);
        return this.queryById(expenseReportDetail.getExpensiveDetailId());
    }

    /**
     * 通过主键删除数据
     *
     * @param expensiveDetailId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer expensiveDetailId) {
        return this.expenseReportDetailDao.deleteById(expensiveDetailId) > 0;
    }

    @Override
    public List<ExpenseReportDetail> queryListById(Integer expenseId) {
        return expenseReportDetailDao.queryListById(expenseId);
    }
}