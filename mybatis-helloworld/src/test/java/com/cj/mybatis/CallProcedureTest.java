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
import com.cj.mybatis.entity.MyPageInfo;
import com.cj.mybatis.mapper.EmployeeMapper;

public class CallProcedureTest {

	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config3.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testProcedure() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			
			MyPageInfo page = new MyPageInfo();
			page.setPageNum(13);
			page.setPageSize(3);
			page.setTotal(0);
			List<Employee> list = employeeMapper.getPageResult(page);
			for(Employee emp:list) {
				System.out.println(emp);
			}
			System.out.println(page.getTotal());
		}
	}
	/*
	[QC] DEBUG BaseJdbcLogger.debug | ==>  Preparing: {call get_page_result( ?, ?, ? )} 
	[QC] DEBUG BaseJdbcLogger.debug | ==> Parameters: 13(Integer), 3(Integer)
	[QC] DEBUG BaseJdbcLogger.debug | <==      Total: 3
	[QC] DEBUG BaseJdbcLogger.debug | <==    Updates: 0
	Employee [id=71, name=QA-01, remark=This is Employee QA-01, departmentId=15, status=NORMAL]
	Employee [id=72, name=QA-02, remark=This is Employee QA-02, departmentId=15, status=CANCEL]
	Employee [id=73, name=QA-02, remark=This is Employee QA-02, departmentId=15, status=CANCEL]
	43
	 */
}
