package com.at.library.service.book;

import com.at.library.dto.BookDTO;
import com.at.library.dto.UserDTO;
import com.at.library.model.User;

public interface UserService {

	/** 
	 * Crea un usuario
	 **/
	UserDTO create(UserDTO user);

	/** 
	 * Elimina un usuario por su id
	 **/
	void delete(Integer id);
	
	/** 
	 * Transforma un User a UserDTO
	 **/
	UserDTO transform(User user);
	
	/** 
	 * Transforma un UserDTO a User
	 **/
	User transform(UserDTO user);
	
	/**
	 * Encontrar un usuario por Id
	 * */
	UserDTO findbyId(Integer id);

	/**
	 * Activar un usuario
	 * **/
	void activeUser(Integer id);
	
	/**
	 * Desactivar un usuario
	 * **/
	void disableUser(Integer id);

}
