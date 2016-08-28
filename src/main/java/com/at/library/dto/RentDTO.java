package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.at.library.model.Employee;
import com.at.library.model.RentPK;
import com.at.library.model.User;

public class RentDTO implements Serializable {


	private static final long serialVersionUID = 7364756623634860483L;
	
	//RentPK rentpk;
	
	private Integer idBook;
	
	private Integer idEmployee;
	
	private Integer idUser;

	public Integer getIdBook() {
		return getIdBook();
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	//Cogemos por defecto la del servidor
	//private Date endDate;
}
