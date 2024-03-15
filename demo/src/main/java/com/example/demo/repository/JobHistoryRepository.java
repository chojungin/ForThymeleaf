package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.HistoryResponse;
import com.example.demo.entity.JobHistory;

@Repository
@EnableJpaRepositories
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long>{
	
	@Query("select e.employeeId as employeeId, "
			+ "concat(e.firstName, ' ', e.lastName) as fullName, "
			+ "e.email as email, "
			+ "replace(e.phoneNumber, '.', '-') as phoneNumber, "
			+ "j.jobTitle as jobTitle, "
			+ "d.departmentName as departmentName, "
			+ "concat(to_char(h.startDate, 'yyyy-mm-dd'), ' ~ ', to_char(h.endDate, 'yyyy-mm-dd')) as period "
			+ "from JobHistory h "
			+ "join h.employees e "
			+ "join h.jobs j "
			+ "join h.departments d "
			+ "where h.employees.employeeId = :id")
	public List<HistoryResponse> findEmployeeHistoryById(@Param("id") Long id);
}
