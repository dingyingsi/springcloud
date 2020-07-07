package com.dys.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        return myBeanPostProcessor;
    }

    @Bean
    public User user() {
        User user = new User();
        return user;
    }
}
