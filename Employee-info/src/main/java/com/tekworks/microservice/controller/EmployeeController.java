package com.tekworks.microservice.controller;

import java.util.LinkedHashMap;
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

import com.tekworks.microservice.entity.Employee;
import com.tekworks.microservice.exception.NoEmployeeFoundException;
import com.tekworks.microservice.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping("/saveEmployee")
	public ResponseEntity<?> saveEmployee(@Validated @RequestBody Employee employee ,BindingResult result){
		
		try {
			if(result.hasErrors()) {
				Map<String, String> errors = new LinkedHashMap<>();
				result.getFieldErrors().forEach(fieldError -> {
					String fieldName = fieldError.getField();
					String errorMessage = fieldError.getDefaultMessage();
					errors.put(fieldName, errorMessage);
				});
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
				
			}
			
			Employee saveEmployee = employeeService.saveEmployee(employee);
			if(saveEmployee==null) {
				throw new RuntimeException();
				
			}
			
			 return ResponseEntity.status(HttpStatus.OK).body("Employee Data Saved Successfully");
		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error:  "+e.getMessage());
		}
	}
	
	
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) throws NoEmployeeFoundException {
		if(id==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Provide Valid Employee id");
		}
		
		  return ResponseEntity.status(HttpStatus.OK).body( employeeService.getEmployeeById(id));
		
		
	}
}
