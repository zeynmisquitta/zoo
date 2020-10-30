package com.java.zoo.controllers.impl;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.java.zoo.controllers.RoomController;
import com.java.zoo.dto.RoomDTO;
import com.java.zoo.services.RoomService;

@RestController
public class RoomControllerImpl implements RoomController {

	RoomService roomService;

	public RoomControllerImpl(RoomService roomService) {
		this.roomService = roomService;
	}

	@Override
	public ResponseEntity<Void> addRoom(@Valid @RequestBody RoomDTO room) {
		Long createdRoomId = roomService.addRoom(room).getId();
		URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(String.valueOf(createdRoomId)).toUri();
		return ResponseEntity.created(uriLocation).build();

	}

	@Override
	public ResponseEntity<Object> getRoom(@PathVariable Long roomId) {
		return ResponseEntity.ok(roomService.getRoom(roomId));
	}

	@Override
	public ResponseEntity<Object> updateRoom(@Valid @RequestBody RoomDTO room, @PathVariable Long roomId) {
		return ResponseEntity.ok(roomService.updateRoom(room, roomId));
	}

	@Override
	public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
		roomService.deleteRoom(roomId);
		return ResponseEntity.noContent().build();

	}

}
