package com.cj.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cj.mybatis.service.DepartmentService;
import com.cj.mybatis.util.ResponseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Department Part")
@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@ApiOperation("List All Departments")
	@GetMapping("/departments")
	public Object listAll() {
		return ResponseUtil.ok(departmentService.listAll());
	}
}
