package com.at.library.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class UserDTO implements Serializable {


	private static final long serialVersionUID = 3827455324138571715L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String dni;
	
	private String name;
	
	private String surname;
	
	private String phone;
	
	private String email;
	
	/* ¿Habría que añadir la lista de alquileres de la asociación al DTO?*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
