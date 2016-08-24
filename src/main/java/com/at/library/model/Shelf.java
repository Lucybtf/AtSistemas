package com.at.library.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Shelf {

	@Id
	@GeneratedValue
	private Integer shelfid;
	
	private String codeshelf;
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Book> books;


	public Integer getShelfid() {
		return shelfid;
	}


	public void setShelfid(Integer shelfid) {
		this.shelfid = shelfid;
	}


	public String getCodeshelf() {
		return codeshelf;
	}


	public void setCodeshelf(String codeshelf) {
		this.codeshelf = codeshelf;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
}
