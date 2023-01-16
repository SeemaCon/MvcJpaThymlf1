package com.leaf.emp.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@jakarta.persistence.Entity
@Table(name="emptab")
public class Employee {
	@Id
	@GeneratedValue
	private Integer eid;
	
	private String ename;
	private String email;
	
	private Double esal;
	private String dept;
	
	private Double hra;
	private Double da;	
}