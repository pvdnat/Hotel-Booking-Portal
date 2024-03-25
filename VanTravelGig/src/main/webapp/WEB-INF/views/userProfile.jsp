
<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script src="../js/profile.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
<meta charset="UTF-8">
<title>Welcome</title>
<style>

	
	.highlighter{
		display: inline-block;
		background-color: #A8A8A8;
		margin: 15px 0px;
		padding: 5px 10px;
		color: white;
	}
	
	.link{
		display: block;
		width: 100px;
		font-weight: 500;
	}
	
	
</style>
</head>
<body>
	<div class="d-flex justify-content-around align-items-center" style="height: 70px; background-color: #A8A8A8">
		<h3><security:authorize access="isAuthenticated()">
    		Welcome <span id="username" ><security:authentication property="principal.username"/> </span> 
    		<security:authorize access="hasRole('ADMIN')">
    			<button id='question-btn' class="btn " >Questions</button>
    		</security:authorize>
		</security:authorize></h3>
		<div>
			<a style ="color: white; text-decoration: none" href="/home"><button class="btn btn-info" >Home</button></a>
			
			<a style ="color: white; text-decoration: none" href="/logout"><button class="btn btn-danger" >Logout</button></a>
		</div>
	</div>
	<div class="d-flex justify-content-between align-items-center">
		<div class="highlighter">
			<h3 class="">Recent Bookings</h3>
		</div>
		<div class="mr-4">
			<button class="btn btn-success" id='btn-upcoming'>Upcoming</button>
			<button class="btn btn-success" id='btn-completed'>Completed</button>
			<button class="btn btn-success" id='btn-showall'>Show All</button>
		</div>
	</div>
      <div class="bookings-body">        
        <div class="d-flex justify-content-center" >
    		
    		<div id= "booking-table" class=" mt-3">
    			<table class='w-75 table table-striped' style=" border:1px solid grey;">
    				<thead class="thead-dark" >
    					<tr style="background-color:#caddfa; " class="text-secondary header">
    						<th>BookingId</th>
    						<th class="d-none">HotelId</th>
    						<th> BookingDate</th>
    						<th>CheckInDate</th>
    						<th>CheckOutDate</th>
    						<th>CustomerMobile</th>
    						<th>Price</th>
    						<th>Status</th>
    						<th style="color: red; text-align: center;" colspan="2">Actions</th>
    					</tr>
    				</thead>
    				
    				<tbody id = "profile-show-table">
    				
    				</tbody>
    			</table>
    	
    		</div>
        </div>
    </div>
    
    <div class="modal" id="reviewModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Please let Us Know!</h4>
        <input style="display:none" id="review-bookingId"/>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" id="reviewModal_modalBody">        
              <div style="overflow: auto; height: 400px" class="w-100 d-flex flex-column">
              	<div class="h-25 ">
              		<h4>Question1: How was the Service? (Rate 1 to 5) </h4>
              		<select id="review-question1" style ="height: 40px; width: 100px; font-weight: 500" class="form-control text-center">
              			<option value="1">1</option>
              			<option value="2">2</option>
              			<option value="3">3</option>
              			<option value="4">4</option>
              			<option value="5" selected="selected">5</option>
              		</select>
              	</div>
              	<div class="h-25">
              		<h4>Question2: Were you satisfied with the amenities? (Rate 1 to 5)</h4>
              		<select id="review-question2" style ="height: 40px; width: 100px" class="form-control text-center">
              			<option>1</option>
              			<option>2</option>
              			<option>3</option>
              			<option>4</option>
              			<option selected="selected">5</option>
              		</select>
              	</div>
              	<div class="h-25">
              		<h4>Question3: How comfortable was the booking process? (Rate 1 to 5)</h4>
              		<select id="review-question3" style ="height: 40px; width: 100px" class="form-control text-center">
              			<option>1</option>
              			<option>2</option>
              			<option>3</option>
              			<option>4</option>
              			<option selected="selected">5</option>
              		</select>
              	</div>
              	<div class="h-25">
              		<h4>Question4: How was your whole experience? (Rate 1 to 5)</h4>
              		<select id="review-question4" style ="height: 40px; width: 100px" class="form-control text-center">
              			<option>1</option>
              			<option>2</option>
              			<option>3</option>
              			<option>4</option>
              			<option selected="selected">5</option>
              		</select>
              	</div>
              	
              	<div class="">
              		<h4>Please leave a review (Optional)</h4>
              		<div>
	              		<textarea id="review-text" rows="3" style =" height: 50px; width: 100%;" class=" form-control">Insert text</textarea>
              		</div>
              	</div>
              	
              	<div >
              		<div><h3>Overall Ratings:</h3> <span id="review-rating"></span></div>
              	</div>
              	
              	<div class="w-100 d-flex justify-content-end">
              		<button id="review-submit" class="btn btn-primary">Submit</button>
              	</div>
              </div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal" id="close">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="question-modal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">You have Questions to be Answered! </h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

	<div class="modal-body" id='questionModal_body'> 
		<div class='form-group'>
		  	
	  	</div> 
	  	
	</div>
        

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="answer-modal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Please Answer! </h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

	<div class="modal-body" id='questionModal_body'> 
		<div id='question'>
		  	
	  	</div> 
	  	
	  	<div id ='answer'>
	  		<textarea id='answer-text' style='width: 100%; height: 100px;'></textarea>
	  		<div>
	  			<button id='submit-answer' class='btn'> Submit</button>
	  		</div>
	  		
	  	</div>
	  	
	</div>
        

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="successModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Confirmed </h4>
        <button type="button" class="close" id="sucess_x">&times;</button>
      </div>

	<div class="modal-body" id='successModel_body'> 
		<h3>SUCCESS!!!</h3>
	  	
	</div>
        

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="sucess_close">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="cancelConf">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Cancel Booking Confirmation </h4>
        <button type="button" id="cancelConf_x">&times;</button>
      </div>

	<div class="modal-body" id='successModel_body'> 
		<h3>Cancel this booking?</h3>
		<h2 style="color: red">The action cannot be undo.</h2>
	  	
	</div>
        

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button type="button" class="btn btn-info" id="cancelConf_conf">Confirm</button>
        <button type="button" class="btn btn-danger" id="cancelConf_close">Close</button>
      </div>

    </div>
  </div>
</div>



</body>
</html>