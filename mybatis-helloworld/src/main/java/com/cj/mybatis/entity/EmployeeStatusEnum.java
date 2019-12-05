package com.cj.mybatis.entity;

public enum EmployeeStatusEnum{

	NORMAL(100,"正常"),CANCEL(200,"注销"),DELETE(300,"删除");
	
	private Integer code;
	private String msg;
	
	private EmployeeStatusEnum(Integer code,String msg) {
		this.code=code;
		this.msg=msg;
	}

	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
