<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/homepizzeria.css" />

<title>${pizzeria.name}</title>

<meta name="viewport" content="width=device-width" />

</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-2">
				<div class="card">
					<ul class="nav nav-pills nav-stacked">
						<!-- <li class="active"><a href="#">Home</a></li> -->
						<li><a href="#">Table</a></li>
						<li><a href="#">Pizza</a></li>
						<li><a href="#">Beverage</a></li>
						<li><a href="#">Menu</a></li>
						<li><a href="#">Table Booking</a></li>
						<li><a href="#">Take Away Booking</a></li>
						<li><a href="#">Delivery Booking</a></li>
						<li><a href="pizzerialiveorder">Live Order Tool</a></li>
						<li><a href="#">Live Restaurant</a></li>
						<li><a href="#">Kitchen</a></li>
						<li><a href="#">Statistics</a></li>
					</ul>
				</div>
			</div>
			<div id="content" class="col-xs-10">
				<div class="card">Home Pizzeria ${pizzeria.name}</div>
			</div>
		</div>
	</div>
</body>
</html>
