package geektime.spring.springbucks.bean;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jackson.*;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringBootApplication
@EnableCaching
public class SpringBeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBeanApplication.class, args);
	}





}
