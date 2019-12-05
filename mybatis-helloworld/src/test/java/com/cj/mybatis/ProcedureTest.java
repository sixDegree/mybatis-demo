package com.cj.mybatis;

public class ProcedureTest {

	/*
	 * 
	Mybatis对存储过程的游标提供了一个jdbcType=CURSOR的支持，
	可以智能的把游标读取到的数据映射到我们声明的结果集中
	
	<select id="" statementType="CALLABLE">
	{
		call hello_test(
			#{start,mode=IN,jdbcType=INTEGER},
			#{end,mode=IN,jdbcType=INTEGER},
			#{count,mode=OUT,jdbcType=INTEGER},
			#{emps,mode=OUT,jdbcType=CURSOR,javaType=ResultSet,resultMap=TestEmp}
		)
	}
	</select>
	
	<resultMap type="Employee" id="TestEmp">
	</resultMap>
	
	public class Page{
		private int start;
		private int end;
		private int count;
		private List<Employee> emps;
	}
	
	create or replace procedure
		hello_test(
			p_start in int,
			p_end in int,
			p_count out,
			p_emps out sys_refcursor
		)as
	begin
		select count(*) into p_count from employees;
		open p_emps for
			select * from (select rownum rn,e.* from employees e where rownum<=p_end)
			where rn>=p_start;
	end hello_test;
	
	create procedure test()
	begin 
		select 'hello';
	end
	
	{call procedure_name(#{param1},#{param2})}
	
	*/
	
	
}
