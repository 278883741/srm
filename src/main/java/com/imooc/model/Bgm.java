package com.imooc.model;

import javax.persistence.*;

public class Bgm {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 歌曲作者
     */
    private String author;

    /**
     * 歌曲名字
     */
    private String name;

    /**
     * 歌曲路径
     */
    private String path;

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
     * 获取歌曲作者
     *
     * @return author - 歌曲作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置歌曲作者
     *
     * @param author 歌曲作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取歌曲名字
     *
     * @return name - 歌曲名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置歌曲名字
     *
     * @param name 歌曲名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取歌曲路径
     *
     * @return path - 歌曲路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置歌曲路径
     *
     * @param path 歌曲路径
     */
    public void setPath(String path) {
        this.path = path;
    }
}