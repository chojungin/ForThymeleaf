package com.example.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employees {
	
	@Id
	@Column(name = "employee_id")
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Jobs jobs;
    
    private Double salary;
    private Double commissionPct;
    
    @Column(name = "manager_id")
    private Long managerId;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Departments departments;
    
    public void updateEmployeeInfo(
			String fullName, 
			String email,
			String phoneNumber
	) {
		this.firstName = fullName.split("\\s")[0];
		this.lastName = fullName.split("\\s")[1];
		this.email = email;
		this.phoneNumber = phoneNumber.replaceAll("-", ".");
	}
    
    public void updateEmployeeSalary(
    		String salary
    ) {
    	this.salary = Double.parseDouble(salary);
    }
    
}
