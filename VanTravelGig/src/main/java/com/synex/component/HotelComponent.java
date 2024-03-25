package com.synex.component;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HotelComponent {

		public JsonNode findHotel(String searchString) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/searchHotel/"+searchString, Object.class);
			Object objects = responseEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
			return returnObj;
		}
		
		public JsonNode saveBooking(Object booking) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/saveBooking",booking, Object.class);
			Object objects = responseEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
			return returnObj;
		}
		
		public JsonNode getBookings(String userName) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/getBookings/"+userName, Object.class);
			Object objects = responseEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
			return returnObj;
		}
		
		public JsonNode statusUpdate(int bookingId, String status) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/currentStatusUpdate/"+bookingId+"/"+status, bookingId , Object.class);
			Object objects = responseEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
			return returnObj;
		}

		public JsonNode saveReview(int bookingId, JsonNode review) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/saveReview/"+bookingId, review, Object.class);
			Object objects = responseEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
			return returnObj;
		}
		
		public JsonNode getReviews(int hotelId) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/getReviews/"+hotelId, Object.class);
			Object objects = responseEntity.getBody();
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
			return returnObj;
		}
		
}
