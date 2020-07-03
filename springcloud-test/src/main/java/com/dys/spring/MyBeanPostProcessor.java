package com.dys.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        User user = (User)bean;
        user.setName("abc");
        System.out.println("4 初始化 after...实例化的bean对象:" + bean + "\t" + beanName);
        // 可以根据beanName不同执行不同的处理操作
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("2 初始化 before--实例化的bean对象:" + bean + "\t" + beanName);
        // 可以根据beanName不同执行不同的处理操作
        return bean;
    }
}
