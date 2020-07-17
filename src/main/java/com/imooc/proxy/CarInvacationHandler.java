package com.imooc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CarInvacationHandler implements InvocationHandler {
    private Object target;

    public CarInvacationHandler() {

    }

    public CarInvacationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("判断用户是否有权限进行操作");
        Object obj = method.invoke(this.target, args);
        System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
        return obj;
    }
}
