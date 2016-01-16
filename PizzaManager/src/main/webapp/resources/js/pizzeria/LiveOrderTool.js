var LiveOrderTool = function(){
	/*Identificativi universali:
		-Bevande: id(per ora corrispondono agli id del database, l'ideale è fare una crittografia degli id del database, ed usare quelli localmente)
		-Ingredienti: id 
		-Pizze: nome + nome ingredienti
	*/
	var loadedBookingToEditId=-1;
	var pizzeFromServer;//è la lista delle pizze, con i suoi ingredienti basilari, fornite dalla pizzeria e che risiedono sul database
	var beverageFromServer;//è la lista di tutte le bevande fornite dalla pizzeria e che risiedono sul database
	var pizzeriaIngredientsFromServer;//è la lista di tutti gli ingredienti di cui è fornita la pizzeria
	
	var pizzaList = new Array();//è la lista delle pizze ordinate dal client
	var beverageList = new Array();//è la lista delle bevande  ordinate dal client
	
	//VIEW
	var tablePizza, tableBeverage;//tabelle
	//Id generator
	var codePizza = 0;//l'id delle pizze viene generato al momento, è un id valido localmente in quanto le pizze create non esistono sul database  
	
	//costanti
	var columnId = 5;
	var columnNumber = 4;

	var initVar = function(){
		
		loadedBookingToEditId=-1;
		pizzeFromServer=new Object();
		beverageFromServer=new Object();
		pizzeriaIngredientsFromServer=new Object();
		pizzaList = new Array();
		beverageList = new Array();
		tablePizza=new Object();
		tableBeverage=new Object();
		codePizza = 0;
		columnId = 5;
		columnNumber = 4;
	}
	
	var initLiveTool = function(){
			console.log(pizzaList);
			//datepicker
			$('#datetimepicker1').datetimepicker({
				format: 'DD/MM/YYYY HH:mm',
			});
			//creazione SELECT2
			$(".js-example-basic-single").select2();
			$(".js-example-basic-multiple").select2();
			//creazione bootsrap-switch
			$(".switch-radio1").bootstrapSwitch();
			checkTypeBooking("takeAway");
			//proprietà bottoni di modifica disabilitati al caricamento della pagina
			setControlButtons("pizza", false, true, true);
			setControlButtons("beverage", false, true, true);

			tablePizza = $('#resumeTablePizza').DataTable({
				"scrollY" : "320px",
				"scrollCollapse" : true,
				"paging" : false,
				columns : [{
					"className" : 'details-control',
					"orderable" : false,
					"data" : null,
					"defaultContent" : ''
				}, {
					"string" : "pizza"
				}, {
					"string" : "glutenFree"
				}, {
					"string" : "size"
				}, {
					"string" : "number"
				}, {
					"string" : "id"
				} ],
				order : [ [ 5, 'asc' ] ]
			});

			
			tableBeverage = $('#resumeTableBeverage').DataTable({
				"scrollY" : "300px",
				"scrollCollapse" : true,
				"paging" : false,
				columns : [ {
					"string" : "beverage"
				}, {
					"string" : "name"
				}, {
					"string" : "container"
				}, {
					"string" : "size"
				}, {
					"string" : "number"
				}, {
					"string" : "id"
				} ],
				order : [ [ 5, 'asc' ] ]
			});
			
			
			//Nascondo la colonna codePizza e codeBeverage in quanto non deve essere visibile
			tablePizza.columns(columnId).visible(false);
			tableBeverage.columns(columnId).visible(false);

			$.ajax({
				url : "/pizzerialiveorderPizzas",
				type : 'GET',
				success : function(pizzeria) {
					
					//init From server
					pizzeFromServer = pizzeria.pizzas;
					beverageFromServer = pizzeria.beverages;
					pizzeriaIngredientsFromServer = pizzeria.allPizzeriaIngredients;
					//popolazione pizzas: fatta con SPRING
					//popolazione beverage
					createBeverage(beverageFromServer);
					//se la pagina è stata invocata per modificare un booking
					if(communicator.bookingToEdit!== undefined){
						
						console.log(communicator.bookingToEdit);
						var bookingToEdit=communicator.bookingToEdit;
						loadedBookingToEditId=bookingToEdit.id;
						for (var int = 0; int < bookingToEdit.pizzas.length; int++) {
							var pizzaPostMapping= mapping(bookingToEdit.pizzas[int]);
							resolvePizza(false,pizzaPostMapping,bookingToEdit.pizzas[int].number);
						}
						for (var int2 = 0; int2 < bookingToEdit.beverages.length; int2++) {
							resolveBeverage(false,bookingToEdit.beverages[int2]);
						}
						//settare le intestazioni 
						initHeading(bookingToEdit);
						//TODO: TROVARE IL MODO DI FARE L'INIT HEADING DOPO IL createTables
						//rimuovere la seconda richiesta ajax ed popolare tramite model e $ 
						
						//Eliminare subito l'oggetto dal communicator
						communicator.bookingToEdit=undefined;
					}
				},
				error : function(data, status, er) {
					alert("error: " + data + " status: "
							+ status + " er:" + er);
				}
			});	
			
			$.ajax({
				url : "/pizzeria/tablesList",
				type : 'GET',
				success : function(data) {
					console.log(data)
					createTables(data);
				},
				error : function(data, status, er) {
					alert("error: " + data + " status: " + status + " er:" + er);
				}
			});
			
			
		}
	
	
	var initListeners = function(){
		
		//listener che ad ogni selezione della pizza carica tutti i suoi ingredienti
		$('#pizzaList.js-example-basic-single').on('select2-selected',function(e) {
				loadIngredientsForPizza($("#pizzaList.js-example-basic-single").select2('data').text);
		});
		
		//listener per aggiungere i dettagli di una riga*/
		$('#resumeTablePizza tbody').on('click','td.details-control', function() {

			var tr = $(this).closest('tr');
			var row = tablePizza.row(tr);
			if (row.child.isShown()) {
				row.child.remove();
				tr.removeClass('shown');
			} 
			else {
				row.child(format(row.data())).show();
				tr.addClass('shown');
			}
		});

		$('#confermeOrder').on("click",function(){
			sendOrder();
		});
		//Beverage listener
		$('#confermeButtonBeverage').on("click",function(){
			resolveBeverage(false);
		});
		$('#editButtonBeverage').on("click",function(){
			resolveBeverage(true);
		});
		$('#removeButtonBeverage').on("click",function(){
			removeItem('beverage');
		});
		$('#decrementCounterBeverage').on("click",function(){
			counterItem('counterBeverage','d');
		});
		$('#incrementCounterBeverage').on("click",function(){
			counterItem('counterBeverage','i');
		});
		//Pizza listener
		$('#confermeButtonPizza').on("click",function(){
			resolvePizza(false);
		});
		$('#editButtonPizza').on("click",function(){
			resolvePizza(true);
		});
		$('#removeButtonPizza').on("click",function(){
			removeItem('pizza');
		});
		$('#decrementCounterPizza').on("click",function(){
			counterItem('counterPizza','d');
		});
		$('#incrementCounterPizza').on("click",function(){
			counterItem('counterPizza','i');
		});
		
		$('.switch-radio1').on('switchChange.bootstrapSwitch', function(event, state) {
			  console.log(this.value); // DOM element
			  checkTypeBooking(this.value);
			});
		
		//Listener che selezione la riga e carica i dati del selezionato
		$('#resumeTablePizza tbody').on('click','tr',function() {
			if ($(this).hasClass('selected') && tablePizza.row(this).data() != undefined && ($(this).hasClass("odd") || $(this).hasClass("even"))) {
				
				$(this).removeClass('selected');
				setControlButtons("pizza", false, true, true);
				resetControls("pizza");
				
			} 
			else if (!$(this).hasClass('selected') && tablePizza.row(this).data() != undefined && ($(this).hasClass("odd") || $(this).hasClass("even"))) {
								
				tablePizza.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
				setControlButtons("pizza", true, false, false);
	
				var code = tablePizza.row($(this)).data()[columnId];
				var number = tablePizza.row($(this)).data()[columnNumber];
				for (var j = 0; j < pizzaList.length; j++) {
						if (pizzaList[j].getCode() == code) {
							loadInfoForPizzaControls( pizzaList[j],number);
						}
					}
				}//end else if
			});

		/*Listener che selezione la riga e carica i dati del selezionato*/
		$('#resumeTableBeverage tbody').on('click','tr',function() {

			if($(this).hasClass('selected') && tableBeverage.row(this).data() != undefined && ($(this).hasClass("odd")||  $(this).hasClass("even"))) {
								
				$(this).removeClass('selected');
				setControlButtons("beverage", false, true, true);
				resetControls("beverage");
					
			} 
			else if (!$(this).hasClass('selected') && tableBeverage.row(this).data() != undefined && ($(this).hasClass("odd") || $(this).hasClass("even"))) {
								
				tableBeverage.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
				setControlButtons("beverage", true, false, false);
				var number = tableBeverage.row($(this)).data()[columnNumber];
				var id = tableBeverage.row($(this)).data()[columnId];
				
				//setting controlsBeverage
				$('#counterBeverage').val(number);
				for (var int = 0; int < beverageFromServer.length; int++) {
					if(beverageFromServer[int].id==id){
						var string=beverageFromServer[int].brand+" "+beverageFromServer[int].name+" - "+beverageFromServer[int].container+" - size:"+beverageFromServer[int].size;
						$("#beverageList.js-example-basic-single").select2('data', {
							id: "idBev_"+id,
							text : string
						});						
					}
				}
			}//end else if
		});

	} 
	/*****************************************************   FUNZIONI COMUNI ********************************************************************************/
	var createBooking = function(){
		

		var booking=new Object();
		booking.beverages=extractData("beverages");
		booking.pizzas=extractData("pizzas");
		booking.date=extractData("date");
		
		//console.log(extractData("beverages"));
		//console.log(extractData("pizzas"));
	
		if($("[value='delivery']").is(':checked')){
			//console.log(extractData("address"));
			booking.address=extractData("address");
			booking.type="delivery";
		}
		else if($("[value='table']").is(':checked')){
			//console.log(extractData("tables"));
			booking.tables=extractData("tables");
			booking.type="table";
		}
		else{
			booking.type="takeAway";
		}
		
		var user=extractData("user");
		var name=extractData("name");
		
		if(user!=undefined || user!="" || user!="User")
			booking.user=user;
		if(name!=undefined || name!="" || name!="Name")	
			booking.underTheNameOf=name;
		
		var dateTime=extractData("date");
		booking.date=dateTime.split(" ")[0];
		booking.time=dateTime.split(" ")[1];
		
		console.log(booking);
		console.log(extractData("user"));
		console.log(extractData("name"));
		
		if(loadedBookingToEditId!=-1){
			booking.id=loadedBookingToEditId;
		}
		else{
			booking.id=undefined;
		}
		return booking;
		//console.log(extractData("date"));
	}
	
	var sendOrder = function() {
		var booking=createBooking();
		console.log(booking);
		
		//TODO se si tratta di modifica di un booking basta richiamare la pagina booking ed appendere il booking al comunicator
		var stringBooking = JSON.stringify(booking);		
		$.ajax({
			url : "/pizzerialiveorderConferme",
			type : 'POST',
			data : {
				booking : stringBooking
			},
			success : function(data) {
				console.log(data)
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});
	}

	var checkTypeBooking = function(type){
		if(type=="delivery"){
			$("#tables").prop("disabled",true);
			$(".address").prop("disabled",false);
		}
		else if(type=="takeAway"){
			$("#tables").prop("disabled",true);
			$(".address").prop("disabled",true);
		}
		else if(type=="table"){
			$("#tables").prop("disabled",false);
			$(".address").prop("disabled",true);
		}
	}
	
	var removeItem = function(type) {
		
		if (type === "pizza") {
			var code = tablePizza.row('.selected').data()[columnId];
			var indexToRemove = -1;
			for (var j = 0; j < pizzaList.length; j++) {
				if (pizzaList[j].getCode() == code) {
					console.log("trovato " + pizzaList[j].getCode() + "="+ code)
					indexToRemove = j;
				}
			}
			//rimuovere dalla lista la pizza vecchia
			//aggiungere la nuova
			console.log(pizzaList)
			if (indexToRemove != -1)
				pizzaList.splice(indexToRemove, 1);
			
			tablePizza.row('.selected').remove().draw(false);
			setControlButtons("pizza", false, true, true);
		} 
		else {//type beverage
			tableBeverage.row('.selected').remove().draw(false);
			setControlButtons("beverage", false, true, true);
		}
	}

	var resetControls = function(type) {

		if (type === "pizza") {
			$("#addIngredients.js-example-basic-multiple").select2('data', "");
			$("#removeIngredients.js-example-basic-multiple").select2('data',"");
			$('#counterPizza').val(1);
			//TODO:MANCA IL RESET DEI BOTTONI
		} else {
			$('#counterBeverage').val(1);
		}
	}

	var counterItem = function(idTextInput, type) {
		//type --> i: increment - d: decrement
		if (type === "i")
			$('#' + idTextInput).val(new Number($('#' + idTextInput).val()) + 1);
		else {
			if($('#' + idTextInput).val() > 1)
				$('#' + idTextInput).val(new Number($('#' + idTextInput).val()) - 1);
		}
	}
	
	var setControlButtons = function(type,boolButtonConferme, boolButtonEdit, boolButtonRemove ){
		
		if(type=="pizza"){
			$("#editButtonPizza").prop('disabled', boolButtonEdit);
			$("#removeButtonPizza").prop('disabled', boolButtonRemove);
			$("#confermeButtonPizza").prop('disabled', boolButtonConferme);
		}
		else{
			$("#editButtonBeverage").prop('disabled', boolButtonEdit);
			$("#removeButtonBeverage").prop('disabled', boolButtonRemove);
			$("#confermeButtonBeverage").prop('disabled', boolButtonConferme);
		}
		
	}
	
	var createTables = function(tables){
		for (var int = 0; int < tables.length; int++) {
			$("#tables").append("<option value='"+tables[int].id+"'>"
					+"TableNumber: "
					+ tables[int].number
					+ " - Max: "
					+ tables[int].maxSeats
					+ " - Min:"
					+ tables[int].minSeats
					+ "</option>");			
		}
	}
	
	var initHeading = function(booking){
	
		//set switchedBotton
		if(booking.type!="takeAway")
			$("[value='"+booking.type+"']").bootstrapSwitch('toggleState');
		//set Date
		console.log(booking.date);
		var dateConverted=booking.date.split("/");
		var dateString=dateConverted[2]+"-"+dateConverted[1]+"-"+dateConverted[0];
		console.log(dateString);
		var date=new Date(dateString);
		console.log(date);
		var time=booking.time.split(":");
		date.setHours(time[0], time[1], time[2]);
		$('#datetimepicker1').data("DateTimePicker").defaultDate(date);
		
		//set User or Name
		if(booking.user!=undefined){
			console.log(booking.user);
			$("#bookingUserInput").val(booking.user);
			$("#bookingNameInput").prop("disabled",true);
		}
		else{
			$("#bookingUserInput").prop("disabled",true);
			$("#bookingNameInput").prop("disabled",true);
		}
		if(booking.underTheNameOf!=undefined){
			console.log(booking.underTheNameOf);
			$("#bookingNameInput").val(booking.underTheNameOf);
			$("#bookingNameInput").prop("disabled",false);
		}
		else{
			$("#bookingUserInput").prop("disabled",true);
		}
		
		//set Address
		if(booking.type=="delivery"){
			$("#bookingCityInput").val(booking.address.city);
			$("#bookingStreetInput").val(booking.address.street);
			$("#bookingNumberInput").val(booking.address.number);
		}
		
		if(booking.type=="table"){
			var tablesId=new Array();
			for (var int = 0; int < booking.tables.length; int++) {
				tablesId.push(booking.tables[int].id);
			}
			console.log(tablesId);
			$("#tables.js-example-basic-multiple").val(tablesId).trigger("change");		
		}
	}
	
	var extractData = function(dataType){
		switch (dataType) {
		case "beverages":
			var orderBeverages = new Array();
			var rows = tableBeverage.rows().data();
			for (var int = 0; int < tableBeverage.rows().count(); int++) {
				var dataRow = rows[int];
				var id = dataRow[columnId];
				for (var int2 = 0; int2 < beverageFromServer.length; int2++) {
					if (beverageFromServer[int2].id == id) {
						beverageList.push(beverageFromServer[int2]);
						orderBeverages.push({
							number : dataRow[columnNumber],
							id : beverageFromServer[int2].id
						});
					}
				}
			}
			return orderBeverages;
			break;
		case "pizzas":
			var orderPizzas = new Array();
			var rows = tablePizza.rows().data();
			for (var int = 0; int < tablePizza.rows().count(); int++) {
				var dataRow = rows[int];
				for (var int2 = 0; int2 < pizzaList.length; int2++) {
					if (dataRow[columnId] == pizzaList[int2].getCode()) {
						orderPizzas.push({
							number : dataRow[columnNumber],
							gluten : pizzaList[int2].getGlutenFree(),
							ingredientsAdded:pizzaList[int2].getIngredientsAdded(),
							//ingredientsBase:pizzaList[int2].,
							ingredientsRemoved:pizzaList[int2].getIngredientsRemoved(),
							name:pizzaList[int2].getName(),
							size:pizzaList[int2].getSize()
						})
					}
				}
			}
			return orderPizzas;
			break;
		case "tables":
			var tables= $("#tables").select2("val");
			console.log(tables);
			return tables;
		break;
		case "address":
			var address=new Object();
			address.city=$("#bookingCityInput").val();
			address.street=$("#bookingStreetInput").val();
			address.number=$("#bookingNumberInput").val();
			return address;
			break;
		case "user":
			return $("#bookingUserInput").val();
			break;
		case "name":
			return $("#bookingNameInput").val();
			break;
		case "date":
			return $("#datetimepicker1").data("DateTimePicker").date().format('DD/MM/YYYY h:mm');
			break;

		default:
			break;
		}
	}

	/*************** FUNZIONI UTILITA SOLO PER PIZZA *****************************************************************************************************************/

	var mapping= function(pizzaFromBooking){
		
	
			var pizza= new Pizza();
			pizza.setName(pizzaFromBooking.name);
			pizza.setSize(pizzaFromBooking.size);
			pizza.setGlutenFree(pizzaFromBooking.gluten);
			var ingredientsToAdd=pizzaFromBooking.ingredientsAdded;
			var ingredientsToRemove=pizzaFromBooking.ingredientsRemoved;
			if (ingredientsToAdd.length > 0 || ingredientsToRemove.length > 0) {
				pizza.setEdited(true);
				for (var int1 = 0; int1 < ingredientsToAdd.length; int1++) {
					pizza.getIngredientsAdded().push({
						id : ingredientsToAdd[int1].id,
						text : ingredientsToAdd[int1].name
					});
				}
				for (var int2 = 0; int2 < ingredientsToRemove.length; int2++) {
					pizza.getIngredientsRemoved().push({
						id : ingredientsToRemove[int2].id,
						text : ingredientsToRemove[int2].name
					});
				}
			}	
			console.log('ho mappato');console.log(pizza);
		return pizza;
	}
	
	
	
	var extractPizzas = function() {

		var orderPizzas = new Array();
		var rows = tablePizza.rows().data();
		for (var int = 0; int < tablePizza.rows().count(); int++) {
			var dataRow = rows[int];
			for (var int2 = 0; int2 < pizzaList.length; int2++) {
				if (dataRow[columnId] == pizzaList[int2].getCode()) {
					orderPizzas.push({
						number : dataRow[columnNumber],
						pizza : pizzaList[int2]
					})
				}
			}
		}
		return orderPizzas;
	}

	//nei casi di EDIT consente di caricare le informazioni sugli strumenti di creazione pizza 
	var loadInfoForPizzaControls = function(pizza, number) {
		console.log("STO CARICANDO " + pizza.toString());
		$("#pizzaList.js-example-basic-single").select2('data', {
			text : pizza.getName()
		});

		loadIngredientsForPizza(pizza.getName());
		$("#addIngredients.js-example-basic-multiple").select2('data',pizza.getIngredientsAdded());
		$("#removeIngredients.js-example-basic-multiple").select2('data',pizza.getIngredientsRemoved());
		$('#counterPizza').val(number);
		//TODO:MANCA IL SETTING DEI BOTTONI
	}

	//carica sui select2 sia gli ingredienti base che quelli aggiungibili ad una pizza
	var loadIngredientsForPizza = function(pizzaName) {
		console.log(pizzaName);
		$("li.select2-search-choice").remove();
		$("#addIngredients.js-example-basic-multiple > option").remove();
		$("#removeIngredients.js-example-basic-multiple > option").remove();
		for (var int = 0; int < pizzeFromServer.length; int++) {
			if (pizzeFromServer[int].name == pizzaName) {
				
				var difference=new Array();
				for (var int2 = 0; int2 < pizzeriaIngredientsFromServer.length; int2++) {
					var currentPizzeriaIngredient=pizzeriaIngredientsFromServer[int2];
					var found=false;
					for (var int3 = 0; int3 < pizzeFromServer[int].ingredients.length; int3++) {
						if(currentPizzeriaIngredient.name==pizzeFromServer[int].ingredients[int3].name){
							found=true;
						}
					}
					
					if(found==false){
						difference.push(currentPizzeriaIngredient);
					}
				}
				
				console.log(difference);
				for (var int2 = 0; int2 < difference.length; int2++) {
					$("#addIngredients.js-example-basic-multiple").append(
							"<option value="+difference[int2].id+">"
									+ difference[int2].name + "</option>");
				}
				//appendiamo gli ingredienti base della pizza all select degli ingredienti rimovibili
				for (var int2 = 0; int2 < pizzeFromServer[int].ingredients.length; int2++) {
					$("#removeIngredients.js-example-basic-multiple").append(
							"<option value="+pizzeFromServer[int].ingredients[int2].id+">"
									+ pizzeFromServer[int].ingredients[int2].name
									+ "</option>");
				}
			}
		}
	}

	var resolvePizza = function(editing,pizzaFromBooking,numberOfItem) {

		var pizza = new Pizza();
		var pizzaNumber;
		var ingredientsToAdd;
		var ingredientsToRemove;
		
		if(pizzaFromBooking===undefined){
			
			var namePizzaSelected = $("#pizzaList.js-example-basic-single").select2("data").text;
			if (namePizzaSelected == "Select Pizza") {
				$('#myModal').modal('show');
				return;
			}
			
			pizzaNumber = $('#counterPizza').val();
			ingredientsToAdd = $('#addIngredients.js-example-basic-multiple').select2("data");
			ingredientsToRemove = $('#removeIngredients.js-example-basic-multiple').select2("data");
			
			pizza.setGlutenFree($('#glutenButtons .active > input').val());
			pizza.setSize($('#sizeButtons .active > input').val());
			pizza.setName(namePizzaSelected);
			
			if (ingredientsToAdd.length > 0 || ingredientsToRemove.length > 0) {
				pizza.setEdited(true);
				for (var int1 = 0; int1 < ingredientsToAdd.length; int1++) {
					pizza.getIngredientsAdded().push({
						id : ingredientsToAdd[int1].id,
						text : ingredientsToAdd[int1].text
					});
				}
				for (var int2 = 0; int2 < ingredientsToRemove.length; int2++) {
					pizza.getIngredientsRemoved().push({
						id : ingredientsToRemove[int2].id,
						text : ingredientsToRemove[int2].text
					});
				}
			}	
		}
		else{//pizzaFromBooking e numberOfItem sono entrambi diversi da undefined
			pizza=pizzaFromBooking;
			pizzaNumber=numberOfItem;
			console.log(pizza);
		}
		
		//se sto modificando un ordine già esistente
		console.log(editing);
		if (editing === true) {
			var code = tablePizza.row($('#resumeTablePizza tbody > tr.odd.selected,tr.even.selected')).data()[columnId];
			pizza.setCode(code);

			var indexToRemove;
			for (var j = 0; j < pizzaList.length; j++) {
				if (pizzaList[j].getCode() == code)
					indexToRemove = j;
			}
			//rimuovere dalla lista la pizza vecchia
			//aggiungere la nuova
			pizzaList.splice(indexToRemove, 1);
			//prima di pushare questa nuova pizza bisogna vedere se ne esiste una uguale
			//se ne esiste una uguale bisogna rimuovere questa , e modificare la nuova trovata
			if (checkPizzaExistence(pizza) != -1) {
				var rows = tablePizza.rows().data();
				//console.log(tablePizza.rows().count());
				for (var int = 0; int < tablePizza.rows().count(); int++) {
					var dataRow = rows[int];
					if (dataRow[columnId] == code) {
						console.log("trovato il tr con code:" + dataRow[columnId]+ ", e numero di pizze: " + dataRow[columnNumber]);
						//rimozione della vecchia riga
						console.log("sto rimuovendo la riga"+ tablePizza.row(int).data())

						for (var int2 = 0; int2 < tablePizza.rows().count(); int2++) {
							console.log(tablePizza.row(int2).data()[columnId] + "=="+ rows[int][columnId])
							if (tablePizza.row(int2).data()[columnId] == rows[int][columnId]) {
								tablePizza.row(int2).remove().draw(false);
							}
						}
					}
				}
				editResumePizzaItem(checkPizzaExistence(pizza), pizzaNumber,false);
				return;
			}
			pizzaList.push(pizza);
			editResumePizzaItem(code, pizzaNumber, true);
			setControlButtons("pizza", false, true, true);
		}
		//se invece sto creando una nuova pizza o se già esisteva la stessa di quella creata allora gli sto aggiungendo numeri al contatore
		else {
			console.log(pizzaList);
			var code = checkPizzaExistence(pizza);
			console.log(code);
			//se ritorno -1 la pizza ancora non esiste, per cui ne creo una nuova
			if (code === -1) {
				
				
				pizza.setCode(codePizza);
				//generazione nuovo codice unico (meglio trovare un modo alternativo per produrre codici univoci XD)
				codePizza++;
				addResumePizzaItem(pizza, pizzaNumber, false);
			}
			//la pizza già esiste, si aggiorna solo quella già esistente
			else {
				editResumePizzaItem(code, pizzaNumber, false);
			}
		}
		//reset button			
		resetControls("pizza");
	}

	var editResumePizzaItem = function(code, number, loaded) {
		//mi serve la dimensione della lista delle pizze per poter accedere correttamente all'oggetto rows

		var rows = tablePizza.rows().data();
		for (var int = 0; int < tablePizza.rows().count(); int++) {
			var dataRow = rows[int];
			if (dataRow[columnId] == code) {
				found = true;
				console.log("trovato il tr con idBeverage:" + dataRow[columnId]+ ", e numero di bevande: " + dataRow[columnNumber]);

				var newNumber;
				if (loaded === false)
					newNumber = new Number(dataRow[columnNumber]) + new Number(number);
				else
					newNumber = new Number(number);

				//rimozione della vecchia riga
				console.log("sto rimuovendo la riga"+ tablePizza.row(int).data())
				//PEZZA PER RIMUOVERE LA RIGA VECCHIA
				for (var int2 = 0; int2 < tablePizza.rows().count(); int2++) {
					console.log(tablePizza.row(int2).data()[columnId] + "=="+ rows[int][columnId])
					if (tablePizza.row(int2).data()[columnId] == rows[int][columnId]) {
						tablePizza.row(int2).remove().draw(false);
					}
				}
				for (var j = 0; j < pizzaList.length; j++) {
					if (pizzaList[j].getCode() == code) {
						var pizzaCurrent = pizzaList[j];
						tablePizza.row.add(
								["", 
								 pizzaCurrent.getName(),
								 pizzaCurrent.getGlutenFree(),
								 pizzaCurrent.getSize(),
								 newNumber.toString(),
								 pizzaCurrent.getCode()]).draw(false);

						//FACCIAMO scrollare la scroll fino alla nuova riga aggiunta
						$('.dataTables_scrollBody').animate({
							scrollTop : $('#resumeTablePizza tbody > tr:last-child').offset().top
						}, 100);

						return;
					}
				}

				//update pizzaList	
			}
		}
		
	}

	var addResumePizzaItem = function(pizza, number) {

		console.log("STO AGGIUNGENDO:  " + pizza.toString());
		pizzaList.push(pizza);

		var n=new String(number);
		tablePizza.row.add([ "",
		                     pizza.getName(),
		                     pizza.getGlutenFree(),
		                     pizza.getSize(),
		                     n.toString(),
		                     pizza.getCode() ]).draw(false);

		//FACCIAMO scrollare la scroll fino alla nuova riga aggiunta
		//TODO: fare il sorting di default della tabella sempre per id
		$('.dataTables_scrollBody').animate({
			scrollTop : $('#resumeTablePizza tbody > tr:last-child').offset().top
		}, 100);
	}

	//verifico se la pizza esiste già nella coda delle pizze
	var checkPizzaExistence = function(pizza) {

		var codeFounded = -1;
		for (var i = 0; i < pizzaList.length; i++) {

			if (pizzaList[i].getName() === pizza.getName()
					&& pizzaList[i].getGlutenFree() === pizza.getGlutenFree()
					&& pizzaList[i].getSize() === pizza.getSize()) {

				var equalAdded = true;
				var equalRemoved = true;
				if (pizzaList[i].getIngredientsAdded().length === pizza
						.getIngredientsAdded().length
						&& pizzaList[i].getIngredientsRemoved().length === pizza
								.getIngredientsRemoved().length) {

					for (var int1 = 0; int1 < pizza.getIngredientsAdded().length; int1++) {
						var ingredient = pizza.getIngredientsAdded()[int1].text;
						var equal = false;
						for (var int2 = 0; int2 < pizzaList[i]
								.getIngredientsAdded().length; int2++) {
							if (ingredient === pizzaList[i]
									.getIngredientsAdded()[int2].text)
								equal = true;
						}

						if (equal === false)
							equalAdded = false;//continue;
					}

					for (var int1 = 0; int1 < pizza.getIngredientsRemoved().length; int1++) {
						var ingredient = pizza.getIngredientsRemoved()[int1].text;
						var equal = false;
						for (var int2 = 0; int2 < pizzaList[i]
								.getIngredientsRemoved().length; int2++) {
							if (ingredient === pizzaList[i].getIngredientsRemoved()[int2].text)
								equal = true;
						}

						if (equal === false)
							equalRemoved = false;//continue;
					}
					if (equalAdded === true && equalRemoved === true) {
						console.log("stessi ingredienti e stessi nomi")						
						return pizzaList[i].getCode();
					}

				}//fine if interno
			}//fine if esterno
		}//fine for
		return codeFounded;
	}

	function format(d) {
		//console.log(d[0]);
		// `d` is the original data object for the row
		console.log(d);
		var code = d[columnId];
		for (var j = 0; j < pizzaList.length; j++) {
			if (pizzaList[j].getCode() == code) {

				console.log(code);
				console.log(pizzaList[j].toStringIngredientsAdded());
				console.log(pizzaList[j].toStringIngredientsRemoved());
				var added = false;
				var string = '<table class="attacable" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;font-size:14px;">';
				if (pizzaList[j].getIngredientsAdded().length > 0) {
					string += '<tr>' + '<td><b>Ingredients added</b></td>' + '<td>'
							+ pizzaList[j].toStringIngredientsAdded() + '</td>'
							+ '</tr>';
					added = true;
				}
				if (pizzaList[j].getIngredientsRemoved().length > 0) {
					string += '<tr>' + '<td><b>Ingredients removed</b></td>' + '<td>'
							+ pizzaList[j].toStringIngredientsRemoved()
							+ '</td>' + '</tr>';
					added = true;
				}

				if (added === false) {
					string += '<tr>Pizza Base</tr>';
				}

				string += '</table>';
				return string;
			}
		}

	}

	/*************** FUNZIONI UTILITA SOLO PER BEVANDA *****************************************************************************************************************/

	var extractBeverages = function() {

		var orderBeverages = new Array();
		var rows = tableBeverage.rows().data();
		for (var int = 0; int < tableBeverage.rows().count(); int++) {
			var dataRow = rows[int];
			var id = dataRow[columnId];
			for (var int2 = 0; int2 < beverageFromServer.length; int2++) {
				if (beverageFromServer[int2].id == id) {
					beverageList.push(beverageFromServer[int2]);
					orderBeverages.push({
						number : dataRow[columnNumber],
						beverage : beverageFromServer[int2]
					});
				}
			}
		}
		return orderBeverages;
	}

	//questa funzione può essere semplificata in quanto ormai non mi serve più l'oggetto beverages ordinato
	//basta solo appendere alla lista delle bevande
	var createBeverage = function(beverages) {
		beverages = _.groupBy(beverages, 'type');
		$.each(beverages,function(type) {
			beverages[type] = _.groupBy(beverages[type],
					'brand');
			var int = 0;
							
				$.each(beverages[type],function(brand) {
					beverages[type][brand] = _.groupBy(beverages[type][brand],'name');
												
					$.each(beverages[type][brand],function(name) {
						beverages[type][brand][name] = _.groupBy(beverages[type][brand][name],'container');
																	
						$.each(beverages[type][brand][name],function(container) {
																						
							var currentBeverage = beverages[type][brand][name][container];
							$("#beverageList").append("<option class='"+type+"' value='idBev_"+currentBeverage[0].id+"'>"
														+ brand
														+ " "
														+ name
														+ " - "
														+ container
														+ " - size:"
														+ currentBeverage[0].size
														+ "</option>");
							int++;
						});
					});
				});
				$("." + type).wrapAll("<optgroup label='"+type+"'>");
			});
	}

	var resolveBeverage = function(editing,beverageFromBooking) {

		var beverageNumber;
		var beverageSelectedId;
		var res=new Array();
		
		if(beverageFromBooking===undefined){
			
			if ($("#beverageList.js-example-basic-single").select2("data").text == "Select Beverage") {
				$('#myModal').modal('show');
				return;
			}
			
			beverageNumber = $('#counterBeverage').val();
			beverageSelectedId = $("#beverageList.js-example-basic-single").select2("data");
			console.log(beverageSelectedId);
			res= beverageSelectedId.id.split("_");
			console.log(res[1]);			
		}
		else{
			console.log(beverageFromBooking.number);
			console.log(beverageFromBooking.id);
			beverageNumber=beverageFromBooking.number;
			//pezza
			res.push("pezza");
			res.push(beverageFromBooking.id);
			console.log(res);
		}

		var code = checkBeverageExistence(res[1]);
		if (editing === true) {
			console.log($('#resumeTableBeverage tbody > tr.odd.selected,tr.even.selected'));

			//se sto modificando la bibita, e ne sto mettendo una che già esiste
			if (code != -1 && code != tableBeverage.row('.selected').data()[columnId]) {
				removeItem("beverage");//rimuove il selezionato corrente
				editResumeBeverageItem(code, beverageNumber, false);
				return;
			}

			var indexToRemove = tableBeverage.row($('#resumeTableBeverage tbody > tr.odd.selected,tr.even.selected')).data()[columnId];
			editResumeBeverageItem(indexToRemove, beverageNumber, true);
			setControlButtons("beverage", false, true, true);
		}
		//sto creando una nuova bevanda o se già esisteva la stessa di quella creata allora gli sto aggiungendo numeri al contatore
		else {
			//se ritorno -1 la bevanda ancora non esiste, per cui ne creo una nuova
			if (code == -1) {
				for (var int = 0; int < beverageFromServer.length; int++) {
					if (beverageFromServer[int].id == res[1]) {
						var currentBeverage = beverageFromServer[int];
						tableBeverage.row.add(
								[ currentBeverage.brand, currentBeverage.name,
										currentBeverage.container,
										currentBeverage.size, beverageNumber,
										currentBeverage.id ]).draw(false);
						resetControls("beverage");
						return;

					}
				}
			}
			//la bevanda già esiste, si aggiorna solo quella già esistente
			else {
				editResumeBeverageItem(code, beverageNumber, false);
			}
		}
		//reset button			
		resetControls("beverage");
	}

	var editResumeBeverageItem = function(idBeverage, number, loaded) {
		
		var rows = tableBeverage.rows().data();
		for (var int = 0; int < tableBeverage.rows().count(); int++) {
			var dataRow = rows[int];
			if (dataRow[columnId] == idBeverage) {
				found = true;
				console.log("trovato il tr con idBeverage:" + dataRow[columnId]+ ", e numero di bevande: " + dataRow[columnNumber]);

				var newNumber;
				if (loaded === false)
					newNumber = new Number(dataRow[columnNumber]) + new Number(number);
				else
					newNumber = new Number(number);

				//rimozione della vecchia riga
				console.log("sto rimuovendo la riga"+ tableBeverage.row(int).data())
				//PEZZA PER RIMUOVERE LA RIGA VECCHIA
				for (var int2 = 0; int2 < tableBeverage.rows().count(); int2++) {
					console.log(tableBeverage.row(int2).data()[columnId] + "=="+ rows[int][columnId])
					if (tableBeverage.row(int2).data()[columnId] == rows[int][columnId]) {
						tableBeverage.row(int2).remove().draw(false);
					}
				}
				//aggiunta della nuova riga (PURTROPPO NON È POSSIBILE EDITARE DIRETTAMENTE LA RIGA)
				for (var int = 0; int < beverageFromServer.length; int++) {
					var beverageSelectedId = $("#beverageList.js-example-basic-single").select2("data");
					var res = beverageSelectedId.id.split("_");
					if (beverageFromServer[int].id == res[1]) {
						var currentBeverage = beverageFromServer[int];
						tableBeverage.row.add(
								[ currentBeverage.brand, currentBeverage.name,
								  currentBeverage.container,
								  currentBeverage.size, newNumber,
								  currentBeverage.id ]).draw(false);
						return;

					}
				}

				//update pizzaList	
			}
		}

	}

	//verifico se la bevanda esiste direttamente sulla tabella, non conviene salvarmi una lista di bevande in quanto non ho problematiche come ad esempio posso avere con gli ingredienti delle pizze
	var checkBeverageExistence = function(idBeverage) {

		var codeFounded = -1;
		var rows = tableBeverage.rows().data();
		for (var int = 0; int < tableBeverage.rows().count(); int++) {
			var dataRow = rows[int];
			if (dataRow[columnId] == idBeverage) {
				codeFounded = dataRow[columnId];
			}
		}
		return codeFounded;
	}
	
	
	
	return {
		init : function() {
			initVar();
			initLiveTool();
			initListeners();
		}
	}
	
}();