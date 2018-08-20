package com.example.demo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.serviceInf.AsyncService;

@RestController
@RequestMapping("/thread")
public class ThreadTestController {
	Logger logger = LoggerFactory.getLogger(ThreadTestController.class);
	
	@Autowired 
	private AsyncService asyncService ;
	
	@RequestMapping("/test")
	public String threadTest() {
		logger.info("start");
		asyncService.executeAsync();
		return "sucess";
	}
}
