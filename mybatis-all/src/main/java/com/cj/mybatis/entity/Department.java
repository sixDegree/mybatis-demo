package com.cj.mybatis.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Department Model")
public class Department {
	
	@ApiModelProperty("唯一标识")
	private Integer id;
	
	@ApiModelProperty("名称")
	private String name;
	
	@ApiModelProperty("备注")
	private String remark;
	
	
	public Department() {
		
	}
	
	public Department(String name,String remark) {
		this.name=name;
		this.remark=remark;
	}
	
	public Department(Integer id, String name, String remark) {
		this.id = id;
		this.name = name;
		this.remark = remark;
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
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}
}
