package com.tekworks.microservice.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	
	private Integer id;
	
	
	private String name;
	

	private String address;

}
