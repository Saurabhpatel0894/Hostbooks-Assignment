package com.hostbooks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostbooks.exception.EmployeeException;
import com.hostbooks.model.Employee;
import com.hostbooks.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	public EmployeeRepository employeeRepo;

	@Override
	public Employee createEmployee(Employee emp) throws EmployeeException {
		
		Employee employee = employeeRepo.save(emp);
		
		return employee;
	}

	@Override
	public List<Employee> listAllEmployee() throws EmployeeException {
		
		List<Employee> empList = employeeRepo.findAll();
		
		if(empList.size()!= 0)
		{
			return empList;
		}
		else
		{
			throw new EmployeeException("No Employee Exist in Database!");
		}
	}

	@Override
	public Employee updateEmployee(Employee emp) throws EmployeeException {
		Optional<Employee> em = employeeRepo.findById(emp.getEmp_id());
		if (em.isEmpty())
		{
			throw new EmployeeException("No Such Employee exist in Database!");
		}
		else {
			return employeeRepo.save(emp);

		}
	}

	@Override
	public Employee deleteEmployee(Integer emp_id) throws EmployeeException {
		 Optional<Employee> opt = employeeRepo.findById(emp_id);
         if (opt.isPresent()) {
             Employee existingEmployee = opt.get();
             employeeRepo.delete(existingEmployee);
             return existingEmployee;
         }
         else {
             throw new EmployeeException("No Employee found with given details");
         }
	}

	@Override
	public Employee viewEmployeeById(Integer emp_id) throws EmployeeException {
		Optional<Employee> emp = employeeRepo.findById(emp_id);
		if (emp.isEmpty())
			throw new EmployeeException("No Employee found by this ID!");
		else {

			return emp.get();
		}
	}

}
