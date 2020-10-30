package com.java.zoo.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.zoo.dto.AnimalWithRoomDTO;
import com.java.zoo.dto.AnimalWithoutRoomDTO;
import com.java.zoo.entities.AnimalEntity;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
	@Query("SELECT new com.java.zoo.dto.AnimalWithoutRoomDTO(e.title, e.zooAllocationDate) FROM AnimalEntity e WHERE e.room is null")
	List<AnimalWithoutRoomDTO> findAllAnimalsWithoutRooms(Sort sort);

	@Query("SELECT new com.java.zoo.dto.AnimalWithRoomDTO(e.title, e.roomAllocationDate) FROM AnimalEntity e WHERE e.room.id=?1")
	List<AnimalWithRoomDTO> findAllAnimalsInSpecficRoom(Long roomid, Sort sort);

}
