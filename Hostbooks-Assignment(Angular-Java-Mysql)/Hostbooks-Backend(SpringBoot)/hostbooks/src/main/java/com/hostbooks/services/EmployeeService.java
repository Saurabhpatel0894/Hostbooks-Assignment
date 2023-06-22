package com.hostbooks.services;

import java.util.List;

import com.hostbooks.exception.EmployeeException;
import com.hostbooks.model.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee emp) throws EmployeeException;
	
	public List<Employee> listAllEmployee() throws EmployeeException;

	public Employee updateEmployee(Employee emp) throws EmployeeException;

	public Employee deleteEmployee(Integer emp_id) throws EmployeeException;

	public Employee viewEmployeeById(Integer emp_id) throws EmployeeException;

}
