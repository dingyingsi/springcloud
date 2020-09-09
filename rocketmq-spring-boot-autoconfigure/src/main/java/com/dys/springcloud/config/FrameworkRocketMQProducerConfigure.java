package com.dys.springcloud.config;

import com.dys.springcloud.properties.FrameworkRocketMQProducerProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties({ FrameworkRocketMQProducerProperties.class })
@ConditionalOnProperty(prefix = "framework.rocketmq.producer", value = "enable", havingValue = "true")
public class FrameworkRocketMQProducerConfigure {

    @Resource
    private FrameworkRocketMQProducerProperties rocketMQProperties;

    @Bean
    public DefaultMQProducer frameworkRocketMQProducer() throws MQClientException {
        if (StringUtils.isEmpty(this.rocketMQProperties.getGroupName())) {
            throw new MQClientException(-1, "groupName is blank");
        }

        if (StringUtils.isEmpty(this.rocketMQProperties.getNamesrvAddr())) {
            throw new MQClientException(-1, "nameServerAddr is blank");
        }
        DefaultMQProducer producer;
        producer = new DefaultMQProducer(this.rocketMQProperties.getGroupName());
        producer.setNamesrvAddr(this.rocketMQProperties.getNamesrvAddr());
        producer.setInstanceName("frameworkRocketMQProducer");
        producer.setMaxMessageSize(this.rocketMQProperties.getProducerMaxMessageSize());
        producer.setSendMsgTimeout(this.rocketMQProperties.getProducerSendMsgTimeout());
        producer.setRetryTimesWhenSendFailed(this.rocketMQProperties.getProducerRetryTimesWhenSendFailed());

        try {
            producer.start();
        } catch (MQClientException e) {
            throw e;
        }
        return producer;
    }

}
