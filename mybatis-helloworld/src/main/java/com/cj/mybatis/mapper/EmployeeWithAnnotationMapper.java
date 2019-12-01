package com.cj.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import com.cj.mybatis.entity.Employee;

public interface EmployeeWithAnnotationMapper {
	
	@Select("select * from pe_employee where id=#{id}")
	public Employee getEmployee(Integer id);
}
