<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/userMainView.css" />
<link rel="stylesheet" type="text/css" href="resources/css/pizza.css" />

<title>${searchedPizza.name}</title>

<meta name="viewport" content="width=device-width" />

<script type="text/javascript">
	$(document).ready(function() {
		$(".addToCart").on('click', function(e) {
			e.preventDefault();
			var id = parseInt($(this).data("id"), 10);
			var pizzeria = parseInt($(this).data("pizzeria"), 10);
			$.ajax({
				type : "POST",
				url : "/cart/addItem",
				data : {
					itemToBook : id

				},
				success : function(response) {
					$.ajax({
						type : "GET",
						url : "/pizzeriamainview",
						data : {
							id : pizzeria

						},
						success : function(response) {
							$("#modalMessage").text("Added to cart.");
							$('#modalAlert').modal('show');
						}
					});
				}
			});
		});

	});
</script>

</head>
<body>
	<jsp:include page="includes/navbar${typeSession}.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<div class="bubble">
					<div class="pizza-name">${searchedPizza.name}</div>
					<div class="pizza-image-container">
						<img src="resources/images/pizza-twitter.png" class="img">
					</div>
					<div>Ingredients:</div>
					<c:forEach items="${searchedPizza.pizzaIngredients}" var="i">
						<div>${i.ingredient.name}</div>
					</c:forEach>
				</div>
			</div>
			<div class="col-xs-8 wrapper">
				<div class="bubble bubble-feedbacks">
					<div class="bubble-title">You can find this pizza at:</div>
					<c:forEach items="${pizzeriaResult}" var="pizzeriaPizza">
						<div class="row menu-entry">
							<div class="pizzeria-name">
								<a href="pizzeriamainview?id=${pizzeriaPizza.pizzeria.id}">${pizzeriaPizza.pizzeria.name}</a>
							</div>
							<div class="pizza-size">
								<span class="pizzeriaPizza-label">Size:</span> <span>${pizzeriaPizza.pizzaSize.string}</span>
							</div>
							<c:if test="${pizzeriaPizza.glutenFree}">
								<div class="pizza-gluten-free">Gluten free</div>
							</c:if>
							<div class="pizza-price">
								&euro;
								<fmt:formatNumber value="${pizzeriaPizza.price}" pattern="0.00" />
							</div>
							<div class="right-container">
								<a href="#" data-id="${pizzeriaPizza.id}" data-pizzeria="${pizzeriaPizza.pizzeria.id}"
									class="btn btn-default button-addtocart addToCart">Add to cart</a>
							</div>
						</div>
					</c:forEach>


				</div>
			</div>
		</div>
	</div>


</body>
</html>

