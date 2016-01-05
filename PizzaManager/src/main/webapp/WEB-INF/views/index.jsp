<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<title>Index</title>

<meta name="viewport" content="width=device-width" />

</head>
<body>
	<jsp:include page="includes/navbarLogin.jsp" />

	<div class="container">
		<div class="card">
			<div>Welcome to PizzaManager!</div>
			<div>This is a card. Use cards as containers to display content.</div>
		</div>
		<div class="card">
			<div>This is the index. If you see this you're not logged in.</div>
			<div>If you use the default populator, ids from 1 to 10 are users, while ids from 11 to 20
				are pizzerias.</div>
			<div>Use "mail4@mail.com" as email and "password4" to login as the user with id 4.</div>
			<div>Use "mail15@mail.com" as email and "password15" to login as the pizzeria with id 15.</div>
			<div>Once logged in, you'll see the home for that particular account.</div>
		</div>
	</div>
</body>
</html>
