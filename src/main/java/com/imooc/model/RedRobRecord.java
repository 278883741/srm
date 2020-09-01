package com.imooc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "red_rob_record")
public class RedRobRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户账号
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 红包金额（单位为分）
     */
    private Integer amount;

    /**
     * 时间
     */
    @Column(name = "rob_time")
    private Date robTime;

    @Column(name = "record_id")
    private Integer recordId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户账号
     *
     * @return user_id - 用户账号
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户账号
     *
     * @param userId 用户账号
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取红包金额（单位为分）
     *
     * @return amount - 红包金额（单位为分）
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置红包金额（单位为分）
     *
     * @param amount 红包金额（单位为分）
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取时间
     *
     * @return rob_time - 时间
     */
    public Date getRobTime() {
        return robTime;
    }

    /**
     * 设置时间
     *
     * @param robTime 时间
     */
    public void setRobTime(Date robTime) {
        this.robTime = robTime;
    }

    /**
     * @return record_id
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * @param recordId
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }
}