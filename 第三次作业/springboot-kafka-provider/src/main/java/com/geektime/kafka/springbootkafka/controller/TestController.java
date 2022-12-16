package com.geektime.kafka.springbootkafka.controller;

import com.geektime.kafka.springbootkafka.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("kafka")
public class TestController {
    @Autowired
    MessageProducer producer;

    @GetMapping("test")
    public void sendMsg(@RequestParam("message") String message){
        producer.sendMsg(message);
    }
}
