package com.synex.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.Booking;
import com.synex.domain.Guest;
import com.synex.domain.Review;
import com.synex.service.BookingService;
import com.synex.service.EmailService;

@RestController
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	EmailService emailService;
	
	
	
	@RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
	public Booking saveBooking(@RequestBody Booking booking) {
		String email =  "Booking Numer #" + booking.getConfirmationNumber() + "\n\n" +
						"Booking Detail\n" +
						"\tHotel Name: " + booking.getHotelName() + "\n" +
						"\tCheck In Date: " + booking.getCheckInDate() + "\n" +
						"\tCheck Out Date: " + booking.getCheckOutDate() + "\n" +
						"\tNumber of Guests: " + booking.getGuests().size() + "\n" +
						"\tNumber of Rooms: " + booking.getNoRooms() + "\n" +
						"\tRooms Type: " + booking.getRoomType() + "\n" +
						"\tDiscount: " + booking.getDiscount() + "\n" +
						"\tTotal Charge: " + booking.getFinalCharges() + "\n\n" +
						"Thanks for using our booking service!\n"
						;
		
		emailService.sendEmail(booking.getUserEmail(), "Travel Gig Booking Information", email);
		
		return bookingService.saveBooking(booking);
	}
	
	
	@RequestMapping(value = "/getBookings/{userName}", method = RequestMethod.GET)
	public List<Booking> getAllBooking(@PathVariable String userName) {
		return bookingService.getAllBooking(userName);
	}
	
	@RequestMapping(value = "/currentStatusUpdate/{bookingId}/{status}", method = RequestMethod.POST) 
	public Booking currentStatusUpdate(@PathVariable int bookingId, @PathVariable String status){
		return bookingService.currentStatusUpdate(bookingId, status);
	}
	
	@RequestMapping(value = "/saveReview/{bookingId}", method = RequestMethod.POST)
	public Booking saveReview(@PathVariable int bookingId, @RequestBody Review review) {
		return bookingService.saveReview(bookingId, review);
	}
	
	@RequestMapping(value = "/getReviews/{hotelId}", method = RequestMethod.GET)
	public Set<Review> getReview(@PathVariable int hotelId) {
		return bookingService.getAvgReview(hotelId);
	}
	
}