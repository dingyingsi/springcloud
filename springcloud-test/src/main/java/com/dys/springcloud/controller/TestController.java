package com.dys.springcloud.controller;

import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @RequestMapping(value = {"/rmq"})
    public String rmq(@RequestParam(name = "size") Integer size) throws Exception {
        for (int i = 0;i < size; i++) {
            String msg = "demo msg test" + i;
            Message sendMsg = new Message("dys_topic", "*", msg.getBytes());
            //默认3秒超时
           // SendResult sendResult = this.frameworkRocketMQProducer.send(sendMsg);

        }

        return "abc";
    }

}
