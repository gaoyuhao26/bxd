package com.softeem.service;

import com.softeem.entity.DealRecord;

import java.util.List;

/**
 * 操作记录表(DealRecord)表服务接口
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:12
 */
public interface DealRecordService {

    /**
     * 通过ID查询单条数据
     *
     * @param recordId 主键
     * @return 实例对象
     */
    DealRecord queryById(Integer recordId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DealRecord> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dealRecord 实例对象
     * @return 实例对象
     */
    DealRecord insert(DealRecord dealRecord);

    /**
     * 修改数据
     *
     * @param dealRecord 实例对象
     * @return 实例对象
     */
    DealRecord update(DealRecord dealRecord);

    /**
     * 通过主键删除数据
     *
     * @param recordId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer recordId);

}