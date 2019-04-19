package com.imooc.model;

public class PageParam {
    private int pageNum = 1;
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageNum() {
        return pageNum;
    }


    private int pageSize = 10;
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPageSize() {
        return pageSize;
    }

    private String iColumns;
    public void setiColumns(String iColumns) {
        this.iColumns = iColumns;
    }
    public String getiColumns() {
        return iColumns;
    }

    /**
     * 每页显示行数
     */
    private int iDisplayLength;
    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }
    public int getiDisplayLength() {
        return iDisplayLength;
    }

    /**
     * 起始行索引
     */
    private int iDisplayStart;
    public int getiDisplayStart() {
        return iDisplayStart;
    }
    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }
}
