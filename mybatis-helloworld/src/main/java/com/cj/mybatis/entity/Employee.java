package com.cj.mybatis.entity;

//@Alias("emp")
public class Employee {
	
	private Integer id;
	private String name;
	private String remark;
	private Integer departmentId;
	private EmployeeStatusEnum status;
	
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
	
	public EmployeeStatusEnum getStatus() {
		return status;
	}
	public void setStatus(EmployeeStatusEnum status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", remark=" + remark + ", departmentId=" + departmentId
				+ ", status=" + status + "]";
	}

}
