package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Configuration
public class QueryDslConfig {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Bean
	JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(this.entityManager);
	}
}
