package com.synex.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="hotel_rooms")
public class HotelRoom {		
	@Id
	private int hotelRoomId; //PK	
	@ManyToOne
	private RoomType type;
	@ManyToMany
	private Set<Amenities> amenities;
	private int noRooms;
	private float price;
	private float discount;
	private String description;
	private String policies;
		
	@Transient
	private String hotelName;
	
	@Transient
	private String roomType;
	
	@Transient
	private Set<String> hotelRoomAmenityNames = new HashSet<>();
	
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}			
	public int getHotelRoomId() {
		return hotelRoomId;
	}
	public void setHotelRoomId(int hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}		
	public RoomType getType() {
		return type;
	}
	public void setType(RoomType type) {
		this.type = type;
	}
	
	public Set<Amenities> getAmenities() {
		return amenities;
	}
	public void setHotelRoomAmenities(Set<Amenities> hotelRoomAmenities) {
		this.amenities = hotelRoomAmenities;
	}
	public Set<String> getHotelRoomAmenityNames() {
		return hotelRoomAmenityNames;
	}
	public void setHotelRoomAmenityNames(Set<String> hotelRoomAmenityNames) {
		this.hotelRoomAmenityNames = hotelRoomAmenityNames;
	}
	public int getNoRooms() {
		return noRooms;
	}
	public void setNoRooms(int noRooms) {
		this.noRooms = noRooms;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPolicies() {
		return policies;
	}
	public void setPolicies(String policies) {
		this.policies = policies;
	}
	
	
}
