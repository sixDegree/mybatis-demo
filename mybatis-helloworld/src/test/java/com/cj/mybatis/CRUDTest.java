package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.Department;
import com.cj.mybatis.mapper.DepartmentMapper;

public class CRUDTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config2.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testQueryDepartment() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			System.out.println("------------listDepartment------------");
			List<Department> list = departmentMapper.listDepartment();
			System.out.println(list);
			
			System.out.println("------------getDepartment------------");
			Department dept = departmentMapper.getDepartment(1);
			System.out.println(dept);
		}
	}
	
	// CUD 可返回类型：Integer/Long/Boolean 受影响的行数/是否成功
	// CUD 需提交数据： factory.openSession(true) 自动commit / factory.openSession() + session.commit() 手动commit
	
	
	@Test
	public void testInsertDepartment() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			System.out.println("------------insertDepartment------------");
			Department dept = new Department("Dep-test01","This is department test01");
			Integer result = departmentMapper.insertDepartment(dept);
			System.out.println(result);
			System.out.println(dept);			// => dept.getId() is null
			
			session.commit();  					// note: commit
		}
	}
	
	@Test
	public void testInsertDepartmentAndReturnId() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			System.out.println("------------insertDepartmentAndReturnId------------");
			Department dept = new Department("Dep-test01","This is department test01");
			Integer result = departmentMapper.insertDepartmentAndReturnId(dept);
			System.out.println(result);
			System.out.println(dept);			// => dept.getId() is not null
			
			session.commit();  					// note: commit
		}
	}
	
	@Test
	public void testInsertDepartmentByNextSeq() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			System.out.println("------------insertDepartmentAndReturnId------------");
			Department dept = new Department("Dep-test01","This is department test01");
			Integer result = departmentMapper.insertDepartmentByNextSeq(dept);
			System.out.println(result);
			System.out.println(dept);			// => dept.getId() is not null
			
			session.commit();  					// note: commit
		}
	}
	
	@Test
	public void testUpdateDepartment() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			System.out.println("------------updateDepartment------------");
			Integer result = departmentMapper.updateDepartment(new Department(5,"Dep-test02","This is department test02"));
			System.out.println(result);
			
			session.commit();  					// note: commits
		}
	}
	
	@Test
	public void testDeleteDepartment() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			System.out.println("------------deleteDepartment------------");
			Integer result = departmentMapper.deleteDepartment(5);
			System.out.println(result);
			
			session.commit();  					// note: commits
		}
	}
	
}
