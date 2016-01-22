<%@page import="it.unical.pizzamanager.persistence.dto.Pizzeria"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

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
			<div id="wrapper" class="col-xs-10">
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
			</div>
		</div>
	</div>

</body>
</html>
