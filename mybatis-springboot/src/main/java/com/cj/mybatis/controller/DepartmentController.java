package com.cj.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cj.mybatis.service.DepartmentService;
import com.cj.mybatis.util.ResponseUtil;

@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@GetMapping("/departments")
	public Object listAll() {
		return ResponseUtil.ok(departmentService.listAll());
	}
}
