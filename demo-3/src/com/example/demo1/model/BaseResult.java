package com.example.demo1.model;

public class BaseResult<T> {
	
	private String resultCode;
	private String resultMsg;
	private T data;
	
	public BaseResult() {
	}
	public BaseResult(String resultCode, String resultMsg, T data) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
		this.data = data;
	}
	public BaseResult(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	

}
