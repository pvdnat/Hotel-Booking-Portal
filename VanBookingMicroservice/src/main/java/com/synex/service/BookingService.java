package com.synex.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synex.domain.Booking;
import com.synex.domain.Review;
import com.synex.repository.BookingRepository;
import com.synex.repository.GuestRepository;
import com.synex.repository.ReviewRepository;

@Service
public class BookingService {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	GuestRepository guestRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	public Booking saveBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	public List<Booking> getAllBooking(String userName) {
		List<Booking> bookingList = bookingRepository.findByUserNameLikeOrderByBookingIdDesc(userName);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
	    LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		for (Booking booking : bookingList) {
			if(date.compareTo(booking.getCheckInDate())==1) {
				booking.setStatus("Completed");
				bookingRepository.save(booking);
			}
		}
		return bookingList;
	}

	public Booking currentStatusUpdate(int bookingId, String status) {
		Booking booking = bookingRepository.findByBookingId(bookingId);
		booking.setStatus(status);
		return bookingRepository.save(booking);
	}

	public Booking saveReview(int bookingId, Review review) {
		Booking booking = bookingRepository.findByBookingId(bookingId);
		booking.setReview(review);
		return bookingRepository.save(booking);
	}

	public Set<Review> getAvgReview(int hotelId) {
		List<Booking> bookingList = bookingRepository.findByHotelId(hotelId);
		Set<Review> reviewList = new HashSet<Review>();
		
		for (Booking booking : bookingList) {
			reviewList.add(booking.getReview());
		}
		return reviewList;
	}
	
	

	
}


