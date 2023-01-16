package com.leaf.emp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leaf.emp.model.Employee;

public interface EmployeeRepo
extends JpaRepository<Employee, Integer> {
	
	@Query("Select count(*) from Employee where email=:email")
	Integer getEmpCountByEmail(String email);

}

