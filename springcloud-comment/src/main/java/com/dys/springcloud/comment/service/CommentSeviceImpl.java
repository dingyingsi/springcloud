package com.dys.springcloud.comment.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

@Service
public class CommentSeviceImpl {

    private Integer value = 0;

    @HystrixCommand(fallbackMethod = "defaultHystrixMethod")
    public String hystrixMethod() throws Exception {
        if (this.value++ % 2 == 0) {
            return "hystrix";
        } else {
            throw new Exception("hystrixMethos is wrong");
        }
    }

    public String defaultHystrixMethod() {
        return "this is defaultHystrixMethod value";
    }
}