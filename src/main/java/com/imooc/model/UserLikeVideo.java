package com.imooc.model;

import javax.persistence.*;

@Table(name = "user_like_video")
public class UserLikeVideo {
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
     * video_id
     */
    @Column(name = "video_id")
    private Integer videoId;

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
     * 获取video_id
     *
     * @return video_id - video_id
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * 设置video_id
     *
     * @param videoId video_id
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}