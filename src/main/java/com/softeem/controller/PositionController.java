package com.softeem.controller;

import com.github.pagehelper.PageInfo;
import com.softeem.dto.Result;
import com.softeem.dto.requestDTO.PositionListRequestDTO;
import com.softeem.dto.responseDTO.PositionActiveListResponseDTO;
import com.softeem.entity.Department;
import com.softeem.entity.Position;
import com.softeem.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 高玉好
 * @ClassName PositionController
 * @since 2021/2/10 13:08
 */
@Api(tags = "职位管理模块")
@Controller
@RequestMapping("/position")
public class PositionController extends BaseController {
    @Resource
    private PositionService positionService;

    @ApiOperation(value = "跳转到职位列表的路由")
    @GetMapping("/toList")
    public String toList(){
        request.getSession().setAttribute("pageName", "职位列表");
        return "positionList";
    }
    /**
     * 查询部门列表：1分页查询 2.模糊查询
     *
     * @param
     * @return
     */
    @ApiOperation(value = "查询职位列表：1分页查询 2.模糊查询")
    @PostMapping("/getList")
    @ResponseBody
    public Result getList(@RequestBody  PositionListRequestDTO positionListRequestDTO){
        PageInfo<Position> list = positionService.queryPageList(positionListRequestDTO);
       return new Result(true,list);
    }


    /**
     * 单个部门状态切换
     *
     * @param position
     * @return
     */
    @ApiOperation(value = "单个部门状态切换")
    @PostMapping("/togglePositionStatus")
    @ResponseBody
    public Result toggleDepartmentStatus(@RequestBody Position position) {
        return new Result(positionService.toggleStatus(position));
    }

    /**
     * 跳转新增职位路由
     *
     */
    @ApiOperation(value = "跳转新增职位路由")
    @GetMapping("/toAddPosition")
    public String toAddPosition(){
        request.getSession().setAttribute("pageName", "新增职位");
        return "positionAdd";
    }

    /**
     * 新增职位
     *
     */
    @ApiOperation(value = "新增职位")
    @PostMapping(value = "/insertPosition",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Result insertPosition(Position position){
        Result<Object> result = new Result<>();
        try {
            positionService.insert(position);
        } catch (Exception e) {
            result.setFlag(false);
            result.setMsg(e.getMessage());
            return result;
        }
        result.setFlag(true);
        return result;
    }

    /**
     * 跳转查询职位路由
     *
     */
    @ApiOperation(value = "跳转查询职位路由")
    @GetMapping("/goPosition")
    public String goPosition(){
        request.getSession().setAttribute("pageName", "查看职位");
        return "positionInfo";
    }

    /**
     * 根据ID查询职位
     *
     */
    @ApiOperation(value = "根据ID查询职位")
    @PostMapping("/queryPositionById")
    @ResponseBody
    public Result goPosition(Integer positionId){
        Position position = positionService.queryById(positionId);
        if(position != null){
            return new Result(true, position);
        }
        return new Result(false, position);
    }

    /**
     * 跳转修改职位路由
     * @return
     */
    @ApiOperation(value = "跳转修改职位路由")
    @GetMapping("/goPositionEdit")
    public String goPositionEdit(){
        request.getSession().setAttribute("pageName", "修改职位");
        return "positionEdit";
    }

    @ApiOperation(value = "根据ID修改职位")
    @PostMapping(value = "/editPosition",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Result editPosition(Position position){
        System.out.println(position.toString());
        Result<Position> positionResult = new Result<>();
        try {
            positionService.update(position);
        }catch (Exception e){
            positionResult.setFlag(false);
            positionResult.setMsg(e.getMessage());
            return positionResult;
        }
        positionResult.setFlag(true);
        return positionResult;
    }

    @ApiOperation(value = "获取有效职位列表(下拉列表用)")
    @PostMapping("/getPositionInfo")
    @ResponseBody
    public Result getPositionInfo(){
        List<PositionActiveListResponseDTO> list = null;
        try {
            list = positionService.queryDepartmentList();
        } catch (Exception e) {
         return new Result(false,e.getMessage());
        }
        return new Result(true,list);
    }


    @ApiOperation(value = "根据部门名称锁定相应的职位下拉列表")
    @PostMapping("/positionByDepartmentName")
    @ResponseBody
    public Result positionByDepartmentName(@RequestParam(value = "positionName",required=false) String positionName){
        List<PositionActiveListResponseDTO> list = null;
        try {
            list = positionService.positionByDepartmentName(positionName);
        } catch (Exception e) {
            return new Result(false,e.getMessage());
        }
        return new Result(true,list);
    }
}
