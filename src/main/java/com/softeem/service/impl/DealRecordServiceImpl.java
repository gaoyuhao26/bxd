package com.softeem.service.impl;

import com.softeem.dao.DealRecordDao;
import com.softeem.entity.DealRecord;
import com.softeem.service.DealRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作记录表(DealRecord)表服务实现类
 *
 * @author 高玉好
 * @since 2021-01-22 22:55:14
 */
@Service("dealRecordService")
public class DealRecordServiceImpl implements DealRecordService {
    @Resource
    private DealRecordDao dealRecordDao;

    /**
     * 通过ID查询单条数据
     *
     * @param recordId 主键
     * @return 实例对象
     */
    @Override
    public DealRecord queryById(Integer recordId) {
        return this.dealRecordDao.queryById(recordId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DealRecord> queryAllByLimit(int offset, int limit) {
        return this.dealRecordDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dealRecord 实例对象
     * @return 实例对象
     */
    @Override
    public DealRecord insert(DealRecord dealRecord) {
        this.dealRecordDao.insert(dealRecord);
        return dealRecord;
    }

    /**
     * 修改数据
     *
     * @param dealRecord 实例对象
     * @return 实例对象
     */
    @Override
    public DealRecord update(DealRecord dealRecord) {
        this.dealRecordDao.update(dealRecord);
        return this.queryById(dealRecord.getRecordId());
    }

    /**
     * 通过主键删除数据
     *
     * @param recordId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer recordId) {
        return this.dealRecordDao.deleteById(recordId) > 0;
    }
}