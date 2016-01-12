<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Pizza Manager</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<script type="text/javascript" src="resources/js/underscore.js"></script>
<script type="text/javascript" src="resources/js/user/cart.js"></script>
<script type="text/javascript"
	src="resources/js/pizzeria/homePizzeria.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/pageCSS/cart.css" />
</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp"></jsp:include>

	<div id="container">
		<div class="row">
			<div class="col-xs-3">
				<div class="bubble">
					<ul class="nav nav-pills nav-stacked">
						<li data-content="cart"><a href="#">My Cart</a></li>
						<li><a href="#">My Order</a></li>
						<li data-content="settings"><a href="#">Settings</a></li>
					</ul>
				</div>
			</div>
			<div id="wrapper" class="col-xs-9">
				<div class="bubble">
					<div id="content">Welcome ${user.name}</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
