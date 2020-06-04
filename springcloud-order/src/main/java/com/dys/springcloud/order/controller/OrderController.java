package com.dys.springcloud.order.controller;

import com.dys.springcloud.order.api.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/order")
public class OrderController implements OrderService {

    @Value("${server.port}")
    private Integer port;

    @Override
    @RequestMapping(value = "/order")
    public Map<String, Object> order() {

        Map<String, Object> result = new HashMap<>(16);
        result.put("order", "OrderController-" + port);

        return result;
    }

}
