package com.at.library.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.at.library.enums.StatusEnum;

public class UserDTO extends DTO {


	private static final long serialVersionUID = 3827455324138571715L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String dni;
	
	private String name;
	
	private String surname;
	
	private String phone;
	
	private String email;
	
	private StatusEnum statusUser;


	public StatusEnum getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(StatusEnum statusUser) {
		this.statusUser = statusUser;
	}

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

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", dni=" + dni + ", name=" + name + ", surname=" + surname + ", phone=" + phone
				+ ", email=" + email + ", statusUser=" + statusUser + "]";
	}
	
	
	
}
