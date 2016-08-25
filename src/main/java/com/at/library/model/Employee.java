package com.at.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.at.library.enums.StatusEnum;

@Entity
public class Employee implements Serializable {
	
	private static final long serialVersionUID = -6404545191979506671L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String dni;
	
	private String name;
	
	private String surname1;
	
	private String surname2;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum statusEmployee;
	
	@OneToMany
	private List<User> users;

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

	public StatusEnum getStatusEmployee() {
		return statusEmployee;
	}

	public void setStatusEmployee(StatusEnum statusEmployee) {
		this.statusEmployee = statusEmployee;
	}
	
}
