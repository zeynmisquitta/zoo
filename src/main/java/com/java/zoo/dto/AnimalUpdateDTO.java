package com.java.zoo.dto;

import java.util.Date;

public class AnimalUpdateDTO extends AnimalWithRoomDTO {

	public AnimalUpdateDTO(String title, Date roomAllocationDate, Date zooAllocationDate) {
		super(title, roomAllocationDate);
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
