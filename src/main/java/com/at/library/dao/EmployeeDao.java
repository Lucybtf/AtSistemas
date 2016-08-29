package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;
import com.at.library.model.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

}
