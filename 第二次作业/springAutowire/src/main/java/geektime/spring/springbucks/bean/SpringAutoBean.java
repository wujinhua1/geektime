package geektime.spring.springbucks.bean;

import geektime.spring.springbucks.bean.model.*;
import org.springframework.beans.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

public class SpringAutoBean implements ApplicationContextAware{
	private static ApplicationContext applicationContext = null;
	public static void main (String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext ("applicationContext-set.xml");
		Coffee coffee = applicationContext.getBean (Coffee.class);
		System.out.println ("set注入" + coffee);


		ApplicationContext constructContext = new ClassPathXmlApplicationContext ("applicationContext-constructor.xml");
		Coffee coffee1 = constructContext.getBean (Coffee.class);
		System.out.println ("构造方法注入" + coffee1);





	}

	@Override
	public void setApplicationContext (ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
