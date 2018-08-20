package com.example.demo1.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo1.model.BaseResult;
import com.example.demo1.model.DescException;
import com.example.demo1.model.ExceptionEnum;
/**
 * 统一异常处理，在Spring扫描范围内实现全局异常回调
 * @author Administrator
 *
 */
@ControllerAdvice
public class ExceptionHandle {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
	public BaseResult executeException(Exception e) {
		//如果是已知异常
		if (e instanceof DescException) {
			DescException exception = (DescException)e;
			return ResultUtil.error(exception.getCode(),exception.getMessage());
		}
		//如果是未知异常
		logger.error("系统异常{}",e);
		return ResultUtil.error(ExceptionEnum.UNKONOW_ERROR);
	}
	

}
