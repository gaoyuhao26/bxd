package com.softeem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 自定义返回结果包装类
 *
 * @author 高玉好
 * @ClassName Result
 * @since 2021/1/31 18:51
 */
@ApiModel(description = "返回信息")
public class Result<T> implements Serializable {


    private static final long serialVersionUID = 5269381436579032884L;
    /**
     * 返回结果正常为true，发生异常返回false
     */
    @ApiModelProperty(value = "返回结果正常为true，发生异常返回false",example = "false" )
    private boolean flag;
    /**
     * 后端返回的消息
     */

    @ApiModelProperty(value = "返回的消息" ,example = "输入正确")
    private String msg;
    /**
     * 后端返回的数据
     */
    @ApiModelProperty(value = "返回的数据")
    private T data;

    public Result() {
    }

    public Result(boolean flag) {
        this.flag = flag;
    }

    public Result(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public Result(boolean flag, T data) {
        this.flag = flag;
        this.data = data;
    }

    public Result(boolean flag, String msg, T data) {
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
