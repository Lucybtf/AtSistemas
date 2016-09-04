package com.at.library.service.punishmentuser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.at.library.dao.PunishmentUserDao;
import com.at.library.dao.RentDao;
import com.at.library.model.PunishmentUser;
import com.at.library.service.employee.EmployeeService;
import com.at.library.service.user.UserService;
import com.at.library.service.user.UserServiceImpl;

@Service
public class PunishmentUserServiceImpl implements PunishmentUserService{

	private static final Logger log = LoggerFactory.getLogger(PunishmentUserServiceImpl.class);
	
	@Autowired
	private PunishmentUserDao punishmentUserDao;
	
	public void create(PunishmentUser p){
		//BUsco el castigo por si ya se ha registrado
		PunishmentUser res=punishmentUserDao.findOne(p.getUser(),p.getStartDate(),p.getEndDate(),p.getPunishmentdays());
		if(res==null) //Si es null lo guardo en la BD
			punishmentUserDao.save(p);
		
	}
	
	public PunishmentUser findOne(Integer iduser){
		return punishmentUserDao.findUser(iduser);
		
	}
}
