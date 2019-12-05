package com.cj.mybatis;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.mapper.EmployeeMapper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BathExecutorTest {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void testBatchExecutor() {
		try(SqlSession session=sqlSessionFactory.openSession(ExecutorType.BATCH)){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			for(int i=0;i<5;i++) {
				Employee emp = new Employee(null,"Test-"+i,"This is Test-"+i,3);
				Integer result = employeeMapper.insertEmployee(emp);
				System.out.println(result+","+emp.getId());
			}
			System.out.println("Done");
		}
	}
}
