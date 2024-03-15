package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.DepartmentResponse;
import com.example.demo.dto.EmployeeRequest;
import com.example.demo.entity.Departments;
import com.example.demo.repository.DepartmentsRepository;
import com.example.demo.repository.EmployeesRepository;
import com.example.demo.service.DepartmentsService;
import com.example.demo.service.EmployeesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/departments")
public class DepartmentsController {
	
	private final DepartmentsService departmentsService;
	private final EmployeesService employeesService;
	private final DepartmentsRepository departmentsRepository;
	private final EmployeesRepository employeesRepository;
	
	@GetMapping("")
	public String departmentsPage() {
		return "departments";
	}
	
	@GetMapping("/search")
	@ResponseBody
    public ResponseEntity<?> getDepartments(@RequestParam("key") String key, @RequestParam("word") String word) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(departmentsService.getDepartmentsByKeyword(key, word));
		} catch (Exception e) {
			log.error("getDepartments Exception : "+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.build();
		}
    }
	
	@GetMapping("/{id}")
    public String getDepartmentDetail(@PathVariable("id") Long id, Model model) {
		try {
			
			DepartmentResponse response = departmentsService.getDepartmentDetailById(id);
			model.addAttribute("department", response);
			
			Departments department = departmentsRepository.findById(id).orElseThrow();
			model.addAttribute("employeeList", employeesRepository.findByDepartments(department));
			
			return "departmentsDetail";
			
		} catch (Exception e) {
			log.error("getDepartmentDetail Exception : "+e);
			return "error";
		}
    }
	
	@PutMapping("/{id}")
	public ResponseEntity<?> putDepartmentEmployeeSalary(@PathVariable("id") Long id, @RequestBody List<Map<String,String>> dataList) {
		try {
			
			for (Map<String,String> data : dataList) {
				employeesService.putEmployeeSalary(Long.parseLong(data.get("id")), data.get("salary"));
	        }
			return ResponseEntity.status(HttpStatus.OK).build();
			
		} catch (Exception e) {
			log.error("putDepartmentEmployeeSalary Exception : "+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
