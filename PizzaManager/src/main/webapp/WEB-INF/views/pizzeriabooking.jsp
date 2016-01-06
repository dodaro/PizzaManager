<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" class="init">
	var tableBooking;
	var bookingFromServer;
	var columnId=5;
	
	$(document).ready(function() {
		
		tableBooking= $('#bookingTable').DataTable({
	        "paging":         true,
			columns : [ 
			        {
		                "className":      'details-control',
		                "orderable":      false,
		                "data":           null,
		                "defaultContent": ''
			        },
				    {"string" : "Name"},
				    {"string" : "Date"},
				    {"string" : "Time"},
				    {"string" : "Payment"},
				    {"string" : "Id"}
				    ],
				order : [ [ 3, 'desc' ] ]
			});
		
		var data;
		$.ajax({
			url : "/pizzeriabookingAjax",
			type : 'GET',
			success : function(data) {
				//alert(data);
				console.log(data);
				//inizialize table
				bookingFromServer=data;
				initializeBookingTable(data);
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});

	});
	
	$('#bookingTable tbody').on('click', 'td.details-control', function() {

		var tr = $(this).closest('tr');
		var row = tableBooking.row(tr);
		
		if (row.child.isShown()) {
			row.child.remove();
			tr.removeClass('shown');
		} else {
			
			loadInfoBooking(2,function (){
				tableBooking.row('.selected');
				tableBooking.row('.selected').child(format(row.data()[columnId])).show();
				console.log("callback");
			});
			
			row.child("<div>loading</div>").show();
			tr.addClass('shown');
		}
	});
	
	/*SOLO PIZZA: Listener che selezione la riga e carica i dati del selezionato*/
	$('#bookingTable tbody').on('click', 'tr', function() {
		 
		if ($(this).hasClass('selected')&& tableBooking.row(this).data()!=undefined &&($(this).hasClass("odd")|| $(this).hasClass("even"))) {
			console.log();
			$(this).removeClass('selected');
			$("#editButtonBooking").prop('disabled', true);
			$("#removeButtonBooking").prop('disabled', true);
			$("#confermeButtonBooking").prop('disabled', true);
		} 
		else if(!$(this).hasClass('selected') && tableBooking.row(this).data()!=undefined &&($(this).hasClass("odd")|| $(this).hasClass("even")) ){
			tableBooking.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
			$("#editButtonBooking").prop('disabled', false);
			$("#removeButtonBooking").prop('disabled', false);
			$("#confermeButtonBooking").prop('disabled', false);
		}//end else if
	});
	
	var initializeBookingTable = function(bookings){
		
		for (var int = 0; int < bookings.length; int++) {
			if(bookings[int].type=="takeAway"){
				
				tableBooking.row.add(
					[ "", 
					  "Giacobbino",
					  bookings[int].date,
					  bookings[int].time,
					  bookings[int].payment,
					  bookings[int].id]).draw(false);
			}
			
		}
	}
	
	function format(idBooking) {
		//console.log(d[0]);
		// `d` is the original data object for the row
		string="";
		for (var int = 0; int < bookingFromServer.length; int++) {
			if(bookingFromServer[int].id==idBooking){
				var booking=bookingFromServer[int];
				if(booking.pizzas.length>0){
					string+='<table class="table table-bordered"><thead><tr>'
							+'<th>Pizza</th>'
							+'<th>Gluten</th>'
							+'<th>Size</th>'
							+'<th>Edited</th>'
							+'<th>Ingredients</th>'
							+'<th>Number</th>'
							+'</tr></thead>';
					
							
					for (var int2 = 0; int2 < booking.pizzas.length; int2++) {
						string+="<tr>"
								+"<td>"+booking.pizzas[int2].name+"</td>"
								+"<td>"+booking.pizzas[int2].gluten+"</td>"
								+"<td>"+booking.pizzas[int2].size+"</td>";
								console.log(booking.pizzas[int2].ingredientsAdded.length);
								if(booking.pizzas[int2].ingredientsAdded.length>0 || booking.pizzas[int2].ingredientsRemoved.length>0)
									string+="<td>yes</td>";	
								else
									string+="<td>no</td>";
									
								var ingredientsTotal;
								ingredientsTotal=_.difference(booking.pizzas[int2].ingredientsBase,booking.pizzas[int2].ingredientsRemoved);
								ingredientsTotal=_.union(ingredientsTotal,booking.pizzas[int2].ingredientsAdded);
								
								string+="<td>"+ingredientsTotal+"</td>"
								+"<td>"+booking.pizzas[int2].number+"</td>"
								+"</tr>";
					}
					string+='</table>';
				}
				if(booking.beverages.length>0){
					string+='<table class="table table-condensed">'
					for (var int2 = 0; int2 < booking.beverages.length; int2++) {
						
					}
					string+='</table>';
				}
				if(booking.menus.length>0){
					string+='<table class="table table-condensed">'
					for (var int2 = 0; int2 < booking.menus.length; int2++) {
						
					}
					string+='</table>';
				}
			}
		}
		
		return string;
	}
	
	function loadInfoBooking(bookingId,loading){
		console.log("chiamata");
		setTimeout(function(){loading();},500);
	}
	
	var editBooking = function(){
		var idBooking=tableBooking.row('.selected').data()[columnId];
		for (var int = 0; int < bookingFromServer.length; int++) {
			if(bookingFromServer[int].id==idBooking){
				communicator.bookingToEdit=bookingFromServer[int];
				console.log($('#liLiveOrderTool'))
				$('#liLiveOrderTool').click();
			}			
		}
	}

</script>
	<div>
		<!--  <button id="demo-modal" type="button" class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal">Launch demo modal</button>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Modal title</h4>
					</div>
					<div class="modal-body">
						<div></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save changes</button>
					</div>
				</div>
			</div>
		</div>-->

	<div class="row">
		<div class="col-xs-8">
			<table id="bookingTable" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th></th>
						<th>Name</th>
						<th>Date</th>
						<th>Time</th>
						<th>Payment</th>
						<th>Id</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="col-xs-4">
			<button id="confermeButtonBooking" type="submit" class="btn btn-default" >Conferme</button>
			<button id="editButtonBooking" type="submit" class="btn btn-default" onclick="editBooking()">Edit</button>
			<button id="removeButtonBooking" type="submit" class="btn btn-default">Remove</button>
		</div>
	</div>

	</div>