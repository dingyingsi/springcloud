package com.dys.springcloud.comment.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@FeignClient(name = "springcloud-comment", qualifier = "commentService", path = "/comment/comment", fallback = CommentServiceFallback.class)
public interface CommentService {

    @RequestMapping(value = "/comment")
    @ResponseBody
    Map<String, Object> comment();
}
