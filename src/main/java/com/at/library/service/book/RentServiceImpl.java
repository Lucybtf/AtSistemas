package com.at.library.service.book;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.at.library.dao.BookDao;
import com.at.library.dao.RentDao;
import com.at.library.dto.RentDTO;

public class RentServiceImpl implements RentService {
	
	@Autowired
	private RentDao rentDao;

	@Autowired
	private DozerBeanMapper dozer;

	//public Rent create
	@Override
	public RentDTO create(RentDTO rentDto){
		
		return null;	
	}
	
	@Override
	public void delete(RentDTO rentDto){
		
	}

	//Devolución de un libro
	@Override
	public RentDTO checkout(){
		return null;
		
	}


}
