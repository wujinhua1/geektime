package geektime.spring.springbucks.bean;

import geektime.spring.springbucks.bean.config.*;
import geektime.spring.springbucks.bean.model.*;
import org.springframework.beans.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.support.*;

public class SpringAutoBean {
	public static void main (String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext ("applicationContext-set.xml");
		Coffee coffee = applicationContext.getBean (Coffee.class);
		System.out.println ("set注入" + coffee);


		ApplicationContext constructContext = new ClassPathXmlApplicationContext ("applicationContext-constructor.xml");
		Coffee coffee1 = constructContext.getBean (Coffee.class);
		System.out.println ("构造方法注入" + coffee1);


		ApplicationContext context = new AnnotationConfigApplicationContext (StudentConfig.class);
		Student student = context.getBean ("myStudent", Student.class);
		System.out.println ("@Configuration + @Bean 注入" + student);

		ApplicationContext annoContext = new ClassPathXmlApplicationContext ("applicationContext-anno.xml");
		Prodcut bean = annoContext.getBean (Prodcut.class);
		System.out.println ("注解注入" + bean);

		ApplicationContext staticContext = new ClassPathXmlApplicationContext ("applicationContext-static-factory.xml");
		Coffee coff = (Coffee) staticContext.getBean ("coff");
		System.out.println ("静态工厂注入" + coff);


		ApplicationContext instContext = new ClassPathXmlApplicationContext ("applicationContext-instance-factory.xml");
		Coffee instCoff = (Coffee) instContext.getBean ("instCoff");
		System.out.println ("实例工厂注入" + instCoff);

	}


}
