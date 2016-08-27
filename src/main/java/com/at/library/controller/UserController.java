package com.at.library.controller;

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
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.User;
import com.at.library.service.user.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/*FUNCIONA PERFECTAMENTE*/
	@RequestMapping(value="/{id}" , method = {RequestMethod.GET})
	public UserDTO findOne(@PathVariable("id")Integer id){
		log.debug(String.format("Buscando el Usuario con el id %s", id));
		log.debug(String.format("Buscando:", userService.findbyId(id)));
		return userService.findbyId(id);
	}
	
	/*FUNCIONA PERFECTAMENTE*/
	@RequestMapping( method = { RequestMethod.POST })
	public UserDTO create(@RequestBody UserDTO user) {
		log.debug(String.format("Vamos a crear el libro siguiente", user));
		return userService.create(user);
	}
	
	/*FUNCIONA PERFECTAMENTE*/
	@RequestMapping(value="/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer user) {
		log.debug("Borrar un usuario  del sistema dado su id: %s", user);
		userService.delete(user);
	}
	
	/*FUNCIONA PERFECTAMENTE: pero no se pinta en el JSON*/
	@RequestMapping( value="/active/{id}", method = { RequestMethod.PUT })
	public void activeUser(@PathVariable("id")Integer id){
		userService.activeUser(id);
	}
	
	/*FUNCIONA PERFECTAMENTE: pero no se pinta en el JSON*/
	@RequestMapping( value="/disable/{id}", method = { RequestMethod.PUT })
	public void disableUser(@PathVariable("id")Integer id){
		userService.disableUser(id);
	}
	

}
