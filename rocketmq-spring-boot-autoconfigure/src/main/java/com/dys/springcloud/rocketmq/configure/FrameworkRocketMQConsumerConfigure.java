package com.dys.springcloud.rocketmq.configure;

import com.dys.springcloud.rocketmq.properties.FrameworkRocketMQConsumerProperties;
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
@ConditionalOnProperty(prefix = "framework.rocketmq.consumer", name = "enabled", havingValue = "true")
public class FrameworkRocketMQConsumerConfigure {

    @Resource
    private FrameworkRocketMQConsumerProperties frameworkRocketMQConsumerProperties;

    @Bean
    public DefaultMQPushConsumer frameworkDefaultMQPushConsumer()  {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(this.frameworkRocketMQConsumerProperties.getGroupName());
        consumer.setNamesrvAddr(this.frameworkRocketMQConsumerProperties.getNamesrvAddr());
        consumer.setConsumeThreadMin(this.frameworkRocketMQConsumerProperties.getConsumeThreadMin());
        consumer.setConsumeThreadMax(this.frameworkRocketMQConsumerProperties.getConsumeThreadMax());

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        consumer.setMessageModel(MessageModel.CLUSTERING);

        consumer.setConsumeMessageBatchMaxSize(this.frameworkRocketMQConsumerProperties.getConsumeMessageBatchMaxSize());
        try {
            String[] topicAndTags = this.frameworkRocketMQConsumerProperties.getTopicAndTags().split(";");
            for (String topicAndTag : topicAndTags) {
                String[] topicTag = topicAndTag.split(":");
                consumer.subscribe(topicTag[0],topicTag[1]);
            }
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }
}
