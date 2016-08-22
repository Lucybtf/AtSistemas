package com.at.library.dto;

import java.io.Serializable;

public class BookDTO implements Serializable {

	private static final long serialVersionUID = 1583585532736761521L;

	private String isbn;

	private String title;

	private String author;

	public BookDTO(String isbn, String title, String author) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}

	public BookDTO() {
		super();
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

}
