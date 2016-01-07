<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<title>Home User</title>

<meta name="viewport" content="width=device-width" />

</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp" />

	<div class="container">
		<div class="bubble">
			<div>Welcome to PizzaManager!</div>
			<div>This is a bubble. Use bubbles as containers to display content.</div>
		</div>
		<div class="bubble">
			<div>This is the home for a user.</div>
		</div>
	</div>
</body>
</html>

