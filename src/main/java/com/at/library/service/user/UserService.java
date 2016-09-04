package com.at.library.service.user;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.dto.UserDTO;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.User;

public interface UserService {

	/** 
	 * Crea un usuario
	 * @throws UserNotFoundException 
	 * @return UserDTO
	 **/
	UserDTO create(UserDTO user) throws UserNotFoundException;

	/** 
	 * Elimina un usuario por su id
	 * @throws UserNotFoundException 
	 **/
	void delete(Integer id) throws UserNotFoundException;
	
	/** 
	 * Transforma User a UserDTO
	 * @param user
	 * @return UserDTO
	 */
	UserDTO transform(User user);
	
	/**
	 * Transforma UserDTO a User
	 * @param user
	 * @return User
	 */
	User transform(UserDTO user);
	
	/**
	 * Encontrar un usuario por Id
	 * @param id
	 * @throws UserNotFoundException
	 * @return Devuelvo un UserDTO 
	 * */
	UserDTO findbyId(Integer id) throws UserNotFoundException;

	/**
	 * Activar un usuario
	 * @param id
	 * @throws UserNotFoundException 
	 * **/
	void activeUser(Integer id) throws UserNotFoundException;
	
	/**
	 * Desactivar un usuario
	 * @param id
	 * @throws UserNotFoundException 
	 * **/
	void disableUser(Integer id) throws UserNotFoundException;
	
	/**
	 * Castiga a los Usuarios si supera el plazo de alquiler de 30 días
	 */
	void punishedUser();

	/**
	 * Perdona a los usuarios si hoy coincide con la fecha de finalización del castigo
	 */
	void forgiveUser();
	
	/**
	 *  Transforma a una lista de UserDTOs
	 * @param findAll
	 * @return Devuelve una lista de UserDTOs
	 */
	List<UserDTO> listUserDTOs(Iterable<User> findAll);

	/**
	 * Busqueda de Usuarios por name y dni(Si pasa 1, los 2 o ninguno)
	 * @param page
	 * @param size
	 * @param dni
	 * @param name
	 * @return Devuelve una lista de UserDTOs
	 */
	List<UserDTO> findBy(Integer page, Integer size, String dni, String name);
	
	//UserDTO findbyDni(String dni) throws UserNotFoundException;

	//UserDTO findbyName(String name) throws UserNotFoundException;


}
