package com.dys.springcloud.comment.api;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommentServiceFallback implements CommentService {
    @Override
    public Map<String, Object> comment() {
        Map<String, Object> result = new HashMap<>();
        result.put("CommentServiceFallback", CommentServiceFallback.class.toString());
        return result;
    }
}
