package com.java.zoo.exceptions;

public class CustomAnimalException extends RuntimeException {

	private static final long serialVersionUID = 1143919739418082088L;

	public CustomAnimalException(String message) {
		super(message);
	}

	public CustomAnimalException(String message, Throwable cause) {
		super(message, cause);
	}

}
