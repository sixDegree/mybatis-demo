package com.cj.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.cj.mybatis.entity.Department;

public interface DepartmentMapper {

	public Department getDepartment(Integer id);
	public Map<String,Object> getDepartmentMap(Integer id);
	
	public Integer insertDepartment(Department department);
	public Integer insertDepartmentAndReturnId(Department department);
	public Integer insertDepartmentByNextSeq(Department department);
	
	public Integer updateDepartment(Department department);
	public Integer deleteDepartment(Integer id);
	
	public List<Department> listByParams1(String name,String remark);
	public List<Department> listByParams2(@Param("name")String name,@Param("remark")String remark);
	
	public List<Department> listByMap(Map<String,Object> params);
	public List<Department> listByExample(Department department);
	public List<Department> listByIds(List<Integer> ids);
	
	public List<Department> listDepartment();
	@MapKey("id")
	public Map<Integer,Department> listDepartmentMap();
}
