package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.mapper.EmployeeMapper;

/**
 * 1. 接口式编程
 * 		原生： 		Dao 	===> DaoImpl
 * 		mybatis：	Mapper	===> xxxMapper.xml
 * 2. 两个重要配置文件：
 * 		mybatis的全局配置文件：配置数据库连接池，事务管理器等
 * 		sql映射文件：配置每个sql语句映射（将sql抽取出来了）
 * 3. SqlSession 代表与数据库的一次会话，用完需关闭；线程非安全，所以每次使用应获取新的
 */

public class HelloWorldTest {

	@Test
	public void test1() throws IOException {
		// 1. 根据全局配置文件创建一个SqlSessionFactory对象
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		
		// 2. 获取SqlSession对象来执行映射的sql(一个SqlSession代表和数据库的一次会话，用完关闭)
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// selectOne(namespace.id,parameter)
			Employee emp = session.selectOne("com.cj.mybatis.mapper.EmployeeMapper.getEmployee", 1);
			System.out.println(emp);
		}finally {
			session.close();
		}
	}
	
	@Test
	public void test2() throws IOException {
		// 1. 根据全局配置文件创建一个SqlSessionFactory对象
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		
		// 2. 获取SqlSession对象，执行映射的sql
		try (SqlSession session = sqlSessionFactory.openSession()) {
			// Method1: no EmployeeMapper.java
            // Employee emp = session.selectOne("com.cj.mybatis.mapper.EmployeeMapper.getEmployee", 1);
            
            // Method2: use EmployeeMapper.java
			// 注： interface EmployeeMapper 无实现类，mybatis会将此接口和sql配置进行绑定，生成一个代理对象执行
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            System.out.println(employeeMapper); 			// 返回代理对象执行 org.apache.ibatis.binding.MapperProxy@7ea37dbf
            Employee emp = employeeMapper.getEmployee(1);
            
            System.out.println(emp);
		}
	}
}
