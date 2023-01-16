package com.leaf.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leaf.emp.model.Employee;
import com.leaf.emp.service.IEmployeeService;

@Controller
//@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService service;

	@GetMapping("/register")
	public String showReg(Model model) {
		//Form Backing object
		model.addAttribute("employee", new Employee());
		return "EmployeeReg";
	}

	@PostMapping("/save")
	public String saveEmp(
			@ModelAttribute Employee employee,
			Model model) 
	{
		Integer id  = service.saveEmployee(employee);
		model.addAttribute("message", "Employee '"+id+"' saved");
		
		//Form Backing object
		model.addAttribute("employee", new Employee());
		return "EmployeeReg";
	}
	
	@GetMapping("/all")
	public String getAllEmps(
						@PageableDefault(page=0,size=3)Pageable pageable,
						Model model) {
		Page<Employee>  page = service.getAllEmployees(pageable);
		List<Employee> list =  page.getContent();
		model.addAttribute("list", list);
		model.addAttribute("page",page);
		return "Data";
	}

	@GetMapping("/delete")
	public String removeEmp(@RequestParam Integer eid) {
		service.deleteEmployee(eid);
		return "redirect:all";
	}
	
	@GetMapping("/edit")
	public String showEdit(@RequestParam Integer eid, Model model) {
		Employee emp = service.getOneEmployee(eid);
		model.addAttribute("employee",emp);
		return "Edit";
	}
	
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee employee) {
		service.updateEmployee(employee);
		return "redirect:all";
	}	
	
	
	///for ajex function 
	@GetMapping("/chkmail")
	public @ResponseBody  String validateemail(@RequestParam String email)
	{
		String msg = "";
		if(service.isEmailExist(email))
		{
			return email+" Already Exist" + "  Use another email";
		}
		return msg;
	}
	
}

