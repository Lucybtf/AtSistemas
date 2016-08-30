package com.at.library.dto;

import java.util.Date;

import com.at.library.model.Book;

public class RentMigrationDTO {

	Date init;
	Date endDate;
	BookDTO book;
	public Date getInit() {
		return init;
	}
	public void setInit(Date init) {
		this.init = init;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BookDTO getBook() {
		return book;
	}
	public void setBook(BookDTO book) {
		this.book = book;
	}
	
	
}
