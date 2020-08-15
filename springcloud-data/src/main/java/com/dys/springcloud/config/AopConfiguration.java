package com.dys.springcloud.config;

import com.dys.springcloud.aop.LogAspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.dys.springcloud")
@EnableAspectJAutoProxy
public class AopConfiguration {

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
} 
