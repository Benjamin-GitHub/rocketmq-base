package com.benjamin.mq;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Decription TODO
 * @Authur Benjamin
 * @Date 2019/9/28 17:20
 * @Version 1.0
 */
public class MqConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_order");
        consumer.setNamesrvAddr("XXX");
        consumer.subscribe("test_topic_order","tag1");
        consumer.setMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (int i = 0 ; i < list.size(); i++) {
                    System.out.println("list.size():....."+list.size());
                    MessageExt msgExt = list.get(i);
                    System.out.println("--------------------messge:"+new String(msgExt.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.printf("Consumer Started.%n");

        

    }
}
