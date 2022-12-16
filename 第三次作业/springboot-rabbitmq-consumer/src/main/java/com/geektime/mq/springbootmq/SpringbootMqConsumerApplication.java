package com.geektime.mq.springbootmq;

import com.geektime.mq.springbootmq.processor.TestInPutProcessor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(value = {TestInPutProcessor.class})
public class SpringbootMqConsumerApplication {

	public static void main (String[] args) {
		SpringApplication.run (SpringbootMqConsumerApplication.class, args);
	}

}
