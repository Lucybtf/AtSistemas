package com.at.library.service.employee;

import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.UserDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Employee;

public interface EmployeeService {

	EmployeeDTO create(EmployeeDTO user);

	EmployeeDTO findbyId(Integer id) throws EmployeeNotFoundException;

	void delete(Integer user) throws EmployeeNotFoundException;
	
	EmployeeDTO transform(Employee employee);
	
	Employee transform(EmployeeDTO employee);

}
