package com.java.zoo.dto;

import javax.validation.constraints.NotBlank;

public class AnimalDTO {
	
	public AnimalDTO() {
		
	}

	public AnimalDTO(String title) {
		this.title = title;
	}

	@NotBlank(message = " title must not be null or empty or blank")
	protected String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
