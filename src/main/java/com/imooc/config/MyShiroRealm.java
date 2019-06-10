package com.imooc.config;

import com.imooc.model.SysPermission;
import com.imooc.model.SysUser;
import com.imooc.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    UserService userService;

    /**
     * 认证,登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)authenticationToken;
        String userName = usernamePasswordToken.getUsername();

        SysUser user = new SysUser();
        user.setUsername(userName);
        user.setDeleteStatus(false);
        user = userService.get(user);
        if(user == null){
            return null;
        }

        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = userService.getLoginUser();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        List<String> roles = userService.getRoleByUserId(user.getId());
        Set<String> set_roles = new HashSet<>();
        for (String item:roles) {
            set_roles.add(item);
        }
        simpleAuthorizationInfo.setRoles(set_roles);

        List<SysPermission> permissions = userService.getPermissionByUserId(user.getId());
        Set<String> set_permissions = new HashSet<>();
        for (SysPermission item: permissions) {
            set_permissions.add(item.getPermissionName());
        }
        simpleAuthorizationInfo.setStringPermissions(set_permissions);
        return simpleAuthorizationInfo;
    }
}
