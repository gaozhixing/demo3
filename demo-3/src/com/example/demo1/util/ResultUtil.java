package com.example.demo1.util;

import com.example.demo1.model.BaseResult;
import com.example.demo1.model.ExceptionEnum;

public class ResultUtil {
	
	/**
	 * 返回成功，传入返回体具体出参
	 * @param object
	 * @return
	 */
	public static <T> BaseResult<T> success(T object) {
		return new BaseResult<T> ("000", "success", object);
	}
	
	/**
	 * 
	 * 提供给部分没有出参的接口
	 * @return
	 */
	public static <T> BaseResult<T> success() {
		return new BaseResult<T> ("000", "success");
	}
	
	/**
	 * 自定义错误信息
	 * @param resultCode
	 * @param resultMsg
	 * @return
	 */
	public static <T> BaseResult<T> error(String resultCode, String resultMsg) {
		return new BaseResult<T> (resultCode, resultMsg);
	}
	
	/**
	 * 返回已知范围内的异常
	 * @param exception
	 * @return
	 */
	public static <T> BaseResult<T> error(ExceptionEnum exception) {
		return new BaseResult<T> (exception.getCode(), exception.getMsg());
	}
	
	
}
