package com.imooc.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Start {
    public static void main(String[] args) {
        ICar car = new Suv();

        // 1.用哪个类加载器去加载代理对象
        // 2.动态代理类需要实现的接口
        // 3.事件处理,执行target对象的方法时，会触发事件处理器的方法，会把当前执行target对象的方法作为参数传入。
        ICar car1 = (ICar) Proxy.newProxyInstance(car.getClass().getClassLoader(), new Class[]{ICar.class}, new CarInvacationHandler(car));
        car1.run();

        // 动态代理 - 目标类可以实现接口，可以集成类，如果是final类不允许集成的话，也没有实现接口，那么就不能创建代理类
        Suv suv = (Suv) Enhancer.create(Suv.class, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] args) throws Throwable {
                System.out.println("判断用户是否有权限进行操作");
                Object result = method.invoke(new Suv(),args);
                System.out.println("记录用户执行操作的用户信息、更改内容和时间等");
                return result;
            }
        });
        suv.run();
    }
}
