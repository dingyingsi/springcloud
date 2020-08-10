package com.dys.springcloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
@EnableEurekaClient
@EnableFeignClients(basePackages = {
        "com.dys.springcloud.order.api",
        "com.dys.springcloud.product.api",
        "com.dys.springcloud.comment.api"})
@ComponentScan(basePackages = {"com.dys.springcloud"})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
