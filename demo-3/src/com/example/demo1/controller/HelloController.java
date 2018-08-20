package com.example.demo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping("/")
	public String demo() {
		logger.info("hello日志");
		return "hello";
	}
	
	@RequestMapping("/hello/{name}")
	public String demo2(@PathVariable String name) {
		logger.info("欢迎你"+name);
		return "欢迎你"+name;
	}

}
