package com.cj.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cj.mybatis.dao.first.EmployeeMapper;
import com.cj.mybatis.entity.Employee;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	public void sayHello() {
		System.out.println("EmployeeService sayHello....");
	}
	
	public List<Employee> listAll(){
		return employeeMapper.listEmployees();
	}
	
	public PageInfo<Employee> listByPage(Integer pageNum,Integer pageSize){
		PageHelper.startPage(pageNum, pageSize);
		List<Employee> list = employeeMapper.listEmployees();	
		return new PageInfo<>(list);
	}
	
	public Employee getEmployee(Integer id) {
		return employeeMapper.getEmployeeById(id);
	}
	
	@Transactional("firstTransactionManager")
	public Integer insertEmployee(Employee emp) {
		return employeeMapper.insertEmployee(emp);
	}
	
	// For test @Transactional
	@Transactional("firstTransactionManager")
	public List<Integer> insertMultipleEmployees(int count) {
		List<Integer> results=new ArrayList<>();
		for(int i=0;i<count;i++) {
			if(i==count/2) {
				throw new RuntimeException("Try to throw an exception here...");
			}
			Employee emp = new Employee(null,"TEmp-"+i,"This is TEmp-"+i,3);
			results.add(employeeMapper.insertEmployee(emp));
		}
		return results;
	}
	
	@Transactional("firstTransactionManager")
	public Integer updateEmployee(Employee emp) {
		return employeeMapper.updateEmployee(emp);
	}
	
	@Transactional("firstTransactionManager")
	public Integer deleteEmployee(Integer id) {
		return employeeMapper.deleteEmployeeById(id);
	}
	
}
