package geektime.spring.springbucks.waiter;

import geektime.spring.springbucks.waiter.model.*;
import geektime.spring.springbucks.waiter.service.*;
import org.springframework.context.support.*;

import java.util.*;

public class ApplicationContextStarter {
	public static void main (String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext ("applicationContext.xml");
		CoffeeService coffeeService = applicationContext.getBean (CoffeeService.class);
		Coffee coffee = coffeeService.getNewCoffee();
		System.out.println ("普通setter" + coffee);

		
	}
}
