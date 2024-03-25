$(document).ready(function(){
	
	
	$.get("http://localhost:8282/getBookings", {
		}, function(result) {
			$("table tr").not(".header").remove();
			$.each(result, function(key, value) {
				if (value.status=="Upcoming") {
					$("table").append("<tr><td>"+value.bookingId+"</td><td>"+value.bookedOnDate+"</td><td>"+
					value.checkInDate+"</td><td>"+value.checkOutDate+"</td><td>"+value.customerMobile+"</td><td>"+
					value.finalCharges+"</td><td>"+value.status+
					"</td><td><button class='cancel btn btn-danger'>Cancel</button></td></tr>")
				} else if (value.status=="Completed") {
					$("table").append("<tr><td>"+value.bookingId+"</td><td>"+value.bookedOnDate+"</td><td>"+
					value.checkInDate+"</td><td>"+value.checkOutDate+"</td><td>"+value.customerMobile+"</td><td>"+
					value.finalCharges+"</td><td>"+value.status+
					"</td><td><button class='review btn btn-info'>Review</button></td></tr>")
				} else {
					$("table").append("<tr><td>"+value.bookingId+"</td><td>"+value.bookedOnDate+"</td><td>"+
					value.checkInDate+"</td><td>"+value.checkOutDate+"</td><td>"+value.customerMobile+"</td><td>"+
					value.finalCharges+"</td><td><b>"+value.status+
					"</b></td><td></td></tr>");
				}
			});
	});

	$("table").on('click','.cancel', function () {
		$("#cancelConf").toggle();
		var bookingId = $(this).closest("tr").find("td:eq(0)").text();
		var status = $(this).closest("tr").find("td:eq(6)").text();
			
		$("#cancelConf_conf").click(function () { 
			if (status=="Upcoming") {
				$.ajax({
					type: "POST",
					contentType: "application/json",
					url: "http://localhost:8282/updateStatus/"+bookingId+"/Cancel",
					success: function(result) {
						$(this).closest("tr").find("td:eq(6)").html("Cancel");
						window.location.reload();
					}, error: function(e) {
						alert(e);
					}
				});
			}
		})
		
	})
	
	$("#cancelConf_x").click(function (){
		$("#cancelConf").toggle();
	})
	
	$("#cancelConf_close").click(function (){
		$("#cancelConf").toggle();
	})
	
	
	
	$("table").on('click','.review', function () {
		$("#review-bookingId").val($(this).closest("tr").find("td:eq()").text());
		var status = $(this).closest("tr").find("td:eq(6)").text();
		if (status=="Completed") {
			$("#reviewModal").toggle();
			
			setInterval(function() {
				var review1 = parseInt($("#review-question1").val());
				var review2 = parseInt($("#review-question2").val());
				var review3 = parseInt($("#review-question3").val());
				var review4 = parseInt($("#review-question4").val());
				var rating = ((review1+review2+review3+review4)/4).toFixed(1);
				$("#review-rating").text(rating); 
			},1000);

		}
	})
	
	$("#close").click(function() {
		$("#reviewModal").toggle();	
	})
	
	$(".close").click(function() {
		$("#reviewModal").toggle();	
	})
	
	$("#review-submit").click(function() {
		var overallRating = parseFloat($("#review-rating").text());
		var text = $("#review-text").val();
		var bookingId = $("#review-bookingId").val();
		
		var review = {
			overallRating,
			text
		}
		
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8282/saveReview/"+bookingId,
			data: JSON.stringify(review),
			dataType: "json",
			success: function(result) {
				$("#successModal").toggle();
			}, error: function(e) {
				alert(e);
			}
		});
	});
	
	$("#btn-upcoming").click(function(){
		var flag=0;
		var tblRow = $("table tr").not(".header");
		$.each(tblRow, function(key, value) {
			var status = $(value).children("td").eq("6").text();
			if (status!="Upcoming") {
				flag=0;
			} else {
				flag=1;
			}
			if(flag==0){
				$(this).hide();	
			} else {
				$(this).show();	
			}
		});
	});
	
	$("#btn-completed").click(function(){
		var flag=0;
		var tblRow = $("table tr").not(".header");
		$.each(tblRow, function(key, value) {
			var status = $(value).children("td").eq("6").text();
			if (status!="Completed") {
				flag=0;
			} else {
				flag=1;
			}
			if(flag==0){
				$(this).hide();	
			} else {
				$(this).show();	
			}
		});
	});
	
	$("#btn-showall").click(function(){
		var tblRow = $("table tr").not(".header");
		$.each(tblRow, function(key, value) {
			$(this).show();	
		});
	});
	
	$("#sucess_x").click(function() {
		$("#successModal").toggle();
	})
	
	$("#sucess_close").click(function() {
		$("#successModal").toggle();
	})
})