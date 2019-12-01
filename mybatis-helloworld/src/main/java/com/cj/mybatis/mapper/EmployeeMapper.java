package com.cj.mybatis.mapper;

import java.util.List;

import com.cj.mybatis.entity.Employee;

public interface EmployeeMapper {
	
	public Employee getEmployee(Integer id);
	public List<Employee> listEmployees();
	public List<Employee> listEmployeesByDept(Integer deptId);
	
	public List<Employee> listEmployeesByExample(Employee employee);
	public Integer updateEmployee(Employee employee);
	public List<Employee> listEmployeesByNameLike(String name);
	public List<Employee> listEmployeesByDeptIds(List<Integer> deptIds);
	
	
	
}
