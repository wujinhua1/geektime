package com.geektime.apollo.springbootmq.producer;

import com.geektime.apollo.springbootmq.processor.TestOutPutProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
