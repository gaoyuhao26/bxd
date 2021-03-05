package com.softeem.dao;

import com.softeem.dto.requestDTO.PositionListRequestDTO;
import com.softeem.dto.responseDTO.PositionActiveListResponseDTO;
import com.softeem.entity.Position;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 职位表(Position)表数据库访问层
 *
 * @author 高玉好
 * @since 2021-02-11 23:13:59
 */
public interface PositionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param positionId 主键
     * @return 实例对象
     */
    Position queryById(Integer positionId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Position> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param position 实例对象
     * @return 对象列表
     */
    List<Position> queryAll(Position position);

    /**
     * 新增数据
     *
     * @param position 实例对象
     * @return 影响行数
     */
    int insert(Position position);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Position> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Position> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Position> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Position> entities);

    /**
     * 修改数据
     *
     * @param position 实例对象
     * @return 影响行数
     */
    int update(Position position);

    /**
     * 通过主键删除数据
     *
     * @param positionId 主键
     * @return 影响行数
     */
    int deleteById(Integer positionId);

    /**
     * 模糊分页查询
     *
     * @param positionListRequestDTO
     * @return
     */
    List<Position> queryPageList(PositionListRequestDTO positionListRequestDTO);

    @Update(" UPDATE position SET status = 0 WHERE position_id = #{positionId}")
    boolean updateFailureStatusById(@Param("positionId") Integer positionId);
    @Update(" UPDATE position SET status = 1 WHERE position_id = #{positionId}")
    boolean updateSuccessStatusById(@Param("positionId") Integer positionId);
    @Select("select count(position_id) from position where position_name = #{positionName}")
    int queryByPositionName(@Param("positionName") String positionName);
    @Select("SELECT * from position where position_id = 1")
    Position SelectPosition();
    @Select("SELECT position_id,position_name FROM position WHERE status = 1")
    List<PositionActiveListResponseDTO> queryDepartmentList();

    List<PositionActiveListResponseDTO> positionByDepartmentName(@Param("positionName") String positionName);
}