package com.cj.mybatis.mapper;

import com.cj.mybatis.entity.DepartmentWithEmployees;

public interface DepartmentWithEmployeesMapper {

	public DepartmentWithEmployees getDepartment(Integer id);
	
	public DepartmentWithEmployees getDepartmentLazy(Integer id);
}
