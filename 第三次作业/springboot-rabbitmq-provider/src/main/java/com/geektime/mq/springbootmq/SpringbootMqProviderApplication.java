package com.geektime.mq.springbootmq;

import com.geektime.mq.springbootmq.processor.TestOutPutProcessor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(value = {TestOutPutProcessor.class})
public class SpringbootMqProviderApplication {

	public static void main (String[] args) {
		SpringApplication.run (SpringbootMqProviderApplication.class, args);
	}

}
