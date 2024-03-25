$(document).ready(function(){
	
	$("#searchBtn").click(function() {		
		var searchLocation = $("#searchLocation").val();
		if (searchLocation=="") {
			alert("Please enter searching information!!!");
		} else {
			$.ajax({
				type: "GET",
				contentType: "application/json",
				url: "http://localhost:8282/findHotel/" + searchLocation,
				success: function(result1) {
					$("#hotelTbl tr").not(".header").remove();
					$.each(result1, function(key1, value1) {
						var amt = "";
						for (obj of value1.amenities) {
							amt+=obj.name+"\n<br>";
						}
						
						$.ajax({
							type: "GET",
							contentType: "application/json",
							url: "http://localhost:8282/getReviews/" + value1.hotelId,
							success: function(result2) {
								var reviews = 0;
								var review_count=0;
								$.each(result2, function(key2, value2) {
									if(value2!=null) {
										reviews+=value2.overallRating;
										review_count++;
									}
								});
								var avgReviews =  (reviews/review_count).toFixed(1);
								$("#hotelTbl").append("<tr><td>"+value1.hotelName+"</td><td>"+value1.averagePrice+"></td><td>"+
								value1.starRating+"</td><td><img class='imgLink' height='200px' width='300px' src='"+value1.imageURL+"'</td><td>"+
								amt+"</td><td><a class='show_reviews' href='#'>"+avgReviews+"</a></td><td style='display:none'>"+
								JSON.stringify(value1.hotelRooms)+"</td><td style='display:none'>"+value1.hotelId+"</td><td style='display:none'>"+
								value1.discount+"</td><td style='display:none'>"+review_count+"</td></tr>")
							}, error: function(e) {
								alert(e);
							}
						});
					});
				}, error: function(e) {
					alert(e);
				}
			});
		}
		
	});
	
	
	$("#hotelTbl").on('click','.show_reviews', function() {
		$("#showAllReviews").modal('toggle');
		var hotelId = $(this).closest("tr").find("td:eq(7)").text();
		$("#showAllReviews_body").html("");
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "http://localhost:8282/getReviews/" + hotelId,
			success: function(result) {
				$.each(result, function(key, value) {
					if(value!=null) {
						$("#showAllReviews_body").append(
						"<div>"+
						"<b>Review ID: "+value.reviewId+"</b><br>"+
						"Overall Rating: "+value.overallRating+"<br>"+
						"Review: "+value.text+
						"</div><hr>");
					}
				});
			}
		});
		
	});
	
	$("#hotelTbl").on('click','.imgLink', function () {
		$("#select_roomTypes").text("");
		var noRooms = $("#noRooms").val();
		var noGuests = $("#noGuests").val();
		var checkIn = $("#checkInDate").val()
		var checkOut = $("#checkOutDate").val();
		var roomType = JSON.parse($(this).closest("tr").find("td:eq(6)").text());
		$("#modal_discount").val(parseInt($(this).closest("tr").find("td:eq(8)").text()));
		$("#modal_hotelId").val(parseInt($(this).closest("tr").find("td:eq(7)").text()));
		$("#modal_price").val(parseInt($(this).closest("tr").find("td:eq(1)").text()));
		$("#myModal").modal('toggle');
		$("#modal_hotelName").val($(this).closest("tr").find("td:eq(0)").text());
		$("#modal_noGuests").val(noGuests);
		$("#modal_checkInDate").val(checkIn);
		$("#modal_checkOutDate").val(checkOut);
		
		for (room of roomType) {
			$("#select_roomTypes").append("<option value='"+room.hotelRoomId+"'>"+room.type.name+"</option>");
		}
		
		$("#modal_noRooms").val(noRooms);

	});
	
	
	$("#addGuests").click( function() {
		var noGuests = $("#modal_noGuests").val();
		if (noGuests=="") {
			alert("Please enter number of Guest")
		} else {
			$("#guestInfoModal").toggle();
			$("#guestsInfo").html("");
			for (let i = 0; i < noGuests; i++) {
				$("#guestsInfo").append('<div class="Guest"><b>Guest '+(i+1)+'</b><br>First Name: <input class="form-control" type="text" id="modal_guestsFirstName'+(i+1)+'"/>Last Name: <input class="form-control" type="text" id="modal_guestsLastName'+(i+1)+'"/>Age: <input class="form-control" type="number" id="modal_guestsAge'+(i+1)+'"/>Gender: <select class="form-control" id="modal_gestsGender'+(i+1)+'"><option>Male</option><option>Female</option></select><div>')
			}
		}
	});
	
	$("#guestInfo_x").click(function() {
		$("#guestInfoModal").toggle();
	})
	$("#guestInfo_close").click(function() {
		$("#guestInfoModal").toggle();
	})
	
	
	
	$("#preview").click(function() {
		$("#booking_hotelName").val($("#modal_hotelName").val());
		$("#booking_noGuests").val($("#modal_noGuests").val());
		$("#booking_noRooms").val($("#modal_noRooms").val());
		$("#booking_checkInDate").val($("#modal_checkInDate").val());
		$("#booking_checkOutDate").val($("#modal_checkOutDate").val());
		$("#booking_roomType").val($("#select_roomTypes :selected").text());
		
		var price = parseInt($("#modal_price").val());
		var discount = parseInt($("#modal_discount").val());
		$("#booking_discount").text(discount);
		$("#booking_price").text(price-(price*discount)/100);
		$("#bookingHotelRoomModal").toggle();	
	});
	
	$("#preview_close").click(function() {
		$("#bookingHotelRoomModal").toggle();
	});
	

	
	$("#confirmBooking").click(function() {
		var guestList = [];
		
		var today = new Date();
		var yyyy = today.getFullYear();
		let mm = today.getMonth() + 1;
		let dd = today.getDate();
		
		if (dd < 10) dd = '0' + dd;
		if (mm < 10) mm = '0' + mm;
		
		var bookedOnDate = yyyy + '-' + mm + '-' + dd;
		
		var hotelRoomId = $("#select_roomTypes :selected").val();
		var noGuests = $("#modal_noGuests").val();
		var price = parseInt($("#modal_price").val());
		var discount = parseInt($("#modal_discount").val());
		var customerMobile = $("#booking_customerMobile").val();
		var roomType= $("#select_roomTypes :selected").text();
		var hotelRoomId = $("#select_roomTypes :selected").val();
		var noRooms = $("#modal_noRooms").val();
		var checkInDate = $("#modal_checkInDate").val();
		var checkOutDate = $("#modal_checkOutDate").val();
		var hotelId = $("#modal_hotelId").val();
		var finalCharges = price-(price*discount)/100;	
		var totalSavings = price - finalCharges;
		var hotelName = $("#booking_hotelName").val();
		
		
		for (let i = 0; i < noGuests; i++) {
			var firstname = $("#modal_guestsFirstName"+(i+1)).val();
			var lastname = $("#modal_guestsLastName"+(i+1)).val();
			var age = $("#modal_guestsAge"+(i+1)).val();
			var gender = $("#modal_gestsGender"+(i+1)+" :selected").text();
			var guest = {
				"firstName": firstname,
				"lastName": lastname,
				"age": age,
				"gender":gender
			}
			guestList.push(guest);
		};
		
		var booking = {
			"bonanzaDiscount":0,
			"bookedOnDate":bookedOnDate,
			"checkInDate":checkInDate,
			"checkOutDate":checkOutDate,
			"customerMobile":customerMobile,
			"discount":discount,
			"finalCharges":finalCharges,
			"hotelId":hotelId,
			"hotelRoomId":hotelRoomId,
			"noRooms":noRooms,
			"price":price,
			"status":"Upcoming",
			"roomType":roomType,
			"taxRateInPercent":0,
			"totalSavings":totalSavings,
			"guests" : guestList,
			"hotelName":hotelName
		}
		
		
		
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8282/saveBooking",
			data: JSON.stringify(booking),
			dataType: "json",
			success: function(result) {
				alert("Successfully!");
			}, error: function(e) {
				alert(e);
			}
		});
	
			
		
	});
		
	
	
	$("#filterBtn").click(function() {
		var filterList = [[],[]];
		var tblRow = $("#hotelTbl tr").not(".header");
		$.each($(".star_rating"), function(key, value) {
			if ($(this).prop('checked')==true) {
				filterList[0].push(parseInt($(this).val()));
			}
		});
		$.each($(".hotel_amenity"), function(key1, value1) {
			if ($(this).prop('checked')==true) {
				if ($(this).val()=='PARKING') {
					filterList[1].push('Parking');
				}
				if ($(this).val()=="CHECK-IN & CHECK-OUT TIMES") {
					filterList[1].push("Travel Desk");
				}
				if ($(this).val()=="BREAKFAST") {
					filterList[1].push("Restaurant");
				}
				if ($(this).val()=="BAR OR LOUNGE") {
					filterList[1].push("Mini Bar");
				}
				if ($(this).val()=="FITNESS CENTER") {
					filterList[1].push("Swimming Pool");
				}
			}
		});
		var priceRange = parseInt($("#priceRange").val());
		var tblRow = $("#hotelTbl tr").not(".header");
		$(tblRow).show();
		$.each(tblRow, function(key2, value2){
			var flag=0;
			var hotelPrice = parseInt($(value2).children("td").eq("1").text());
			var hotelRating = parseInt($(value2).children("td").eq("2").text());
			var hotelAmentities = (($(value2).children("td").eq("4").text()).trim()).split("\n");
			
			var filterStar = filterList[0].length;
			var filterAmt = filterList[1].length;
			var result_filterAmt = (hotelAmentities.filter(x => filterList[1].includes(x))).length;
			if(hotelPrice<priceRange) {
				if (filterStar==0 && filterAmt==0) {
					flag=1;
				} 
				else if (filterStar!=0 && filterAmt==0) {
					if (filterList[0].includes(hotelRating)) {
						flag=1;
					}
				}
			
				else if (filterAmt!=0 && filterStar!=0) {
					if (filterList[0].includes(hotelRating) && (result_filterAmt == filterAmt)) {
						flag=1;
					}
				} else if (filterAmt!=0 && filterStar==0) {
					if (result_filterAmt == filterAmt) {
						flag=1;
					}
				}
			}
			if(flag==0){
				$(this).hide();	
			} else {
				
			}
		});
	});	
	
	
	
});