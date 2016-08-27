package com.at.library.service.employee;

import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.UserDTO;
import com.at.library.model.Employee;

public interface EmployeeService {

	EmployeeDTO create(EmployeeDTO user);

	EmployeeDTO findbyId(Integer id);

	void delete(Integer user);
	
	EmployeeDTO transform(Employee employee);
	
	Employee transform(EmployeeDTO employee);

}
