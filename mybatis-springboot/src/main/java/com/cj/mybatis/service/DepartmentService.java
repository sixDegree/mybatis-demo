package com.cj.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cj.mybatis.entity.Department;
import com.cj.mybatis.mapper.DepartmentMapper;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;

	public List<Department> listAll(){
		return this.departmentMapper.listAll();
	}
}
