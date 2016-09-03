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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.dto.RentDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.dto.UserDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.UserNotFoundException;
import com.at.library.service.book.BookService;
import com.at.library.service.rent.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {

	private static final Logger log = LoggerFactory.getLogger(RentController.class);
	
	
	@Autowired
	private RentService rentService;
	
	
	@RequestMapping( method = { RequestMethod.POST})
	public RentPostDTO create(@RequestBody RentDTO rentDto) throws UserNotFoundException, BookNotFoundException, EmployeeNotFoundException{
		log.debug(String.format("Creamos el siguiente Alquiler", rentDto));
		return rentService.create(rentDto);
	}
	
	@RequestMapping(value="/{id}", method = { RequestMethod.DELETE})
	public void delete(@PathVariable("id")Integer idrent){
		log.debug(String.format("Devolución del Alquiler con el id del Book: %s", idrent));
		rentService.delete(idrent);
	}
	
	@RequestMapping(method = { RequestMethod.GET})
	public List<HistoryRentedDTO> RentsHistory(@RequestParam(value="page",required = false)Integer page,@RequestParam(value="size",required = false)Integer size){
		log.debug(String.format("Mostramos todos los alquileres"));
		return rentService.RentsHistory(page,size);
	}
	
}
