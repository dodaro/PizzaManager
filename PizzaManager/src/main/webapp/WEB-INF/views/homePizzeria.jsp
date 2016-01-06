<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="resources/js/select2.js"></script>
<script type="text/javascript" src="resources/js/underscore.js"></script>

<!-- Home scripts. -->
<script type="text/javascript" src="resources/js/pizzeria/homePizzeria.js"></script>

<!-- Scripts for tables management -->
<script type="text/javascript" src="resources/js/pizzeria/pizzeriaTable.js"></script>

<!-- Scripts for the live order tool -->
<script type="text/javascript" src="resources/js/pizzeria/Pizza.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />

<link rel="stylesheet" type="text/css" href="resources/css/jquery.dataTables.css" />

<!-- This will break the page and it's most likely not needed. -->
<!-- <link rel="stylesheet" type="text/css" href="resources/css/dataTables.css" /> -->

<link rel="stylesheet" type="text/css" href="resources/css/select2.css" />
<link rel="stylesheet" type="text/css" href="resources/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/pizzerialiveorder.css" />
<link rel="stylesheet" type="text/css" href="resources/css/pageCSS/pizzerialiveorder.css" />

<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/homePizzeria.css" />

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
						<li data-content="pizzeriaTable"><a href="#">Manage tables</a></li>
						<li><a href="#">Pizza</a></li>
						<li><a href="#">Beverage</a></li>
						<li><a href="#">Menu</a></li>
						<li><a href="#">Table Booking</a></li>
						<li><a href="#">Take Away Booking</a></li>
						<li><a href="#">Delivery Booking</a></li>
						<li data-content="pizzerialiveorder"><a href="#">Live Order Tool</a></li>
						<li><a href="#">Live Restaurant</a></li>
						<li><a href="#">Kitchen</a></li>
						<li><a href="#">Statistics</a></li>
					</ul>
				</div>
			</div>
			<div id="wrapper" class="col-xs-10">
				<div class="card">
					<div id="content">Home Pizzeria ${pizzeria.name}</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
