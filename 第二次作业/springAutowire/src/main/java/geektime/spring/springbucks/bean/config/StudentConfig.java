package geektime.spring.springbucks.bean.config;

import geektime.spring.springbucks.bean.model.*;
import org.springframework.context.annotation.*;

@Configuration
public class StudentConfig {

	@Bean
	public Student myStudent(){
		return new Student ("张三","F");
	}
}
