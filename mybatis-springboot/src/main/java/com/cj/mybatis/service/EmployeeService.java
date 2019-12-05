package com.cj.mybatis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.mapper.EmployeeMapper;
import com.cj.mybatis.mapper.EmployeeWithAnnotationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private EmployeeWithAnnotationMapper employeeWithAnnotationMapper;
	
	public void sayHello() {
		System.out.println("EmployeeService sayHello....");
	}
	
	public Employee getEmployeeUseAnnotation(Integer id) {
		return this.employeeWithAnnotationMapper.getEmployee(id);
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
	
	@Transactional
	public Integer insertEmployee(Employee emp) {
		return employeeMapper.insertEmployee(emp);
	}
	
	// For test @Transactional
	@Transactional
	public List<Integer> insertMultipleEmployees(int count) {
		List<Integer> results=new ArrayList<>();
		for(int i=0;i<count;i++) {
			if(i==count/2) {
				throw new RuntimeException("Try to throw an exception here...");
			}
			Employee emp = new Employee(null,"Test-"+i,"This is Test-"+i,3);
			results.add(employeeMapper.insertEmployee(emp));
		}
		return results;
	}
	
	@Transactional
	public Integer updateEmployee(Employee emp) {
		return employeeMapper.updateEmployee(emp);
	}
	
	@Transactional
	public Integer deleteEmployee(Integer id) {
		return employeeMapper.deleteEmployeeById(id);
	}
	
}
