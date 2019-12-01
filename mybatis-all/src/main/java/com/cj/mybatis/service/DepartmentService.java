package com.cj.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cj.mybatis.dao.second.DepartmentMapper;
import com.cj.mybatis.entity.Department;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;

	public List<Department> listAll(){
		return this.departmentMapper.listAll();
	}
	
	@Transactional("secondTransactionManager")
	public List<Integer> insertMultipleDepartments(int count) {
		List<Integer> results=new ArrayList<>();
		for(int i=0;i<count;i++) {
			if(i==count/2) {
				throw new RuntimeException("Try to throw an exception here...");
			}
			Department dept = new Department(null,"TDept-"+i,"This is TDept-"+i);
			results.add(departmentMapper.insert(dept));
		}
		return results;
	}
}
