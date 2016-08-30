package com.at.library.service.migracion;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.at.library.controller.BookController;
import com.at.library.dto.BookDTO;
import com.at.library.dto.RentMigrationDTO;
import com.at.library.model.Book;
import com.at.library.model.Rent;
import com.at.library.service.book.BookService;



public class MigrationServiceImpl implements MigrationService{

	final String url = "http://192.168.11.57:8080/rent";
	
	private static final Logger log = LoggerFactory.getLogger(MigrationServiceImpl.class);
	
	public void migratedata(){
		
		//System.out.println( new RestTemplate().getForEntity(url,  RentMigrationDTO.class));
		//final BookDTO book = new RestTemplate().getForObject(url, BookDTO.class);
		
		//int i=0;
		
		RentMigrationDTO res[]= new RentMigrationDTO[0];
		log.debug("Hacemos la peticion");
		res= new RestTemplate().getForObject(url, RentMigrationDTO[].class );
			System.out.println(res.toString());
		
		
	}
}
