package com.java.zoo.dto;

import java.util.Date;

public class AnimalWithRoomDTO extends AnimalDTO {

	public AnimalWithRoomDTO(String title, Date roomAllocationDate) {
		super(title);
		this.roomAllocationDate = roomAllocationDate;
	}

	protected Date roomAllocationDate;

	public Date getRoomAllocationDate() {
		return roomAllocationDate;
	}

	public void setRoomAllocationDate(Date roomAllocationDate) {
		this.roomAllocationDate = roomAllocationDate;
	}
}
