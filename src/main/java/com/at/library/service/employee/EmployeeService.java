package com.at.library.service.employee;

import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.UserDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Employee;

public interface EmployeeService {

	/**
	 * Crear un EmployeeDTO
	 * @param user 
	 * @return EmployeeDTO
	 */
	EmployeeDTO create(EmployeeDTO user);

	/**
	 * Busqueda por id a un empleado
	 * @param id
	 * @return EmployeeDTO
	 * @throws EmployeeNotFoundException
	 */
	EmployeeDTO findbyId(Integer id) throws EmployeeNotFoundException;

	/**
	 * Borrar un Employee
	 * @param employee
	 * @throws EmployeeNotFoundException
	 */
	void delete(Integer employee) throws EmployeeNotFoundException;
	
	/**
	 * Transforma Employee a EmployeeDTO
	 * @param employee
	 * @return EmployeeDTO
	 */
	EmployeeDTO transform(Employee employee);
	
	/**
	 * Transforma EmployeeDTO a Employee
	 * @param employee
	 * @return Employee
	 */
	Employee transform(EmployeeDTO employee);

}
