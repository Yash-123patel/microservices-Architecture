package com.tekworks.department.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tekworks.department.entity.Department;
import com.tekworks.department.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	
	@PostMapping("/saveDepartment")
	public ResponseEntity<Object> saveDepartment(@Validated @RequestBody Department department,BindingResult bindingResult){
		
		try {
			if(bindingResult.hasErrors()) {
				
				Map<String ,String> errors=new HashMap<>();
				
				bindingResult.getFieldErrors().forEach(err->
				          errors.put(err.getField(),err.getDefaultMessage()));
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
			}
			
			Department saveDepartment = departmentService.saveDepartment(department);
			if(saveDepartment==null) {
				throw new Exception("Error while saving department");
			}
			return ResponseEntity.status(HttpStatus.OK).body("Departmnet Information Saved Successfully");
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/getDepartmentById/{id}")
	public ResponseEntity<Object> getDepartmentById(@PathVariable Integer id){
		try {
			if(id==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Provide valid id to get departmnet");
			}
			return ResponseEntity.status(HttpStatus.OK).body(departmentService.getDepartmentById(id));			 
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
