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

	var itemList=new Array();

	function format(d) {
		//console.log(d[0]);
		// `d` is the original data object for the row
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'
				+ '<tr>' + '<td>Full name:</td>' + '<td> camo </td>'
				+ '</tr>' + '<tr>' + '<td>Extension number:</td>' + '<td>' + "milla" + '</td>'
				+ '</tr>' + '<tr>' + '<td>Extra info:</td>'
				+ '<td>And any further details here (images etc)...</td>' + '</tr>' + '</table>';
	}

	var tablePizza,tableBeverage;
	$(document).ready(function() {
		$(".js-example-basic-single").select2();
		tablePizza= $('#resumeTablePizza').DataTable({
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
					    {"string" : "number"}
					    ],
					order : [ [ 1, 'asc' ] ]
				});
		
		tableBeverage= $('#resumeTableBeverage').DataTable({
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
			console.log("chiamata");
			console.log($(this));
			//il closest mi da il primo parent che matcha con 'tr'
			//$(this) in questo caso � il td su cui si ha cliccato
			var tr = $(this).closest('tr');
			console.log(tr);
			var row = tablePizza.row(tr);
			//la funzione row() mi ritorna tutta la riga relativo all'elemento tr
			console.log(row.data());

			//se applico row.child() ottengo tutti i figli diretti di row;
			//se applico row.child(codeHTML) aggiungo il codice alla riga;
			if (row.child.isShown()) {
				// This row is already open - close it
				row.child.hide();
				tr.removeClass('shown');
			} else {
				// Open this row
				row.child(format(row.data())).show();
				tr.addClass('shown');
			}
		});
		
		$('#resumeTablePizza tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				tablePizza.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
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
	
	
	
	var counterItem = function(idTextInput, type){
		//i: increment
		//d: decrement
		if(type==="i"){
			$('#'+idTextInput).val(new Number($('#'+idTextInput).val())+1);
		}
		else{
			$('#'+idTextInput).val(new Number($('#'+idTextInput).val())-1);
		}
	}
	
	var resolvePizza = function(){
		
		var pizza = new Pizza();
		var selected=$("#pizzaList.js-example-basic-single").select2("data");
		var pizzaNumber=$('#counterPizza').val();
		
		pizza.setGlutenFree($('#glutenButtons .active > input').val());
		pizza.setName(selected.text);
		pizza.setSize($('#sizeButtons .active > input').val());
			
		/*console.log($('#glutenButtons .active > input').val());
		console.log($('#sizeButtons .active > input').val());
		console.log(selected.text);*/
		addResumeItem(pizza, pizzaNumber);
	}
	
	var addResumeItem = function(pizza, number){
		
		tablePizza.row.add([
		    "",
		    pizza.getName(),
		    pizza.getGlutenFree(),
		    pizza.getSize(),
		    number,
		]).draw( false );
	}
	
	
	
	

</script>


<link rel="stylesheet" type="text/css" href="resources/css/jquery.dataTables.css" /> 
<!-- <!-- il css datables.css è trascurabile in quanto fornisce solo uno stile più bello a tutta la pagina, compresa la tabella -->
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

					<div id="resume">
						<table id="resumeTablePizza" class="display" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th></th>
									<th>Pizza</th>
									<th>GlutenFree</th>
									<th>Size</th>
									<th>Number</th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<th></th>
									<th>Pizza</th>
									<th>GlutenFree</th>
									<th>Size</th>
									<th>Number</th>
								</tr>
							</tfoot>
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
							<tfoot>
								<tr>
									<th></th>
									<th>Item</th>
									<th>Number</th>
								</tr>
							</tfoot>
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

					<div id="orderInstruments">
						
						<div id="pizzas">
							<div >
								<select id="pizzaList" class="js-example-basic-single">
								<c:choose>
									<c:when test="${pizzasList.size() > 0}">
										<c:forEach var="pizza" items="${pizzasList}">
											<option value="${pizza.getId()}">${pizza.getName()}</option>
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
							
								<button type="submit" class="btn btn-default" onclick="resolvePizza()">Add</button>
							
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

		<div id="footer"></div>
	</div>
</body>
</html>