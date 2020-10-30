package com.java.zoo.constants;

public class ErrorMessages {
	private ErrorMessages() {
		throw new IllegalStateException(" Error Message Constants class");
	}

	public static final String ROOM_TITLE_DUPLICATE = "Room title already present. Please give a unique room title";
	public static final String ROOM_NOT_FOUND = "No room present with given id";
	public static final String ANIMAL_TITLE_DUPLICATE = "Animal title already present. Please give a unique animal title";
	public static final String ANIMAL_NOT_FOUND = "No animal present with given id";
	public static final String UNABLE_TO_DELETE_ANIMAL = "Unable to delete animal. Please remove the animal from assigned room before deleting";
	public static final String UNABLE_TO_DELETE_ROOM = "Unable to delete room. Please remove the animals from assigned room and room from animal favourite room before deleting";
}
