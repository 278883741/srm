package com.imooc.proxy;

public class Suv implements ICar {
    @Override
    public void run(String person,String carName) {
        System.out.println(person + "," + carName);
    }
}
