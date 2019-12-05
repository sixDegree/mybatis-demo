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
import com.cj.mybatis.entity.UserRole;
import com.cj.mybatis.entity.EmployeeStatusEnum;
import com.cj.mybatis.entity.UserTypeEnum;
import com.cj.mybatis.mapper.EmployeeMapper;
import com.cj.mybatis.mapper.UserRoleMapper;

public class EnumTest {
	
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config3.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	@Test
	public void testEnum() {
		
		System.out.println("========== UserTypeEnum.Employee ============");
		UserTypeEnum userType=UserTypeEnum.Employee;
		System.out.println(userType);
		System.out.println("ordinal:"+userType.ordinal());
		System.out.println("name:"+userType.name());
		
		System.out.println("========== UserTypeEnum.Department ============");
		
		userType=UserTypeEnum.Department;
		System.out.println(userType);
		System.out.println("ordinal:"+userType.ordinal());
		System.out.println("name:"+userType.name());
		
		System.out.println("========= EmployeeStatusEnum.Normal =============");
		
		EmployeeStatusEnum employeeStatus=EmployeeStatusEnum.NORMAL;
		System.out.println(employeeStatus);
		System.out.println("ordinal:"+employeeStatus.ordinal());
		System.out.println("name:"+employeeStatus.name());
		System.out.println("code:"+employeeStatus.getCode());
		System.out.println("msg:"+employeeStatus.getMsg());
		
		System.out.println("============= EmployeeStatusEnum.values() ================");
		EmployeeStatusEnum[] list = EmployeeStatusEnum.values();
		for(EmployeeStatusEnum s:list) {
			System.out.println(s);
		}
		
		System.out.println("======== EmployeeStatusEnum.class.getEnumConstants() =========");
		list = EmployeeStatusEnum.class.getEnumConstants();
		for(EmployeeStatusEnum s:list) {
			System.out.println(s);
		}
		
		System.out.println("============= EmployeeStatusEnum.valueOf(xxx) ================");
		EmployeeStatusEnum result = EmployeeStatusEnum.valueOf("NORMAL");
		System.out.println(result);
	}
	
	//============== for test typeHandler: EnumTypeHandler(default) =================
	
	
	// 处理枚举对象: 
	// 默认使用EnumTypeHandler，即操作的是枚举的名字 => select/insert: Enum name
	// 可改变使用EnumOrdinalTypeHandler,即操作的是枚举的ordinal值(0,1,2,...) => select/insert: Enum ordinal
	
	@Test
	public void testInsert() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			UserRoleMapper userRoleMapper = session.getMapper(UserRoleMapper.class);
			
			UserRole userRole=new UserRole();
			userRole.setRoleName("PE-01");
			userRole.setUserId(4);
			userRole.setUserType(UserTypeEnum.Employee);
			
			Integer result = userRoleMapper.insertUserRole(userRole);
			System.out.println(result);
			System.out.println(userRole.getId());
			
