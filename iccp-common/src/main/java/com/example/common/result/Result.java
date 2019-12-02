package com.example.common.result;

import java.io.Serializable;

/**
 * @Description: 接口统一返回格式
 * @Author: yaos
 * @Date: 2019-11-29 09:50
 * @Version: V1.0
 **/
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer statusCode;
	
	private T data;
	
	private long timestamp = System.currentTimeMillis();
	
	public Result() {
		
	}
	
	public static Result<String> ok(String data) {
		Result<String> r = new Result<String>();
		r.setData(data);
		r.setStatusCode(200);
		return r;
	}
	
	public static <T> Result<T> ok(T data) {
		Result<T> r = new Result<T>();
		r.setData(data);
		r.setStatusCode(200);
		return r;
	}
	
	public static Result<String> error(String msg) {
		return error(500, msg);
	}
	
	public static <T> Result<T> error(int code, T data) {
		Result<T> r = new Result<T>();
		r.setData(data);
		r.setStatusCode(code);
		return r;
	}
	
	public static Result<String> error(int code, String msg) {
		Result<String> r = new Result<String>();
		r.setData(msg);
		r.setStatusCode(code);
		return r;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	
}
