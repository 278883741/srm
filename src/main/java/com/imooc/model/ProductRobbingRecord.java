package com.imooc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "product_robbing_record")
public class ProductRobbingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 产品Id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 抢单时间
     */
    @Column(name = "robbing_time")
    private Date robbingTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取产品Id
     *
     * @return product_id - 产品Id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品Id
     *
     * @param productId 产品Id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取抢单时间
     *
     * @return robbing_time - 抢单时间
     */
    public Date getRobbingTime() {
        return robbingTime;
    }

    /**
     * 设置抢单时间
     *
     * @param robbingTime 抢单时间
     */
    public void setRobbingTime(Date robbingTime) {
        this.robbingTime = robbingTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}