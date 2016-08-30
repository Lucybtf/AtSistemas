package com.at.library.controller;

import java.util.Date;
import java.util.List;

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
import com.at.library.exceptions.UserNotFoundException;
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
	public RentDTO create(@RequestBody RentDTO rentDto) throws UserNotFoundException{
		log.debug(String.format("Creamos el siguiente Alquiler", rentDto));
		return rentService.create(rentDto);
	}
	
	@RequestMapping( method = { RequestMethod.DELETE})
	public void delete(@PathVariable("id")Integer idrent){
		log.debug(String.format("Eliminar el Alquiler con el id: %s", idrent));
		rentService.delete(idrent);
	}
	
	@RequestMapping( value="/{id}", method = { RequestMethod.PUT })
	public void returnBook(@PathVariable("id")Integer idBook){
		log.debug(String.format("Buscando el Rent cuyo idBook es  %s",  idBook));
		rentService.returnBook(idBook);
	}
	
	@RequestMapping(value="/{id}", method = { RequestMethod.GET})
	public List<RentDTO> rentBookHistory(@PathVariable("id") Integer idbook){
		log.debug(String.format("Historial de Alquileres de un Libro: %s", idbook));
		return rentService.rentBookHistory(idbook);
	}
}
