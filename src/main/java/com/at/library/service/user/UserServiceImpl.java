package com.at.library.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dozer.DozerBeanMapper;
import org.joda.time.DateTime;
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
import com.at.library.model.PunishmentUser;
import com.at.library.model.Rent;
import com.at.library.model.User;
import com.at.library.service.book.BookServiceImpl;
import com.at.library.service.punishmentuser.PunishmentUserService;
import com.at.library.exceptions.UserNotFoundException;

@Service
@EnableScheduling
public class UserServiceImpl implements UserService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PunishmentUserService punishedUserservice;
	
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
	
	public Integer diferenceBetweenDays(Date day1, Date day2){
		
		long days2=(day1.getTime()-day2.getTime())/(24 * 60 * 60 * 1000);
		return (int)days2;
	}
	
	//Castigar a todos los usuarios que se hayan pasado en el Alquiler
	@Override
	@Scheduled(cron = "0 * * * * ?")
	public void punishedUser(){
		log.debug(String.format("Cron de Castigo de Usuarios"));
		List<Rent> rents=userDao.findReturnRents();
		final Iterator<Rent> iterator = rents.iterator();
		
		while (iterator.hasNext()) {
			final Rent r = iterator.next();
			
			Integer delaydays=diferenceBetweenDays(r.getEndDate(),r.getInitDate());
			DateTime initpunishmentdays = new DateTime(r.getEndDate()); 
			
			if(diferenceBetweenDays(r.getEndDate(),r.getInitDate())>30){ //Si el alquiler es superior a 30 días, procedemos a castigar al usuario
				
				PunishmentUser pu = new PunishmentUser();
				pu.setStartDate(r.getEndDate()); //Colocamos la fecha de fin
				pu.setEndDate(initpunishmentdays.plusDays(delaydays).toDate()); //Sumamos tantos días como se haya retrasado
				pu.setPunishmentdays(delaydays); //Guardamos los días de castigos
				User u=r.getUser(); //Obtenemos el Usuario
				u.setStatususer(StatusEnum.PUNISHED); //Cambiamos el estado a castigado
				pu.setUser(u);	
				
				userDao.save(u);
				punishedUserservice.create(pu);
			}
			
		}
	}
	
	//Perdonar a todos los Usuarios que haya pasado su castigo
	@Override
	@Scheduled(cron = "0 * * * * ?")
	public void forgiveUser(){
		log.debug(String.format("Cron de Perdonar de Usuarios"));
		List<Integer> users=userDao.findUsersPunished();
		final Iterator<Integer> iterator = users.iterator();
		while (iterator.hasNext()) {
			User u=userDao.findOne(iterator.next());
			PunishmentUser pu = punishedUserservice.findOne(u.getId());
		
			//Comprobar si endDate es igual a currentdate
			DateTime dt=new DateTime(pu.getEndDate()); //Probado con la fecha de inicio en vez de con la de fin
			DateTime da= new DateTime();
			
			if(dt.getDayOfMonth()==da.getDayOfMonth()){
				
				u.setStatususer(StatusEnum.ACTIVE);
				userDao.save(u);
			}
			
		}
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

	
}
