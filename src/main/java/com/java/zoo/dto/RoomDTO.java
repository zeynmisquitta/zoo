package com.java.zoo.dto;

import javax.validation.constraints.NotBlank;

public class RoomDTO {

	@NotBlank(message = "Title should not be null empty or blank")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public RoomDTO(String title) {
		this.title = title;
	}

	public RoomDTO() {

	}
}
