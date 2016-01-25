<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/moment.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

<script type="text/javascript" src="resources/js/maps.js"></script>

<script type="text/javascript" src="resources/js/pizzeria/pizzeriaMainView.js"></script>

 <!--<script type="text/javascript">
$(document).ready(function() {
	$(".addToBook").on('click', function() {
		var id = parseInt($(this).closest('div').find('input').val());
		var pizzeria = parseInt($(this).data("pizzeria"), 10);
		var date =$(this).closest('div').find('div').find('.datetimepicker').data("DateTimePicker").date().format("YYYY/MM/DD HH:mm");
		$.ajax({
			type : "POST",
			url : "/book/booking",
			data : {
				placeToBook : id,
				date : date,
				pizzeria : pizzeria

			},
			error : function(response) {
				$.ajax({
					type : "GET",
					url : "/pizzeriamainview",
					data : {
						id : pizzeria

					},
					success : function(response) {
						if(response){
						$("#modalMessage").text("no seats");
						$('#modalAlert').modal('show');}
					}
				});
			},
			success : function(response) {
				$.ajax({
					type : "GET",
					url : "/pizzeriamainview",
					data : {
						id : pizzeria

					},
					success : function(response) {
						if(response){
						$("#modalMessage").text("Booked");
						$('#modalAlert').modal('show');}
					}
				});
			},
			
		});
	});

});
</script>--> 
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/pizzeriaMainView.css" />

<title>Book at ${pizzeriaResult.name}</title>

<meta name="viewport" content="width=device-width" />

<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="includes/navbar${typeSession}.jsp" />

	<jsp:include page="includes/modalMessage.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<div class="bubble pizzeria-info-bubble">
					<div class="name-container">${pizzeriaResult.name}</div>
				
					
					<div class="pizzeria-buttons-container">
					<input type="text" class="form-control" name="numeroPosti" placeholder="Inserire numero di posti da prenotare">
					<div class="form-group">
									<div class='input-group date datetimepicker'>
										<input type='text' class="form-control" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
						<a href="#" data-pizzeria="${pizzeriaResult.id}" class="btn btn-primary button-bookatable addBook">Book a table</a>
					</div>
					</div>
					
					</div>
				<div class="col-xs-8 wrapper">
				<div class="bubble">
					<div class="bubble-title">Book also Your Pizzas NOW!!!</div>
					<c:forEach items="${pizzeriaResult.pizzasPriceList}" var="pizzeriaPizza">
						<div class="row menu-entry">
							<div class="pizza-name">${pizzeriaPizza.pizza.name}</div>
							<div class="pizza-ingredients">
								<span class="pizzeriaPizza-label">Ingredients:</span>
								<c:forEach var="i" begin="0" end="${pizzeriaPizza.pizza.pizzaIngredients.size() - 1}">
									<span>${pizzeriaPizza.pizza.pizzaIngredients[i].ingredient.name}</span>
									<c:if test="${i != pizzeriaPizza.pizza.pizzaIngredients.size() -1 }">,</c:if>
								</c:forEach>
							</div>
							<div class="pizza-size">
								<span class="pizzeriaPizza-label">Size:</span> <span>${pizzeriaPizza.pizzaSize.string}</span>
							</div>
							<c:if test="${pizzeriaPizza.glutenFree}">
								<div class="pizza-gluten-free">Gluten free</div>
							</c:if>
							<div class="right-container">
								<div class="pizza-price">
									&euro;
									<fmt:formatNumber value="${pizzeriaPizza.price}" pattern="0.00" />
								</div>
								<a href="#" data-id="${pizzeriaPizza.id}" data-pizzeria="${pizzeriaResult.id}"
									class="btn btn-primary button-bookatable addToBook">Book Pizza</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			
			
		</div>
	</div>
</body>
</html>