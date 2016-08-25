package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;

import com.at.library.model.Book;
import com.at.library.model.Rent;

public interface RentDao extends CrudRepository<Rent, Integer>{

}
