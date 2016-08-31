package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;
import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	
	/* Busquedas por dni y por nombre en el Usuario*/
	User findByDni(String dni);
	User findByName(String name);
}
