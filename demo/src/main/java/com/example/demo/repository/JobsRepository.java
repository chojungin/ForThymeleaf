package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Jobs;

@Repository
@EnableJpaRepositories
public interface JobsRepository extends JpaRepository<Jobs, String>{

}
