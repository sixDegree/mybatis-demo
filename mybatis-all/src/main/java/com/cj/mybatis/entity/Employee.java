package com.cj.mybatis.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Employee Model")
public class Employee {
	
	@ApiModelProperty("唯一标识")
	private Integer id;
	
	@ApiModelProperty("名称")
	private String name;
	
	@ApiModelProperty("备注")
	private String remark;
	
	@ApiModelProperty("部门Id")
	private Integer departmentId;
	
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
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", remark=" + remark + ", departmentId=" + departmentId + "]";
	}
}
