package geektime.spring.springbucks.controller;


import geektime.spring.springbucks.model.*;
import geektime.spring.springbucks.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CoffeeController {
	@Autowired
	private CoffeeService coffeeService;

	/**
	 * 设置produces
	 *
	 * @return
	 */

	@RequestMapping(value = "/allByJson",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Coffee> getAllCoffeeJson(){
		List<Coffee> allCoffee = coffeeService.getAllCoffee (new CoffeeExample ());
		return allCoffee;
	}

	@RequestMapping(value = "/allByXml",produces=MediaType.APPLICATION_XML_VALUE)
	public List<Coffee> getAllCoffeeXml(){
		List<Coffee> allCoffee = coffeeService.getAllCoffee (new CoffeeExample ());
		return allCoffee;
	}


	/**
	 * 设置header的Content-Type
	 */

	@RequestMapping(value = "/getAllCoffee/json")
	public ResponseEntity getCoffeeJSonById(){
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", "application/json");
		List<Coffee> allCoffee = coffeeService.getAllCoffee (new CoffeeExample ());
		return new ResponseEntity<>(allCoffee,header,HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllCoffee/xml")
	public ResponseEntity getCoffeeXmlById(){
		HttpHeaders header = new HttpHeaders();
		header.set("Content-Type", "application/xml");
		List<Coffee> allCoffee = coffeeService.getAllCoffee (new CoffeeExample ());
		return new ResponseEntity<>(allCoffee,header,HttpStatus.OK);
	}

}
