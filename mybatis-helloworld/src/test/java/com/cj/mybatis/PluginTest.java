package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.mapper.EmployeeMapper;

public class PluginTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config3.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testGet() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			Employee emp = employeeMapper.getEmployee(1);
			System.out.println(emp);
		}
	}
	
	/*
	1. StatementHandler:处理Sql语句预编译，设置参数等相关工作
	2. ParameterHandler: 设置预编译参数用的
	3. ResultHandler: 处理结果集
	4. TypeHandler: 在整个过程中，进行数据库类型和JavaBean类型的映射
	
	四大对象：
	- Executor
	- ParameterHandler
	- ResultSetHandler
	- StatementHandler
	- 注意：每个创建的时候都会执行interceptorChain.pluginAll(parameterHandler)操作，执行插件拦截器
	
	 StatementHandler -> BaseStatementHandler - RoutingStatementHandler
	 -> PreparedStatementHandler 
	 -> ParameterHandler(DefaultParameterHandler: MappedStatement,BoundSql,parameterObject,Configuration)
		  
	*/
}
