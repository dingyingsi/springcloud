package com.dys.springcloud;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan(value = {"com.liubike.framework", "com.dys"})
public class SpringCloudTest implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTest.class, args);
    }

    @Value("${framework.rocketmq.consumer.enabled}")
    private boolean consumerEnabled;

    @Value("${framework.rocketmq.producer.enabled}")
    private boolean producerEnabled;

    @Resource
    private DefaultMQPushConsumer frameworkDefaultMQPushConsumer;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("consumerEnabled = " + consumerEnabled + ", producerEnabled = " + producerEnabled);
    }
}
