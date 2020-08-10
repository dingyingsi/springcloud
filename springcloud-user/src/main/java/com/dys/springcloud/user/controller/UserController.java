package com.dys.springcloud.user.controller;

import com.dys.springcloud.comment.api.CommentService;
import com.dys.springcloud.order.api.OrderService;
import com.dys.springcloud.product.api.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private OrderService orderService;

    @Resource
    private CommentService commentService;

    @Resource
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/user")
    public Map<String, Object> user() {

        Map<String, Object> order = this.orderService.order();

        Map<String, Object> comment = this.commentService.comment();
        Map<String, Object> product = this.productService.product();

        Map<String, Object> result = new HashMap<>(16);
        result.put("user", "UserController");
        result.put("order", order);

        result.put("product", product);
        result.put("comment", comment);

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/v1/user")
    public Map<String, Object> v1user() {

        Map<String, Object> order = this.orderService.order();

        Map<String, Object> comment = this.commentService.comment();
        Map<String, Object> product = this.productService.product();

        Map<String, Object> result = new HashMap<>(16);
        result.put("user", "UserController");
        result.put("order", order);

        result.put("product", product);
        result.put("comment", comment);
        result.put("version", "v1");

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/v2/user")
    public Map<String, Object> v2user() {

        Map<String, Object> order = this.orderService.order();

        Map<String, Object> comment = this.commentService.comment();
        Map<String, Object> product = this.productService.product();

        Map<String, Object> result = new HashMap<>(16);
        result.put("user", "UserController");
        result.put("order", order);

        result.put("product", product);
        result.put("comment", comment);
        result.put("version", "v2");

        return result;
    }
}
