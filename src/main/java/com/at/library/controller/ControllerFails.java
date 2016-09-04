package com.at.library.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.at.library.dto.ApiErrorDTO;
import com.at.library.exceptions.BookNotFoundException;
import com.at.library.exceptions.EmployeeNotFoundException;
import com.at.library.exceptions.RentNotFoundException;
import com.at.library.exceptions.UserNotFoundException;

@ControllerAdvice(basePackages = {"com.at.library.controller"})
public class ControllerFails {

	@ResponseBody
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO BookException(BookNotFoundException e) {
		return new ApiErrorDTO("404", e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO UserException(UserNotFoundException e) {
		return new ApiErrorDTO("404", e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(RentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO RentException(RentNotFoundException e) {
		return new ApiErrorDTO("404", e.getMessage());
	}
	
	@ResponseBody
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO EmployeeException(EmployeeNotFoundException e) {
		return new ApiErrorDTO("404", e.getMessage());
	}
}
