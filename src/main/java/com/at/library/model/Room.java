package com.at.library.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room {

	@Id
	@GeneratedValue
	private Integer roomid;
	
	private String roomplace;
	
	private Integer roomnumber;
	
	@OneToMany
	private List<Shelf> shelfs;

	public Integer getRoomid() {
		return roomid;
	}

	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}

	public String getRoomplace() {
		return roomplace;
	}

	public void setRoomplace(String roomplace) {
		this.roomplace = roomplace;
	}

	public Integer getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(Integer roomnumber) {
		this.roomnumber = roomnumber;
	}

	public List<Shelf> getShelfs() {
		return shelfs;
	}

	public void setShelfs(List<Shelf> shelfs) {
		this.shelfs = shelfs;
	}
	
	
}
