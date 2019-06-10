package com.imooc.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "sys_permission")
public class SysPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 权限路径
     */
    @Column(name = "permission_url")
    private String permissionUrl;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 删除时间/失效时间
     */
    private Date deleted;

    /**
     * 删除状态0为未删除/可用，1为已删除/不可用
     */
    @Column(name = "delete_status")
    private Boolean deleteStatus;

    /**
     * 权限级别
     */
    private Integer level;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 父级id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    @Transient
    private List<SysPermission> childList;
    public void setChildList(List<SysPermission> childList) {
        this.childList = childList;
    }
    public List<SysPermission> getChildList() {
        return childList;
    }

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
     * 获取权限名称
     *
     * @return permission_name - 权限名称
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 设置权限名称
     *
     * @param permissionName 权限名称
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * 获取权限路径
     *
     * @return permission_url - 权限路径
     */
    public String getPermissionUrl() {
        return permissionUrl;
    }

    /**
     * 设置权限路径
     *
     * @param permissionUrl 权限路径
     */
    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取删除时间/失效时间
     *
     * @return deleted - 删除时间/失效时间
     */
    public Date getDeleted() {
        return deleted;
    }

    /**
     * 设置删除时间/失效时间
     *
     * @param deleted 删除时间/失效时间
     */
    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取删除状态0为未删除/可用，1为已删除/不可用
     *
     * @return delete_status - 删除状态0为未删除/可用，1为已删除/不可用
     */
    public Boolean getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置删除状态0为未删除/可用，1为已删除/不可用
     *
     * @param deleteStatus 删除状态0为未删除/可用，1为已删除/不可用
     */
    public void setDeleteStatus(Boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 获取权限级别
     *
     * @return level - 权限级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置权限级别
     *
     * @param level 权限级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取排序值
     *
     * @return sort - 排序值
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序值
     *
     * @param sort 排序值
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取父级id
     *
     * @return parent_id - 父级id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级id
     *
     * @param parentId 父级id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}