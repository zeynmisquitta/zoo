package com.java.zoo.entities;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "animal")
public class AnimalEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "animal_id")
	private Long id;

	@Column(unique = true)
	private String title;

	// change to addition date
	@Temporal(TemporalType.DATE)
	private Date zooAllocationDate;

	// Add a column room allocation date
	@Temporal(TemporalType.DATE)
	private Date roomAllocationDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "room_id")
	private RoomEntity room;

	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
	@JoinTable(name = "favourite_room", joinColumns = @JoinColumn(name = "animal_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
	private Set<RoomEntity> favAnimalRooms=new HashSet<>();

	public void addFavRooms(RoomEntity room) {
		favAnimalRooms.add(room);
		
	}

	public Set<RoomEntity> getFavAnimalRooms() {
		return favAnimalRooms;
	}

	public void setFavAnimalRooms(Set<RoomEntity> favAnimalRooms) {
		this.favAnimalRooms = favAnimalRooms;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getZooAllocationDate() {
		return zooAllocationDate;
	}

	public void setZooAllocationDate(Date zooAllocationDate) {
		this.zooAllocationDate = zooAllocationDate;
	}

	public Date getRoomAllocationDate() {
		return roomAllocationDate;
	}

	public void setRoomAllocationDate(Date roomAllocationDate) {
		this.roomAllocationDate = roomAllocationDate;
	}

	public RoomEntity getRoom() {
		return room;
	}

	public void setRoom(RoomEntity room) {
		this.room = room;
	}

	public AnimalEntity() {

	}

	public AnimalEntity(String title, Date zooAllocationDate) {
		super();
		this.title = title;
		this.zooAllocationDate = zooAllocationDate;
	}

}
