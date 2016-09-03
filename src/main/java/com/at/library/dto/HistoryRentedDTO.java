package com.at.library.dto;

import java.util.Date;

//import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HistoryRentedDTO {

	//@DateTimeFormat
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date init;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date end;
	private String title;
	private Integer id;
	
	public HistoryRentedDTO(){
		
	}
	
	public HistoryRentedDTO(Date init, Date end, String title, Integer id){
		this.id=id;
		this.init=init;
		this.end=end;
		this.title=title;
	}
	
	public Date getInit() {
		return init;
	}
	public void setInit(Date init) {
		this.init = init;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "HistoryRentedDTO [init=" + init + ", end=" + end + ", title=" + title + ", id=" + id + "]";
	}
	
	
}
