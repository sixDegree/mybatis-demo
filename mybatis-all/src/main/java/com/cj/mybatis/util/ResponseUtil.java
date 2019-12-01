package com.cj.mybatis.util;

public class ResponseUtil {

	private boolean success;
	private Object data;
	
	public ResponseUtil(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}
	
	public static ResponseUtil result(boolean success,Object data) {
		return new ResponseUtil(success, data);
	}
	
	public static ResponseUtil ok(Object data) {
		return new ResponseUtil(true,data);
	}
	
	public static ResponseUtil fail(Object data) {
		return new ResponseUtil(false,data);
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
