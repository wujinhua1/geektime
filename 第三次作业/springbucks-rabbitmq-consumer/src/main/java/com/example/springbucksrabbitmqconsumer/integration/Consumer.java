package com.example.springbucksrabbitmqconsumer.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Consumer {
    String INPUT = "msg_input";

    @Input(Consumer.INPUT)
    SubscribableChannel input();

}