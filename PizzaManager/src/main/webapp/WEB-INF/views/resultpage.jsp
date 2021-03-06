<%@page import="it.unical.pizzamanager.persistence.entities.Pizzeria"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/userMainView.css" />
<link rel="stylesheet" type="text/css" href="resources/css/pizza.css" />

<title>Result Page</title>

<meta name="viewport" content="width=device-width" />
<style>
h2 {
	margin-top: 0;
	margin-bottom: 20px;
}
</style>

</head>
<body>
	<jsp:include page="includes/navbar${typeSession}.jsp" />

	<div class="container">

		<div class="row">
		<div class="col-xs-2"></div>
		<div class="col-xs-8 wrapper">
				<div class="bubble bubble-feedbacks">
					<div class="bubble-title">Result found:</div>
					<c:forEach items="${pizzeriaResult}" var="pizzeria">
						<div class="row menu-entry">
							<div class="pizzeria-name">
								<a href="pizzeriamainview?id=${pizzeria.id}">${pizzeria.name}</a>
							</div>
							<div class="pizza-size"> <span>${pizzeria.address.street}
								${pizzeria.address.number}, ${pizzeria.address.city}</span>
							</div>
							
							<div class="right-container">
								<h4>(Pizzeria)</h4>
							</div>
						</div>
					</c:forEach>
					<c:forEach var="pizza" items="${pizzeriaResult2}">
						<div class="row menu-entry">
							<div class="pizzeria-name">
								<a href="pizza?id=${pizza.id}">${pizza.name}</a>
							</div>							
							<div class="right-container">
								<h4>(Pizza)</h4>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		<!-- 	<div id="wrapper" class="col-xs-10">
				<div class="bubble">
					<div id="content">
						<h2>Results</h2>
						<c:forEach var="r" items="${pizzeriaResult}">
							<div class="row">
								<a class="myref" href="pizzeriamainview?id=${r.getId()}">${r.getName()} (Pizzeria)</a>
							</div>
						</c:forEach>
						<c:forEach var="r" items="${pizzeriaResult2}">
							<div class="row">
								<a class="myref" href="pizza?id=${r.getId()}">${r.getName()} (Pizza)</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>-->
		</div>
	</div>

</body>
</html>
