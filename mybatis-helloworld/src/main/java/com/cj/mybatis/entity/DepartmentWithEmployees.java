package com.cj.mybatis.entity;

import java.util.List;

public class DepartmentWithEmployees {
	
	private Integer id;
	private String name;
	private String remark;
	
	private List<Employee> employees;

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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "DepartmentWithEmployees [id=" + id + ", name=" + name + ", remark=" + remark + ", employees="
				+ employees + "]";
	}

}
