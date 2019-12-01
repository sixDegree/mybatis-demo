package com.cj.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// for Test Exception:
	@GetMapping("/error")
	public Object error() {
		int i=1/0;
		return ResponseUtil.ok("Test Error:"+i);
	}
	
	// for Test @Transactional
	@GetMapping("/error/insertMultiple/{count}")
	public Object insertMultiple(@PathVariable("count") Integer count) {
		List<Integer> result = employeeService.insertMultipleEmployees(count);
		return ResponseUtil.ok(result);
	}
		
	// ================== CRUD ==========================
	
	@GetMapping("/employees")
	public Object listEmployees(@RequestParam(name="page",required=false) Integer page
			,@RequestParam(name="size",required=false) Integer size) {
		if(page!=null && size!=null) {
			return ResponseUtil.ok(employeeService.listByPage(page, size));
		}else {
			return ResponseUtil.ok(employeeService.listAll());
		}
	}

	@GetMapping("/employees/{id}")
	public Object getEmployee(@PathVariable Integer id) {
		return ResponseUtil.ok(employeeService.getEmployee(id));
	}
	
	@PutMapping("/employees/{id}")
	public Object updateEmployee(@PathVariable("id") Integer id,@RequestBody Employee emp) {
		emp.setId(id);
		Integer result=this.employeeService.updateEmployee(emp);
		return ResponseUtil.result(result==1, result);
	}
	
	@PostMapping("/employees")
	public Object insertEmployee(@RequestBody Employee emp) {
		Integer result = this.employeeService.insertEmployee(emp);
		return ResponseUtil.result(result!=null, result);
	}
	
	@DeleteMapping("/employees/{id}")
	public Object deleteEmployee(@PathVariable Integer id) {
		Integer result = this.employeeService.deleteEmployee(id);
		return ResponseUtil.result(result==1, result);
	}
}
