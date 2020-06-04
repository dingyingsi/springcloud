package com.dys.springcloud.order.config;

import feign.*;

public class OrderServiceRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.header("aaa", "bbb");

    }
}