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
import com.at.library.service.book.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {

	private static final Logger log = LoggerFactory.getLogger(RentController.class);
	
	@Autowired
	private RentService rentService;
	
	@RequestMapping( method = { RequestMethod.GET})
	public RentDTO create(@RequestBody RentDTO rentDto){
		log.debug(String.format("Creamos el siguiente Alquiler", rentDto));
		return rentService.create(rentDto);
	}
	
	@RequestMapping( method = { RequestMethod.DELETE})
	public void delete(@PathVariable("id")Integer idrent){
		log.debug(String.format("Eliminar el Alquiler con el id: %s", idrent));
		rentService.delete(idrent);
	}
	
}
