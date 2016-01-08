var LiveOrderTool = function(){
	

	/*Identificativi universali:
		-Bevande: id(per ora corrispondono agli id del database, l'ideale è fare una crittografia degli id del database, ed usare quelli localmente)
		-Ingredienti: nome
		-Pizze: nome + nome ingredienti
	*/
	
	var pizzeFromServer;//è la lista delle pizze, con i suoi ingredienti basilari, fornite dalla pizzeria e che risiedono sul database
	var beverageFromServer;//è la lista di tutte le bevande fornite dalla pizzeria e che risiedono sul database
	var pizzeriaIngredients;//è la lista di tutti gli ingredienti di cui è fornita la pizzeria
	
	var pizzaList = new Array();//è la lista delle pizze ordinate dal client
	var beverageList = new Array();//è la lista delle bevande  ordinate dal client
	
	var tablePizza, tableBeverage;//tabelle
	var codePizza = 0;//l'id delle pizze viene generato al momento, è un id valido localmente in quanto le pizze create non esistono sul database  
	
	//costanti
	var columnId = 5;
	var columnNumber = 4;

	var initLiveTool = function(){
		
		if(communicator.bookingToEdit!== undefined){
			//RICHIAMARE IL MAPPING
			
			//Eliminare subito l'oggetto dal communicator
			communicator.bookingToEdit=undefined;
		}
		
			//datepicker
			$('#datetimepicker1').datetimepicker();
			//creazione SELECT2
			$(".js-example-basic-single").select2();
			$(".js-example-basic-multiple").select2();
			//proprietà bottoni di modifica disabilitati al caricamento della pagina
			setControlButtons("pizza", false, true, true);
			setControlButtons("beverage", false, true, true);

			tablePizza = $('#resumeTablePizza').DataTable({
				"scrollY" : "320px",
				"scrollCollapse" : true,
				"paging" : false,
				columns : [ {
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
			tablePizza.columns(5).visible(false);
			tableBeverage.columns(5).visible(false);

			
			$.ajax({
					url : "/pizzerialiveorderPizzas",
					type : 'GET',
					success : function(pizzeria) {
						console.log(pizzeria);
						pizzeFromServer = pizzeria.pizzas;
						beverageFromServer = pizzeria.beverages;
						pizzeriaIngredients = pizzeria.allPizzeriaIngredients;
						console.log(pizzeFromServer);
						//popolazione beverage
						createBeverage(beverageFromServer);
					},
					error : function(data, status, er) {
						alert("error: " + data + " status: "
								+ status + " er:" + er);
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
			//TODO: mi prendo dalla riga selezionata la pizza, e verifico se possiede ingredienti aggiunti
			//o rimossi
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
		
		//Listener che selezione la riga e carica i dati del selezionato
		$('#resumeTablePizza tbody').on('click','tr',function() {
			if ($(this).hasClass('selected') && tablePizza.row(this).data() != undefined && ($(this).hasClass("odd") || $(this).hasClass("odd") || $(this).hasClass("even"))) {
				
				$(this).removeClass('selected');
				setControlButtons("pizza", false, true, true);
				resetControls("pizza");
				
			} 
			else if (!$(this).hasClass('selected') && tablePizza.row(this).data() != undefined && ($(this).hasClass("odd") || $(this).hasClass("even"))) {
								
				tablePizza.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
				setControlButtons("pizza", true, false, false);
				//load value relative to the selected row
				var code = tablePizza.row($(this)).data()[columnId];
				var number = tablePizza.row($(this)).data()[columnNumber];
				for (var j = 0; j < pizzaList.length; j++) {
						if (pizzaList[j].getCode() == code) {
							var pizzaCurrent = pizzaList[j];
							loadInfoForPizzaControls(pizzaCurrent,number);
						}
					}
				}//end else if
			});

		/*Listener che selezione la riga e carica i dati del selezionato*/
		$('#resumeTableBeverage tbody').on('click','tr',function() {

			if($(this).hasClass('selected') && tableBeverage.row(this).data() != undefined && ($(this).hasClass("odd")|| $(this).hasClass("odd") || $(this).hasClass("even"))) {
								
				$(this).removeClass('selected');
				setControlButtons("beverage", false, true, true);
				resetControls("beverage");
					
			} 
			else if (!$(this).hasClass('selected') && tableBeverage.row(this).data() != undefined && ($(this).hasClass("odd") || $(this).hasClass("even"))) {
								
				tablePizza.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
				setControlButtons("beverage", true, false, false);
				var number = tableBeverage.row($(this)).data()[columnNumber];
				$('#counterBeverage').val(number);

			}//end else if
		});

	} 
	/*****************************************************   FUNZIONI COMUNI ********************************************************************************/
	var sendOrder = function() {
		var orderBeverages = extractBeverages();
		var orderPizzas = extractPizzas();
		
		console.log(beverageList);
		console.log(orderBeverages);
		console.log(pizzaList);
		console.log(orderPizzas);
	
		var stringP = JSON.stringify(orderPizzas);
		var stringB = JSON.stringify(orderBeverages);
		
		$.ajax({
			url : "/pizzerialiveorderConferme",
			type : 'POST',
			data : {
				pizzas : stringP,
				beverages : stringB
			},
			success : function(data) {
				console.log(data)
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});
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

	/*************** FUNZIONI UTILITA SOLO PER PIZZA *****************************************************************************************************************/

	var mappingBookingToPizzaListAndTable = function(){
		
	}
	
	var extractPizzas = function() {

		var orderPizzas = new Array();
		var rows = tablePizza.rows().data();
		//console.log(tablePizza.rows().count());
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
				//appendiamo la differenza tra tutti gli ingredienti della pizzeria e quelli della pizza, ovvero gli ingredienti aggiungibili alla pizza
				var difference = _.difference(pizzeriaIngredients,
						pizzeFromServer[int].ingredients);
				console.log(difference);
				for (var int2 = 0; int2 < difference.length; int2++) {
					$("#addIngredients.js-example-basic-multiple").append(
							"<option value="+pizzaName+"_"+int2+">"
									+ difference[int2] + "</option>");
				}
				//appendiamo gli ingredienti base della pizza all select degli ingredienti rimovibili
				for (var int2 = 0; int2 < pizzeFromServer[int].ingredients.length; int2++) {
					$("#removeIngredients.js-example-basic-multiple").append(
							"<option value="+pizzaName+"_"+int2+">"
									+ pizzeFromServer[int].ingredients[int2]
									+ "</option>");
				}
			}
		}
	}

	var resolvePizza = function(editing) {

		var namePizzaSelected = $("#pizzaList.js-example-basic-single").select2("data").text;
		if (namePizzaSelected == "Select Pizza") {
			$('#myModal').modal('show');
			return;
		}

		var pizza = new Pizza();
		console.log(namePizzaSelected)
		var pizzaNumber = $('#counterPizza').val();
		var ingredientsToAdd = $('#addIngredients.js-example-basic-multiple').select2("data");
		var ingredientsToRemove = $('#removeIngredients.js-example-basic-multiple').select2("data");
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
		

		//se sto modificando un ordine già esistente
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
			var code = checkPizzaExistence(pizza);
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

		console.log("STO EDITANDO LA PIZZA con code:" + code);
		var rows = tablePizza.rows().data();
		for (var int = 0; int < tablePizza.rows().count(); int++) {
			var dataRow = rows[int];
			if (dataRow[columnId] == code) {
				found = true;
				console.log("trovato il tr con code:" + dataRow[columnId]+ ", e numero di pizze: " + dataRow[columnNumber]);

				var newNumber;
				if (loaded === false)
					newNumber = new Number(dataRow[columnNumber]) + new Number(number);
				else
					newNumber = new Number(number);

				//rimozione della vecchia riga
				console.log("sto rimuovendo la riga"+ tablePizza.row(int).data())
				//PEZZA PER RIMUOVERE LA RIGA VECCHIA
				for (var int2 = 0; int2 < tablePizza.rows().count(); int2++) {
					
					if (tablePizza.row(int2).data()[columnId] == rows[int][columnId]) {
						tablePizza.row(int2).remove().draw(false);
					}
				}
				//aggiunta della nuova riga (PURTROPPO NON È POSSIBILE EDITARE DIRETTAMENTE LA RIGA)
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

		tablePizza.row.add([ "",
		                     pizza.getName(),
		                     pizza.getGlutenFree(),
		                     pizza.getSize(),
		                     number.toString(),
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
							if (ingredient === pizzaList[i]
									.getIngredientsRemoved()[int2].text)
								equal = true;
						}

						if (equal === false)
							equalRemoved = false;//continue;
					}
					if (equalAdded === true && equalRemoved === true) {
						//console.log("stessi ingredienti e stessi nomi")						
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
				var string = '<table class="attacable" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">';
				if (pizzaList[j].getIngredientsAdded().length > 0) {
					string += '<tr>' + '<td>Ingredients added:</td>' + '<td>'
							+ pizzaList[j].toStringIngredientsAdded() + '</td>'
							+ '</tr>';
					added = true;
				}
				if (pizzaList[j].getIngredientsRemoved().length > 0) {
					string += '<tr>' + '<td>Ingredients removed:</td>' + '<td>'
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

	var resolveBeverage = function(editing) {

		if ($("#beverageList.js-example-basic-single").select2("data").text == "Select Beverage") {
			$('#myModal').modal('show');
			return;
		}

		var beverageNumber = $('#counterBeverage').val();
		var beverageSelectedId = $("#beverageList.js-example-basic-single").select2("data");
		console.log(beverageSelectedId);
		var res = beverageSelectedId.id.split("_");
		console.log(res[1]);

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
			initLiveTool();
			initListeners();
		}
	}
	
}();