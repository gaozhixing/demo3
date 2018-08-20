package com.example.demo1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo1.serviceInf.AsyncService;

@Service
public class AsyncServiceImpl implements AsyncService {

	private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
	@Override
	@Async("asyncServiceExecutor")
	public void executeAsync() {
		// TODO Auto-generated method stub
		logger.info("测试demo");
		try {
			System.out.println("start");
			Thread.sleep(1000);
			System.out.println("start");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
