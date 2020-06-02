package com.alibaba.dubbo.demo.provider;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.AbstractApplicationContext;

import java.lang.reflect.Method;

public class TestSpringBean extends TestSpringParentBean implements InitializingBean, DisposableBean,
        ApplicationContextAware, BeanNameAware, ApplicationListener<ContextRefreshedEvent>, BeanPostProcessor,
        BeanFactoryPostProcessor, BeanFactoryAware {

    public  TestSpringBean(){
        System.out.println("TestSpringBean的初始化方法被调用");
    }


    public String sayHello(){
        System.out.println("TestSpringBean------sayHello----的方法被调用");

        return "Hello";
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("TestSpringBean--------InitializingBean接口------afterPropertiesSet实现方法----的方法被调用");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("TestSpringBean--------DisposableBean接口------destroy实现方法----的方法被调用");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("TestSpringBean--------ApplicationContextAware接口------setApplicationContext实现方法----的方法被调用");
        try {
            Method method = applicationContext.getClass().getMethod("addApplicationListener", new Class<?>[]{ApplicationListener.class});
            method.invoke(applicationContext, new Object[]{this});
        } catch (Throwable t) {
            if (applicationContext instanceof AbstractApplicationContext) {
                try {
                    Method method = AbstractApplicationContext.class.getDeclaredMethod("addListener", new Class<?>[]{ApplicationListener.class});
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    method.invoke(applicationContext, new Object[]{this});
                } catch (Throwable t2) {
                }
            }
        }
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("TestSpringBean--------BeanNameAwaree接口------setBeanName实现方法----的方法被调用");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("TestSpringBean--------ApplicationListener接口------onApplicationEvent实现方法----的方法被调用");
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("TestSpringBean--------BeanPostProcessor接口------postProcessBeforeInitialization实现方法----的方法被调用");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("TestSpringBean--------BeanPostProcessor接口------postProcessAfterInitialization实现方法----的方法被调用");
        return o;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("TestSpringBean--------BeanFactoryPostProcessor接口------postProcessBeanFactory实现方法----的方法被调用");

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("TestSpringBean--------BeanFactoryAware接口------setBeanFactory实现方法----的方法被调用");
    }

    public void initMethod(){
        System.out.println("TestSpringBean--------initMethod----的方法被调用");
    }

    public void destoryMethod(){
        System.out.println("TestSpringBean--------destoryMethod----的方法被调用");
    }
}
