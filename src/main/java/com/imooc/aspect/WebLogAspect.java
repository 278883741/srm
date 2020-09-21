package com.imooc.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution( * com.imooc.controller..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        try{
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            logger.info("请求路径 URL : " + request.getRequestURL().toString());
            logger.info("请求方式 : " + request.getMethod());
            if(request.getMethod().equals("POST")){
                Object[] items = joinPoint.getArgs();
                Object[] arguments = new Object[items.length];
                if(items != null){
                    for (int i = 0; i < items.length; i++){
                        if (items[i] instanceof ServletRequest || items[i] instanceof ServletResponse || items[i] instanceof MultipartFile){
                            continue;
                        }
                        arguments[i] = items[i];
                    }
                    logger.info("请求参数 : " + JSONObject.toJSONString(arguments));
                }
            }else if(request.getMethod().equals("GET")){
                Enumeration<String> enu = request.getParameterNames();
                StringBuffer out = new StringBuffer();
                while (enu.hasMoreElements()) {
                    out.append("请求参数：");
                    String name = (String) enu.nextElement();
                    String value = (String) request.getParameter(name);
                    out.append(name);
                    out.append(":");
                    out.append(value);
                    out.append(",");
                }
                if(!out.toString().equals(""))
                    logger.info(out.toString());
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        String responseJson = "";
        if (ret != null) {
            responseJson = JSONObject.toJSONString(ret);
        }
        logger.info("响应体 : " + responseJson);
    }
}