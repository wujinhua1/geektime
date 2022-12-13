package com.example.springbuckskafkaconsumer.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableBinding(Consumer.class)
public class MyMQReciver {
    @StreamListener(Consumer.INPUT)
    public void process(String message) {
        log.info("获取信息: " + message);
    }
}
