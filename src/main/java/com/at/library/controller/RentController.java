package com.at.library.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.UserDTO;
import com.at.library.service.book.BookService;
import com.at.library.service.rent.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {

	private static final Logger log = LoggerFactory.getLogger(RentController.class);
	
	//LA LOGICA VA EN EL SERVICIO
	@Autowired
	private RentService rentService;
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping( method = { RequestMethod.POST})
	public RentDTO create(@RequestBody RentDTO rentDto){
		log.debug(String.format("Creamos el siguiente Alquiler", rentDto));
		return rentService.create(rentDto);
	}
	
	@RequestMapping( method = { RequestMethod.DELETE})
	public void delete(@PathVariable("id")Integer idrent){
		log.debug(String.format("Eliminar el Alquiler con el id: %s", idrent));
		rentService.delete(idrent);
	}
	
	@RequestMapping( value="/{date}", method = { RequestMethod.GET})
	public RentDTO findDateInit( @RequestBody BookDTO book, @PathVariable("date")Date init){
		log.debug(String.format("Buscando el Rent  Fecha %s",  init));
		//return rentService.findbyBookAndDate(book, init);
		return null;
	}
	
/*	@RequestMapping( value="/{idbook}", method = { RequestMethod.PUT })
	public void rentBook(@PathVariable("idbook")Integer id){
		rentService.rentBook(id);
	}
	

	@RequestMapping( value="/disable/{id}", method = { RequestMethod.PUT })
	public void returnBook(@PathVariable("id")Integer id){
		rentService.returnBook(id);
	}*/
	
}
