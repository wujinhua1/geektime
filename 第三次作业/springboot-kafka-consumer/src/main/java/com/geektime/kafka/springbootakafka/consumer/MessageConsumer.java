package com.geektime.kafka.springbootakafka.consumer;

import com.geektime.kafka.springbootakafka.processor.TestInPutProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

    @StreamListener(TestInPutProcessor.TEST_INPUT)
    public void receiveMsg(String msg){
        log.info("接收消息成功：" + msg);
    }
}
