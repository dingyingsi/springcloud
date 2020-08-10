package com.dys.springcloud.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CustomGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put("coutStartTime", System.currentTimeMillis());

        Mono<Void> mono = chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute("coutStartTime");
            Long endTime = System.currentTimeMillis() - startTime;
            if (startTime != null) {
                System.out.println(exchange.getRequest().getURI().getRawPath() + " : " + endTime + "ms");
            }
        }));

        return mono;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
