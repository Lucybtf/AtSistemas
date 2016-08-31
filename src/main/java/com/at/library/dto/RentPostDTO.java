package com.at.library.dto;

public class RentPostDTO extends DTO{

	private Integer book;
	private Integer user;
	
	public Integer getLibro() {
		return book;
	}
	public void setLibro(Integer libro) {
		this.book = libro;
	}
	public Integer getUser() {
		return user;
	}
	public void setUser(Integer user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "RentPostDTO [book=" + book + ", user=" + user + "]";
	}

	
}
