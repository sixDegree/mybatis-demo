package com.cj.mybatis.mapper;

import com.cj.mybatis.entity.EmployeeWithDepartment;

public interface EmployeeWithDepartmentMapper {

	public EmployeeWithDepartment getEmployee(Integer id);
	
	public EmployeeWithDepartment getEmployeeLazy(Integer id);
}
