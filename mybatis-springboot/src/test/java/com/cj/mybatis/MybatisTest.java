package com.cj.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.service.EmployeeService;
import com.github.pagehelper.PageInfo;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisTest {
	
	@Autowired
	EmployeeService employeeService;
	
	@Test
	public void testService() {
		employeeService.sayHello();
	}
	
	@Test
	public void testListAll() {
		List<Employee> list = employeeService.listAll();
		System.out.println(list);
	}
	
	@Test
	public void testListByPage() {
		PageInfo<Employee> result = employeeService.listByPage(1,4);
		System.out.println(result);
	}
	
	@Test
	public void testGet() {
		Employee emp = employeeService.getEmployee(1);
		System.out.println(emp);
		
		emp=employeeService.getEmployeeUseAnnotation(1);
		System.out.println(emp);
	}
	
	@Test
	public void testInsert() {
		Employee emp = new Employee(null,"Test-AA","This is Employee AA",3);
		Integer result = employeeService.insertEmployee(emp);
		System.out.println(result);
		System.out.println(emp);
	}
	
	@Test
	public void testUpdate() {
		Employee emp = new Employee(17,"Test-CC","This is Employee CC",3);
		Integer result =  employeeService.updateEmployee(emp);
		System.out.println(result);
	}
	
	@Test
	public void testDelete() {
		Integer result = employeeService.deleteEmployee(17);
		System.out.println(result);
	}
	
	//========================================
	
	// Test for @Transactional
	
	@Test
	public void testInsertMultiple() {
		List<Integer> results = employeeService.insertMultipleEmployees(5);
		System.out.println(results);
	}
	
}
