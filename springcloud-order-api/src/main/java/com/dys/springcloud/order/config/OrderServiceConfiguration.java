package com.dys.springcloud.order.config;

import org.springframework.context.annotation.Bean;

public class OrderServiceConfiguration {

    @Bean
    public OrderServiceRequestInterceptor orderServiceRequestInterceptor() {
        OrderServiceRequestInterceptor orderServiceRequestInterceptor = new OrderServiceRequestInterceptor();
        return orderServiceRequestInterceptor;
    }
}
