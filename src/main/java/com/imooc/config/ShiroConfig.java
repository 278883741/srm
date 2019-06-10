package com.imooc.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import net.sf.ehcache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 生成cookie对象
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(60 * 60 * 24);
        return  simpleCookie;
    }

    /**
     * cookie管理对象;
     * rememberMeManager()方法是生成rememberMe管理器，而且要将这个rememberMe管理器设置到securityManager中
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehcacheManager = new EhCacheManager();
        ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehcacheManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCachingEnabled(true);
        //不配置权限缓存
        myShiroRealm.setAuthorizationCachingEnabled(true);
        myShiroRealm.setAuthorizationCacheName("authorizationCache");
        return myShiroRealm;
    }

    /**
     * 会话管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setCacheManager(getEhCacheManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //anon--匿名访问 (访问静态文件)
        filterChainDefinitionMap.put("/assets/**","anon");
        filterChainDefinitionMap.put("/imgs/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/layer/**","anon");
        filterChainDefinitionMap.put("/ueditor/**","anon");

        // swagger相关
        filterChainDefinitionMap.put("/swagger-ui.html","anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/v2/api-docs","anon");
        filterChainDefinitionMap.put("/webjars/**","anon");

        filterChainDefinitionMap.put("/user/add", "anon");
        filterChainDefinitionMap.put("/user/uploadHeadImg", "anon");
        filterChainDefinitionMap.put("/user/queryUserInfo", "anon");

        filterChainDefinitionMap.put("/card/queryListByaPage", "anon");

        // 登录相关
        filterChainDefinitionMap.put("/checkLogin","anon");
        filterChainDefinitionMap.put("/login","anon");

        // authc - 认证后可访问
        // filterChainDefinitionMap.put("/**", "authc");
        // user - 记住我或登录可访问
        filterChainDefinitionMap.put("/**", "user");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
        /**
         当前登录用户: <shiro:principal/><br/>
         <shiro:authenticated>我已登录,但未记住<br/></shiro:authenticated>
         <shiro:user>我已登录,或已记住<br/></shiro:user>
         <shiro:guest>我是访客<br/></shiro:guest>
         <shiro:hasAnyRoles name="manager,admin">manager or admin 角色用户登录显示此内容<br/></shiro:hasAnyRoles>
         <shiro:hasRole name="系统管理员">我是系统管理员<br/></shiro:hasRole>
         <shiro:hasRole name="会员">我是会员<br/></shiro:hasRole>
         <h2>权限列表</h2>
         <shiro:hasPermission name="权限列表">具有权限列表权限用户显示此内容<br/></shiro:hasPermission>
         <shiro:hasPermission name="用户列表">具有用户列表权限用户显示此内容<br/></shiro:hasPermission>
         <shiro:hasPermission name="在线用户">具有在线用户权限用户显示此内容<br/></shiro:hasPermission>
         <shiro:lacksPermission name="角色分配保存">不具有角色分配保存权限的用户显示此内容 <br/></shiro:lacksPermission>
         */
    }

    /**
     * thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
