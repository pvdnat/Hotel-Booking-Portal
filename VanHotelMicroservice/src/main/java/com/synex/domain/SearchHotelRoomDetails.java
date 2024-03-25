package com.synex.domain;

public class SearchHotelRoomDetails {
	private int hotelId;
	private String checkInDate;
	private String checkOutDate;
	private int noRooms;
	private int noGuests;
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getNoRooms() {
		return noRooms;
	}
	public void setNoRooms(int noRooms) {
		this.noRooms = noRooms;
	}
	public int getNoGuests() {
		return noGuests;
	}
	public void setNoGuests(int noGuests) {
		this.noGuests = noGuests;
	}
	
	
}
