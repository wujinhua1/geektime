package com.geektime.mq.springbootmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageProducer {
    @Autowired
    @Qualifier("testOutput")
    private MessageChannel messageChannel;

    public void sendMsg(String msg){
        messageChannel.send(MessageBuilder.withPayload(msg).build());
        log.info("消息发送成功：" + msg);
    }
}
