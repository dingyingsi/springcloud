package com.dys.springcloud.product.controller;

import com.dys.springcloud.product.api.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/product")
public class ProductController implements ProductService {

    @Value("${server.port}")
    private Integer port;

    @Override
    @RequestMapping(value = "/product")
    public Map<String, Object> product() {

        Map<String, Object> result = new HashMap<>(16);
        result.put("product", "ProductController-" + port);
        return result;
    }

}
