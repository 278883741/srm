package com.imooc.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    // 再被调用方法之前执行，比如再调用login之前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            Object bean = handlerMethod.getBean();
            Method method = handlerMethod.getMethod();
            String parameters = Arrays.toString(method.getParameters());
            System.out.println("bean: " + bean.getClass().getName() + ";method: " + method.getName() + ";args: " + parameters);
        }

        return true;
    }

    @Override
    // 再被调用方法之后执行，渲染视图之前
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    // 渲染视图之后，一般用来释放资源
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
