package com.java.zoo.dto;

public class ResponseDTO {

	public ResponseDTO(Object data) {
		this.data = data;
	}

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
