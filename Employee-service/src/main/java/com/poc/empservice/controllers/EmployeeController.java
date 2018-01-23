package com.poc.empservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.empservice.model.Employee;
import com.poc.empservice.services.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/getallrecord", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getRecords() {
		System.out.println("get all records");
		List<Employee> emp = employeeService.getAllList();
		return emp;
	}

	@RequestMapping(value = "/getrecord/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getRecord(@PathVariable("id") int id) {
		System.out.println("get single record");
		Employee emp = employeeService.getRecord(id);
		return emp;
	}

}
