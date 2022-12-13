package com.example.springbucksrabbitmqproducer;

import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * author:詹围超
 * date:2022/7/4
 */
@SpringBootApplication
public class SpringbucksRabbitmqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbucksRabbitmqProducerApplication.class, args);
    }

}
