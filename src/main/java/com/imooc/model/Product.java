package com.imooc.model;

import java.util.Date;
import javax.persistence.*;

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 商品编号
     */
    @Column(name = "product_no")
    private String productNo;

    /**
     * 库存量
     */
    private Integer total;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取商品编号
     *
     * @return product_no - 商品编号
     */
    public String getProductNo() {
        return productNo;
    }

    /**
     * 设置商品编号
     *
     * @param productNo 商品编号
     */
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    /**
     * 获取库存量
     *
     * @return total - 库存量
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置库存量
     *
     * @param total 库存量
     */
    public void setTotal(Integer total) {
        this.total = total;
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