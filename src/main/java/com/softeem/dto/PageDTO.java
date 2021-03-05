package com.softeem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页信息
 */
@ApiModel(description = "分页信息")
public class PageDTO implements Serializable {

    private static final long serialVersionUID = 546125947779828553L;

    /**
     * 页码,从1开始
     */
    @ApiModelProperty(value = "页码,从1开始")
    private int pageNum = 1;

    /**
     * 页面大小
     */
    @ApiModelProperty(value = "页面大小")
    private int pageSize = 10;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数")
    private long total;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private int pages;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
