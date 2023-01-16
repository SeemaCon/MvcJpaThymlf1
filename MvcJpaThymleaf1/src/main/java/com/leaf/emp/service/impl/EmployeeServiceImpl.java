package com.leaf.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leaf.emp.exception.EmployeeNotFoundException;
import com.leaf.emp.model.Employee;
import com.leaf.emp.repo.EmployeeRepo;
import com.leaf.emp.service.IEmployeeService;

@Service    //calculations,logics, TxManagement
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepo repo; //HAS-A

	@Override
	public Integer saveEmployee(Employee e) {
		//calculations
		double esal = e.getEsal();
		double hra = esal * 20/100.0;
		double da = esal * 10/100.0;
		e.setHra(hra);
		e.setDa(da);
		//save employee
		e = repo.save(e);
		return e.getEid();
	}

	@Override
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	@Override
	public void deleteEmployee(Integer id) {
		Employee e = getOneEmployee(id);
		repo.delete(e);
		/*
		if(repo.existsById(id)) {
			repo.deleteById(id);
		} else {
			throw new EmployeeNotFoundException("Employee '"+id+"' Not Exist");
		}*/
	}

	@Override
	public Employee getOneEmployee(Integer id) {
		Employee e = repo.findById(id)
				.orElseThrow(
						()-> new EmployeeNotFoundException("Employee '"+id+"' Not Exist")
						);
		return e;
		/*
		Optional<Employee> opt = repo.findById(id);
		if(opt.isPresent()) {
			Employee e = opt.get();
			return e;
		}else {
			throw new EmployeeNotFoundException("Employee '"+id+"' Not Exist");
		}*/
	}

	@Override
	public void updateEmployee(Employee e) {
		if(repo.existsById(e.getEid())) {
			//calculations
			double esal = e.getEsal();
			double hra = esal * 20/100.0;
			double da = esal * 10/100.0;
			e.setHra(hra);
			e.setDa(da);
			repo.save(e);
		} else {
			//throw EmployeeNotFoundException
			throw new EmployeeNotFoundException("Employee '"+e.getEid()+"' not exist");
		}
	}

	@Override
	public Page<Employee> getAllEmployees(Pageable pageable) {
		Page<Employee>  page = repo.findAll(pageable);
		return page;
	}

	@Override
	public boolean isEmailExist(String email) {
		System.out.println("in service impl" +  repo.getEmpCountByEmail(email));
		return repo.getEmpCountByEmail(email)>0;
	}
	

}

