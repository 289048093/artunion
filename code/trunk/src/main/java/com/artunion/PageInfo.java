/**
 * Copyright(c) 2012 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Nov 14, 2012  3:14:34 PM
 */
package com.artunion;

/**
 * 分页信息
 * 
 * @author CloudKing
 * 
 */
public class PageInfo {
    /**
     * 当前页
     */
    private Integer nowPage = 1;
    /**
     * 每页数据
     */
    private Integer eachPageData = 20;
    /**
     * 总页数
     */
    private Integer pageCount = 0;
    /**
     * 数据总数
     */
    private Integer dataCount = 0;

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public Integer getStart() {
        return (nowPage - 1) * eachPageData;
    }

    public Integer getEachPageData() {
        return eachPageData;
    }

    public void setEachPageData(Integer eachPageData) {
        this.eachPageData = eachPageData;
    }
}
