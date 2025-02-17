package com.tekworks.department.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tekworks.department.entity.Department;
import com.tekworks.department.repository.DepartmentRepository;

@Service

public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Transactional(rollbackForClassName = "java.lang.Exception")
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}
	
	public Department getDepartmentById(Integer id) {
		Optional<Department> byId = departmentRepository.findById(id);
		if(!(byId.isPresent())) {
			throw new RuntimeException("Department not found with id "+id);
		}
		return byId.get();
	}

}
