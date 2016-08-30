package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.service.migracion.*;

//@ControllerAdvice(basepackages ={""})
@RestController
@RequestMapping(value = "/migration")
public class MigrationController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);
	
	private MigrationService migrationService;
	
	/*@ResponseBody
	@ExceptionHandler(UserNotFOundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) */
	@RequestMapping(method = { RequestMethod.GET})
	public void migrate(){
		log.debug(String.format("Devolvemos todos los libros disponibles"));
		migrationService.migratedata();
		//return bookservice.findBooksAvailable();
	}
}
