package com.imooc.model;

import java.math.BigDecimal;
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
     * 红包标识串
     */
    @Column(name = "red_packet")
    private String redPacket;

    /**
     * 红包金额（单位为分）
     */
    private BigDecimal amount;

    /**
     * 时间
     */
    @Column(name = "rob_time")
    private Date robTime;

    @Column(name = "is_active")
    private Byte isActive;

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
     * 获取红包标识串
     *
     * @return red_packet - 红包标识串
     */
    public String getRedPacket() {
        return redPacket;
    }

    /**
     * 设置红包标识串
     *
     * @param redPacket 红包标识串
     */
    public void setRedPacket(String redPacket) {
        this.redPacket = redPacket;
    }

    /**
     * 获取红包金额（单位为分）
     *
     * @return amount - 红包金额（单位为分）
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置红包金额（单位为分）
     *
     * @param amount 红包金额（单位为分）
     */
    public void setAmount(BigDecimal amount) {
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
     * @return is_active
     */
    public Byte getIsActive() {
        return isActive;
    }

    /**
     * @param isActive
     */
    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}