package com.dys.springcloud.order.api;

import com.dys.springcloud.order.config.OrderServiceConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(name = "springcloud-order", contextId = "orderService", qualifier = "orderService", path = "/order/order", configuration = {OrderServiceConfiguration.class})
public interface OrderService {

    @RequestMapping(value = "/order")
    @ResponseBody
    Map<String, Object> order();
}
