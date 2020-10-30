package com.java.zoo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.java.zoo.entities.AnimalEntity;
import com.java.zoo.entities.RoomEntity;
import com.java.zoo.repositories.AnimalRepository;
import com.java.zoo.repositories.RoomRepository;

@Component
public class ZooCommandLineRunner implements CommandLineRunner {

	@Autowired
	RoomRepository roomRepo;
	
	@Autowired
	AnimalRepository animalRepo;


	private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

	@Override
	public void run(String... args) throws Exception {

		// Creating rooms
		RoomEntity room1 = new RoomEntity("Paris");
		RoomEntity room2 = new RoomEntity("London");
		RoomEntity room3 = new RoomEntity("Dubai");
		RoomEntity room4 = new RoomEntity("Tokyo");

		Date today = new Date();
		Date yesterday = new Date(today.getTime() - MILLIS_IN_A_DAY);
		Date oneWeekAgo = new Date(today.getTime() - (7L * MILLIS_IN_A_DAY));
		Date twoWeeksAgo = new Date(today.getTime() - (14L * MILLIS_IN_A_DAY));

		// Creating animals
		AnimalEntity lion = new AnimalEntity("Lion", today);

		AnimalEntity tiger = new AnimalEntity("Tiger", oneWeekAgo);

		tiger.setRoom(room1);
		tiger.setRoomAllocationDate(today);
		tiger.addFavRooms(room2);
		tiger.addFavRooms(room3);

		AnimalEntity monkey = new AnimalEntity("Monkey", yesterday);
		monkey.setRoom(room2);
		monkey.setRoomAllocationDate(oneWeekAgo);
		monkey.addFavRooms(room2);

		AnimalEntity elephant = new AnimalEntity("Elephant", twoWeeksAgo);
		elephant.setRoom(room3);
		elephant.setRoomAllocationDate(twoWeeksAgo);
		elephant.addFavRooms(room4);

		// Saving animals to the repository
		List<AnimalEntity> animals = new LinkedList<>();
		animals.add(lion);
		animals.add(tiger);
		animals.add(monkey);
		animals.add(elephant);
		animalRepo.saveAll(animals);

	}
}
