package com.dys.springcloud.comment.controller;

import com.dys.springcloud.comment.api.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/comment")
public class CommentController implements CommentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    @Value("${server.port}")
    private Integer port;


    @Override
    @RequestMapping(value = "/comment")
    @ResponseBody
    public Map<String, Object> comment() {
        Map<String, Object> result = new HashMap<>(16);
        result.put("comment", "CommentController-" + port);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("param: {}, return: {}", null, result);
        }
        return result;
    }
}
