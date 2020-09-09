package com.dys.springcloud.rocketmq.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "framework.rocketmq.consumer")
public class FrameworkRocketMQConsumerProperties {
    private Boolean enabled = false;
    private String namesrvAddr;
    private String groupName;
    private Integer consumeThreadMin;
    private Integer consumeThreadMax;
    private String topicAndTags;
    private Integer consumeMessageBatchMaxSize;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public int getConsumeThreadMin() {
        return consumeThreadMin;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public int getConsumeThreadMax() {
        return consumeThreadMax;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    public String getTopicAndTags() {
        return topicAndTags;
    }

    public void setTopicAndTags(String topicAndTags) {
        this.topicAndTags = topicAndTags;
    }

    public int getConsumeMessageBatchMaxSize() {
        return consumeMessageBatchMaxSize;
    }

    public void setConsumeMessageBatchMaxSize(int consumeMessageBatchMaxSize) {
        this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
    }
}
