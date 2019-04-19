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

    private int pageTotal;
    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
    public int getPageTotal() {
        return pageTotal;
    }


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
}
