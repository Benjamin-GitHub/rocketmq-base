package com.benjamin.mq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @Decription TODO
 * @Authur Benjamin
 * @Date 2019/9/28 17:19
 * @Version 1.0
 */
public class MqProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group_order");
        producer.setNamesrvAddr("XXX");
        producer.start();
        System.out.printf("Producer Started.%n");
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("test_topic_order","tag1",("hello mq"+i).getBytes());
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
        System.out.printf("Producer Shutdown.%n");
    }
}
