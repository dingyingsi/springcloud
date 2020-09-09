package com.dys.springcloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringCloudTest implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTest.class, args);
    }

    @Resource
    private DefaultMQProducer frameworkRocketMQProducer;

    @Resource
    private DefaultMQPushConsumer defaultMQPushConsumer;

    @Override
    public void run(String... args) throws Exception {

    }
}
