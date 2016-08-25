package com.at.library.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Bookshelf implements Serializable {

	private static final long serialVersionUID = 6694728324797188102L;


	@Id
	private String code;
	
	
	@OneToMany(fetch=FetchType.LAZY )
	private List<Book> books;
	
	@OneToOne
	private Room room;


	public String getCodeshelf() {
		return code;
	}


	public void setCodeshelf(String codeshelf) {
		this.code = codeshelf;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}


	
}
