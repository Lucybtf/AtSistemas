package com.at.library.service.employee;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.at.library.controller.UserController;
import com.at.library.dao.EmployeeDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.EmployeeDTO;
import com.at.library.dto.UserDTO;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.model.Book;
import com.at.library.model.Employee;
import com.at.library.service.book.BookService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	//Comprobar que el JSON viene con el formato adecuado
	@Override
	public EmployeeDTO create(EmployeeDTO emplDTO) {
		final Employee em=transform(emplDTO);
		return transform(employeeDao.save(em));
		
	}
	
	@Override
	public EmployeeDTO transform(Employee employee) {
		return dozer.map(employee, EmployeeDTO.class);
	}

	@Override
	public Employee transform(EmployeeDTO employee) {
		return dozer.map(employee, Employee.class);
	}

	@Override
	public EmployeeDTO findbyId(Integer id) throws EmployeeNotFoundException{
		final Employee e=employeeDao.findOne(id);
		if(e == null) throw new EmployeeNotFoundException();
		return transform(e);
	}

	@Override
	public void delete(Integer id) throws EmployeeNotFoundException{
		final Employee e=employeeDao.findOne(id);
		if(e == null) throw new EmployeeNotFoundException();
		employeeDao.delete(id);
	}




}
