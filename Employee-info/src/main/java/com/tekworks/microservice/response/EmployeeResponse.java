package com.tekworks.microservice.response;

import org.springframework.stereotype.Component;

import com.tekworks.microservice.entity.Employee;
import com.tekworks.microservice.model.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmployeeResponse {
	private Employee emp;
	private Department dept;

}