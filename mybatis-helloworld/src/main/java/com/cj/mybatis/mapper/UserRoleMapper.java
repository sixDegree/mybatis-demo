package com.cj.mybatis.mapper;

import java.util.List;

import com.cj.mybatis.entity.UserRole;

public interface UserRoleMapper {

	public List<UserRole> listUserRoles();
	
	public List<UserRole> listUserRoleWithDetails();
	
	public Integer insertUserRole(UserRole userRole);
	
	// For test TypeHandler: EnumOrdinalTypeHandler
	public Integer insertOnEnumOrdinal(UserRole userRole);
	public List<UserRole> listOnEnumOrdinal();
}
