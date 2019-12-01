package com.cj.mybatis.dao.second;

import java.util.List;

import com.cj.mybatis.entity.Department;

public interface DepartmentMapper {
	
	public List<Department> listAll();
	
	public Integer updateById(Integer id);
	
	public Integer insert(Department dept);
}
