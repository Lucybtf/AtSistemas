package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;

import com.at.library.model.Book;
import com.at.library.model.Employee;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

}
