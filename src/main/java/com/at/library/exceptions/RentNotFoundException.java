package com.at.library.exceptions;

public class RentNotFoundException extends Exception{
	
	private static final long serialVersionUID = 531006446982944085L;

	public RentNotFoundException(){
		super("Rent Not Found");
	}
	

}
