package com.at.library.dto;

public class RentPostDTO extends DTO{

	private Integer book;
	private Integer user;
	
	public RentPostDTO(){
		
	}
	
	public RentPostDTO(Integer id, Integer id2) {
		// TODO Auto-generated constructor stub
		this.book=id;
		this.user=id2;
	}
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
