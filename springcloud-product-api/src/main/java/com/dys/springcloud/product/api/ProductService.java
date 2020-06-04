package com.dys.springcloud.product.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(name = "springcloud-product", qualifier = "productService", path = "/product/product")
public interface ProductService {

    @RequestMapping(value = "/product")
    @ResponseBody
    Map<String, Object> product();
}
