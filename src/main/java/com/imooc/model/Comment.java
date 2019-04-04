package com.imooc.model;

import java.util.Date;
import javax.persistence.*;

public class Comment {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 视频id
     */
    @Column(name = "video_id")
    private Integer videoId;

    /**
     * 留言用户的id
     */
    @Column(name = "from_user_id")
    private Integer fromUserId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 留言内容
     */
    private String comment;

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
     * 获取视频id
     *
     * @return video_id - 视频id
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * 设置视频id
     *
     * @param videoId 视频id
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取留言用户的id
     *
     * @return from_user_id - 留言用户的id
     */
    public Integer getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置留言用户的id
     *
     * @param fromUserId 留言用户的id
     */
    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
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
     * 获取留言内容
     *
     * @return comment - 留言内容
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置留言内容
     *
     * @param comment 留言内容
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}