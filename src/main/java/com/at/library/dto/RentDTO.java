package com.at.library.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.at.library.model.Employee;
import com.at.library.model.RentPK;
import com.at.library.model.User;

public class RentDTO extends DTO {


	private static final long serialVersionUID = 7364756623634860483L;
	
	//RentPK rentpk;
	
	private Integer idBook;
	
	private Integer idEmployee;
	
	private Integer idUser;
	
	private Date initDate;

	public RentDTO(){
		super();
	}
	
	public RentDTO(Integer idBook, Integer idEmployee,Integer idUser, Date initDate){
		super();
		this.idBook=idBook;
		this.idEmployee=idEmployee;
		this.idUser=idUser;
		this.initDate=initDate;
	}
	public Integer getIdBook() {
		return idBook;
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


	@Override
	public String toString() {
		return "RentDTO [idBook=" + idBook + ", idEmployee=" + idEmployee + ", idUser=" + idUser + ", initDate="
				+ initDate + "]";
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	//Cogemos por defecto la del servidor
	//private Date endDate;
}
