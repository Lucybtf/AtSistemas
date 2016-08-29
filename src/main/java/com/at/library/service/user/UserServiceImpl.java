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
import com.at.library.model.Book;
import com.at.library.model.User;

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
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	@Override
	public UserDTO findbyId(Integer id) {
		// TODO Auto-generated method stub
		final User u=userDao.findOne(id);
		return transform(u);
	}

	@Override
	public void activeUser(Integer id) {
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
	public void disableUser(Integer id) {
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
	public UserDTO findbyDni(String dni) {
		// TODO Auto-generated method stub
		return transform(userDao.findByDni(dni));
	}


}
