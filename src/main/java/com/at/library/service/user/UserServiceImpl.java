package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.at.library.controller.UserController;
import com.at.library.dao.BookDao;
import com.at.library.dao.UserDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.model.Book;
import com.at.library.model.User;
import com.at.library.service.book.BookServiceImpl;
import com.at.library.exceptions.UserNotFoundException;
/**
 * @author Lucia Batista Flores
 *
 */
@Service
@EnableScheduling
public class UserServiceImpl implements UserService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DozerBeanMapper dozer;
	
	public UserDTO create(UserDTO userDTO) throws UserNotFoundException{
		final User user=transform(userDTO);
		activeUser(user.getId());
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
		disableUser(id);
		//userDao.delete(id);
	}

	@Override
	public UserDTO findbyId(Integer id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final User u=userDao.findOne(id);
		if (u == null) throw new UserNotFoundException();
		return transform(u);
	}

	@Override
	public void activeUser(Integer id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final User u = userDao.findOne(id);
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
		final User u = userDao.findOne(id);
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

	/*@Override
	public UserDTO findbyDni(String dni) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final List<User> u = userDao.findByDni(dni);
		if(u == null) throw new UserNotFoundException();
		return transform(u);
	}*/
	
/*	@Override
	public UserDTO findbyName(String name) throws UserNotFoundException {
		// TODO Auto-generated method stub
		final User u = userDao.findByName(name);
		if(u == null) throw new UserNotFoundException();
		return transform(u);
	}*/
	
	
	//Castigar a todos los usuarios que se hayan pasado en el Alquiler
	@Override
	@Scheduled(cron = "0 * * * * ?")
	//@Scheduled(cron = "0 0/1 * * * ?" )
	public void punishedUser(){
		log.debug(String.format("Cron de Castigo de Usuarios"));
	}
	
	//Perdonar a todos los Usuarios que haya pasado su castigo
	@Override
	@Scheduled(cron = "0 * * * * ?")
	public void forgiveUser(){
		log.debug(String.format("Cron de Perdonar de Usuarios"));
	}

	@Override
	public List<UserDTO> listUserDTOs(Iterable<User> findAll){
		
		final Iterator<User> iterator = findAll.iterator();
		final List<UserDTO> result = new ArrayList<>();
		while (iterator.hasNext()) {
			final User b = iterator.next();
			final UserDTO bDTO = transform(b);
			result.add(bDTO);
		}
		return result;
	}
	
	@Override
	public List<UserDTO> findBy(Integer page, Integer size,String dni, String name) {
		// TODO Auto-generated method stub
		List<UserDTO> users=new ArrayList<UserDTO>();
		Iterable<User> findAll;
		if(page!=null && size!=null){
			if(dni!= null && name!=null || dni!= null && name==null || dni==null &&  name!=null){
				
				findAll= userDao.findByDniAndName(dni, name, new PageRequest(page-1,size));
				users=listUserDTOs(findAll);
				return users;
			}
			
			findAll= userDao.findAll(new PageRequest(page-1,size));
			users=listUserDTOs(findAll);
			return users;
		}
		else{
			if(dni!= null && name!=null || dni!= null && name==null || dni==null &&  name!=null){
				
				findAll= userDao.findByDniAndName(dni, name, new PageRequest(0,100));
				users=listUserDTOs(findAll);
				return users;
			}
			findAll= userDao.findAll(new PageRequest(0,10)); //Volver a poner en el DAO para que me devuelva todos los usuarios/books/user activos
			users=listUserDTOs(findAll);
			return users;
		}
		
	}

	
}
