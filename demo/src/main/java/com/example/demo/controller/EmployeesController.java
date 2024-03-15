package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.EmployeeRequest;
import com.example.demo.dto.EmployeeResponse;
import com.example.demo.repository.EmployeesRepository;
import com.example.demo.service.EmployeesService;
import com.example.demo.service.JobHistoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/employees")
public class EmployeesController {
	
	private final EmployeesService employeesService;
	private final JobHistoryService jobHistoryService;
	
	@GetMapping("")
	public String employeesPage() {
		return "employees";
	}
	
	@GetMapping("/search")
	@ResponseBody
    public ResponseEntity<?> getEmployees(@RequestParam("key") String key, @RequestParam("word") String word) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(employeesService.getEmployeesByKeyword(key, word));
		} catch (Exception e) {
			log.error("getEmployees Exception : "+e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.build();
		}
    }
	
	@GetMapping("/{id}")
    public String getEmployeeDetail(@PathVariable("id") Long id, Model model) { //@RequestParam("id") String id
		try {
			model.addAttribute("employee", employeesService.getEmployeeDetailById(id)); //Long.parseLong(id)
			return "employeesDetail";
		} catch (Exception e) {
			log.error("getEmployeeDetail Exception : "+e);
			return "error";
		}
    }
	
	@GetMapping("/{id}/modify")
	public String getEmployeeModify(@PathVariable("id") Long id, Model model) {
		try {
			EmployeeResponse response = employeesService.getEmployeeDetailById(id);
			//String regNumber = response.getPhoneNumber().replaceAll("[^0-9]","");
			model.addAttribute("employee", response);
			return "employeesModify";
		} catch (Exception e) {
			log.error("getEmployeeModify Exception : "+e);
			return "error";
		}
	}
	
	@PutMapping("/{id}")
	public String putEmployeeDetail(@ModelAttribute("employee") EmployeeRequest request) {
		try {
			employeesService.putEmployeeDetail(request);
			return "redirect:/employees/"+request.getEmployeeId();
		} catch (Exception e) {
			log.error("postEmployeeModify Exception : "+e);
			return "error";
		}
	}
	
	@GetMapping("/{id}/history")
    public String getEmployeeHistory(@PathVariable("id") Long id, Model model) { //@RequestParam("id") String id
		try {
			model.addAttribute("history", jobHistoryService.getEmployeeHistory(id));
			return "employeesHistory";
		} catch (Exception e) {
			log.error("getEmployeeHistory Exception : "+e);
			return "error";
		}
	}
	
}
