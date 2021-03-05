package com.softeem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.softeem.dao.PositionDao;
import com.softeem.dto.requestDTO.PositionListRequestDTO;
import com.softeem.dto.responseDTO.PositionActiveListResponseDTO;
import com.softeem.entity.Position;
import com.softeem.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * 职位表(Position)表服务实现类
 *
 * @author 高玉好
 * @since 2021-02-11 23:14:01
 */
@Service("positionService")
public class PositionServiceImpl implements PositionService {
    @Resource
    private PositionDao positionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param positionId 主键
     * @return 实例对象
     */
    @Override
    public Position queryById(Integer positionId) {
        return this.positionDao.queryById(positionId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Position> queryAllByLimit(int offset, int limit) {
        return this.positionDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    @Override
    public Position insert(Position position) throws Exception{
        //判断是否输入的职位与数据库中的相同
        if(positionDao.queryByPositionName(position.getPositionName()) < 1){
            //添加创建时间
            position.setCreateTime(new Date());
            this.positionDao.insert(position);
        }else {
            throw new Exception();
        }
        return position;
    }

    /**
     * 修改数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    @Override
    public Position update(Position position) {
        this.positionDao.update(position);
        return this.queryById(position.getPositionId());
    }

    /**
     * 通过主键删除数据
     *
     * @param positionId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer positionId) {
        return this.positionDao.deleteById(positionId) > 0;
    }

    @Override
    public PageInfo<Position> queryPageList(PositionListRequestDTO positionListRequestDTO) {

        PageHelper.startPage(positionListRequestDTO.getPageNum(),positionListRequestDTO.getPageSize());
        List<Position> list = positionDao.queryPageList(positionListRequestDTO);
        return new PageInfo<Position>(list);
    }

    @Override
    public boolean toggleStatus(Position position) {
        if(position.getStatus() == 1){
            //根据id修改成失败状态
           return positionDao.updateFailureStatusById(position.getPositionId());
        }else {
           return positionDao.updateSuccessStatusById(position.getPositionId());
        }
    }

    @Override
    public List<PositionActiveListResponseDTO>
    queryDepartmentList() throws Exception {
        List<PositionActiveListResponseDTO> list = positionDao.queryDepartmentList();
        if(list == null || list.isEmpty()){
            throw new Exception("没有有效职位");
        }
        return list;
    }

    @Override
    public List<PositionActiveListResponseDTO> positionByDepartmentName(String positionName) throws Exception {
        List<PositionActiveListResponseDTO> list = positionDao.positionByDepartmentName(positionName);
        if(list == null || list.isEmpty()){
            list = positionDao.queryDepartmentList();
        }
        return list;
    }
}