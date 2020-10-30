package com.java.zoo.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.zoo.dto.AnimalUpdateDTO;
import com.java.zoo.dto.AnimalWithoutRoomDTO;

public interface AnimalController {

	// Handling the addition of an animal
	@PostMapping("/animals")
	public ResponseEntity<Void> addAnimal(@Valid @RequestBody AnimalWithoutRoomDTO animal);

	// Handling the fetching of an animal as per animal id
	@GetMapping("/animals/{animalId}")
	public ResponseEntity<Object> getAnimal(@PathVariable Long animalId);

	// Handling the updating of an animal
	@PutMapping("/animals/{animalId}")
	public ResponseEntity<Void> updateAnimal(@Valid @RequestBody AnimalUpdateDTO animal, @PathVariable Long animalId);

	// Handling the deletion of an animal
	@DeleteMapping("/animals/{animalId}")
	public ResponseEntity<Void> deleteAnimal(@PathVariable Long animalId);

	// Handling the addition of an animal to a particular room
	@PostMapping("/animals/{animalId}/rooms/{roomId}")
	public ResponseEntity<Void> addAnimalToRoom(@PathVariable Long animalId, @PathVariable Long roomId);

	// Handling the moving of an animal from one room to another particular room
	@PutMapping("/animals/{animalId}/rooms/{updatedRoomId}")
	public ResponseEntity<Void> moveAnimalToAnotherRoom(@PathVariable Long animalId, @PathVariable Long updatedRoomId);

	// Handling the removal of an animal from a particular room
	@DeleteMapping("/animals/{animalId}/room")
	public ResponseEntity<Void> deleteAnimalFromRoom(@PathVariable Long animalId);

	// Handling the addition of favourite rooms of an animal
	@PostMapping("/animals/{animalId}/fav-room/{roomId}")
	public ResponseEntity<Void> addFavRoom(@PathVariable Long animalId, @PathVariable Long roomId);

	// Handling the removal of favourite rooms of an animal
	@DeleteMapping("/animals/{animalId}/fav-room/{roomId}")
	public ResponseEntity<Void> removeFavRoom(@PathVariable Long animalId, @PathVariable Long roomId);

	//Handling the retrieval of animals that are not allocated to any rooms
	@GetMapping("/animals/no-rooms")
	public ResponseEntity<Object> getAnimalsWithoutRooms(
			@RequestParam(defaultValue = "zooAllocationDate", required = false) String sortKey,
			@RequestParam(defaultValue = "false", required = false) boolean sortOrder);

	// Handling the retrieval of animals assigned to a specific room
	@GetMapping("/animals/rooms/{roomId}")
	public ResponseEntity<Object> getAnimalsInSpecificRooms(@PathVariable Long roomId,
			@RequestParam(defaultValue = "roomAllocationDate", required = false) String sortKey,
			@RequestParam(defaultValue = "false", required = false) boolean sortOrder);

	// Handling the retrieval favourite rooms of a specific animals 
	@GetMapping("/animals/{animalId}/fav-room")
	public ResponseEntity<Object> getAnimalFavRooms(@PathVariable Long animalId);
}
