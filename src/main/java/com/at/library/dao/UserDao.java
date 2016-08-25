package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;

import com.at.library.model.User;

public interface UserDao extends CrudRepository<User, Integer> {
	

}
