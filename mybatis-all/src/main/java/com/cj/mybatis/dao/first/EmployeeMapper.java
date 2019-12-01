package com.cj.mybatis.dao.first;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cj.mybatis.entity.Employee;

@Repository
public interface EmployeeMapper {
	public List<Employee> listEmployees();
	public Employee getEmployeeById(Integer id);
	public Integer updateEmployee(Employee emp);
	public Integer insertEmployee(Employee emp);
	public Integer deleteEmployeeById(Integer id);
}
