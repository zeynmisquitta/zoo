package com.java.zoo.services.impl;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.java.zoo.constants.ErrorMessages;
import com.java.zoo.dto.RoomDTO;
import com.java.zoo.entities.RoomEntity;
import com.java.zoo.exceptions.CustomAnimalException;
import com.java.zoo.exceptions.CustomRoomException;
import com.java.zoo.repositories.RoomRepository;
import com.java.zoo.services.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	private RoomRepository roomRepo;

	public RoomServiceImpl(RoomRepository roomRepo) {
		this.roomRepo = roomRepo;
	}

	@Override
	public RoomEntity getRoomEntity(Long roomId) {
		Optional<RoomEntity> existingRoom = roomRepo.findById(roomId);
		if (existingRoom.isPresent()) {
			return existingRoom.get();
		} else {
			throw new CustomRoomException(ErrorMessages.ROOM_NOT_FOUND);
		}
	}

	@Override
	public RoomDTO getRoom(Long roomId) {
		return new RoomDTO(getRoomEntity(roomId).getTitle());
	}

	@Override
	public RoomEntity addRoom(RoomDTO room) {
		try {
			return roomRepo.save(new RoomEntity(room.getTitle()));
		} catch (DataIntegrityViolationException de) {
			throw new CustomAnimalException(ErrorMessages.ROOM_TITLE_DUPLICATE);
		}
	}

	@Override
	public void deleteRoom(Long id) {
		RoomEntity room=getRoomEntity(id);
		roomRepo.delete(room);	
		
	}

	@Override
	public RoomDTO updateRoom(RoomDTO room, Long roomId) {
		try {
			RoomEntity updatedRoom = getRoomEntity(roomId);
			updatedRoom.setTitle(room.getTitle());
			return new RoomDTO(roomRepo.save(updatedRoom).getTitle());
		} catch (DataIntegrityViolationException de) {
			throw new CustomRoomException(ErrorMessages.ROOM_TITLE_DUPLICATE);
		}
	}

}
