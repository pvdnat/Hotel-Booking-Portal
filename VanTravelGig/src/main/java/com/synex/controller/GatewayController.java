package com.synex.controller;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.synex.component.HotelComponent;
import com.synex.service.UserService;

@RestController
public class GatewayController {
	
	@Autowired
	HotelComponent hotelComponent;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/findHotel/{searchString}", method = RequestMethod.GET)
	public JsonNode findHotel(@PathVariable String searchString) {
		return hotelComponent.findHotel(searchString);
	}
	
	@RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
	public JsonNode saveBooking(@RequestBody JsonNode booking, Principal principal) {
		
		String userName = principal.getName();
		
		((ObjectNode)booking).put("userName",userName);
		((ObjectNode)booking).put("userEmail",userService.findByUserName(userName).getEmail());
		((ObjectNode)booking).put("confirmationNumber", UUID.randomUUID().toString());
		return hotelComponent.saveBooking(booking);
		
	}
	
	@RequestMapping(value = "/getBookings", method = RequestMethod.GET)
	public JsonNode getBookings(Principal principal) {
		String userName = principal.getName();
		return hotelComponent.getBookings(userName);
	}
	
	@RequestMapping(value = "/updateStatus/{bookingId}/{status}", method = RequestMethod.POST)
	public JsonNode updateStatus(@PathVariable int bookingId, @PathVariable String status) {
		return hotelComponent.statusUpdate(bookingId, status);
	}
	
	@RequestMapping(value = "/saveReview/{bookingId}", method = RequestMethod.POST)
	public JsonNode saveReview(@PathVariable int bookingId, @RequestBody JsonNode review) {
		return hotelComponent.saveReview(bookingId, review);
	}
	
	@RequestMapping(value = "/getReviews/{hotelId}", method = RequestMethod.GET)
	public JsonNode getReview(@PathVariable int hotelId) {
		return hotelComponent.getReviews(hotelId);
	}
}
