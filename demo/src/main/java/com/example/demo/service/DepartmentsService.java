package com.example.demo.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.DepartmentResponse;
import com.example.demo.entity.Departments;
import com.example.demo.repository.DepartmentsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DepartmentsService {
	
	private final DepartmentsRepository departmentsRepository;
	
	public List<Departments> getDepartmentsByKeyword(String key, String word){
		if (word == null || word.isEmpty()) {
			return departmentsRepository.findAll();
		} else if (key.equals("id") && !word.isEmpty()){
			return departmentsRepository.findDepartmentsById(Long.parseLong(word));
		} else if (key.equals("name") && !word.isEmpty()) {
			return departmentsRepository.findDepartmentsByName(word);
		} else {
			return null;
		}
	}
	
	public DepartmentResponse getDepartmentDetailById(Long id) throws BadRequestException {
		return departmentsRepository.findDepartmentsDetailById(id).orElseThrow(()-> new BadRequestException("해당 부서의 상세 정보가 존재하지 않습니다."));
	}
}
