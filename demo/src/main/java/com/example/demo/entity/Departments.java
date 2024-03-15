package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Departments {
	
	@Id
	@Column(name = "department_id")
    private Long departmentId;
    private String departmentName;

    @OneToMany(mappedBy = "departments")
    private List<Employees> employees;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employees managers;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Locations locations;
}
