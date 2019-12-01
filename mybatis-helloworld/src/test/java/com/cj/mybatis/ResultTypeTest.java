package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.Department;
import com.cj.mybatis.mapper.DepartmentMapper;

public class ResultTypeTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config2.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testReturnSingle() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			// return POJO (set resultType="com.cj.mybatis.entity.Department")
			System.out.println("------------getDepartment------------");
			Department dept = departmentMapper.getDepartment(1);
			System.out.println(dept);
			
			// return Map (set resultType="map")
			System.out.println("------------getDepartmentMap------------");
			Map<String,Object> deptMap = departmentMapper.getDepartmentMap(1);
			System.out.println(deptMap);
		}
	}
	
	@Test
	public void testReturnMultiple() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			// return List<Department> (set resultType="com.cj.mybatis.entity.Department")
			System.out.println("------------listDepartment------------");
			List<Department> list = departmentMapper.listDepartment();
			System.out.println(list);
			
			// return Map<Integer,Department> (set resultType="com.cj.mybatis.entity.Department")
			// + @MapKey("") 注解 => 将结果集封装成Map
			System.out.println("------------listDepartmentMap------------");
			Map<Integer,Department> map = departmentMapper.listDepartmentMap();
			System.out.println(map);
		}
	}
	
	// ===================================================
	
	
}
