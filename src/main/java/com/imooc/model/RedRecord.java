package com.imooc.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Table(name = "red_record")
public class RedRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 人数
     */
    private Integer total;

    /**
     * 总金额（单位为分）
     */
    private Integer amount;

    @Column(name = "is_active")
    private Boolean isActive;

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
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置总金额（单位为分）
     *
     * @param amount 总金额（单位为分）
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return is_active
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * @param isActive
     */
    public void setIsActive(Boolean isActive) {
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

    @Override
    public String toString() {
        return "RedRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", total=" + total +
                ", amount=" + amount +
                ", isActive=" + isActive +
                ", createTime=" + createTime +
                '}';
    }
}