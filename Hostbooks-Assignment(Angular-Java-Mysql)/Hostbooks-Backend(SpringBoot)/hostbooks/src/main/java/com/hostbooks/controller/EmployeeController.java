package com.hostbooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hostbooks.exception.EmployeeException;
import com.hostbooks.model.Employee;
import com.hostbooks.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	
	@Autowired
	public EmployeeService empService;
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee emp)throws EmployeeException
	{
		return new ResponseEntity<Employee>(empService.createEmployee(emp),HttpStatus.CREATED);
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> viewAllEmployee()throws EmployeeException
	{
		List<Employee> empList = empService.listAllEmployee();
		return new ResponseEntity<List<Employee>>(empList,HttpStatus.OK);
	}
	
	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee emp)throws EmployeeException
	{
		return new ResponseEntity<Employee>(empService.updateEmployee(emp),HttpStatus.OK);
	}
	
	@DeleteMapping("/employees/{empId}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("empId")Integer empId)throws EmployeeException
	{
		return new ResponseEntity<Employee>(empService.deleteEmployee(empId), HttpStatus.OK);
	}
	
	@GetMapping("/employees/{EmpId}")
	public ResponseEntity<Employee> viewEmployeeById(@PathVariable("empId")Integer empId)throws EmployeeException
	{
		return new ResponseEntity<Employee>(empService.viewEmployeeById(empId), HttpStatus.ACCEPTED);
	}
}
