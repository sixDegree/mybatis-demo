package com.cj.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import com.cj.mybatis.entity.Employee;

// @Mapper  // configed: @MapperScan("com.cj.mybatis.mapper") ,no need @Mapper
public interface EmployeeWithAnnotationMapper {
	
	@Select("select * from pe_employee where id=#{id}")
	public Employee getEmployee(Integer id);
}
