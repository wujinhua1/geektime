package com.geektime.apollo.springbootapollo.controller;

import com.ctrip.framework.apollo.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class ApolloController {


	@Value ("${name:AA}")
	private String name;
	@Value ("${sex:F}")
	private String sex;

	@Value ("${age:13}")
	private String age;

	/**
	 * Placeholder方式
	 */

	@GetMapping("apollo1")
	public void testApollo1(){
		log.info ("name: {}",name);
		log.info ("sex: {}",sex);
		log.info ("age: {}",age);
	}

	/**
	 * 通过API调用
	 */
	@GetMapping("apollo2")
	public void testApollo2(){
		Config config = ConfigService.getAppConfig();
		String name1 = config.getProperty("name", "");
		String age = config.getProperty("age", "");
		String sex = config.getProperty("sex", "");
		log.info ("name: {}",name1);
		log.info ("age: {}",age);
		log.info ("sex: {}",sex);

	}

	/**
	 * yaml/yml格式的namespace
	 */
	@GetMapping("apollo3")
	public void testApollo3(){
		Config config = ConfigService.getConfig("testspace.yaml");
		String property = config.getProperty ("mybatis-plus.mapper-locations", "");
		String property2 = config.getProperty ("mybatis-plus.global-config.db-config.id-type", "");
		log.info ("property: {}",property);
		log.info ("property2: {}",property2);

	}
}
