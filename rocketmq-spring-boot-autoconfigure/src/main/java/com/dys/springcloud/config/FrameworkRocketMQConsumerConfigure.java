package com.dys.springcloud.config;

import com.dys.springcloud.properties.FrameworkRocketMQConsumerProperties;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties({FrameworkRocketMQConsumerProperties.class })
@ConditionalOnProperty(prefix = "framework.rocketmq.consumer", value = "enable", havingValue = "true")
public class FrameworkRocketMQConsumerConfigure {

    @Resource
    private FrameworkRocketMQConsumerProperties rocketMQConsumerProperties;

    @Bean
    public DefaultMQPushConsumer frameworkDefaultMQPushConsumer()  {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.rocketMQConsumerProperties.getGroupName());
        consumer.setNamesrvAddr(this.rocketMQConsumerProperties.getNamesrvAddr());
        consumer.setConsumeThreadMin(this.rocketMQConsumerProperties.getConsumeThreadMin());
        consumer.setConsumeThreadMax(this.rocketMQConsumerProperties.getConsumeThreadMax());

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        consumer.setMessageModel(MessageModel.CLUSTERING);

        consumer.setConsumeMessageBatchMaxSize(this.rocketMQConsumerProperties.getConsumeMessageBatchMaxSize());
        try {
            String[] topicAndTags = this.rocketMQConsumerProperties.getTopicAndTags().split(";");
            for (String topicAndTag : topicAndTags) {
                String[] topicTag = topicAndTag.split(":");
                consumer.subscribe(topicTag[0],topicTag[1]);
            }
            consumer.start();
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }
}
