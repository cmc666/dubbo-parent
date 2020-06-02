package com.alibaba.dubbo.demo.provider;

public class TestSpringParentBean {

    private static int staticInt;

    static {
        System.out.println(staticInt);
        staticInt = 1;
        System.out.println(staticInt);
    }

    TestSpringParentBean(){
        System.out.println("TestSpringParentBean的初始化方法被调用");
        System.out.println(staticInt);
        staticInt = 2;
        System.out.println(staticInt);
        System.out.println("TestSpringParentBean的初始化方法结束调用");
    }


}
