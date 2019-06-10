package com.imooc.model;

import java.util.List;

public class BasePageModel {
    private int pageIndex;
    public void setPage(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPage() {
        return pageIndex;
    }

    /**
     * 共计页数
     */
    private int pageTotal;
    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
    public int getPageTotal() {
        return pageTotal;
    }

    /**
     * 共计数据数
     */
    private int records;
    public void setRecords(int records) {
        this.records = records;
    }
    public int getRecords() {
        return records;
    }

    /**
     * 表格中的实际数据。　
     */
    private List<?> aaData;
    public void setAaData(List<?> aaData) {
        this.aaData = aaData;
    }
    public List<?> getAaData() {
        return aaData;
    }

    /**
     * 实际的行数
     */
    private int iTotalRecords;
    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }
    public int getiTotalRecords() {
        return iTotalRecords;
    }

    /**
     * 过滤之后，实际的行数。
     */
    private int iTotalDisplayRecords;
    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }
    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }
}
