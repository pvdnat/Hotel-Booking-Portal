package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.synex.domain.Booking;
import com.synex.domain.Review;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

	public List<Booking> findByUserNameLikeOrderByBookingIdDesc(String userName);

	public Booking findByBookingId(int bookingId);

	public List<Booking> findByHotelId(int hotelId);

	
	
}
