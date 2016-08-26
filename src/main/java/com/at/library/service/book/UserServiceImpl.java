package com.at.library.service.book;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.UserDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Book;
import com.at.library.model.User;

/**
 * @author Formacion8
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
		return transform(userDao.findOne(id));
	}

	@Override
	public void activeUser(Integer id) {
		// TODO Auto-generated method stub
		final User u = transform(findbyId(id));
		if(u.getStatususer() == com.at.library.enums.StatusEnum.DISABLE){
			u.setStatususer(com.at.library.enums.StatusEnum.ACTIVE);
		}	
	}

	@Override
	public void disableUser(Integer id) {
		// TODO Auto-generated method stub
		final User u = transform(findbyId(id));
		if(u.getStatususer() == com.at.library.enums.StatusEnum.ACTIVE){
			u.setStatususer(com.at.library.enums.StatusEnum.DISABLE);
		}
	}
}
