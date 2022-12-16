package com.geektime.kafka.springbootkafka;

import com.geektime.kafka.springbootkafka.processor.TestOutPutProcessor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(TestOutPutProcessor.class)
public class SpringbootKafkaProviderApplication {

	public static void main (String[] args) {
		SpringApplication.run (SpringbootKafkaProviderApplication.class, args);
	}

}
