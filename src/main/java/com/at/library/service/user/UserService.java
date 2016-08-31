package com.at.library.service.user;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.dto.UserDTO;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.User;

public interface UserService {

	/** 
	 * Crea un usuario
	 **/
	UserDTO create(UserDTO user);

	/** 
	 * Elimina un usuario por su id
	 * @throws UserNotFoundException 
	 **/
	void delete(Integer id) throws UserNotFoundException;
	
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
	 * @throws UserNotFoundException 
	 * */
	UserDTO findbyId(Integer id) throws UserNotFoundException;

	/**
	 * Activar un usuario
	 * @throws UserNotFoundException 
	 * **/
	void activeUser(Integer id) throws UserNotFoundException;
	
	/**
	 * Desactivar un usuario
	 * @throws UserNotFoundException 
	 * **/
	void disableUser(Integer id) throws UserNotFoundException;

	/**
	 * Buscar el Usuario por Dni
	 * @param dni
	 * @return
	 * @throws UserNotFoundException 
	 */
	UserDTO findbyDni(String dni) throws UserNotFoundException;

	void punishedUser();

	void forgiveUser();

	UserDTO findbyName(String name) throws UserNotFoundException;

	

}
