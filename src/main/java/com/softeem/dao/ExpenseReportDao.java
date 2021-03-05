package com.softeem.dao;

import com.softeem.dto.ExpenseReportDTO;
import com.softeem.dto.requestDTO.ExpenseReportRequestDTO;
import com.softeem.dto.responseDTO.ExpenseReportListResponseDTO;
import com.softeem.entity.ExpenseReport;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 报销单表(ExpenseReport)表数据库访问层
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:42
 */
public interface ExpenseReportDao {

    /**
     * 通过ID查询单条数据
     *
     * @param expenseId 主键
     * @return 实例对象
     */
    ExpenseReport queryById(Integer expenseId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExpenseReport> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param expenseReport 实例对象
     * @return 对象列表
     */
    List<ExpenseReport> queryAll(ExpenseReport expenseReport);

    /**
     * 新增数据
     *
     * @param expenseReport 实例对象
     * @return 影响行数
     */
    int insert(ExpenseReport expenseReport);

    /**
     * 修改数据
     *
     * @param expenseReport 实例对象
     * @return 影响行数
     */
    int update(ExpenseReport expenseReport);

    /**
     * 通过主键删除数据
     *
     * @param expenseId 主键
     * @return 影响行数
     */
    int deleteById(Integer expenseId);

    List<ExpenseReportListResponseDTO> getexpenseReportList(ExpenseReportRequestDTO expenseReportRequestDTO);

    List<ExpenseReportListResponseDTO> getPersonalexpenseReportList(ExpenseReportRequestDTO expenseReportRequestDTO);

    @Update("update ssm_project.expense_report set next_deal_em = 1 where expense_id = #{expenseId}")
    void ExpenseReportNextDealEmChange(@Param("expenseId") Integer expenseId);
}