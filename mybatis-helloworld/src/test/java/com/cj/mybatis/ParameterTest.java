package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.Department;
import com.cj.mybatis.mapper.DepartmentMapper;

public class ParameterTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config2.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	

	/*
  	public List<Department> listByParams(String name,String remark);
	public List<Department> listByMap(Map params);
	public List<Department> listByIds(List<Integer> ids);
	public Map<Integer,Department> listAndReturnMap();
	*/
	
	@Test
	public void testListByParams() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			System.out.println("------------listByParams1 => [arg0,arg1,param1,param2]------------");
			List<Department> list = departmentMapper.listByParams1("Dep%","This is %");
			System.out.println(list);
			
			System.out.println("------------listByParams2 +@Param => [name,remark,param1,param2]------------");
			list = departmentMapper.listByParams2("Dep%","This is %");
			System.out.println(list);
		}
	}
	
	@Test
	public void testListByMap()throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			System.out.println("------------listByMap------------");
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("name", "Dep%");
			paramMap.put("remark","This is %");
			List<Department> list = departmentMapper.listByMap(paramMap);
			System.out.println(list);
			
			System.out.println("------------listByExample------------");
			Department dept = new Department(null,"Dep%","This is %");
			list = departmentMapper.listByExample(dept);
			System.out.println(list);
		}
	}
	
	@Test
	public void testListByIds()throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
			
			System.out.println("------------listByIds------------");
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(1);
			ids.add(3);
			List<Department> list = departmentMapper.listByIds(ids);
			System.out.println(list);
		}
	}
	
	
  
   
}
