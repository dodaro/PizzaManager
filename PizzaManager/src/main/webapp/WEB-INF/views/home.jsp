<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />

<title>Home</title>

</head>
<body>
	<h1>Welcome to PizzaManager!</h1>

	<div>List of users:</div>
	<c:choose>
		<c:when test="${users.size() > 0}">
			<c:forEach var="user" items="${users}">
				<div>${user.toString()}</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>There are no users into the database.</div>
		</c:otherwise>
	</c:choose>

	<a href="signup"><button class="btn btn-primary">Sign Up</button></a>
	<a href="pizzeriabooking"><button class="btn btn-primary">pizzeria</button></a>

</body>
</html>
