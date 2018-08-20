package com.example.demo1.model;

public enum ExceptionEnum {
	UNKONOW_ERROR("-1","未知错误"),USER_NOT_FIND("-101","用户不存在"),REQUEST_IS_NULL("-102","查询结果为空");
	private String code;
	private String msg;
	ExceptionEnum(String code, String msg){
		this.code = code;;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
