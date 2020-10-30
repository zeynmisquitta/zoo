package com.java.zoo.dto;

import java.util.Date;

public class AnimalWithoutRoomDTO extends AnimalDTO {

	public AnimalWithoutRoomDTO(String title, Date zooAllocationDate) {
		super(title);
		this.zooAllocationDate = zooAllocationDate;
	}

	protected Date zooAllocationDate;

	public Date getZooAllocationDate() {
		return zooAllocationDate;
	}

	public void setZooAllocationDate(Date zooAllocationDate) {
		this.zooAllocationDate = zooAllocationDate;
	}

}
