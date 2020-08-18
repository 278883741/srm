package com.imooc.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "red_record")
public class RedRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 红包全局唯一标识串
     */
    @Column(name = "red_packet")
    private String redPacket;

    /**
     * 人数
     */
    private Integer total;

    /**
     * 总金额（单位为分）
     */
    private BigDecimal amount;

    @Column(name = "is_active")
    private Byte isActive;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取红包全局唯一标识串
     *
     * @return red_packet - 红包全局唯一标识串
     */
    public String getRedPacket() {
        return redPacket;
    }

    /**
     * 设置红包全局唯一标识串
     *
     * @param redPacket 红包全局唯一标识串
     */
    public void setRedPacket(String redPacket) {
        this.redPacket = redPacket;
    }

    /**
     * 获取人数
     *
     * @return total - 人数
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置人数
     *
     * @param total 人数
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取总金额（单位为分）
     *
     * @return amount - 总金额（单位为分）
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置总金额（单位为分）
     *
     * @param amount 总金额（单位为分）
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}