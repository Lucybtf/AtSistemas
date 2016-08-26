package com.at.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.dto.UserDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.User;
import com.at.library.service.book.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	@RequestMapping( method = { RequestMethod.POST })
	public UserDTO create(@RequestBody UserDTO user) {
		return userService.create(user);
	}
	
	@RequestMapping( method = { RequestMethod.DELETE })
	public void delete(@RequestBody Integer user) {
		userService.delete(user);
	}
	
	@RequestMapping( method = { RequestMethod.PUT })
	public void activeUser(Integer id){
		userService.activeUser(id);
	}
	
	@RequestMapping( method = { RequestMethod.PUT })
	public void disableUser(Integer id){
		userService.disableUser(id);
	}

}
