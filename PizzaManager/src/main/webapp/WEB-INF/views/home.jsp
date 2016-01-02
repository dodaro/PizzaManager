<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<title>Home</title>

<meta name="viewport" content="width=device-width" />

</head>
<body>
	<jsp:include page="includes/${navbar}.jsp" />

	<div class="container">
		<div class="card">
			<div>Welcome to PizzaManager!</div>
			<div>This is a card. Use cards as containers to display content.</div>
		</div>
		<div class="card">
			<div>List of users:</div>
			<c:choose>
				<c:when test="${users.size() > 0}">
					<c:forEach var="user" items="${users}">
						<div>${user.email}</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div>There are no users into the database.</div>
				</c:otherwise>
			</c:choose>
			<a href="signup">
				<button class="btn btn-primary">Sign Up</button>
			</a> <a href="pizzeriabooking">
				<button class="btn btn-primary">Pizzeria</button>
			</a>
		</div>
	</div>
</body>
</html>
