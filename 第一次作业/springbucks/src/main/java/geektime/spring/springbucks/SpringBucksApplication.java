package geektime.spring.springbucks;

import com.alibaba.druid.filter.*;
import com.alibaba.druid.proxy.jdbc.*;
import com.alibaba.druid.sql.*;
import com.alibaba.druid.sql.ast.*;
import com.alibaba.druid.sql.dialect.mysql.parser.*;
import com.alibaba.druid.sql.dialect.mysql.visitor.*;
import com.alibaba.druid.stat.*;
import geektime.spring.springbucks.model.*;
import geektime.spring.springbucks.service.*;
import lombok.extern.slf4j.*;
import org.apache.ibatis.session.*;
import org.joda.money.*;
import org.mybatis.spring.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cache.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

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

		List<Coffee> allCoffee = coffeeService.getAllCoffee (null);
		log.info ("查询所有咖啡111: {}",allCoffee);

		Coffee coffee = new Coffee ();
		coffeeService.saveCoffee(coffee.withName("拿铁")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 9.9))
				.withCreateTime(new Date())
				.withUpdateTime(new Date()));
		coffeeService.saveCoffee(coffee.withName("美式")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 39.5))
				.withCreateTime(new Date())
				.withUpdateTime(new Date()));
		coffeeService.saveCoffee(coffee.withName("奶茶")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 25.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date()));
		coffeeService.saveCoffee(coffee.withName("可口可乐")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 8.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date ()));


//		//分页查询
//		List<Coffee> coffeeByPage = coffeeService.findCoffeeByPage (null, new RowBounds (0, 3));
//		log.info ("coffeeByPage :{}" , coffeeByPage);

//		从缓存查询
		List<Coffee> coffees = coffeeService.getAllCoffee (null);
		log.info ("查询所有咖啡222: {}",coffees);

		//根据ID查询
		Coffee coffee1 = coffeeService.getCoffeeById (2L);
		log.info ("{根据ID查询: {}}",coffee1);

		//更新ID为2的数据
		Coffee coffee2 = new Coffee ();
		coffee2.setId (Long.valueOf (2L));
		coffee2.setName ("雪碧");
		coffee2.setUpdateTime (new Date ());
		coffee2.setPrice (Money.of(CurrencyUnit.of("CNY"), 2.5));
		Coffee coffee3 = coffeeService.updateCoffee (coffee2);
		log.info ("更新后的数据: {}",coffee3);

		//根据ID批量查询

		CoffeeExample example = new CoffeeExample ();
		example.createCriteria ().andIdIn (Arrays.asList (1L, 2L));
		List<Coffee> coffees1 = coffeeService.batchCoffee (example);
		log.info ("[批量查询：{}]",coffees1);

		//清除缓存
		coffeeService.reloadCoffee ();

		coffeeService.deleteCoffee (Long.valueOf (3L));

		List<Coffee> coffeeByPage1 = coffeeService.findCoffeeByPage (null, new RowBounds (0, 5));
		log.info ("缓存清除后从数据库中查询剩余数据 :{}" , coffeeByPage1);


		//sql校验，写在这里为了测试方便，真正在geektime.spring.springbucks.filter.ConnectionFilter路径下
		String sqls = "select * from t_coffee where id = 1;select * from t_coffee where id = 2;";
		String sqlss = "select * from t_coffee where id in(1,2,3,4,5)";

		MySqlStatementParser parser = new MySqlStatementParser(sqlss);
		List<SQLStatement> stmtList = parser.parseStatementList();
		System.out.println ("stmtList = " + stmtList);
		if(!CollectionUtils.isEmpty (stmtList)){
			if(10 < stmtList.size ()){
				throw new SQLException (String.format ("SQL 分割条数为 %s,超过10条限制", Integer.valueOf (stmtList.size ())));
			}

			MySqlSchemaStatVisitor statVisitor = new MySqlSchemaStatVisitor ();
			for (SQLStatement sqlStatement : stmtList) {
				sqlStatement.accept (statVisitor);
				for (TableStat.Condition condition : statVisitor.getConditions ()) {
					System.out.println ("statVisitor = " + statVisitor.getConditions ());
					System.out.println ("condition = " + condition);
					System.out.println ("condition = " + condition.getValues ());
					if(CollectionUtils.isEmpty (condition.getValues ()) || 10 > condition.getValues ().size ()){
						continue;
					}
					throw new SQLException(String.format ("入参个数：%s,超过10条限制",
							Integer.valueOf (condition.getValues ().size ())));
				}
			}

		}

	}


}

