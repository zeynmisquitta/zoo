package com.java.zoo.services;

import com.java.zoo.dto.RoomDTO;
import com.java.zoo.entities.RoomEntity;

public interface RoomService {

	public RoomEntity getRoomEntity(Long roomId);

	public RoomDTO getRoom(Long roomId);

	public RoomEntity addRoom(RoomDTO room);

	public void deleteRoom(Long id);

	public RoomDTO updateRoom(RoomDTO room, Long roomId);

}
