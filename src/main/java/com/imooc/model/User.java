package com.imooc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(value = "用户对象",description = "这是用户对象")
public class User {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    /**
     * user_name
     */
    @ApiModelProperty(value = "用户名", name = "userName", example = "imoocUser", required = true)
    @Column(name = "user_name")
    private String userName;

    /**
     * password
     */
    @ApiModelProperty(value = "用户密码", name = "password", example = "123456", required = true)
    private String password;

    /**
     * 头像
     */
    @Column(name = "face_image")
    @ApiModelProperty(hidden = true)
    private String faceImage;

    /**
     * 昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 粉丝数
     */
    @Column(name = "fans_count")
    @ApiModelProperty(hidden = true)
    private Integer fansCount;

    /**
     * 关注数
     */
    @Column(name = "follow_count")
    @ApiModelProperty(hidden = true)
    private Integer followCount;

    /**
     * 获赞数
     */
    @Column(name = "receive_like_count")
    @ApiModelProperty(hidden = true)
    private Integer receiveLikeCount;

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
     * 获取user_name
     *
     * @return user_name - user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置user_name
     *
     * @param userName user_name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取password
     *
     * @return password - password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置password
     *
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像
     *
     * @return face_image - 头像
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置头像
     *
     * @param faceImage 头像
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * 获取昵称
     *
     * @return nick_name - 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置昵称
     *
     * @param nickName 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取粉丝数
     *
     * @return fans_count - 粉丝数
     */
    public Integer getFansCount() {
        return fansCount;
    }

    /**
     * 设置粉丝数
     *
     * @param fansCount 粉丝数
     */
    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    /**
     * 获取关注数
     *
     * @return follow_count - 关注数
     */
    public Integer getFollowCount() {
        return followCount;
    }

    /**
     * 设置关注数
     *
     * @param followCount 关注数
     */
    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    /**
     * 获取获赞数
     *
     * @return receive_like_count - 获赞数
     */
    public Integer getReceiveLikeCount() {
        return receiveLikeCount;
    }

    /**
     * 设置获赞数
     *
     * @param receiveLikeCount 获赞数
     */
    public void setReceiveLikeCount(Integer receiveLikeCount) {
        this.receiveLikeCount = receiveLikeCount;
    }
}