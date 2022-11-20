package geektime.spring.springbucks;

import geektime.spring.springbucks.service.*;
import lombok.extern.slf4j.*;
import org.mybatis.spring.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.annotation.*;
import org.springframework.transaction.annotation.*;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
@EnableCaching (proxyTargetClass = true)
@MapperScan ("geektime.spring.springbucks.mapper")
public class SpringBucksApplication implements ApplicationRunner {

	@Autowired
	private CoffeeService coffeeService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBucksApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {


	}

}

