package com.at.library.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.User;
import com.at.library.service.user.UserService;
import com.at.library.exceptions.*;


@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping( method = { RequestMethod.POST })
	public UserDTO create(@RequestBody UserDTO user) throws UserNotFoundException {
		log.debug(String.format("Vamos a crear el Usuario", user));
		return userService.create(user);
	}
	
	@RequestMapping(value="/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer user) throws UserNotFoundException {
		log.debug("Borrar un usuario  del sistema dado su id: %s", user);
		userService.delete(user);
	}
	
	@RequestMapping( method = {RequestMethod.GET})
	public List<UserDTO> findBy(@RequestParam(value="page",required = false)Integer page,@RequestParam(value="size",required = false)Integer size,@RequestParam(value="dni", required = false)String dni,@RequestParam(value="name", required = false)String name) throws UserNotFoundException{
		log.debug(String.format("Buscando el Usuario con el dni %s y nombre %s", dni, name));
		return userService.findBy(page,size,dni, name);
	}
	

}
