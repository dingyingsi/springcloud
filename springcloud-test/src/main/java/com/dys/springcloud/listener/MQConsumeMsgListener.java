package com.dys.springcloud.listener;

import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MQConsumeMsgListener implements MessageListenerConcurrently {

    private static final Logger logger = LoggerFactory.getLogger(MQConsumeMsgListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if(CollectionUtils.isEmpty(msgs)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = msgs.get(0);

        if(messageExt.getTopic().equals("dys_topic")){
            if(messageExt.getTags().equals("*")){
                int reconsume = messageExt.getReconsumeTimes();
                // 重试3次
                if(reconsume ==3){
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
                System.out.println(new String(messageExt.getBody()));
            }
        }
        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
    }
}