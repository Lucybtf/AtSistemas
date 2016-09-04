package com.at.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.UserDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.service.employee.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/{id}" , method = {RequestMethod.GET})
	public EmployeeDTO findOne(@PathVariable("id")Integer id) throws EmployeeNotFoundException{
		log.debug(String.format("Buscando el Usuario con el id %s", id));
		return employeeService.findbyId(id);
	}
	
	@RequestMapping( method = { RequestMethod.POST })
	public EmployeeDTO create(@RequestBody EmployeeDTO user) {
		log.debug(String.format("Vamos a crear el libro siguiente", user));
		return employeeService.create(user);
	}
	
	
	@RequestMapping( value="/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer user) throws EmployeeNotFoundException {
		log.debug("Borrar un usuario  del sistema dado su id: %s", user);
		employeeService.delete(user);
	}
	
}
