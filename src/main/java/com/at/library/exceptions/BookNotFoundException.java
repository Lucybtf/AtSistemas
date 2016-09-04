package com.at.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


//@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception {

	private static final long serialVersionUID = 1644583033208122457L;

	public BookNotFoundException(){
		super("Book Not Found");
	}
	
}
