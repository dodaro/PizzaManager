<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

<script type="text/javascript" src="resources/js/maps.js"></script>

<script type="text/javascript" src="resources/js/pizzeria/pizzeriaMainView.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".addToCart").on('click', function() {
			var id = parseInt($(this).data("id"), 10);
			var pizzeria = parseInt($(this).data("pizzeria"), 10);
			alert($(this).data("pizzeria"));
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

						}
					});
				}
			});
		});

	});
</script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/pizzeriaMainView.css" />

<title>${pizzeriaResult.name}</title>

<meta name="viewport" content="width=device-width" />

<style type="text/css">
</style>
</head>
<body>
	<jsp:include page="includes/navbar${typeSession}.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<div class="bubble pizzeria-info-bubble">
					<div class="name-container">${pizzeriaResult.name}</div>
					<div class="address-container">
						<div class="address-route">
							<span class="glyphicon glyphicon-map-marker"></span><span>${pizzeriaResult.address.street}
								${pizzeriaResult.address.number}, ${pizzeriaResult.address.city}</span>
						</div>
					</div>
					<div class="email-container">
						<span class="glyphicon glyphicon-envelope"></span>${pizzeriaResult.email}</div>
					<div class="phone-container">
						<span class="glyphicon glyphicon-earphone"></span>${pizzeriaResult.phoneNumber}</div>
					<div class="pizzeria-buttons-container">
						<button class="btn btn-primary button-bookatable">Book a table</button>
					</div>
				</div>
				<div class="bubble feedbacks-bubble">
					<div class="bubble-title">Feedbacks</div>
					<div class="feedbacks">
						<c:if test="${pizzeriaResult.feedbacks.size() == 0}">
							<div>This pizzeria has not received feedbacks.</div>
						</c:if>
						<c:forEach items="${pizzeriaResult.feedbacks}" var="feedback">
							<div class="feedback">
								<div class="user-name">
									<a href="usermainview?id=${feedback.user.id}">${feedback.user.name}</a>
								</div>
								<div class="ratings">
									<div class="rating row">
										<div class="col-xs-3 rating-name">Quality</div>
										<div class="col-xs-9">
											<span class="stars"><c:forEach begin="1" end="${feedback.qualityRating}">
													<img src="resources/images/star.png">
												</c:forEach> <c:forEach begin="${feedback.qualityRating}" end="4">
													<img src="resources/images/star_grey.png">
												</c:forEach></span>
										</div>
									</div>
									<div class="rating row">
										<div class="col-xs-3 rating-name">Fastness</div>
										<div class="col-xs-9">
											<span class="stars"><c:forEach begin="1" end="${feedback.fastnessRating}">
													<img src="resources/images/star.png">
												</c:forEach> <c:forEach begin="${feedback.fastnessRating}" end="4">
													<img src="resources/images/star_grey.png">
												</c:forEach></span>
										</div>
									</div>
									<div class="rating row">
										<div class="col-xs-3 rating-name">Hospitality</div>
										<div class="col-xs-9">
											<span class="stars"><c:forEach begin="1" end="${feedback.hospitalityRating}">
													<img src="resources/images/star.png">
												</c:forEach> <c:forEach begin="${feedback.hospitalityRating}" end="4">
													<img src="resources/images/star_grey.png">
												</c:forEach></span>
										</div>
									</div>
								</div>
								<c:if test="${feedback.comment.length() > 0}">
									<div class="comment">"${feedback.comment}"</div>
								</c:if>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-xs-8 wrapper">
				<div class="bubble">
					<div class="bubble-title">Location</div>
					<div id="map"></div>
				</div>
				<c:if test="${userLogged}">
					<div class="bubble">
						<div class="bubble-title">Leave a feedback</div>
						<form:form method="post" modelAttribute="feedbackForm">
							<div class="ratings">
								<div class="rating row">
									<div class="col-xs-2 rating-name">Quality</div>
									<div class="col-xs-10">
										<span class="givefeedback-stars stars"><c:forEach begin="1" end="5" var="i">
												<img src="resources/images/star_grey.png" class="givefeedback-star" data-index="${i}">
											</c:forEach><span class="glyphicon glyphicon-ok"></span> <form:hidden path="quality" value="0" /></span>
									</div>
								</div>
								<div class="rating row">
									<div class="col-xs-2 rating-name">Fastness</div>
									<div class="col-xs-10">
										<span class="givefeedback-stars stars"><c:forEach begin="1" end="5" var="i">
												<img src="resources/images/star_grey.png" class="givefeedback-star" data-index="${i}">
											</c:forEach><span class="glyphicon glyphicon-ok"></span> <form:hidden path="fastness" value="0" /></span>
									</div>

								</div>
								<div class="rating row">
									<div class="col-xs-2 rating-name">Hospitality</div>
									<div class="col-xs-10">
										<span class="givefeedback-stars stars"><c:forEach begin="1" end="5" var="i">
												<img src="resources/images/star_grey.png" class="givefeedback-star" data-index="${i}">
											</c:forEach><span class="glyphicon glyphicon-ok"></span> <form:hidden path="hospitality" value="0" /></span>
									</div>
								</div>
							</div>
							<form:textarea path="comment" class="form-control comment-textarea" rows="2"
								placeholder="Leave a comment"></form:textarea>
							<div class="button-feedback-container">
								<button type="submit" class="btn btn-default">Give feedback</button>
							</div>
						</form:form>
					</div>
				</c:if>
				<div class="bubble">
					<div class="bubble-title">Menu</div>
					<c:forEach items="${pizzeriaResult.pizzasPriceList}" var="pizzeriaPizza">
						<div class="row menu-entry">
							<div class="pizza-name">${pizzeriaPizza.pizza.name}</div>
							<div class="pizza-ingredients">
								<span class="pizzeriaPizza-label">Ingredients:</span>
								<c:forEach var="i" begin="0" end="${pizzeriaPizza.pizza.pizzaIngredients.size() - 1}"><span>${pizzeriaPizza.pizza.pizzaIngredients[i].ingredient.name}</span><c:if test="${i != pizzeriaPizza.pizza.pizzaIngredients.size() -1 }">,</c:if>
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
									class="btn btn-default button-addtocart addToCart">Add to cart</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<script>
		pizzeriaMainView.init({
			latitude : <c:out value="${pizzeriaResult.location.latitude}" />,
			longitude : <c:out value="${pizzeriaResult.location.longitude}" />
		});
	</script>
</body>
</html>