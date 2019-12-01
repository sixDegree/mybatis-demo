package com.cj.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cj.mybatis.entity.Employee;
import com.cj.mybatis.service.EmployeeService;
import com.cj.mybatis.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="Employee Part")
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// for Test Exception:
	@ApiOperation("For Test Exception")
	@GetMapping("/error")
	public Object error() {
		int i=1/0;
		return ResponseUtil.ok("Test Error:"+i);
	}
	
	// for Test @Transactional
	@ApiOperation("For Test @Transactional")
	@ApiImplicitParam(name="count",value="插入数",dataType="Integer",paramType="path")
	@GetMapping("/error/insertMultiple/{count}")
	public Object insertMultiple(@PathVariable("count") Integer count) {
		List<Integer> result = employeeService.insertMultipleEmployees(count);
		return ResponseUtil.ok(result);
	}
		
	// ================== CRUD ==========================
	
	@ApiOperation(value="List Emlpoyees")
	@ApiImplicitParams({
			@ApiImplicitParam(name="page",value="页码",dataType="Integer",paramType="query",required=false),
			@ApiImplicitParam(name="size",value="每页数量",dataType="Integer",paramType="query",required=false)
	})
	@GetMapping("/employees")
	public Object listEmployees(@RequestParam(name="page",required=false) Integer page
			,@RequestParam(name="size",required=false) Integer size) {
		if(page!=null && size!=null) {
			return ResponseUtil.ok(employeeService.listByPage(page, size));
		}else {
//			return new ResponseEntity<>(employeeService.listAll(),HttpStatus.OK);
			return ResponseUtil.ok(employeeService.listAll());
		}
	}

	@ApiOperation("Get Employee By Id")
	@ApiImplicitParam(name="id",value="Employee唯一标识",dataType="Integer",paramType="path",required=true)
	@GetMapping("/employees/{id}")
	public Object getEmployee(@PathVariable Integer id) {
		return ResponseUtil.ok(employeeService.getEmployee(id));
	}
	
	@ApiOperation("Update Employee By Id")
	@ApiImplicitParam(name="id",value="Employee唯一标识",dataType="Integer",paramType="path",required=true)
	@PutMapping("/employees/{id}")
	public Object updateEmployee(@PathVariable("id") Integer id,@RequestBody Employee emp) {
		emp.setId(id);
		Integer result=this.employeeService.updateEmployee(emp);
		return ResponseUtil.result(result==1, result);
	}
	
	@ApiOperation("Add Employee")
	@PostMapping("/employees")
	public Object insertEmployee(@RequestBody Employee emp) {
		emp.setId(null);
		Integer result = this.employeeService.insertEmployee(emp);
		return ResponseUtil.result(result!=null, result);
	}
	
	@ApiOperation("Delete Employee By Id")
	@ApiImplicitParam(name="id",value="Employee唯一标识",dataType="Integer",paramType="path",required=true)
	@DeleteMapping("/employees/{id}")
	public Object deleteEmployee(@PathVariable Integer id) {
		Integer result = this.employeeService.deleteEmployee(id);
		return ResponseUtil.result(result==1, result);
	}
}
