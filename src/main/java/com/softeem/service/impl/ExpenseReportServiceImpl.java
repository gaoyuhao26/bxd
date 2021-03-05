package com.softeem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softeem.dao.ExpenseReportDao;
import com.softeem.dao.ExpenseReportDetailDao;
import com.softeem.dto.ExpenseReportDTO;
import com.softeem.dto.requestDTO.ExpenseReportRequestDTO;
import com.softeem.dto.responseDTO.ExpenseReportListResponseDTO;
import com.softeem.entity.ExpenseReport;
import com.softeem.entity.ExpenseReportDetail;
import com.softeem.service.ExpenseReportService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 报销单表(ExpenseReport)表服务实现类
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:42
 */
@Service("expenseReportService")
public class ExpenseReportServiceImpl implements ExpenseReportService {
    @Resource
    private ExpenseReportDao expenseReportDao;
    @Resource
    private ExpenseReportDetailDao expenseReportDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param expenseId 主键
     * @return 实例对象
     */
    @Override
    public ExpenseReport queryById(Integer expenseId) {
        return this.expenseReportDao.queryById(expenseId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ExpenseReport> queryAllByLimit(int offset, int limit) {
        return this.expenseReportDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param expenseReportDTO 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(ExpenseReportDTO expenseReportDTO) throws Exception {
        //获取增加的报销单细节信息
        List<ExpenseReportDetail> expenseReportDetailList = expenseReportDTO.getExpenseReportDetailList();
        if(expenseReportDetailList.isEmpty()){
            throw new Exception("报销单细节不能为空");
        }
        ExpenseReport expenseReport = new ExpenseReport();
        BeanUtils.copyProperties(expenseReportDTO,expenseReport);
        expenseReport.setCreateTime(new Date());
        expenseReport.setStatus("未处理");
        //先新增报销单
        int insert = this.expenseReportDao.insert(expenseReport);
        //如果成功再增添报销单细节信息
        if(insert != 0){
            //替换报销单细节的expensiveId
            for(ExpenseReportDetail item :expenseReportDetailList){
                item.setExpensiveId(expenseReport.getExpenseId());
            }
            boolean isInsertList  = expenseReportDetailDao.insertList(expenseReportDetailList);
            if(isInsertList){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 修改数据
     *
     * @param expenseReport 实例对象
     * @return 实例对象
     */
    @Override
    public ExpenseReport update(ExpenseReport expenseReport) {
        this.expenseReportDao.update(expenseReport);
        return this.queryById(expenseReport.getExpenseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param expenseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer expenseId) {
        return this.expenseReportDao.deleteById(expenseId) > 0 && this.expenseReportDetailDao.deleteByExpensiveId(expenseId);
    }

    @Override
    public PageInfo<ExpenseReportListResponseDTO> getexpenseReportList(ExpenseReportRequestDTO expenseReportRequestDTO) throws Exception {
        PageHelper.startPage(expenseReportRequestDTO.getPageNum(),expenseReportRequestDTO.getPageSize());
        List<ExpenseReportListResponseDTO> list = null;
        if(expenseReportRequestDTO.getEmName() == null && expenseReportRequestDTO.getNextDealEmName() !=null) {
            list = expenseReportDao.getexpenseReportList(expenseReportRequestDTO);
        }else {
           list = expenseReportDao.getPersonalexpenseReportList(expenseReportRequestDTO);
        }
        if(list == null){
            throw new Exception("查询不到相关报销单");
        }
        return new PageInfo<ExpenseReportListResponseDTO>(list);
    }

    @Override
    public void queryPersonalExpenseReport(Integer expenseId) {
        ExpenseReport expenseReport = expenseReportDao.queryById(expenseId);

    }

    @Override
    public void ExpenseReportNextDealEmChange(Integer expenseId) {
        expenseReportDao.ExpenseReportNextDealEmChange(expenseId);
    }

    @Override
    public void toggleExpenseReportStatus(Integer expenseId, String status) throws Exception {
        ExpenseReport expenseReport = new ExpenseReport();
        expenseReport.setExpenseId(expenseId);
        expenseReport.setStatus(status);
        int update = expenseReportDao.update(expenseReport);
        if(update != 1){
            throw new Exception("审批操作失败~");
        }
    }

}