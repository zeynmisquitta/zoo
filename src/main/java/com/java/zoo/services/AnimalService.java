package com.java.zoo.services;

import java.util.List;
import java.util.Set;

import com.java.zoo.dto.AnimalDTO;
import com.java.zoo.dto.AnimalUpdateDTO;
import com.java.zoo.dto.AnimalWithRoomDTO;
import com.java.zoo.dto.AnimalWithoutRoomDTO;
import com.java.zoo.dto.RoomDTO;
import com.java.zoo.entities.AnimalEntity;
import com.java.zoo.entities.RoomEntity;

public interface AnimalService {

	public AnimalEntity addAnimal(AnimalWithoutRoomDTO animal);

	public AnimalEntity getAnimal(Long animalId);

	public void deleteAnimal(Long animalId);

	public AnimalEntity updateAnimal(Long animalId, AnimalUpdateDTO animal);

	public void addAnimalToRoom(Long animalId, Long roomId);

	public void updateAnimalRoom(Long animalId, Long updatedRoomId);

	public void removeAnimalfromRoom(Long animalId);

	public void addAnimalFavRoom(Long animalId, Long roomId);

	public void deleteAnimalFavRoom(Long animalId, Long roomId);

	List<AnimalWithoutRoomDTO> getAnimalsWithoutRooms(String sortKey, boolean sortOrder);

	public List<AnimalWithRoomDTO> getAnimalsInSpecificRooms(Long roomId, String sortKey, boolean sortOrder);

	public List<RoomDTO> getAnimalFavRooms(Long animalId);
}
