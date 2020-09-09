package com.dys.springcloud.rocketmq.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "framework.rocketmq.producer")
public class FrameworkRocketMQProducerProperties {
    private Boolean enabled = false;
    private String namesrvAddr;
    private String groupName;
    private Integer maxMessageSize;
    private Integer sendMsgTimeout;
    private Integer retryTimesWhenSendFailed;

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

    public Integer getMaxMessageSize() {
        return maxMessageSize;
    }

    public void setMaxMessageSize(Integer maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public Integer getSendMsgTimeout() {
        return sendMsgTimeout;
    }

    public void setSendMsgTimeout(Integer sendMsgTimeout) {
        this.sendMsgTimeout = sendMsgTimeout;
    }

    public Integer getRetryTimesWhenSendFailed() {
        return retryTimesWhenSendFailed;
    }

    public void setRetryTimesWhenSendFailed(Integer retryTimesWhenSendFailed) {
        this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
    }
}
