package com.java.zoo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.zoo.entities.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

}
