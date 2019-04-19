package com.imooc.model;

import java.util.Date;
import javax.persistence.*;

public class Video {
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
     * 视频配字
     */
    @Column(name = "video_desccription")
    private String videoDesccription;

    /**
     * 视频路径
     */
    @Column(name = "video_path")
    private String videoPath;

    /**
     * 视频时长
     */
    @Column(name = "video_seconds")
    private Float videoSeconds;

    /**
     * 视频宽度
     */
    @Column(name = "video_width")
    private Integer videoWidth;

    /**
     * 视频高度
     */
    @Column(name = "video_height")
    private Integer videoHeight;

    /**
     * 封面
     */
    @Column(name = "cover_path")
    private String coverPath;

    /**
     * 获得喜欢数
     */
    @Column(name = "like_count")
    private Integer likeCount;

    /**
     * 视频状态 - 1：正常,2：被举报
     */
    private Integer status;

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
     * 获取视频配字
     *
     * @return video_desccription - 视频配字
     */
    public String getVideoDesccription() {
        return videoDesccription;
    }

    /**
     * 设置视频配字
     *
     * @param videoDesccription 视频配字
     */
    public void setVideoDesccription(String videoDesccription) {
        this.videoDesccription = videoDesccription;
    }

    /**
     * 获取视频路径
     *
     * @return video_path - 视频路径
     */
    public String getVideoPath() {
        return videoPath;
    }

    /**
     * 设置视频路径
     *
     * @param videoPath 视频路径
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * 获取视频时长
     *
     * @return video_seconds - 视频时长
     */
    public Float getVideoSeconds() {
        return videoSeconds;
    }

    /**
     * 设置视频时长
     *
     * @param videoSeconds 视频时长
     */
    public void setVideoSeconds(Float videoSeconds) {
        this.videoSeconds = videoSeconds;
    }

    /**
     * 获取视频宽度
     *
     * @return video_width - 视频宽度
     */
    public Integer getVideoWidth() {
        return videoWidth;
    }

    /**
     * 设置视频宽度
     *
     * @param videoWidth 视频宽度
     */
    public void setVideoWidth(Integer videoWidth) {
        this.videoWidth = videoWidth;
    }

    /**
     * 获取视频高度
     *
     * @return video_height - 视频高度
     */
    public Integer getVideoHeight() {
        return videoHeight;
    }

    /**
     * 设置视频高度
     *
     * @param videoHeight 视频高度
     */
    public void setVideoHeight(Integer videoHeight) {
        this.videoHeight = videoHeight;
    }

    /**
     * 获取封面
     *
     * @return cover_path - 封面
     */
    public String getCoverPath() {
        return coverPath;
    }

    /**
     * 设置封面
     *
     * @param coverPath 封面
     */
    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    /**
     * 获取获得喜欢数
     *
     * @return like_count - 获得喜欢数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 设置获得喜欢数
     *
     * @param likeCount 获得喜欢数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 获取视频状态 - 1：正常,2：被举报
     *
     * @return status - 视频状态 - 1：正常,2：被举报
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置视频状态 - 1：正常,2：被举报
     *
     * @param status 视频状态 - 1：正常,2：被举报
     */
    public void setStatus(Integer status) {
        this.status = status;
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