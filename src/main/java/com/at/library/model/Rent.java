package com.at.library.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Rent implements Serializable {

	private static final long serialVersionUID = 7835850664567352801L;

	@EmbeddedId
	RentPK rentpk;
	
	@ManyToOne
	private Employee employee;
	
	@ManyToOne
	private User user;
	
//	@Temporal(TemporalType.TIMESTAMP)
	//private Date initDate;
	
	@Temporal(TemporalType.TIMESTAMP)
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

	/*Propios get/set de los parámetros del RentPK*/
	@Transient
	public Book getBook() {
		return rentpk.getBook();
	}

	@Transient
	public void setBook(Book book) {
		this.rentpk.setBook(book);
	}

	@Transient
	public Date getInitDate() {
		return rentpk.getInitDate();
	}

	@Transient
	public void setInitDate(Date init) {
		this.rentpk.setInitDate(init);
	}
	

	
}
