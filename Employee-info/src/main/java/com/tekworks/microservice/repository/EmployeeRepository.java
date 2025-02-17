package com.tekworks.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekworks.microservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
