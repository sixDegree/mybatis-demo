package com.cj.mybatis.entity;

public class UserRole {
	private Integer id;
	private Integer userId;
	private UserTypeEnum userType;
	private String roleName;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public UserTypeEnum getUserType() {
		return userType;
	}
	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}



	private String name;
	private String remark;
	
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
		return "UserRole [id=" + id + ", userId=" + userId + ", userType=" + userType + ", roleName=" + roleName
				+ ", name=" + name + ", remark=" + remark + "]";
	}
}
