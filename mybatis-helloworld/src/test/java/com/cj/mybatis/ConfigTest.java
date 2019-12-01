package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.Department;
import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.mapper.DepartmentMapper;
import com.cj.mybatis.mapper.EmployeeMapper;
import com.cj.mybatis.mapper.EmployeeWithAnnotationMapper;

public class ConfigTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config2.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testDepartment() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			Department dept = departmentMapper.getDepartment(1);
			System.out.println(dept);
		}
	}
	
	@Test
	public void testEmployee() throws IOException{
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			Employee emp = employeeMapper.getEmployee(1);
			System.out.println(emp);
		}
	}
	
	@Test
	public void testEmployeeWithAnnotation() throws IOException{
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeWithAnnotationMapper employeeAnnotationMapper = session.getMapper(EmployeeWithAnnotationMapper.class);
			Employee emp = employeeAnnotationMapper.getEmployee(1);
			System.out.println(emp);
		}
	}
}
