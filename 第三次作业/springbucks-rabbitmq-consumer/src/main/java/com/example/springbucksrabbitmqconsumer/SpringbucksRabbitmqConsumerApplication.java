package com.example.springbucksrabbitmqconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;


@SpringBootApplication
@Slf4j
public class SpringbucksRabbitmqConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbucksRabbitmqConsumerApplication.class, args);
    }

}
