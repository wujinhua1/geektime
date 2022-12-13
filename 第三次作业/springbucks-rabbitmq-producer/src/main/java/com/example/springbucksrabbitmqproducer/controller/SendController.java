package com.example.springbucksrabbitmqproducer.controller;

import com.example.springbucksrabbitmqproducer.integration.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Producer.class)
public class SendController {

    @Autowired
    private Producer producer;

    @GetMapping("/send")
    public void send(@RequestParam String message) {
        producer.output().send(MessageBuilder.withPayload(message).build());
    }
}
