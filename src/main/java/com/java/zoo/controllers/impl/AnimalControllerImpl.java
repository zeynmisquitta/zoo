package com.java.zoo.controllers.impl;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.java.zoo.controllers.AnimalController;
import com.java.zoo.dto.AnimalDTO;
import com.java.zoo.dto.AnimalUpdateDTO;
import com.java.zoo.dto.AnimalWithRoomDTO;
import com.java.zoo.dto.AnimalWithoutRoomDTO;
import com.java.zoo.dto.ResponseDTO;
import com.java.zoo.dto.RoomDTO;
import com.java.zoo.entities.RoomEntity;
import com.java.zoo.services.AnimalService;

@RestController
public class AnimalControllerImpl implements AnimalController {

	AnimalService animalService;

	public AnimalControllerImpl(AnimalService animalService) {
		this.animalService = animalService;
	}

	@Override
	public ResponseEntity<Void> addAnimal(@Valid @RequestBody AnimalWithoutRoomDTO animal) {
		Long createdAnimalId = animalService.addAnimal(animal).getId();
		URI uriLocation = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(String.valueOf(createdAnimalId)).toUri();
		return ResponseEntity.created(uriLocation).build();
	}

	@Override
	public ResponseEntity<Object> getAnimal(@PathVariable Long animalId) {

		return ResponseEntity.ok(new ResponseDTO(animalService.getAnimal(animalId)));
	}

	@Override
	public ResponseEntity<Void> updateAnimal(@Valid @RequestBody AnimalUpdateDTO animal, @PathVariable Long animalId) {
		animalService.updateAnimal(animalId, animal);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> deleteAnimal(@PathVariable Long animalId) {
		animalService.deleteAnimal(animalId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> addAnimalToRoom(@PathVariable Long animalId, @PathVariable Long roomId) {
		animalService.addAnimalToRoom(animalId, roomId);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> moveAnimalToAnotherRoom(@PathVariable Long animalId, @PathVariable Long updatedRoomId) {
		animalService.updateAnimalRoom(animalId, updatedRoomId);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> deleteAnimalFromRoom(@PathVariable Long animalId) {
		animalService.removeAnimalfromRoom(animalId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> addFavRoom(@PathVariable Long animalId, @PathVariable Long roomId) {
		animalService.addAnimalFavRoom(animalId, roomId);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> removeFavRoom(@PathVariable Long animalId, @PathVariable Long roomId) {
		animalService.deleteAnimalFavRoom(animalId, roomId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Object> getAnimalsWithoutRooms(@NotBlank @RequestParam String sortKey,
			@RequestParam(defaultValue = "true", required = false) boolean sortOrder) {
		List<AnimalWithoutRoomDTO> results = animalService.getAnimalsWithoutRooms(sortKey, sortOrder);
		return ResponseEntity.ok(new ResponseDTO(results));

	}

	@Override
	public ResponseEntity<Object> getAnimalsInSpecificRooms(@PathVariable Long roomId, @RequestParam String sortKey,
			@RequestParam(defaultValue = "true", required = false) boolean sortOrder) {

		List<AnimalWithRoomDTO> results = animalService.getAnimalsInSpecificRooms(roomId, sortKey, sortOrder);
		return ResponseEntity.ok(new ResponseDTO(results));
	}

	@Override
	public ResponseEntity<Object> getAnimalFavRooms(@PathVariable Long animalId) {
		List<RoomDTO> results = animalService.getAnimalFavRooms(animalId);
		return ResponseEntity.ok(new ResponseDTO(results));
	}

}
