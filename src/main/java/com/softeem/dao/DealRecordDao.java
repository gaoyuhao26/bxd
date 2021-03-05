package com.softeem.dao;

import com.softeem.entity.DealRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作记录表(DealRecord)表数据库访问层
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:10
 */
public interface DealRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param recordId 主键
     * @return 实例对象
     */
    DealRecord queryById(Integer recordId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DealRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dealRecord 实例对象
     * @return 对象列表
     */
    List<DealRecord> queryAll(DealRecord dealRecord);

    /**
     * 新增数据
     *
     * @param dealRecord 实例对象
     * @return 影响行数
     */
    int insert(DealRecord dealRecord);

    /**
     * 修改数据
     *
     * @param dealRecord 实例对象
     * @return 影响行数
     */
    int update(DealRecord dealRecord);

    /**
     * 通过主键删除数据
     *
     * @param recordId 主键
     * @return 影响行数
     */
    int deleteById(Integer recordId);

}