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


</head>
<body>
	<jsp:include page="includes/navbar${typeSession}.jsp"/> 

	<div class="container">

		<div class="row">
<div id="wrapper" class="col-xs-10">
				<div class="card">
					<div id="content"><h2>Result </h2>
				 	<c:forEach var="r" items="${pizzeriaResult}">
					<div class="row">
						<a class="myref" href="pizzeriamainview?id=${r.getId()}">${r.getName()}</a>
					</div>
					</c:forEach>
					<%--<div class="row">
					<a>${pizzeriaResult.getName()}</a>--%>
					</div>
				</div>
				</div>
			</div>
			</div>
			
			</body>
			</html>
