var LiveRestaurant = function(){
	
	var tableLiveRestaurant;
	var bookingConfermedFromServer;
	var columnId = 7;
	var columnPayment = 5;

	var initDataTable = function() {
		
		tableLiveRestaurant= $('#liveRestaurantTable').DataTable({
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
				    {"string" : "Data"},
				    {"string" : "Time"},
				    {"string" : "Payment"},
				    {"string" : "Bill"},
				    {"string" : "Id"}
				   
				    ],
				order : [ [ 3, 'desc' ] ]
			});
		
		var data;
		$.ajax({
			url : "/pizzerialiverestaurantAjax",
			type : 'GET',
			success : function(data) {
				console.log(data);
				//inizialize table
				bookingConfermedFromServer=data;
				initializeLiveRestaurantTable(data);
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});
		
		setControlButtons(true,true,true,true,true);
	}

	var initializeLiveRestaurantTable = function(bookingsConfermed){
		
		for (var int = 0; int < bookingsConfermed.length; int++) {
			
			var user="-"
			var nome="-"
			if(bookingsConfermed[int].user!=undefined)
				user=bookingsConfermed[int].user;
			if(bookingsConfermed[int].underTheNameOf!=undefined)
				nome=bookingsConfermed[int].underTheNameOf;
			
			var rowToAdd=[ "", 
						  user,
						  nome,
						  bookingsConfermed[int].date,
						  bookingsConfermed[int].time,
						  bookingsConfermed[int].payment,
						  bookingsConfermed[int].bill +" &euro;",
						  bookingsConfermed[int].id];
			tableLiveRestaurant.row.add(rowToAdd).draw(false);
		}
	}

	
	
	
	var initListeners = function(){
		
		$('#liveRestaurantTable tbody').on('click', 'td.details-control', function() {

			var tr = $(this).closest('tr');
			var row = tableLiveRestaurant.row(tr);
			
			if (row.child.isShown()) {
				row.child.remove();
				tr.removeClass('shown');
			} 
			else{
				loadInfoLiveRestaurant(2,function (){
					tableLiveRestaurant.row('.selected');
					tableLiveRestaurant.row('.selected').child(format(row.data()[columnId])).show();
					console.log("callback");
				});
				
				row.child("<div>loading</div>").show();
				tr.addClass('shown');
			}
		});

		$('#liveRestaurantTable tbody').on('click', 'tr', function() {
			 
			if ($(this).hasClass('selected')&& tableLiveRestaurant.row(this).data()!=undefined &&($(this).hasClass("odd")|| $(this).hasClass("even"))) {

				$(this).removeClass('selected');
				setControlButtons(true,true,true,true,true);
			
			} 
			else if(!$(this).hasClass('selected') && tableLiveRestaurant.row(this).data()!=undefined &&($(this).hasClass("odd")|| $(this).hasClass("even")) ){
				
				tableLiveRestaurant.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
				if(tableLiveRestaurant.row(this).data()[columnPayment]=="true")
						setControlButtons(false,false,false,false,true);
					else
						setControlButtons(false,false,false,false,false);
			}//end else if
		});
		
		/*$("#confermeButtonLiveRestaurant").on('click',function(){
			var idLiveRestaurant=tableLiveRestaurant.row('.selected').data()[columnId];
			sendRequest('conferme', findLiveRestaurant(idLiveRestaurant), function(response) {
				tableLiveRestaurant.row('.selected').remove().draw(false);
				alert('liveRestaurant'+idLiveRestaurant + response);
			});	
		});
		
		$("#editButtonLiveRestaurant").on('click',function(){
			editLiveRestaurant();
		});
		
		$("#removeButtonLiveRestaurant").on('click',function(){
			var idLiveRestaurant=tableLiveRestaurant.row('.selected').data()[columnId];
			sendRequest('remove', findLiveRestaurant(idLiveRestaurant), function(response) {
				tableLiveRestaurant.row('.selected').remove().draw(false);
				alert('liveRestaurant'+idLiveRestaurant + response);
			});
		});*/
		
	}
	
	function format(idBookingConfermed) {
	
		string="";
		for (var int = 0; int < bookingConfermedFromServer.length; int++) {
			if(bookingConfermedFromServer[int].id==idBookingConfermed){
				var bookingConfermed=bookingConfermedFromServer[int];
				string+="<div> type:"+bookingConfermed.type+"</div>";
				if(bookingConfermed.type=="delivery")
						"<div> type:"+bookingConfermed.address+"</div>";
				else if(bookingConfermed.type=="table")
						"<div> type:"+bookingConfermed.table+"</div>";
				
				
				if(bookingConfermed.pizzas.length>0){
					string+='<table class="table table-bordered"><thead><tr>'
							+'<th>Pizza</th>'
							+'<th>Gluten</th>'
							+'<th>Size</th>'
							+'<th>Edited</th>'
							+'<th>Ingredients</th>'
							+'<th>Number</th>'
							+'<th>Price</th>'
							+'<th>Total</th>'
							+'</tr></thead>';
					
							
					for (var int2 = 0; int2 < bookingConfermed.pizzas.length; int2++) {
						string+="<tr>"
								+"<td>"+bookingConfermed.pizzas[int2].name+"</td>"
								+"<td>"+bookingConfermed.pizzas[int2].gluten+"</td>"
								+"<td>"+bookingConfermed.pizzas[int2].size+"</td>";
								console.log(bookingConfermed.pizzas[int2].ingredientsAdded.length);
								if(bookingConfermed.pizzas[int2].ingredientsAdded.length>0 || bookingConfermed.pizzas[int2].ingredientsRemoved.length>0)
									string+="<td>yes</td>";	
								else
									string+="<td>no</td>";
									
								var ingredientsTotal;
								ingredientsTotal=_.difference(bookingConfermed.pizzas[int2].ingredientsBase,bookingConfermed.pizzas[int2].ingredientsRemoved);
								ingredientsTotal=_.union(ingredientsTotal,bookingConfermed.pizzas[int2].ingredientsAdded);
								
								var listIngredients="";
								for (var int3 = 0; int3 < ingredientsTotal.length; int3++) {
									listIngredients+=ingredientsTotal[int3].name;
									if(int3!==ingredientsTotal.length-1)
										listIngredients+=",";
								}
								
								string+="<td>"+listIngredients+"</td>"
								+"<td>"+bookingConfermed.pizzas[int2].number+"</td>"
								+"<td>"+bookingConfermed.pizzas[int2].priceEach+" &euro;</td>"
								+"<td>"+new Number(bookingConfermed.pizzas[int2].number)*new Number(bookingConfermed.pizzas[int2].priceEach)+ " &euro;</td>"
								+"</tr>";
					}
					string+='</table>';
				}
				if(bookingConfermed.beverages.length>0){
					string+='<table class="table table-bordered"><thead><tr>'
							+'<th>Beverage</th>'
							+'<th>Brand</th>'
							+'<th>Type</th>'
							+'<th>Container</th>'
							+'<th>Size</th>'
							+'<th>Number</th>'
							+'<th>Price</th>'
							+'<th>Total</th>'
							+'</tr></thead>';
					for (var int2 = 0; int2 < bookingConfermed.beverages.length; int2++) {
						string+="<tr>"
								+"<td>"+bookingConfermed.beverages[int2].name+"</td>"
								+"<td>"+bookingConfermed.beverages[int2].brand+"</td>"
								+"<td>"+bookingConfermed.beverages[int2].type+"</td>"
								+"<td>"+bookingConfermed.beverages[int2].container+"</td>"
								+"<td>"+bookingConfermed.beverages[int2].size+"</td>"
								+"<td>"+bookingConfermed.beverages[int2].number+"</td>"	
								+"<td>"+bookingConfermed.beverages[int2].priceEach+" &euro;</td>"
								+"<td>"+new Number(bookingConfermed.beverages[int2].number)*(new Number(bookingConfermed.beverages[int2].priceEach))+" &euro;</td>"
								+"</tr>";
					}
					string+='</table>';
					
				}
			}
		}
		
		return string;
	}

	function loadInfoLiveRestaurant(idBookingConfermed,loading){
		console.log("chiamata");
		setTimeout(function(){loading();},200);
	}
	/*
	var sendRequest = function(action, liveRestaurantResume, onSuccess) {
		var reducedLiveRestaurant=reduceLiveRestaurant(liveRestaurantResume);
		var stringB=JSON.stringify(reducedLiveRestaurant);
		$.ajax({
			method : 'POST',
			url : '/pizzerialiveRestaurantAction',
			data :{
				action: action,
				liveRestaurant: stringB
			},
			success : function(response) {
				console.log(response);
				onSuccess(response);
			}
		});
	};
	
	var reduceLiveRestaurant = function(liveRestaurant){
		var beverages=new Array();
		for (var int = 0; int < liveRestaurant.beverages.length; int++) {
			beverages.push(_.pick(liveRestaurant.beverages[int],'id','number'));
		}
		
		var newLiveRestaurant=_.clone(liveRestaurant);
		newLiveRestaurant.beverages=beverages;
		//newLiveRestaurant=_.omit(newLiveRestaurant,'pizzas','beverages');
		console.log(newLiveRestaurant);
		console.log(liveRestaurant);
		return newLiveRestaurant;
	}
	*/
	var findBookingConfermed = function(idBooking){
		for (var int = 0; int < bookingConfermedFromServer.length; int++) {
			if(bookingConfermedFromServer[int].id==idBooking)
				return bookingConfermedFromServer[int];
		}
	}
	/*
	var editLiveRestaurant = function(){
		var idLiveRestaurant=tableLiveRestaurant.row('.selected').data()[columnId];
		communicator.liveRestaurantToEdit=findLiveRestaurant(idLiveRestaurant);
		
		//TODO pezza da sistemare
		console.log($('#liLiveOrderTool'))
		$('#liLiveOrderTool').click();	
	}*/	
	
	
	
	var setControlButtons = function(boolButtonComplete, boolButtonRestore, boolButtonRemove, boolButtonSave, boolButtonPay  ){
		
		$("#completeButtonLiveRestaurant").prop('disabled', boolButtonComplete);
		$("#restoreButtonLiveRestaurant").prop('disabled', boolButtonRestore);
		$("#removeButtonLiveRestaurant").prop('disabled', boolButtonRemove);
		$("#saveButtonLiveRestaurant").prop('disabled', boolButtonSave);
		$("#payButtonLiveRestaurant").prop('disabled', boolButtonPay);
	}
	
	return {
		init : function() {
			initDataTable();
			initListeners();
		}
	}
	
}();

