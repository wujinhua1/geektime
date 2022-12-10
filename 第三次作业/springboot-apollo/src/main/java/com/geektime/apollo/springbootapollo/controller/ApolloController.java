package com.geektime.apollo.springbootapollo.controller;

import com.ctrip.framework.apollo.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
public class ApolloController {


//	@Value ("${name}")
//	private String name;
//	@Value ("${code}")
//	private String code;
//
//
//	@GetMapping("apollo1")
//	public void testApollo1(){
//		log.info ("name: {}",name);
//		log.info ("code: {}",code);
//	}

	/**
	 *
	 */
	@GetMapping("apollo2")
	public void testApollo2(){
		Config config = ConfigService.getAppConfig();
		String name1 = config.getProperty("name", "");
		String code1 = config.getProperty("code", "");
		log.info ("name: {}",name1);
		log.info ("code: {}",code1);
	}

	@GetMapping("apollo3")
	public void testApollo3(){
		Config appnamespace = ConfigService.getConfig("application");
		String name2 = appnamespace.getProperty("name2", "");
		String code2 = appnamespace.getProperty("code2", "");
		log.info ("name: {}",name2);
		log.info ("code: {}",code2);
	}
}
