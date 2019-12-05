package com.cj.mybatis.mapper;

import java.util.List;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.entity.MyPageInfo;

public interface EmployeeMapper {
	
	public Employee getEmployee(Integer id);
	public List<Employee> listEmployees();
	public List<Employee> listEmployeesByDept(Integer deptId);
	
	public List<Employee> listEmployeesByExample(Employee employee);
	public Integer updateEmployee(Employee employee);
	public List<Employee> listEmployeesByNameLike(String name);
	public List<Employee> listEmployeesByDeptIds(List<Integer> deptIds);
	
	public Integer insertEmployee(Employee employee);
	
	// for test self defined Enum TypeHandler
	public Integer insertEmployeeOnMyEnum(Employee employee);
	public List<Employee> listEmployeesOnMyEnum();
	
	// for test procedure
	public List<Employee> getPageResult(MyPageInfo page);

}
