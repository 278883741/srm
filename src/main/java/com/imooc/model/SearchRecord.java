package com.imooc.model;

import javax.persistence.*;

@Table(name = "search_record")
public class SearchRecord {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 搜索内容
     */
    private String content;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取搜索内容
     *
     * @return content - 搜索内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置搜索内容
     *
     * @param content 搜索内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}