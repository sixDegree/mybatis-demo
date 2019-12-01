package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.mapper.EmployeeMapper;

public class DynamicSqlTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config2.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testDynamicFilter() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

			System.out.println("------------listEmployeesByExample------------");
			Employee sample = new Employee();
			sample.setName("Test1");
			List<Employee> employees = employeeMapper.listEmployeesByExample(sample);
			System.out.println(employees);
		}
	}
	
	@Test
	public void testDynamicUpdate() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

			System.out.println("------------updateEmployee------------");
			Employee sample = new Employee();
			sample.setId(9);
			sample.setName("TestAB9");
			sample.setRemark("This is Employee TestAB9");
			Integer result= employeeMapper.updateEmployee(sample);
			System.out.println(result);
			
			session.commit();
		}
	}
	
	@Test
	public void testBind() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

			System.out.println("------------listEmployeesByNameLike------------");
			List<Employee> employees= employeeMapper.listEmployeesByNameLike("Test");
			for(Employee emp : employees) {
				System.out.println(emp);
			}
		}
	}
	
	@Test
	public void testForeach() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

			System.out.println("------------listEmployeesByDeptIds------------");
			List<Employee> employees= employeeMapper.listEmployeesByDeptIds(Arrays.asList(1,3,5));
			for(Employee emp : employees) {
				System.out.println(emp);
			}
		}
	}
}
