package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequest {
	private String employeeId;
	private String fullName;
	private String email;
	private String phoneNumber;
	//private String jobTitle;
	//private String departmentName;
	//private String salary;
	
	@Builder
	public EmployeeRequest(
		String fullName, 
		String email, 
		String phoneNumber
	) {
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
}
