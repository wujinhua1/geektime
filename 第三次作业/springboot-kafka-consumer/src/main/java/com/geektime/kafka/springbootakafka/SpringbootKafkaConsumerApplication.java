package com.geektime.kafka.springbootakafka;

import com.geektime.kafka.springbootakafka.processor.TestInPutProcessor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(TestInPutProcessor.class)
public class SpringbootKafkaConsumerApplication {

	public static void main (String[] args) {
		SpringApplication.run (SpringbootKafkaConsumerApplication.class, args);
	}

}
