package com.example.springbuckskafkaproducer.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springbuckskafkaproducer.integration.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Producer.class)
@Slf4j
public class SendController {

    @Autowired
    private Producer producer;

    @GetMapping("/send")
    public void send(@RequestParam String message) {
        log.info("==============");
        log.info(message);
        message= JSONObject.toJSONString(message);
        log.info("==============");
        log.info(message);
        producer.output().send(MessageBuilder.withPayload(message).build());
    }
}
