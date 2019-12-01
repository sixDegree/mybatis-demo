package com.cj.mybatis.entity;

public class EmployeeWithDepartment {
	private Integer id;
	private String name;
	private String remark;
	private Department department;
	
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "EmployeeWithDepartment [id=" + id + ", name=" + name + ", remark=" + remark + ", department="
				+ department + "]";
	}
}
