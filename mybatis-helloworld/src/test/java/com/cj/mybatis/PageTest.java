package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.mapper.EmployeeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class PageTest {
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config2.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testPage01() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

			System.out.println("------------listEmployees------------");
			Page<Object> page = PageHelper.startPage(2, 4);
			List<Employee> employees = employeeMapper.listEmployees();
			for(Employee emp:employees) {
				System.out.println(emp);
			}
			System.out.println("Current Page:\t"+page.getPageNum());
			System.out.println("Page Size:\t"+page.getPageSize());
			System.out.println("Total Pages:\t"+page.getPages());
			System.out.println("Total Records:\t"+page.getTotal());
		}
	}
	
	@Test
	public void testPage02() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

			System.out.println("------------listEmployees------------");
			PageHelper.startPage(1, 4);
			List<Employee> employees = employeeMapper.listEmployees();
			PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
			
			System.out.println("Current Page:\t"+pageInfo.getPageNum());
			System.out.println("Page Size:\t"+pageInfo.getPageSize());
			System.out.println("Total Pages:\t"+pageInfo.getPages());
			System.out.println("Total Records:\t"+pageInfo.getTotal());
			
			System.out.print("List Nav Pages:\t");
			for(int i : pageInfo.getNavigatepageNums()) {
				System.out.print(i+" ");
			}
			System.out.println();
			
			System.out.println("List Records:");
			List<Employee> results=pageInfo.getList();
			for(Employee emp : results) {
				System.out.println(emp);
			}
			System.out.println(results);
		}
	}
	
}
