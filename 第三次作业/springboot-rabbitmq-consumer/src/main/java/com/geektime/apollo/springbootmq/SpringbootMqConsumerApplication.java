package com.geektime.apollo.springbootmq;

import com.geektime.apollo.springbootmq.processor.TestInPutProcessor;
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
