package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class RentPK implements Serializable {

	private static final long serialVersionUID = -1765600097223154219L;

	@OneToOne
	private Book book;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date initDate;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date init) {
		this.initDate = init;
	}
	
	
}
