package com.java.zoo.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class RoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private Long id;

	@Column(unique = true)
	private String title;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<AnimalEntity> roomAnimals;

	@ManyToMany(mappedBy="favAnimalRooms")
	//@JoinTable(name = "favourite_room", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "animal_id"))
	private Set<AnimalEntity> favRoomAnimals=new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<AnimalEntity> getFavRoomAnimals() {
		return favRoomAnimals;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AnimalEntity> getRoomAnimals() {
		return roomAnimals;
	}

	public void setRoomAnimals(List<AnimalEntity> roomAnimals) {
		this.roomAnimals = roomAnimals;
	}

	public void setFavRoomAnimals(Set<AnimalEntity> favRoomAnimals) {
		this.favRoomAnimals = favRoomAnimals;
	}

	public RoomEntity(String title) {
		this.title = title;
	}

	public RoomEntity() {

	}

}
