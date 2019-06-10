package com.imooc.controller;

import com.imooc.model.SysPermission;
import com.imooc.model.SysUser;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,HttpSession session) {
        SysUser user = userService.getLoginUser();

//        session.setAttribute("functions",userService.getFunctionByUserId(user.getId()));
//        session.setAttribute("user",user);

        Object object = session.getAttribute("permissions");
        model.addAttribute("permissions11",object);
        Integer i = 1;
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {

//        session.setAttribute("functions",userService.getFunctionByName(user.getOaid()));
//        session.setAttribute("user",userService.getLoginUser());
        return "login";
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public String checkLogin(String userName, String password,boolean rememberMe, HttpSession session, RedirectAttributes redirectAttributes) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, MD5Utils.getMD5Str(password));
        if(rememberMe){
            token.setRememberMe(true);
        }
        Boolean loginStatus = false;

        try {
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + userName + "]进行登录验证..验证开始");
            subject.login(token);
            loginStatus = true;
            token.clear();

            logger.info("对用户[" + userName + "]进行登录验证..验证通过");
        } catch (UnknownAccountException uae) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addFlashAttribute("message", "密码不正确");
        } catch (LockedAccountException lae) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,堆栈轨迹如下");
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }

        //验证是否登录成功
        if (loginStatus) {
            SysUser user = (SysUser) subject.getPrincipal();
            session.setAttribute("user", user);
            List<String> roles = userService.getRoleByUserId(user.getId());
            List<SysPermission> permissions = userService.getPermissionByUserId(user.getId());

            List<String> list_permissions = new ArrayList<>();
            for (SysPermission item: permissions) {
                list_permissions.add(item.getPermissionName());
            }

            logger.info("登录用户:" + userName);
            logger.info("登录角色:" + roles.toString());
            logger.info("登录权限:" + list_permissions.toString());

            session.setAttribute("roles",roles.toString());
            session.setAttribute("permissions",permissions);
            session.setAttribute("list_permissions",list_permissions.toString());
            return "redirect:/";
        } else {
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
