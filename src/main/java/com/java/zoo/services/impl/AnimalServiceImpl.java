package com.java.zoo.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.zoo.constants.ErrorMessages;
import com.java.zoo.dto.AnimalUpdateDTO;
import com.java.zoo.dto.AnimalWithRoomDTO;
import com.java.zoo.dto.AnimalWithoutRoomDTO;
import com.java.zoo.dto.RoomDTO;
import com.java.zoo.entities.AnimalEntity;
import com.java.zoo.entities.RoomEntity;
import com.java.zoo.exceptions.CustomAnimalException;
import com.java.zoo.repositories.AnimalRepository;
import com.java.zoo.services.AnimalService;
import com.java.zoo.services.RoomService;

@Service
public class AnimalServiceImpl implements AnimalService {

	public AnimalServiceImpl(AnimalRepository animalRepo, RoomService roomService) {
		super();
		this.animalRepo = animalRepo;
		this.roomService = roomService;
	}

	private AnimalRepository animalRepo;

	private RoomService roomService;

	@Override
	public AnimalEntity addAnimal(AnimalWithoutRoomDTO animal) {
		try {
			AnimalEntity animalEntity = new AnimalEntity(animal.getTitle(), animal.getZooAllocationDate());
			return animalRepo.save(animalEntity);

		} catch (DataIntegrityViolationException de) {
			throw new CustomAnimalException(ErrorMessages.ANIMAL_TITLE_DUPLICATE);
		}

	}

	@Override
	public AnimalEntity getAnimal(Long animalId) {
		Optional<AnimalEntity> existingAnimal = animalRepo.findById(animalId);
		if (existingAnimal.isPresent()) {
			return existingAnimal.get();
		} else {
			throw new CustomAnimalException(ErrorMessages.ANIMAL_NOT_FOUND);
		}
	}

	@Override
	public void deleteAnimal(Long animalId) {
			animalRepo.delete(getAnimal(animalId));
	
	}

	@Override
	public AnimalEntity updateAnimal(Long animalId, AnimalUpdateDTO animal) {
		AnimalEntity updatedAnimal = getAnimal(animalId);

		try {
			updatedAnimal.setTitle(animal.getTitle());
		} catch (DataIntegrityViolationException de) {
			throw new CustomAnimalException(ErrorMessages.ANIMAL_TITLE_DUPLICATE);
		}

		if (null != animal.getRoomAllocationDate()) {
			updatedAnimal.setRoomAllocationDate(animal.getRoomAllocationDate());
		}

		if (null != animal.getZooAllocationDate()) {
			updatedAnimal.setZooAllocationDate(animal.getZooAllocationDate());
		}

		animalRepo.save(updatedAnimal);
		return updatedAnimal;

	}

	@Override
	public void addAnimalToRoom(Long animalId, Long roomId) {
		RoomEntity newRoom = roomService.getRoomEntity(roomId);

		AnimalEntity existingAnimal = getAnimal(animalId);
		existingAnimal.setRoom(newRoom);
		existingAnimal.setRoomAllocationDate(new Date());

		animalRepo.save(existingAnimal);

	}

	@Override
	public void updateAnimalRoom(Long animalId, Long updatedRoomId) {
		RoomEntity newRoom = roomService.getRoomEntity(updatedRoomId);

		AnimalEntity existingAnimal = getAnimal(animalId);
		existingAnimal.setRoom(newRoom);
		existingAnimal.setRoomAllocationDate(new Date());

		animalRepo.save(existingAnimal);

	}

	@Override
	public void removeAnimalfromRoom(Long animalId) {
		AnimalEntity existingAnimal = getAnimal(animalId);
		existingAnimal.setRoom(null);
		existingAnimal.setRoomAllocationDate(null);

		animalRepo.save(existingAnimal);
	}

	@Override
	public void addAnimalFavRoom(Long animalId, Long roomId) {
		AnimalEntity existingAnimal = getAnimal(animalId);
		RoomEntity room = roomService.getRoomEntity(roomId);
		existingAnimal.addFavRooms(room);
		animalRepo.save(existingAnimal);
	}

	@Override
	public void deleteAnimalFavRoom(Long animalId, Long roomId) {
		AnimalEntity existingAnimal = getAnimal(animalId);
		RoomEntity favRoom = roomService.getRoomEntity(roomId);
		Set<RoomEntity> favRooms = existingAnimal.getFavAnimalRooms();
		favRooms.remove(favRoom);
		animalRepo.save(existingAnimal);
	}

	@Override
	public List<AnimalWithoutRoomDTO> getAnimalsWithoutRooms(String sortKey, boolean sortOrder) {
		return animalRepo.findAllAnimalsWithoutRooms(getSortObject(sortKey, sortOrder));
	}

	@Override
	public List<AnimalWithRoomDTO> getAnimalsInSpecificRooms(Long roomId, String sortKey, boolean sortOrder) {
		return animalRepo.findAllAnimalsInSpecficRoom(roomId, getSortObject(sortKey, sortOrder));
	}

	@Override
	public List<RoomDTO> getAnimalFavRooms(Long animalId) {
		AnimalEntity existingAnimal = getAnimal(animalId);
		Set<RoomEntity> favRooms = existingAnimal.getFavAnimalRooms();
		List<RoomDTO> results = new ArrayList<>();
		for (RoomEntity entry : favRooms) {
			RoomDTO room = new RoomDTO(entry.getTitle());
			results.add(room);
		}
		return results;
	}

	/**
	 * This is a helper method to create the sort object based on the sort key and
	 * the sort order.
	 * 
	 * @param sortKey   is a value to be used to sorting in the query.
	 * @param sortOrder can true for ascending and false for descending. Default is
	 *                  descending
	 * @return Sort object
	 */
	private Sort getSortObject(String sortKey, boolean sortOrder) {
		Sort sortObj = Sort.by(sortKey).descending();
		if (sortOrder) {
			sortObj = Sort.by(sortKey).ascending();
		}
		return sortObj;
	}

}