package com.imooc.model;

public class PageParam {
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


    private String iSortingCols;
    public void setiSortingCols(String iSortingCols) {
        this.iSortingCols = iSortingCols;
    }
    public String getiSortingCols() {
        return iSortingCols;
    }

    /**
     *
     */
    private String sColumns;
    public void setsColumns(String sColumns) {
        this.sColumns = sColumns;
    }
    public String getsColumns() {
        return sColumns;
    }

    /**
     * 排序字段
     */
    private Integer iSortCol_0;
    public void setiSortCol_0(Integer iSortCol_0) {
        this.iSortCol_0 = iSortCol_0;
    }
    public Integer getiSortCol_0() {
        return iSortCol_0;
    }

    /**
     * 数据库排序字段
     */
    private String sortPro;
    public void setSortPro(String sortPro) {
        this.sortPro = sortPro;
    }
    public String getSortPro() {
        return sortPro;
    }

    /**
     * （asc/desc）
     */
    private String sSortDir_0;
    public void setsSortDir_0(String sSortDir_0) {
        this.sSortDir_0 = sSortDir_0;
    }
    public String getsSortDir_0() {
        return sSortDir_0;
    }

    private String sSearch;
    public void setsSearch(String sSearch) {
        this.sSearch = sSearch;
    }
    public String getsSearch() {
        return sSearch;
    }

    private String bRegex;
    public void setbRegex(String bRegex) {
        this.bRegex = bRegex;
    }
    public String getbRegex() {
        return bRegex;
    }
}
