package com.example.demo.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.HistoryResponse;
import com.example.demo.repository.JobHistoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class JobHistoryService {
	
	private final JobHistoryRepository jobHistoryRepository;
	
	public List<HistoryResponse> getEmployeeHistory(Long id) throws BadRequestException {
		return jobHistoryRepository.findEmployeeHistoryById(id); //.orElseThrow(()-> new BadRequestException("해당 회원의 이력 정보가 존재하지 않습니다."));
	}
}
