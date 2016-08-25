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
	
	RentPK rentpk;
	
	private Employee employee;
	
	private User user;
	
	private Date endDate;

	public RentPK getRentpk() {
		return rentpk;
	}

	public void setRentpk(RentPK rentpk) {
		this.rentpk = rentpk;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	

}
