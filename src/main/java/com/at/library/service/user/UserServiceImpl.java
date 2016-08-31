package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.controller.UserController;
import com.at.library.dao.UserDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Book;
import com.at.library.model.User;
import com.at.library.exceptions.UserNotFoundException;
/**
 * @author Lucia Batista Flores
 *
 */
@Service
public class UserServiceImpl implements UserService{


	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	public UserDTO create(UserDTO userDTO){
		final User user=transform(userDTO);
		return transform(userDao.save(user));
	}
	
	@Override
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}

	@Override
	public User transform(UserDTO user) {
		return dozer.map(user, User.class);
	}

	@Override
	public void delete(Integer id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final User u= userDao.findOne(id);
		if (u == null) 
			throw new UserNotFoundException();
			userDao.delete(id);
	}

	@Override
	public UserDTO findbyId(Integer id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final User u=userDao.findOne(id);
		if (u == null) 
			throw new UserNotFoundException();
		return transform(u);
	}

	@Override
	public void activeUser(Integer id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final User u = transform(findbyId(id));
		if(u.getStatususer()== null){
			u.setStatususer(StatusEnum.ACTIVE);
		}
		else{
			if(u.getStatususer() == StatusEnum.DISABLE){
				u.setStatususer(StatusEnum.ACTIVE);
			}	
			
		}
		userDao.save(u);
	}

	@Override
	public void disableUser(Integer id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final User u = transform(findbyId(id));
		if(u.getStatususer()== null){
			u.setStatususer(StatusEnum.DISABLE);
		}
		else{
			if(u.getStatususer() == StatusEnum.ACTIVE){
				u.setStatususer(StatusEnum.DISABLE);
			}	
			
		}
		userDao.save(u);
	}

	@Override
	public UserDTO findbyDni(String dni) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final User u = userDao.findByDni(dni);
		if(u == null) throw new UserNotFoundException();
		return transform(u);
	}
	
	@Override
	public UserDTO findbyName(String name) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final User u = userDao.findByName(name);
		if(u == null) throw new UserNotFoundException();
		return transform(u);
	}
	
	
	//Castigar a todos los usuarios que se hayan pasado en el Alquiler
	@Override
	public void punishedUser(){
		
	}
	
	//Perdonar a todos los Usuarios que haya pasado su castigo
	@Override
	public void forgiveUser(){
		
	}


}
