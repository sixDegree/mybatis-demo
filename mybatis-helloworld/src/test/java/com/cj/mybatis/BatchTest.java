package com.cj.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.mapper.EmployeeMapper;

public class BatchTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config2.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	// 非批量：预编译sql+设置参数 -> 执行10000次
	// 批量：预编译sql -> 设置参数 10000次 -> 执行1次
	
	@Test
	public void testOneByOneInsert() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			long start = System.currentTimeMillis();
			
			for(int i=0;i<10;i++) {
				Employee emp = new Employee();
				emp.setName("Bat-"+i);
				emp.setRemark("This is Employee Bat-"+i);
				emp.setDepartmentId(3);
				Integer result = employeeMapper.insertEmployee(emp);
				System.out.println(result+","+emp.getId());
			}
			
			session.commit();
			long end = System.currentTimeMillis();
			System.out.println("cost time:"+(end-start));
		}
	}
	
	@Test
	public void testBatchInsert() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession(ExecutorType.BATCH)){ // Batch!
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			long start = System.currentTimeMillis();
			
			for(int i=10;i<20;i++) {
				Employee emp = new Employee();
				emp.setName("Bat-"+i);
				emp.setRemark("This is Employee Bat-"+i);
				emp.setDepartmentId(3);
				Integer result = employeeMapper.insertEmployee(emp);
				System.out.println(result+","+emp.getId());
			}
			
			session.commit();
			long end = System.currentTimeMillis();
			System.out.println("cost time:"+(end-start));
		}
	}
	
	/*Global Setting: defaultExecutorType: SIMPLE(default)/REUSE/BATCH */
	
	/*
	  Spring:
	  <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
	  	<constructor-arg name="sqlSessionFactory" ref="sqlSessionFactory"/>
	  	<constructor-arg name="executorType" value="BATCH" />
	  </bean>
	  
	  EmployeeService:
	  
	  @Autowired
	  private SqlSession sqlSession;
	  
	  sqlSession.getMapper(xxx);
	  ...
	 */
}
