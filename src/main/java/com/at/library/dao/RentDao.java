package com.at.library.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.at.library.dto.BookDTO;
import com.at.library.dto.RentDTO;
import com.at.library.model.Book;
import com.at.library.model.Rent;

public interface RentDao extends CrudRepository<Rent, Integer>{

	//List<RentDTO> findByBook(BookDTO b);
	 //Rent findByBookAndDate(BookDTO book, Date date);
}
