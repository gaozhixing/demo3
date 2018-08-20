package com.example.demo1.model;
/**
 * 系统返回的异常信息一般是没有异常Code的(404,500等除外),继承RuntimeException增加code字段来自定义错误信息
 * @author Administrator
 *
 */
public class DescException extends RuntimeException{
	private String code;
	public DescException(ExceptionEnum exception) {
		super(exception.getMsg());
		this.code = exception.getCode();
	}
	
	public DescException(String msg, String code) {
		super(msg);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
