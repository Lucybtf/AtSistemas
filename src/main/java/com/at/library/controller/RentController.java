package com.at.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.RentDTO;
import com.at.library.dto.UserDTO;
import com.at.library.service.rent.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {

	private static final Logger log = LoggerFactory.getLogger(RentController.class);
	
	//LA LOGICA VA EN EL SERVICIO
	private RentService rentService;
	
	@RequestMapping( method = { RequestMethod.GET})
	public RentDTO create(@RequestBody RentDTO rentDto){
		log.debug(String.format("Creamos el siguiente Alquiler", rentDto));
		log.debug(String.format("Alquiler", rentService.create(rentDto)));
		return rentService.create(rentDto);
	}
	
	@RequestMapping( method = { RequestMethod.DELETE})
	public void delete(@PathVariable("id")Integer idrent){
		log.debug(String.format("Eliminar el Alquiler con el id: %s", idrent));
		rentService.delete(idrent);
	}
	
	@RequestMapping( value="/{id}", method = { RequestMethod.GET})
	public RentDTO findOne(@PathVariable("id")Integer id){
		log.debug(String.format("Buscando el Rent con el id %s", id));
		return rentService.findbyId(id);
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
