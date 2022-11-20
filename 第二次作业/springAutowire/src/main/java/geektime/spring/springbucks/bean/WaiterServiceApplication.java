package geektime.spring.springbucks.bean;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jackson.*;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.*;

import java.util.*;

@SpringBootApplication
@EnableCaching
public class WaiterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaiterServiceApplication.class, args);
	}



	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
		return builder -> {
			builder.indentOutput(true);
			builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		};
	}
}
