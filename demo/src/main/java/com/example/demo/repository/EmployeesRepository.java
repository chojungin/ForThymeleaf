package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.EmployeeResponse;
import com.example.demo.dto.HistoryResponse;
import com.example.demo.entity.Departments;
import com.example.demo.entity.Employees;

@Repository
@EnableJpaRepositories
public interface EmployeesRepository extends JpaRepository<Employees,Long>{
	
	@Query("select e from Employees e where e.employeeId = :id")
	public List<Employees> findEmployeesById(@Param("id") Long id);
	
	@Query("select e from Employees e where concat(e.firstName,e.lastName) like %:word%")
	public List<Employees> findEmployeesByName(@Param("word") String word);
	
	@Query("select e.employeeId as employeeId, "
			+ "concat(e.firstName, ' ', e.lastName) as fullName, "
			+ "e.email as email, "
			+ "replace(e.phoneNumber, '.', '-') as phoneNumber, "
			+ "cast(e.salary as text) as salary, "
			+ "j.jobTitle as jobTitle, "
			+ "d.departmentName as departmentName "
			+ "from Employees e "
			+ "join e.jobs j "
			+ "join e.departments d "
			+ "where e.employeeId = :id")
	public Optional<EmployeeResponse> findEmployeeDetailById(@Param("id") Long id);

	
	@Query("select e.employeeId as employeeId, "
			+ "concat(e.firstName, ' ', e.lastName) as fullName, "
			+ "e.email as email, "
			+ "replace(e.phoneNumber, '.', '-') as phoneNumber, "
			+ "cast(e.salary as text) as salary, "
			+ "j.jobTitle as jobTitle, "
			+ "d.departmentName as departmentName "
			+ "from Employees e " 
			+ "join e.departments d "
			+ "join e.jobs j " 
			+ "where e.departments = :department")
	public List<EmployeeResponse> findByDepartments(@Param("department") Departments department);

}
