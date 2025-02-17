package com.tekworks.microservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.tekworks.microservice.entity.Employee;
import com.tekworks.microservice.exception.NoEmployeeFoundException;
import com.tekworks.microservice.model.Department;
import com.tekworks.microservice.repository.EmployeeRepository;
import com.tekworks.microservice.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired RestTemplate restTemplate;
	
	@Autowired EmployeeResponse employeeResponse;
	
	@Transactional(rollbackForClassName = "java.lang.Exception")
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public EmployeeResponse getEmployeeById(Integer id) throws NoEmployeeFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		
		if(employee.isPresent()) {
			
			
			 Employee employee2 = employee.get();
			 
				Department department = restTemplate.getForObject("http://DEPARTMENT-INFO/department/getDepartmentById/"+employee2.getDeptId(), Department.class);
		        System.out.println("Departmnet data is "+department);
		        employeeResponse.setEmp(employee2);
		        employeeResponse.setDept(department);
		        return employeeResponse;
		}
		
		throw new NoEmployeeFoundException("No Employee Found With id: "+id);
	}
	
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@Transactional(rollbackForClassName = "java.lang.Exception")
	public void deleteEmployeeById(Integer id) {
		employeeRepository.deleteById(id);
	}

}
