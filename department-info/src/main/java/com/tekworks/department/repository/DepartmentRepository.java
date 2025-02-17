package com.tekworks.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekworks.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
