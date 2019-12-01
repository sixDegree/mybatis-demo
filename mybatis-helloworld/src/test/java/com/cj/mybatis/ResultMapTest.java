package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.DepartmentWithEmployees;
import com.cj.mybatis.entity.EmployeeWithDepartment;
import com.cj.mybatis.entity.UserRole;
import com.cj.mybatis.mapper.DepartmentWithEmployeesMapper;
import com.cj.mybatis.mapper.EmployeeWithDepartmentMapper;
import com.cj.mybatis.mapper.UserRoleMapper;

public class ResultMapTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config2.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/*
	 * EmployeeWithDepartmentMapper.xml 
	 * => resultMap - association
	 */
	@Test
	public void testEmployeeWithDepartment() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeWithDepartmentMapper edMapper = session.getMapper(EmployeeWithDepartmentMapper.class);
			
			System.out.println("------------getEmployee------------");
			EmployeeWithDepartment emp = edMapper.getEmployee(1);
			System.out.println(emp);
			
			System.out.println("------------getEmployeeLazy------------");
			emp = edMapper.getEmployeeLazy(1);
			System.out.println(emp.getName());
			System.out.println(emp.getDepartment());
			System.out.println(emp);
		}
	}
	
	/*
	 * DepartmentWithEmployeesMapper.xml 
	 * => resultMap - collection
	 */
	@Test
	public void testDepartmentWithEmployees() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentWithEmployeesMapper deMapper = session.getMapper(DepartmentWithEmployeesMapper.class);
			
			System.out.println("------------getDepartment------------");
			DepartmentWithEmployees dept = deMapper.getDepartment(1);
			System.out.println(dept);
			
			System.out.println("------------getDepartmentLazy------------");
			dept = deMapper.getDepartmentLazy(1);
			System.out.println(dept.getName());
			System.out.println(dept.getEmployees());
		}
	}
	
	/*
	 * UserRoleMapper.xml 
	 * => resultMap - discriminator
	 */
	@Test
	public void testUserRole() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			UserRoleMapper urMapper = session.getMapper(UserRoleMapper.class);
			
			System.out.println("------------listUserRole------------");
			List<UserRole>	list = urMapper.listUserRoles();
			System.out.println(list);
			
			System.out.println("------------listUserRoleWithDetails------------");
			list = urMapper.listUserRoleWithDetails();
			for(UserRole ur : list) {
				System.out.println(ur);
			}
		}
	}
	
}
