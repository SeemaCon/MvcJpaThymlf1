package com.leaf.emp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.leaf.emp.model.Employee;

public interface IEmployeeService {

	Integer saveEmployee(Employee e);
	List<Employee> getAllEmployees();
	void deleteEmployee(Integer id);
	
	Employee getOneEmployee(Integer id);
	void updateEmployee(Employee e);
	
	Page<Employee> getAllEmployees(Pageable pageable);
	
	boolean isEmailExist(String email);
	
}
