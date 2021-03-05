package com.softeem.service;

import com.github.pagehelper.PageInfo;
import com.softeem.dto.requestDTO.PositionListRequestDTO;
import com.softeem.dto.responseDTO.PositionActiveListResponseDTO;
import com.softeem.entity.Position;

import java.util.List;

/**
 * 职位表(Position)表服务接口
 *
 * @author 高玉好
 * @since 2021-02-11 23:14:00
 */
public interface PositionService {

    /**
     * 通过ID查询单条数据
     *
     * @param positionId 主键
     * @return 实例对象
     */
    Position queryById(Integer positionId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Position> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    Position insert(Position position) throws Exception;

    /**
     * 修改数据
     *
     * @param position 实例对象
     * @return 实例对象
     */
    Position update(Position position);

    /**
     * 通过主键删除数据
     *
     * @param positionId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer positionId);

    /**
     * 模糊分页查询
     *
     * @param positionListRequestDTO
     * @return
     */
    PageInfo<Position> queryPageList(PositionListRequestDTO positionListRequestDTO);

    boolean toggleStatus(Position position);

    List<PositionActiveListResponseDTO> queryDepartmentList() throws Exception;

    List<PositionActiveListResponseDTO> positionByDepartmentName(String positionName) throws Exception;
}