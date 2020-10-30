package com.java.zoo.exceptions;

public class CustomRoomException extends RuntimeException {

	private static final long serialVersionUID = 320227744030963745L;

	public CustomRoomException(String message) {
		super(message);
	}

	public CustomRoomException(String message, Throwable cause) {
		super(message, cause);
	}

}
