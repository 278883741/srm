package com.imooc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "red_detail")
public class RedDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 红包记录id
     */
    @Column(name = "record_id")
    private Integer recordId;

    /**
     * 每个小红包的金额（单位为分）
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
     * 获取红包记录id
     *
     * @return record_id - 红包记录id
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * 设置红包记录id
     *
     * @param recordId 红包记录id
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取每个小红包的金额（单位为分）
     *
     * @return amount - 每个小红包的金额（单位为分）
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置每个小红包的金额（单位为分）
     *
     * @param amount 每个小红包的金额（单位为分）
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
}