package com.cj.mybatis.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Employee {
	private Integer id;
	private String name;
	private String remark;
	private Integer departmentId;
	
	/*
	 show variables like '%time_zone';
	 system_time_zone null
	 time_zone		  SYSTEM
	 */
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	public Employee() {
		
	}
	
	public Employee(Integer id, String name, String remark, Integer departmentId) {
		this.id = id;
		this.name = name;
		this.remark = remark;
		this.departmentId = departmentId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", remark=" + remark + ", departmentId=" + departmentId
				+ ", updateTime=" + updateTime + "]";
	}
	
}
