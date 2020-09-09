package com.dys.springcloud.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "framework.rocketmq")
public class FrameworkRocketMQProducerProperties {
    private boolean enable = false;
    private String namesrvAddr = "localhost:9876";
    private String groupName = "default";
    private int producerMaxMessageSize = 1024;
    private int producerSendMsgTimeout = 2000;
    private int producerRetryTimesWhenSendFailed = 2;
    private int consumerConsumeThreadMin = 5;
    private int consumerConsumeThreadMax = 30;
    private int consumerConsumeMessageBatchMaxSize = 1;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getProducerMaxMessageSize() {
        return producerMaxMessageSize;
    }

    public void setProducerMaxMessageSize(int producerMaxMessageSize) {
        this.producerMaxMessageSize = producerMaxMessageSize;
    }

    public int getProducerSendMsgTimeout() {
        return producerSendMsgTimeout;
    }

    public void setProducerSendMsgTimeout(int producerSendMsgTimeout) {
        this.producerSendMsgTimeout = producerSendMsgTimeout;
    }

    public int getProducerRetryTimesWhenSendFailed() {
        return producerRetryTimesWhenSendFailed;
    }

    public void setProducerRetryTimesWhenSendFailed(int producerRetryTimesWhenSendFailed) {
        this.producerRetryTimesWhenSendFailed = producerRetryTimesWhenSendFailed;
    }

    public int getConsumerConsumeThreadMin() {
        return consumerConsumeThreadMin;
    }

    public void setConsumerConsumeThreadMin(int consumerConsumeThreadMin) {
        this.consumerConsumeThreadMin = consumerConsumeThreadMin;
    }

    public int getConsumerConsumeThreadMax() {
        return consumerConsumeThreadMax;
    }

    public void setConsumerConsumeThreadMax(int consumerConsumeThreadMax) {
        this.consumerConsumeThreadMax = consumerConsumeThreadMax;
    }

    public int getConsumerConsumeMessageBatchMaxSize() {
        return consumerConsumeMessageBatchMaxSize;
    }

    public void setConsumerConsumeMessageBatchMaxSize(int consumerConsumeMessageBatchMaxSize) {
        this.consumerConsumeMessageBatchMaxSize = consumerConsumeMessageBatchMaxSize;
    }
}
