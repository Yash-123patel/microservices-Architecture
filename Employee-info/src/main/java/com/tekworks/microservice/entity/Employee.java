package com.tekworks.microservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Please Provid Valid EmployeeName")
	private String name;
	
	@NotNull(message = "Please provide valid Employee Salary")
	@Positive(message = "Salary Must be positive")
    private Double salary;
	
	@NotNull(message = "Please provide valid Address Id")
    private Integer addressId;
	
	@NotNull(message = "Please provide valid Department Id")
    private Integer deptId;

}
