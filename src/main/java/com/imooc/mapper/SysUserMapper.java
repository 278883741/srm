package com.imooc.mapper;

import com.imooc.model.SysPermission;
import com.imooc.model.SysUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserMapper extends Mapper<SysUser> {
    List<String> getRoleByUserId(@Param(value = "userId")Integer userId);
    List<SysPermission> getPermissionByUserId(@Param(value = "userId")Integer userId);
}