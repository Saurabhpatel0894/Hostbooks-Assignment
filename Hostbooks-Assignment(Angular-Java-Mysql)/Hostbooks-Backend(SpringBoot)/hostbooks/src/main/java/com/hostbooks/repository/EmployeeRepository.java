package com.hostbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostbooks.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
