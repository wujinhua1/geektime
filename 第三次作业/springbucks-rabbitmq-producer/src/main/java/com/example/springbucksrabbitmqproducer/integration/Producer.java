package com.example.springbucksrabbitmqproducer.integration;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Producer {

    String OUTPUT = "msg_output";

    @Output(Producer.OUTPUT)
    MessageChannel output();

}