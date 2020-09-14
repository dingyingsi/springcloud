package com.dys.springcloud.listener;

import com.liubike.framework.rocketmq.annotation.FrameworkRocketMQMessageListener;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FrameworkRocketMQMessageListener
public class MQConsumeMsgListener implements MessageListenerConcurrently {

    private static final Logger logger = LoggerFactory.getLogger(MQConsumeMsgListener.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        msgs.stream().forEach(messageExt -> {

            System.out.println(new String(messageExt.getBody()) + ", " + messageExt.getReconsumeTimes());

        });


        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}