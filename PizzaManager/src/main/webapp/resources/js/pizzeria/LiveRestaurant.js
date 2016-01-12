var LiveRestaurant = function(){
	
	var tableLiveRestaurant;
	var liveRestaurantFromServer;
	var columnId = 5;

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
				    {"string" : "Name"},
				    {"string" : "Date"},
				    {"string" : "Time"},
				    {"string" : "Payment"},
				    {"string" : "Id"},
				    {"string" : "Type"},
				    {"string" : "Tables"},
				    {"string" : "AddressTo"}
				   
				    ],
				order : [ [ 3, 'desc' ] ]
			});
		
		var data;
		$.ajax({
			url : "/pizzerialiveRestaurantAjax",
			type : 'GET',
			success : function(data) {
				//alert(data);
				console.log(data);
				//inizialize table
				liveRestaurantFromServer=data;
				initializeLiveRestaurantTable(data);
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});
		
		setControlButtons(true,true,true);
	}

	var initializeLiveRestaurantTable = function(liveRestaurants){
		
		for (var int = 0; int < liveRestaurants.length; int++) {
			var rowToAdd=[ "", 
						  "Giacobbino",
						  liveRestaurants[int].date,
						  liveRestaurants[int].time,
						  liveRestaurants[int].payment,
						  liveRestaurants[int].id];
			
			if(liveRestaurants[int].type=="takeAway"){
				rowToAdd.push("TAKE_AWAY");
				rowToAdd.push("-");//Tables
				rowToAdd.push("-");//AddressTo
			}
			else if(liveRestaurants[int].type=="delivery"){
				rowToAdd.push("DELIVERY");
				rowToAdd.push("-");//Tables
				rowToAdd.push(liveRestaurants[int].address);//AddressTo --> FIX		
			}
			else if(liveRestaurants[int].type=="table"){
				rowToAdd.push("TABLE");
				rowToAdd.push(liveRestaurants[int].table);//Tables  --> FIX
				rowToAdd.push("-");//AddressTo	 	
			}
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
			} else {
				
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
				console.log();
				$(this).removeClass('selected');
				setControlButtons(true,true,true);
			} 
			else if(!$(this).hasClass('selected') && tableLiveRestaurant.row(this).data()!=undefined &&($(this).hasClass("odd")|| $(this).hasClass("even")) ){
				tableLiveRestaurant.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
				setControlButtons(false,false,false);
			}//end else if
		});
		
		$("#confermeButtonLiveRestaurant").on('click',function(){
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
		});
		
	}
	
	function format(idLiveRestaurant) {
	
		string="";
		for (var int = 0; int < liveRestaurantFromServer.length; int++) {
			if(liveRestaurantFromServer[int].id==idLiveRestaurant){
				var liveRestaurant=liveRestaurantFromServer[int];
				if(liveRestaurant.pizzas.length>0){
					string+='<table class="table table-bordered"><thead><tr>'
							+'<th>Pizza</th>'
							+'<th>Gluten</th>'
							+'<th>Size</th>'
							+'<th>Edited</th>'
							+'<th>Ingredients</th>'
							+'<th>Number</th>'
							+'</tr></thead>';
					
							
					for (var int2 = 0; int2 < liveRestaurant.pizzas.length; int2++) {
						string+="<tr>"
								+"<td>"+liveRestaurant.pizzas[int2].name+"</td>"
								+"<td>"+liveRestaurant.pizzas[int2].gluten+"</td>"
								+"<td>"+liveRestaurant.pizzas[int2].size+"</td>";
								console.log(liveRestaurant.pizzas[int2].ingredientsAdded.length);
								if(liveRestaurant.pizzas[int2].ingredientsAdded.length>0 || liveRestaurant.pizzas[int2].ingredientsRemoved.length>0)
									string+="<td>yes</td>";	
								else
									string+="<td>no</td>";
									
								var ingredientsTotal;
								ingredientsTotal=_.difference(liveRestaurant.pizzas[int2].ingredientsBase,liveRestaurant.pizzas[int2].ingredientsRemoved);
								ingredientsTotal=_.union(ingredientsTotal,liveRestaurant.pizzas[int2].ingredientsAdded);
								
								var listIngredients="";
								for (var int3 = 0; int3 < ingredientsTotal.length; int3++) {
									listIngredients+=ingredientsTotal[int3].name;
									if(int3!==ingredientsTotal.length-1)
										listIngredients+=",";
								}
								
								string+="<td>"+listIngredients+"</td>"
								+"<td>"+liveRestaurant.pizzas[int2].number+"</td>"
								+"</tr>";
					}
					string+='</table>';
				}
				if(liveRestaurant.beverages.length>0){
					string+='<table class="table table-bordered"><thead><tr>'
							+'<th>Beverage</th>'
							+'<th>Brand</th>'
							+'<th>Type</th>'
							+'<th>Container</th>'
							+'<th>Size</th>'
							+'<th>Number</th>'
							+'</tr></thead>';
					for (var int2 = 0; int2 < liveRestaurant.beverages.length; int2++) {
						string+="<tr>"
								+"<td>"+liveRestaurant.beverages[int2].name+"</td>"
								+"<td>"+liveRestaurant.beverages[int2].brand+"</td>"
								+"<td>"+liveRestaurant.beverages[int2].type+"</td>"
								+"<td>"+liveRestaurant.beverages[int2].container+"</td>"
								+"<td>"+liveRestaurant.beverages[int2].size+"</td>"
								+"<td>"+liveRestaurant.beverages[int2].number+"</td>"		
								+"</tr>";
					}
					string+='</table>';
					
				}
			}
		}
		
		return string;
	}

	function loadInfoLiveRestaurant(liveRestaurantId,loading){
		console.log("chiamata");
		setTimeout(function(){loading();},200);
	}

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
	
	var findLiveRestaurant = function(idLiveRestaurant){
		for (var int = 0; int < liveRestaurantFromServer.length; int++) {
			if(liveRestaurantFromServer[int].id==idLiveRestaurant)
				return liveRestaurantFromServer[int];
		}
	}
	
	var editLiveRestaurant = function(){
		var idLiveRestaurant=tableLiveRestaurant.row('.selected').data()[columnId];
		communicator.liveRestaurantToEdit=findLiveRestaurant(idLiveRestaurant);
		
		//TODO pezza da sistemare
		console.log($('#liLiveOrderTool'))
		$('#liLiveOrderTool').click();	
	}	
	
	
	
	var setControlButtons = function(boolButtonConferme, boolButtonEdit, boolButtonRemove ){
		
		$("#confermeButtonLiveRestaurant").prop('disabled', boolButtonConferme);
		$("#editButtonLiveRestaurant").prop('disabled', boolButtonEdit);
		$("#removeButtonLiveRestaurant").prop('disabled', boolButtonRemove);
	}
	
	return {
		init : function() {
			initDataTable();
			initListeners();
		}
	}
	
}();

