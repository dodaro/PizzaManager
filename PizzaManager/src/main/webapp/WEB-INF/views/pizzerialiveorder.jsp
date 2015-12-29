<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script> 
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="resources/js/select2.js"></script>
<script type="text/javascript" src="resources/js/pizzeria/Pizza.js"></script>


<script type="text/javascript">

	//VARIABILI GLOBALI
	var pizzaList=new Array();
	var tablePizza,tableBeverage;
	var codePizza=0;
	var dropDownList;
	
	$(document).ready(function() {
		$(".js-example-basic-single").select2();
		$(".js-example-basic-multiple").select2();
		$("#editButton").prop('disabled', true);
		/*$('#addIngredients.js-example-basic-multiple').on('select2-loaded', function(e) {
		    dropDownList = e.items.results;
		}).on('select2-selecting', function(e) {
		    console.log(dropDownList);
		});*/
		
		tablePizza= $('#resumeTablePizza').DataTable({
				"scrollY":        "200px",
		        //"scrollCollapse": false,
		        "paging":         false,
		        
		       //"lengthMenu": [[5, 25, 50, -1], [5, 25, 50, "All"]],
				columns : [ 
				        {
			                "className":      'details-control',
			                "orderable":      false,
			                "data":           null,
			                "defaultContent": ''
				        },
					    {"string" : "pizza"},
					    {"string" : "glutenFree"},
					    {"string" : "size"},
					    {"string" : "number"},
					    {"string" : "id"}
					    ],
					order : [ [ 1, 'asc' ] ]
				});
		
		//	RIMOZIONE della colonna codePizza in quanto non deve essere visibile
		var columns = tablePizza.columns(5).visible(false);
		
		tableBeverage= $('#resumeTableBeverage').DataTable({
				"scrollY":        "200px",
		        "scrollCollapse": false,
		        "paging":         false,
				columns : [ 
				        {
			                "className":      'details-control',
			                "orderable":      false,
			                "data":           null,
			                "defaultContent": ''
				        },
					    {"string" : "beverage"},
					    {"string" : "name"},
					    {"string" : "container"},
					    {"string" : "size"}
					    ],
					order : [ [ 1, 'asc' ] ]
			});
		
		
		
		$('#resumeTablePizza tbody').on('click', 'td.details-control', function() {
			//il closest mi da il primo parent che matcha con 'tr'
			//$(this) in questo caso è il td su cui si ha cliccato
			var tr = $(this).closest('tr');
			var row = tablePizza.row(tr);
			//la funzione row() mi ritorna tutta la riga relativo all'elemento tr
			//se applico row.child() ottengo tutti i figli diretti di row;
			//se applico row.child(codeHTML) aggiungo il codice alla riga;
			if (row.child.isShown()) {
				// This row is already open - close it
				//row.child.hide();
				row.child.remove();
				tr.removeClass('shown');
			} else {
				// Open this row
				row.child(format(row.data())).show();
				tr.addClass('shown');
			}
		});
		
		
		$('#resumeTablePizza tbody').on('click', 'tr', function() {
			 
			if ($(this).hasClass('selected') && ($(this).hasClass("odd")||$(this).hasClass("odd")|| $(this).hasClass("even")) ) {
				$(this).removeClass('selected');
				$("#editButton").prop('disabled', true);
				$("#confermeButton").prop('disabled', false);
				resetControls();
			} 
			else if(!$(this).hasClass('selected') && ($(this).hasClass("odd")|| $(this).hasClass("even")) ){
				tablePizza.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
				$("#editButton").prop('disabled', false);
				$("#confermeButton").prop('disabled', true);
				//load value relative to the selected row
				var code=tablePizza.row($(this)).data()[5];
				var number=tablePizza.row($(this)).data()[4];
				for (var j = 0; j < pizzaList.length; j++) {
					if (pizzaList[j].getCode() == code) {
						var pizzaCurrent = pizzaList[j];
						loadInfoForControls(pizzaCurrent,number);
					}
				}
			}//end else if
			
		});
		
		
		var pizzeria;
		$.ajax({
			url : "/pizzerialiveorderPizzas",
			type : 'GET',
			success : function(pizzeria) {
				//alert(data);
				console.log(pizzeria);
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});
	});
	
	
	var loadInfoForControls = function(pizza,number){
		console.log("STO CARICANDO "+pizza.toString());
		$("#pizzaList.js-example-basic-single").select2('data',{text:pizza.getName()});
		$("#addIngredients.js-example-basic-multiple").select2('data',pizza.getIngredientsAdded());
		$("#removeIngredients.js-example-basic-multiple").select2('data',pizza.getIngredientsRemoved());
		$('#counterPizza').val(number);
		//TODO:MANCA IL SETTING DEI BOTTONI
		
		//$("#addIngredients.js-example-basic-multiple").select2('data',[{id:"TON", text:"Tonno"},{id:"MAY", text:"May"}]);
		//$('#s2id_addIngredients > ul').append("<li class='select2-search-choice'><div>CIAO</div><a href='#' class='select2-search-choice-close' tabindex='-1'></a><li>");
	}
	
	var resetControls = function(){
		
		//$("#pizzaList.js-example-basic-single").select2('data',"");
		$("#addIngredients.js-example-basic-multiple").select2('data',"");
		$("#removeIngredients.js-example-basic-multiple").select2('data',"");
		$('#counterPizza').val(1);
		//TODO:MANCA IL RESET DEI BOTTONI
	}
	
	var counterItem = function(idTextInput, type){
		//type --> i: increment - d: decrement
		if(type==="i")
			$('#'+idTextInput).val(new Number($('#'+idTextInput).val())+1);
		else{
			if($('#'+idTextInput).val()>1)
				$('#'+idTextInput).val(new Number($('#'+idTextInput).val())-1);
		}
	}
	
	var resolvePizza = function(editing){
		
		var pizza = new Pizza();
		//console.log(pizza.toString());
		var namePizzaSelected=$("#pizzaList.js-example-basic-single").select2("data");
		console.log(namePizzaSelected)
		var pizzaNumber=$('#counterPizza').val();
		//var ingredientsToAdd=$('#s2id_addIngredients .select2-search-choice > div');
		//var ingredientsToRemove=$('#s2id_removeIngredients .select2-search-choice > div');
		var ingredientsToAdd=$('#addIngredients.js-example-basic-multiple').select2("data");
		var ingredientsToRemove=$('#removeIngredients.js-example-basic-multiple').select2("data");
		pizza.setGlutenFree($('#glutenButtons .active > input').val());
		pizza.setName(namePizzaSelected.text);
		pizza.setSize($('#sizeButtons .active > input').val());
	
		if(ingredientsToAdd.length>0 || ingredientsToRemove.length>0){
			pizza.setEdited(true);			
			for (var int1 = 0; int1 < ingredientsToAdd.length; int1++) {
				pizza.getIngredientsAdded().push({id:ingredientsToAdd[int1].id, text:ingredientsToAdd[int1].text});
			}
			for (var int2 = 0; int2 < ingredientsToRemove.length; int2++) {
				pizza.getIngredientsRemoved().push({id:ingredientsToRemove[int2].id, text:ingredientsToRemove[int2].text});
			}
		}
		
		//sto modificando un ordine
		if(editing===true){
			console.log($('#resumeTablePizza tbody > tr.odd.selected,tr.even.selected'));
			var code=tablePizza.row($('#resumeTablePizza tbody > tr.odd.selected,tr.even.selected')).data()[5];
			console.log(code);
			pizza.setCode(code);
			
			var indexToRemove;
			for (var j = 0; j < pizzaList.length; j++) {
				if (pizzaList[j].getCode() == code) 
					indexToRemove=j;
			}
			//rimuovere dalla lista la pizza vecchia
			//aggiungere la nuova
			pizzaList=pizzaList.splice(j,1);
			pizzaList.push(pizza);
			
			
			console.log(pizzaList);
			editResumeItem(code, pizzaNumber,true);
			
			
			
			$("#editButton").prop('disabled', true);
			$("#confermeButton").prop('disabled', false);
		}
		//sto creando una nuova pizza o se già esisteva la stessa di quella creata allora gli sto aggiungendo numeri al contatore
		else{
			//TODO: RENDERE UNIQUE ogni lista
			var code=checkPizzaExistence(pizza);
			//se ritorno -1 la pizza ancora non esiste, per cui ne creo una nuova
			if(code===-1){
				pizza.setCode(codePizza);
				//generazione nuovo codice unico (meglio trovare un modo alternativo per produrre codici univoci XD)
				codePizza++;
				addResumeItem(pizza, pizzaNumber);
			}
			//la pizza già esiste, si aggiorna solo quella già esistente
			else{
				editResumeItem(code, pizzaNumber,false);
			}
		}
		
		//reset button			
		resetControls();
	}
	
	
	
	var editResumeItem = function(code, number, loaded){
	//mi serve la dimensione della lista delle pizze per poter accedere correttamente all'oggetto rows
	
		console.log("STO EDITANDO LA PIZZA con code:"+ code);
		var rows = tablePizza.rows().data();
				//console.log(tablePizza.rows().count());
				for (var int = 0; int < tablePizza.rows().count(); int++) {
					var dataRow=rows[int];
					if (dataRow[5] == code) {
						found = true;
						console.log("trovato il tr con code:"+dataRow[5]+", e numero di pizze: "+dataRow[4]);
						
						var newNumber;
						if(loaded===false)
						 	newNumber= new Number(dataRow[4]) + new Number(number);
						else
							newNumber=new Number(number);
						
						//rimozione della vecchia riga
						tablePizza.row(int).remove().draw(false);
						//aggiungta della nuova riga (PURTROPPO NON È POSSIBILE EDITARE DIRETTAMENTE LA RIGA)
						for (var j = 0; j < pizzaList.length; j++) {
							if (pizzaList[j].getCode() == code) {
								var pizzaCurrent = pizzaList[j];
								tablePizza.row.add([
								  "", 
								  pizzaCurrent.getName(),
								  pizzaCurrent.getGlutenFree(),
								  pizzaCurrent.getSize(),
								  newNumber.toString(),
								  pizzaCurrent.getCode()
								 ]).draw(false);
								return;
							}
						}
						
						//update pizzaList	
					}
				}

	}

	var addResumeItem = function(pizza, number) {
	
		console.log("STO AGGIUNGENDO:  "+pizza.toString());
		pizzaList.push(pizza);
		
		tablePizza.row.add(
				[ "", 
				  pizza.getName(),
				  pizza.getGlutenFree(),
				  pizza.getSize(),
				  number.toString(),
				  pizza.getCode() ]).draw(false);
	}

	var checkPizzaExistence = function(pizza) {

		var codeFounded=-1;
		for (var i = 0; i < pizzaList.length; i++) {
			
			if (pizzaList[i].getName() === pizza.getName() && 
				pizzaList[i].getGlutenFree() === pizza.getGlutenFree() && 
				pizzaList[i].getSize() === pizza.getSize()) {
				
				
				
				var equalAdded = true;
				var equalRemoved = true;
				if (pizzaList[i].getIngredientsAdded().length === pizza.getIngredientsAdded().length && 
					pizzaList[i].getIngredientsRemoved().length === pizza.getIngredientsRemoved().length) {

					for (var int1 = 0; int1 < pizza.getIngredientsAdded().length; int1++) {
						var ingredient = pizza.getIngredientsAdded()[int1].text;
						var equal = false;
						for (var int2 = 0; int2 < pizzaList[i].getIngredientsAdded().length; int2++) {
							if (ingredient === pizzaList[i].getIngredientsAdded()[int2].text)
								equal = true;
						}

						if (equal === false)
							equalAdded = false;//continue;
					}

					for (var int1 = 0; int1 < pizza.getIngredientsRemoved().length; int1++) {
						var ingredient = pizza.getIngredientsRemoved()[int1].text;
						var equal = false;
						for (var int2 = 0; int2 < pizzaList[i].getIngredientsRemoved().length; int2++) {
							if (ingredient === pizzaList[i].getIngredientsRemoved()[int2].text)
								equal = true;
						}

						if (equal === false)
							equalRemoved = false;//continue;
					}
					if (equalAdded === true && equalRemoved === true){
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
		var code=d[5];
		for (var j = 0; j < pizzaList.length; j++) {
			if (pizzaList[j].getCode() == code) {
				console.log(code);
				console.log(pizzaList[j].toStringIngredientsAdded());
				console.log(pizzaList[j].toStringIngredientsRemoved());
				return '<table class="attacable" cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'
				+ '<tr>'
				+ '<td>Ingredients added:</td>'
				+ '<td>'+pizzaList[j].toStringIngredientsAdded()+'</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td>Ingredients removed:</td>'
				+ '<td>'+pizzaList[j].toStringIngredientsRemoved()+'</td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td>Extra info:</td>'
				+ '<td>And any further details here (images etc)...</td>'
				+ '</tr>' + '</table>';
			}
		}
		
	}
</script>


<link rel="stylesheet" type="text/css" href="resources/css/jquery.dataTables.css" /> 
<!-- <!-- il css datables.css Ã¨ trascurabile in quanto fornisce solo uno stile piÃ¹ bello a tutta la pagina, compresa la tabella -->
<!-- 	bisogna attribuire questo stile solo alla tabella -->
<link rel="stylesheet" type="text/css" href="resources/css/dataTables.css" /> 
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/pizzerialiveorder.css" />
<link href="resources/css/select2.css" rel="stylesheet" />
<link href="resources/css/select2-bootstrap.css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	


	<div id="container">

		<div id="header"></div>

		<div id="wrapper">
			<div class="row">
				<div id="menu" class="col-xs-2">
					<div>ciao</div>
				</div>
				<div id="content" class="col-xs-10">
					<div class="row">		
						<div id="resume" class="col-xs-8">
							<table id="resumeTablePizza" class="display" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th></th>
										<th>Pizza</th>
										<th>GlutenFree</th>
										<th>Size</th>
										<th>Number</th>
										<th>Id</th>
									</tr>
								</thead>
								<!--  <tfoot>
									<tr>
										<th></th>
										<th>Pizza</th>
										<th>GlutenFree</th>
										<th>Size</th>
										<th>Number</th>
										<th>Id</th>
									</tr>
								</tfoot>-->
							</table>
							
							<table id="resumeTableBeverage" class="display" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th></th>
										<th>Beverage</th>
										<th>Name</th>
										<th>Container</th>
										<th>Size</th>
									</tr>
								</thead>
								<!-- <tfoot>
									<tr>
										<th></th>
										<th>Item</th>
										<th>Number</th>
									</tr>
								</tfoot> -->
							</table>
							
							<!-- <table id="resumeTableMenu" class="display" cellspacing="0" width="100%">
								<thead>
									<tr>
										<th></th>
										<th>Name</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th></th>
										<th>Name</th>
									</tr>
								</tfoot>
							</table>-->
						</div>
	
						<div id="orderInstruments" class="col-xs-2">
							
							<div id="pizzas">
								<div >
									<select id="pizzaList" class="js-example-basic-single">
									<c:choose>
										<c:when test="${pizzeria.getPizzasPriceList().size() > 0}">
											<c:forEach var="pizza_prezzo" items="${pizzeria.getPizzasPriceList()}">
												<option value="${pizza_prezzo.getPizza().getId()}">${pizza_prezzo.getPizza().getName()}</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<div>There are no pizzas into the database.</div>
										</c:otherwise>
									</c:choose>
									</select>
									
									<!--  <select class="js-example-basic-single">
										<option value="AL">Alabama</option>		
										<option value="LA">Cosenza</option>	
									</select>-->
								</div>
								<div id="pizzaControls">
								
									<div>
										GLUTEN:
										<div id="glutenButtons" class="btn-group" data-toggle="buttons">
										  <label class="btn btn-primary active">
										    <input type="radio" name="options" value="yes" id="option1" autocomplete="off" checked> Yes
										  </label>
										  <label class="btn btn-primary">
										    <input type="radio" name="options" value="no" id="option2" autocomplete="off"> No
										  </label>
										</div>
										
										Size:
										<div id="sizeButtons"class="btn-group" data-toggle="buttons">
										  <label class="btn btn-primary active">
										    <input type="radio" name="options" value="s" id="option11" autocomplete="off" checked> S
										  </label>
										  <label class="btn btn-primary">
										    <input type="radio" name="options" value="m" id="option22" autocomplete="off"> M
										  </label>
										   <label class="btn btn-primary">
										    <input type="radio" name="options" value="l" id="option33" autocomplete="off"> L
										  </label>
										</div>
									
										N:
										<button type="submit" class="btn btn-default" onclick="counterItem('counterPizza','d')">-</button>
										<input id="counterPizza" value="1">
										<button type="submit" class="btn btn-default" onclick="counterItem('counterPizza','i')">+</button>
									
										<button id="confermeButton" type="submit" class="btn btn-default" onclick="resolvePizza(false)">Conferme</button>
										<button id="editButton" type="submit" class="btn btn-default" onclick="resolvePizza(true)">Edit</button>
									</div>
									<div>
										<select id="addIngredients" class="js-example-basic-multiple" multiple="multiple">
											  <!--<c:choose>
												<c:when test="${pizzeria.getPizzasPriceList().size() > 0}">
													<c:forEach var="pizza_prezzo" items="${pizzeria.getPizzasPriceList()}">
														<option value="${pizza_prezzo.getPizza().getId()}">${pizza_prezzo.getPizza().getName()}</option>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<div>There are no pizzas into the database.</div>
												</c:otherwise>
											  </c:choose>-->
										  <option value="TON">Tonno</option>
										  <option value="MAY">Mayonese</option>
										</select>
										<select id="removeIngredients" class="js-example-basic-multiple" multiple="multiple">
										  <option value="Al">Alabama</option>
										  <option value="WY">Wyoming</option>
										</select>
									</div>
									
								</div>
							</div>
	
							<div id="beverages">
								<div>
									<!--<select id="beverageList" class="js-example-basic-single">
									<c:choose>
										<c:when test="${beverageList.size() > 0}">
											<c:forEach var="beverage" items="${beveragesList}">
												<option value="${beverage.getId()}">${beverage.getName()}</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<div>There are no beverages into the database.</div>
										</c:otherwise>
									</c:choose>
									</select>-->
									
									<select id="beverageList" class="js-example-basic-single">
										<option value="COC">Cocacola</option>		
										<option value="BIRRA">Cosenza</option>	
									</select>
								</div>
								<div id="beverageControls"></div>
							</div>
	
	
							<div id="menus">
								<div>
									<!--<select id="menuList" class="js-example-basic-single">
									<c:choose>
										<c:when test="${beverageList.size() > 0}">
											<c:forEach var="beverage" items="${beveragesList}">
												<option value="${beverage.getId()}">${beverage.getName()}</option>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<div>There are no beverages into the database.</div>
										</c:otherwise>
									</c:choose>
									</select>-->
									
									<select id="menuList" class="js-example-basic-single">
										<option value="M1">Menu1: margherita + coca lattina da 33 </option>		
										<option value="M2">Menu2: banana + birra vetro da 33</option>	
									</select>
								</div>
								<div id="menuControls"></div>
							</div>
						</div>
	
	
	
	
	
					</div>
				</div>
			</div>
		</div>

		<div id="footer"></div>
	</div>
</body>
</html>