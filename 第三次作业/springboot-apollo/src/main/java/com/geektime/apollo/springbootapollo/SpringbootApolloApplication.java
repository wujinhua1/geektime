package com.geektime.apollo.springbootapollo;

import com.ctrip.framework.apollo.spring.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableApolloConfig
@SpringBootApplication
public class SpringbootApolloApplication {

	public static void main (String[] args) {
		SpringApplication.run (SpringbootApolloApplication.class, args);
	}

}
