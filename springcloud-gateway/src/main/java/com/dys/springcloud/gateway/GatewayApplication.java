package com.dys.springcloud.gateway;

import com.dys.springcloud.gateway.filter.CustomGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/user/**")
                        .filters(f -> f.filter(new CustomGatewayFilter()))
                        .uri("lb://SPRINGCLOUD-USER")
                        .order(0)
                        .id("custom_gateway_filter")
                ).route(r -> r.path("/order/**")
                        .filters(f -> f.filter(new CustomGatewayFilter()))
                        .uri("lb://SPRINGCLOUD-ORDER")
                        .order(0)
                        .id("custom_gateway_filter")
                ).route(r -> r.path("/product/**")
                        .filters(f -> f.filter(new CustomGatewayFilter()))
                        .uri("lb://SPRINGCLOUD-PRODUCT")
                        .order(0)
                        .id("custom_gateway_filter")
                ).route(r -> r.path("/comment/**")
                        .filters(f -> f.filter(new CustomGatewayFilter()))
                        .uri("lb://SPRINGCLOUD-COMMENT")
                        .order(0)
                        .id("custom_gateway_filter")
                )

                .build();
    }
}