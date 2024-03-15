package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.DepartmentResponse;
import com.example.demo.entity.Departments;

@Repository
@EnableJpaRepositories
public interface DepartmentsRepository extends JpaRepository<Departments,Long>{
	
	@Query("select d from Departments d where d.departmentId = :id")
	public List<Departments> findDepartmentsById(@Param("id") Long id);
	
	@Query("select d from Departments d where d.departmentName like %:word%")
	public List<Departments> findDepartmentsByName(@Param("word") String word);
	
	@Query("select distinct d.departmentId as departmentId, "
			+ "d.departmentName as departmentName, "
			+ "concat(l.streetAddress, ', ', l.city, ', ', l.stateProvince, ', ', c.countryName, ', ', l.postalCode) as locations, "
			+ "concat(m.firstName, ' ', m.lastName) as managerName "
			+ "from Departments d " 
			+ "join d.employees e "
			+ "join d.managers m "
			+ "join d.locations l "
			+ "join l.countries c "
			+ "where d.departmentId = :id "
			+ "order by d.departmentId")
	public Optional<DepartmentResponse> findDepartmentsDetailById(@Param("id") Long id);
}
