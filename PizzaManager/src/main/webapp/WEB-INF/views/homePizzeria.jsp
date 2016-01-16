<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-switch.js"></script>

<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="resources/js/select2.js"></script>
<script type="text/javascript" src="resources/js/underscore.js"></script>

<script type="text/javascript" src="resources/js/moment.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="resources/js/pizzeria/Pizza.js"></script>

<!-- Home scripts. -->
<script type="text/javascript" src="resources/js/pizzeria/homePizzeria.js"></script>

<!-- Scripts for tables management -->
<script type="text/javascript" src="resources/js/pizzeria/pizzeriaTableManager.js"></script>
<script type="text/javascript" src="resources/js/pizzeria/pizzeriaPizzaManager.js"></script>
<script type="text/javascript" src="resources/js/pizzeria/pizzeriaBeverageManager.js"></script>

<!-- Scripts for the live order tool -->
<script type="text/javascript" src="resources/js/pizzeria/Pizza.js"></script>
<script type="text/javascript" src="resources/js/pizzeria/LiveOrderTool.js"></script>

<!-- Scripts for the booking -->
<script type="text/javascript" src="resources/js/pizzeria/Booking.js"></script>

<!-- Scripts for the live restaurant -->
<script type="text/javascript" src="resources/js/pizzeria/LiveRestaurant.js"></script>

<!-- PER DANILO: DECIDI TU DOVE METTERE QUESTO SCRIPT -->
<script type="text/javascript">
	/*VARIABILE CHE SERVE PER METTERE IN COMUNICAZIONE LE VARIE .jsp
	 USIAMOLO COME UNA STANDARD HASHTABLE(GIÀ LO È DI PER SE), a cui si aggiunge un oggetto da comunicare ad un altra jsp
	 RICORDATE però di rimuovere subito l'oggetto aggiunta una volta che non serve più.
	 Prim di agginugere un oggetto controllate che la parola chiave che volete inserire non sia già presente dentro l'hash
	 perchè non bisogna mai sovrascrivere qualcosa di già esistente.
	 */
	communicator = new Object();
</script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-datepicker.css" />

<link rel="stylesheet" type="text/css" href="resources/css/jquery.dataTables.css" />

<!-- This will break the page and it's most likely not needed. -->
<!-- <link rel="stylesheet" type="text/css" href="resources/css/dataTables.css" /> -->

<link rel="stylesheet" type="text/css" href="resources/css/select2.css" />
<link rel="stylesheet" type="text/css" href="resources/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap-switch.css" />

<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/homePizzeria.css" />

<!-- <link rel="stylesheet" type="text/css" href="resources/css/pizzeriaTableManager.css" /> -->

<link rel="stylesheet" type="text/css" href="resources/css/pizzerialiveorder.css" />

<title>${pizzeria.name}</title>

<meta name="viewport" content="width=device-width" />

</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-xs-2">
				<div class="bubble affix">
					<ul class="nav nav-pills nav-stacked">
						<!-- <li class="active"><a href="#">Home</a></li> -->
						<li data-content="pizzeriaTableManager"><a href="#">Manage tables</a></li>
						<li data-content="pizzeriaPizzaManager"><a href="#">Manage pizzas</a></li>
						<li data-content="pizzeriaBeverageManager"><a href="#">Manage beverages</a></li>
						<li data-content="pizzeriabooking"><a href="#">Manage Bookings</a></li>
						<!-- PER DANILO: ho aggiunto un id per poter triggerare il tasto, è una soluzione sporca però, che ne dici? -->
						<li id="liLiveOrderTool" data-content="pizzerialiveorder"><a href="#">Live Order Tool</a></li>
						<li data-content="pizzerialiverestaurant"><a href="#">Live Restaurant</a></li>
						<li><a href="#">Kitchen</a></li>
						<li><a href="#">Statistics</a></li>
					</ul>
				</div>
			</div>
			<div id="wrapper" class="col-xs-10">
				<div class="bubble">
					<div id="content">Home Pizzeria ${pizzeria.name}</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
