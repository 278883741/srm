package com.imooc.controller;

import com.imooc.model.SysPermission;
import com.imooc.model.SysUser;
import com.imooc.service.CardService;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Utils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
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
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    CardService cardService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,HttpSession session) {
        SysUser user = userService.getLoginUser();
        model.addAttribute("occupationPercent",cardService.getOccupationPercent());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

    public static String getMessageByUrlToken(String id,String name,String token) throws UnsupportedEncodingException, URISyntaxException {

        HttpHost proxy = new HttpHost("proxy02.h3c.com", 8080);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);

        CredentialsProvider provider = new BasicCredentialsProvider();

        provider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("zys2349", "2691894sC!"));


       CloseableHttpClient httpclient = HttpClients.custom().build();


        java.net.URI uri = new URIBuilder("http://open.api.tianyancha.com/services/v4/open/xgbaseinfoV2").setParameter("id", id).setParameter("name", name).build();//URLEncoder.encode(name,"UTF-8")
        //setParameter("id", id).
        System.out.println(uri);
        HttpGet request = new HttpGet(uri);//这里发送get请求
        String result="";
        try {

            request.setHeader("Authorization", token);
            //  CloseableHttpClient httpClient = HttpClients.createDefault();
            // 通过请求对象获取响应对象
            HttpResponse response = httpclient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result= EntityUtils.toString(response.getEntity(),"utf-8");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
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
