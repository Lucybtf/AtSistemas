package com.at.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends Exception {


	private static final long serialVersionUID = 1969511977784301117L;

	public EmployeeNotFoundException(){
		super("Employee Not Found");
	}
	
}
