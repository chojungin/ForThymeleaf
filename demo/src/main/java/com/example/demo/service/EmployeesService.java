package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.EmployeesController;
import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.dto.HistoryResponse;
import com.example.demo.entity.Employees;
import com.example.demo.repository.EmployeesRepository;
import com.example.demo.repository.JobHistoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeesService {
	
	private final EmployeesRepository employeesRepository;
	
	public List<Employees> getEmployeesByKeyword(String key, String word){
		if (word == null || word.isEmpty()) {
			return employeesRepository.findAll();
		} else if (key.equals("id") && !word.isEmpty()){
			return employeesRepository.findEmployeesById(Long.parseLong(word));
		} else if (key.equals("name") && !word.isEmpty()) {
			return employeesRepository.findEmployeesByName(word);
		} else {
			return null;
		}
	}

	public EmployeeResponse getEmployeeDetailById(Long id) throws BadRequestException {
		return employeesRepository.findEmployeeDetailById(id).orElseThrow(()-> new BadRequestException("해당 회원의 상세 정보가 존재하지 않습니다."));
	}
	
	@Transactional
	public void putEmployeeDetail(EmployeeRequest request) throws BadRequestException {
		Employees employees = employeesRepository.findById(Long.parseLong(request.getEmployeeId())).orElseThrow(()-> new BadRequestException("해당 회원의 상세 정보가 존재하지 않습니다."));
		employees.updateEmployeeInfo(request.getFullName(), request.getEmail(), request.getPhoneNumber());
	}
	
	@Transactional
	public void putEmployeeSalary(Long id, String salary) throws BadRequestException {
		Employees employees = employeesRepository.findById(id).orElseThrow(()-> new BadRequestException("해당 회원의 정보가 존재하지 않습니다."));
		employees.updateEmployeeSalary(salary);
	}
}
