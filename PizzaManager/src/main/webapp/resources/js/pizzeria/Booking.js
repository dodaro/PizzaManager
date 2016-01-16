var Booking = function(){
	
	//NON HO BISOGNO DELL'INITVAR perchè queste due variabili vengono inizializzate ogni volta
	var tableBooking;
	var bookingFromServer;
	var columnId = 6;
	var columnPaid = 5;

	var initDataTable = function() {
		
		tableBooking= $('#bookingTable').DataTable({
	        "paging":         true,
			columns : [ 
			        {
		                "className":      'details-control',
		                "orderable":      false,
		                "data":           null,
		                "defaultContent": ''
			        },
			        {"string" : "User"},
				    {"string" : "Name"},
				    {"string" : "Date"},
				    {"string" : "Time"},
				    {"string" : "Payment"},
				    {"string" : "Id"},
				    {"string" : "Type"},
				    {"string" : "Tables"},
				    {"string" : "AddressTo"},
				    {"string" : "Bill"}
				   
				    ],
				order : [ [ 3, 'desc' ] ]
			});
		tableBooking.columns(columnId).visible(false);
		
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
		
		setControlButtons(true,true,true,true);
		
		//controllo in caso di richiesta pagina post booking edited
		if(communicator.bookingEdited!==undefined)
			//richiamare send operation
			sendRequest('update', communicator.bookingEdited, function(response) {
				//update row
			});
	}

	var initializeBookingTable = function(bookings){
		
		for (var int = 0; int < bookings.length; int++) {
			var user="-"
			var nome="-"
			if(bookings[int].user!=undefined)
				user=bookings[int].user;
			if(bookings[int].underTheNameOf!=undefined)
				nome=bookings[int].underTheNameOf;
			
			var rowToAdd=[ "", 
			              user,
			              nome,
						  bookings[int].date,
						  bookings[int].time,
						  bookings[int].payment,
						  bookings[int].id];
			
			if(bookings[int].type=="takeAway"){
				rowToAdd.push("TAKE_AWAY");
				rowToAdd.push("-");//Tables
				rowToAdd.push("-");//AddressTo
			}
			else if(bookings[int].type=="delivery"){
				rowToAdd.push("DELIVERY");
				rowToAdd.push("-");//Tables
				rowToAdd.push(bookings[int].address.city +", "+bookings[int].address.street+" "+bookings[int].address.number);//AddressTo --> FIX		
			}
			else if(bookings[int].type=="table"){
				rowToAdd.push("TABLE");
				var stringTables="";
				for (var int2 = 0; int2 < bookings[int].tables.length; int2++) {
					stringTables+=bookings[int].tables[int2].number;
					if(int2!==bookings[int].tables.length-1)
						stringTables+=", ";
				}
				rowToAdd.push(stringTables);//Tables  --> FIX
				rowToAdd.push("-");//AddressTo	 	
			}
			
			rowToAdd.push(bookings[int].bill+" &euro;");
			tableBooking.row.add(rowToAdd).draw(false);
		}
	}

	var initListeners = function(){
		
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
				
				row.child("<img src='resources/gifs/loading.gif' width='50px' height='50px'></img>").show();
				tr.addClass('shown');
			}
		});

		$('#bookingTable tbody').on('click', 'tr', function() {
			 
			if ($(this).hasClass('selected')&& tableBooking.row(this).data()!=undefined &&($(this).hasClass("odd")|| $(this).hasClass("even"))) {
				console.log();
				$(this).removeClass('selected');
				setControlButtons(true,true,true,true);
			
			} 
			else if(!$(this).hasClass('selected') && tableBooking.row(this).data()!=undefined &&($(this).hasClass("odd")|| $(this).hasClass("even")) ){
				tableBooking.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
				console.log(tableBooking.row(this).data()[columnPaid]);
				if(tableBooking.row(this).data()[columnPaid]==true)
					setControlButtons(false,false,false,false);
				else
					setControlButtons(false,false,false,true);
			}//end else if
		});
		
		$("#confermeButtonBooking").on('click',function(){
			var idBooking=tableBooking.row('.selected').data()[columnId];
			sendRequest('conferme', findBooking(idBooking), function(response) {
				tableBooking.row('.selected').remove().draw(false);
				alert('booking'+idBooking + response);
			});	
		});
		
		$("#editButtonBooking").on('click',function(){
			editBooking();
			//com
		});
		
		$("#removeButtonBooking").on('click',function(){
			var idBooking=tableBooking.row('.selected').data()[columnId];
			sendRequest('remove', findBooking(idBooking), function(response) {
				tableBooking.row('.selected').remove().draw(false);
				alert('booking'+idBooking + response);
			});
		});
		
		//TODO LATO SERVER GESTIRE IL SAVE
		$("#saveButtonBooking").on('click',function(){
			var idBooking=tableBooking.row('.selected').data()[columnId];
			
			//simulazione salvataggio e rimozione tupla
			
			tableBooking.row('.selected').remove().draw(true);
			/*sendRequest('save', findBooking(idBooking), function(response) {
				tableBooking.row('.selected').remove().draw(false);
				alert('booking'+idBooking + response);
			});*/	
		});
		
	}
	
	function format(idBooking) {
	
		string="";
		for (var int = 0; int < bookingFromServer.length; int++) {
			if(bookingFromServer[int].id==idBooking){
				var booking=bookingFromServer[int];
				if(booking.pizzas.length>0){
					string+='<table class="table table-bordered" style="font-size:13px;"><thead><tr>'
							+'<th>Pizza</th>'
							+'<th>Gluten</th>'
							+'<th>Size</th>'
							+'<th>Edited</th>'
							+'<th>Ingredients</th>'
							+'<th>Number</th>'
							+'<th>Price</th>'
							+'<th>Total</th>'
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
								
								var listIngredients="";
								for (var int3 = 0; int3 < ingredientsTotal.length; int3++) {
									listIngredients+=ingredientsTotal[int3].name;
									if(int3!==ingredientsTotal.length-1)
										listIngredients+=", ";
								}
								
								string+="<td>"+listIngredients+"</td>"
								+"<td>"+booking.pizzas[int2].number+"</td>"
								+"<td>"+booking.pizzas[int2].priceEach+" &euro;</td>"
								+"<td>"+new Number(booking.pizzas[int2].number)*new Number(booking.pizzas[int2].priceEach)+ " &euro;</td>"
								+"</tr>";
					}
					string+='</table>';
				}
				if(booking.beverages.length>0){
					string+='<table class="table table-bordered" style="font-size:13px;"><thead><tr>'
							+'<th>Beverage</th>'
							+'<th>Brand</th>'
							+'<th>Type</th>'
							+'<th>Container</th>'
							+'<th>Size</th>'
							+'<th>Number</th>'
							+'<th>Price</th>'
							+'<th>Total</th>'
							+'</tr></thead>';
					for (var int2 = 0; int2 < booking.beverages.length; int2++) {
						string+="<tr>"
								+"<td>"+booking.beverages[int2].name+"</td>"
								+"<td>"+booking.beverages[int2].brand+"</td>"
								+"<td>"+booking.beverages[int2].type+"</td>"
								+"<td>"+booking.beverages[int2].container+"</td>"
								+"<td>"+booking.beverages[int2].size+"</td>"
								+"<td>"+booking.beverages[int2].number+"</td>"
								+"<td>"+booking.beverages[int2].priceEach+" &euro;</td>"
								+"<td>"+new Number(booking.beverages[int2].number)*(new Number(booking.beverages[int2].priceEach))+" &euro;</td>"
								+"</tr>";
					}
					string+='</table>';
					
				}
			}
		}
		
		return string;
	}

	function loadInfoBooking(bookingId,loading){
		console.log("chiamata");
		setTimeout(function(){loading();},300);
	}

	var sendRequest = function(action, bookingResume, onSuccess) {
		var reducedBooking=reduceBooking(bookingResume);
		var stringB=JSON.stringify(reducedBooking);
		$.ajax({
			method : 'POST',
			url : '/pizzeriabookingAction',
			data :{
				action: action,
				booking: stringB
			},
			success : function(response) {
				console.log(response);
				onSuccess(response);
			}
		});
	};
	
	var reduceBooking = function(booking){
		var newBooking=_.clone(booking);
		var beverages=new Array();
		var address=new Object();
		var tables=new Array();
		
		for (var int = 0; int < booking.beverages.length; int++) {
			beverages.push(_.pick(booking.beverages[int],'id','number'));
		}
		newBooking.beverages=beverages;
		//newBooking=_.omit(newBooking,'pizzas','beverages');
		
		if(booking.type=="delivery"){
			addressId=booking.address['id'];
			console.log(addressId);
			newBooking.address=new Object();
			newBooking.address.id=addressId;
			console.log(newBooking)
		}
		else if(booking.type=="table"){
		
			for (var int = 0; int < booking.tables.length; int++) {
				tables.push(booking.tables[int]['id']);
			}
			newBooking.tables=tables;
		}
		console.log(newBooking);
		console.log(booking);
		return newBooking;
	}
	
	var findBooking = function(idBooking){
		for (var int = 0; int < bookingFromServer.length; int++) {
			if(bookingFromServer[int].id==idBooking)
				return bookingFromServer[int];
		}
	}
	
	var editBooking = function(){
		var idBooking=tableBooking.row('.selected').data()[columnId];
		communicator.bookingToEdit=findBooking(idBooking);
		
		//TODO pezza da sistemare
		console.log($('#liLiveOrderTool'))
		$('#liLiveOrderTool').click();	
	}	
	
	
	
	var setControlButtons = function(boolButtonConferme, boolButtonEdit, boolButtonRemove, boolButtonSave ){
		
		$("#confermeButtonBooking").prop('disabled', boolButtonConferme);
		$("#editButtonBooking").prop('disabled', boolButtonEdit);
		$("#removeButtonBooking").prop('disabled', boolButtonRemove);
		$("#saveButtonBooking").prop('disabled', boolButtonSave);
	}
	
	return {
		init : function() {
			initDataTable();
			initListeners();
		}
	}
	
}();

