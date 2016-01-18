<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/userMainView.css" />

<title>Pizza View</title>

<meta name="viewport" content="width=device-width" />

</head>
<body>
	<jsp:include page="includes/navbar${typeSession}.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-4">
				<div class="bubble">
					<div class="profile-image-container">
						<img src="resources/images/pizza-twitter.png" class="img">
					</div>
					<div class="username-container">
					<div>${searchedPizza.name}
					</div>
					<div>Comon Ingredients:</div>
					<c:forEach items="${searchedPizza.pizzaIngredients}" var="i">
					<div class="row">
					<div>-${i.ingredient.name}</div>
					</div>
					</c:forEach>
					</div>
					
				</div>
			</div>
			<div class="col-xs-8 wrapper">
				<div class="bubble bubble-feedbacks">
					<div class="bubble-title">You can find this pizza at:</div>
					<c:forEach items="${pizzeriaResult}" var="pizzerie">
					<div class="row">
					<a href="pizzeriamainview?id=${pizzerie.pizzeria.id}">${pizzerie.pizzeria.name} (price ${pizzerie.price})</a>
					</div>
					</c:forEach>
					
				
						

					</div>
				</div>
			</div>
		</div>


</body>
</html>

