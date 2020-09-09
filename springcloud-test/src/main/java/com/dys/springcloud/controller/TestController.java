package com.dys.springcloud.controller;

import com.dys.springcloud.listener.MQConsumeMsgListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Controller
public class TestController {

    @Resource
    private DefaultMQProducer frameworkRocketMQProducer;

    @Resource
    private DefaultMQPushConsumer frameworkDefaultMQPushConsumer;

    @Resource
    private MQConsumeMsgListener mqConsumeMsgListener;

    @PostConstruct
    public void postContruct(){
        try {
            this.frameworkDefaultMQPushConsumer.registerMessageListener(this.mqConsumeMsgListener);
            this.frameworkDefaultMQPushConsumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = {"/rmq"})
    public String rmq(@RequestParam(name = "size") Integer size) throws Exception {
        for (int i = 0;i < size; i++) {
            String msg = "demo msg test" + i;
            Message sendMsg = new Message("dys_topic", "*", msg.getBytes());
            //默认3秒超时
            SendResult sendResult = this.frameworkRocketMQProducer.send(sendMsg);

        }

        return "abc";
    }

}