			session.commit();
		}
	}
	/*
	 Result Sample:
	 [QC] DEBUG BaseJdbcLogger.debug | ==>  Preparing: insert into pe_role(role_name,user_id,user_type) values(?,?,?) 
	 [QC] DEBUG BaseJdbcLogger.debug | ==> Parameters: PE-01(String), 4(Integer), Employee(String)
	 [QC] DEBUG BaseJdbcLogger.debug | <==    Updates: 1
	 1
	 8
	 */
	
	@Test
	public void testList() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			UserRoleMapper urMapper = session.getMapper(UserRoleMapper.class);
			
			System.out.println("------------listUserRole------------");
			List<UserRole>	list = urMapper.listUserRoles();
			for(UserRole ur : list) {
				System.out.println(ur);
			}
			
			System.out.println("------------listUserRoleWithDetails------------");
			list = urMapper.listUserRoleWithDetails();
			for(UserRole ur : list) {
				System.out.println(ur);
			}
		}
	}
	/*
	Result Sample:
 	------------listUserRole------------
	[QC] DEBUG BaseJdbcLogger.debug | ==>  Preparing: select * from pe_role 
	[QC] DEBUG BaseJdbcLogger.debug | ==> Parameters: 
	[QC] DEBUG BaseJdbcLogger.debug | <==      Total: 7
	UserRole [id=1, userId=1, userType=Employee, roleName=manager, name=null, remark=null]
	UserRole [id=2, userId=2, userType=Employee, roleName=leader, name=null, remark=null]
	UserRole [id=3, userId=3, userType=Employee, roleName=temporary, name=null, remark=null]
	UserRole [id=4, userId=1, userType=Department, roleName=IT-01, name=null, remark=null]
	UserRole [id=5, userId=2, userType=Department, roleName=IT-02, name=null, remark=null]
	UserRole [id=6, userId=3, userType=Department, roleName=Finance-01, name=null, remark=null]
	UserRole [id=8, userId=4, userType=Employee, roleName=PE-01, name=null, remark=null]
	*/
	
	//============== for test typeHandler: EnumOrdinalTypeHandler =================
	
	@Test
	public void testInsertOnEnumOrdinalTypeHandler() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			UserRoleMapper userRoleMapper = session.getMapper(UserRoleMapper.class);
			
			UserRole userRole=new UserRole();
			userRole.setRoleName("PE-02");
			userRole.setUserId(4);
			userRole.setUserType(UserTypeEnum.Department);
			
			Integer result = userRoleMapper.insertOnEnumOrdinal(userRole);
			System.out.println(result);
			System.out.println(userRole.getId());
			
			session.commit();
		}
	}
	/*
	[QC] DEBUG BaseJdbcLogger.debug | ==>  Preparing: insert into pe_role2(role_name,user_id,user_type) values( ?, ?, ? ) 
	[QC] DEBUG BaseJdbcLogger.debug | ==> Parameters: PE-02(String), 4(Integer), 1(Integer)
	[QC] DEBUG BaseJdbcLogger.debug | <==    Updates: 1
	1
	4
	*/
	
	
	@Test
	public void testListOnEnumOrinalTypeHandler() throws IOException {
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			UserRoleMapper urMapper = session.getMapper(UserRoleMapper.class);
			
			System.out.println("------------listUserRole------------");
			List<UserRole>	list = urMapper.listOnEnumOrdinal();
			for(UserRole ur : list) {
				System.out.println(ur);
			}
		}
	}
	
	/*
	 ------------listUserRole------------
	[QC] DEBUG BaseJdbcLogger.debug | ==>  Preparing: select * from pe_role2 
	[QC] DEBUG BaseJdbcLogger.debug | ==> Parameters: 
	[QC] DEBUG BaseJdbcLogger.debug | <==      Total: 3
	UserRole [id=1, userId=1, userType=Department, roleName=PE-00, name=null, remark=null]
	UserRole [id=3, userId=4, userType=Department, roleName=PE-01, name=null, remark=null]
	UserRole [id=4, userId=4, userType=Department, roleName=PE-02, name=null, remark=null]
	*/
	
	
	// =========== for test self definition typeHandler: MyEnumTypeHandler ==============
	
	@Test
	public void testInsertOnMyEnumTypeHandler() throws Exception{
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			System.out.println("------------insertEmployeeOnMyEnum------------");
			Employee emp = new Employee();
			emp.setName("QA-03");
			emp.setRemark("This is Employee QA-03");
			emp.setDepartmentId(15);
			emp.setStatus(EmployeeStatusEnum.DELETE);
			Integer result = employeeMapper.insertEmployeeOnMyEnum(emp);
			System.out.println(result);
			System.out.println(emp.getId());
			session.commit();
		}
	}
	/*
	------------insertEmployeeOnMyEnum------------
	[QC] DEBUG BaseJdbcLogger.debug | ==>  Preparing: insert into pe_employee(name,remark,department_id,status) values (?,?,?,?) 
	[QC] DEBUG BaseJdbcLogger.debug | ==> Parameters: QA-02(String), This is Employee QA-02(String), 15(Integer), 200(Integer)
	[QC] DEBUG BaseJdbcLogger.debug | <==    Updates: 1
	1
	72
	*/
	
	@Test
	public void testListOnMyEnumTypeHandler() throws Exception{
		SqlSessionFactory factory = getSqlSessionFactory();
		try(SqlSession session=factory.openSession()){
			EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
			System.out.println("------------listEmployees------------");
			List<Employee>	list = employeeMapper.listEmployeesOnMyEnum();
			for(Employee ur : list) {
				System.out.println(ur);
			}
		}
	}
	/*
	 ------------listEmployees------------
	[QC] DEBUG BaseJdbcLogger.debug | ==>  Preparing: select * from pe_employee 
	[QC] DEBUG BaseJdbcLogger.debug | ==> Parameters: 
	[QC] DEBUG BaseJdbcLogger.debug | <==      Total: 38
	Employee [id=1, name=Test1, remark=This is Test1, depar
	Employee [id=68, name=Bat-19, remark=This is Employee Bat-19, departmentId=3, status=null]
	Employee [id=71, name=QA-01, remark=This is Employee QA-01, departmentId=15, status=NORMAL]
	Employee [id=72, name=QA-02, remark=This is Employee QA-02, departmentId=15, status=CANCEL]
	*/
	
}
