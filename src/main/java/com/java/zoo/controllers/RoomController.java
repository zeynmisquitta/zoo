package com.java.zoo.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.java.zoo.dto.RoomDTO;

public interface RoomController {

	// Handling the addition of a room
	@PostMapping("/rooms")
	public ResponseEntity<Void> addRoom(@Valid @RequestBody RoomDTO room) ;
	
	// Handling the retrieval details of a specific room
	@GetMapping("/rooms/{roomId}")
	public ResponseEntity<Object> getRoom(@PathVariable Long roomId);
	
	// Handling the updating  of details of a specific room
	@PutMapping("/rooms/{roomId}")
	public ResponseEntity<Object> updateRoom(@Valid @RequestBody RoomDTO room, @PathVariable Long roomId);
	
	// Handling the deletion of a room
	@DeleteMapping("/rooms/{roomId}")
	public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId);
}
