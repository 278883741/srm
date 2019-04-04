package com.imooc.model;

import javax.persistence.*;

@Table(name = "user_fans")
public class UserFans {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * user_id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * fan_id
     */
    @Column(name = "fan_id")
    private Integer fanId;

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
     * 获取user_id
     *
     * @return user_id - user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置user_id
     *
     * @param userId user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取fan_id
     *
     * @return fan_id - fan_id
     */
    public Integer getFanId() {
        return fanId;
    }

    /**
     * 设置fan_id
     *
     * @param fanId fan_id
     */
    public void setFanId(Integer fanId) {
        this.fanId = fanId;
    }
}