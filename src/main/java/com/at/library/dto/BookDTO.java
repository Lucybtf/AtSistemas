package com.at.library.dto;


import java.io.Serializable;

import com.at.library.enums.StatusEnum;

public class BookDTO extends DTO {

	private static final long serialVersionUID = 1583585532736761521L;

	private Integer id;

	private String isbn;

	private String title;

	private String author;
	
	private StatusEnum statusBook;


	public StatusEnum getStatusBook() {
		return statusBook;
	}

	public void setStatusBook(StatusEnum statusBook) {
		this.statusBook = statusBook;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + "]";
	}

	
}
