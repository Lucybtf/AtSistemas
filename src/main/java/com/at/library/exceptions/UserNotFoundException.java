package com.at.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;

@ResponseBody
//@ExceptionHandler(UserNotFoundException.class)  Esta anotacion se supone que va en el controlador
@ResponseStatus(value=HttpStatus.NOT_FOUND) //, reason ="User not Found" tambien te escribe el mensaje de la exception
public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = -930159632594513828L;
	//private String msg;
	
	public UserNotFoundException(){
		super("User Not Found");
	}
	/*public String getMsg() {
		return msg;
	}*/
	
}
