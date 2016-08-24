package com.at.library.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.at.library.enums.StatusEnum;

@Entity
public class User {

	@Id
	private String dni;
	
	private String name;
	
	private String surname1;
	
	private String surname2;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum statususer;
	
	/* Establecemos una relaci�n bidireccional con usuario*/
	@OneToMany(mappedBy="user")
	private List<Rent> rent;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname1() {
		return surname1;
	}

	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}

	public String getSurname2() {
		return surname2;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public StatusEnum getStatususer() {
		return statususer;
	}

	public void setStatususer(StatusEnum statususer) {
		this.statususer = statususer;
	}

	public List<Rent> getRent() {
		return rent;
	}

	public void setRent(List<Rent> rent) {
		this.rent = rent;
	}

	
	
	
}
