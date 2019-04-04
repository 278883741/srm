package com.imooc.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_report")
public class UserReport {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 被举报人id
     */
    @Column(name = "deal_user_id")
    private Integer dealUserId;

    /**
     * 被举报视频id
     */
    @Column(name = "deal_video_id")
    private Integer dealVideoId;

    /**
     * 举报标题
     */
    private String title;

    /**
     * 举报内容
     */
    private String content;

    /**
     * 举报人id
     */
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "create_time")
    private Date createTime;

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
     * 获取被举报人id
     *
     * @return deal_user_id - 被举报人id
     */
    public Integer getDealUserId() {
        return dealUserId;
    }

    /**
     * 设置被举报人id
     *
     * @param dealUserId 被举报人id
     */
    public void setDealUserId(Integer dealUserId) {
        this.dealUserId = dealUserId;
    }

    /**
     * 获取被举报视频id
     *
     * @return deal_video_id - 被举报视频id
     */
    public Integer getDealVideoId() {
        return dealVideoId;
    }

    /**
     * 设置被举报视频id
     *
     * @param dealVideoId 被举报视频id
     */
    public void setDealVideoId(Integer dealVideoId) {
        this.dealVideoId = dealVideoId;
    }

    /**
     * 获取举报标题
     *
     * @return title - 举报标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置举报标题
     *
     * @param title 举报标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取举报内容
     *
     * @return content - 举报内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置举报内容
     *
     * @param content 举报内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取举报人id
     *
     * @return user_id - 举报人id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置举报人id
     *
     * @param userId 举报人id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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