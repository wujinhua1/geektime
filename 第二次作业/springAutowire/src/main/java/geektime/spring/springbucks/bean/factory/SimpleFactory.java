package geektime.spring.springbucks.bean.factory;

import geektime.spring.springbucks.bean.model.*;

public class SimpleFactory {

	/**
	 * 静态工厂
	 * @return
	 */
	public static Coffee getCoffee(){
		return new Coffee (11,"奶茶",34.5);
	}


	/**
	 * 实例工厂
	 * @return
	 */
	public Coffee getCoff(){
		return new Coffee (12,"雪碧",5.5);
	}
}
