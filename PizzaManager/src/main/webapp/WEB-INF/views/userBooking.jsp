<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Pizza Manager</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/moment.js"></script>
<script type="text/javascript"
	src="resources/js/bootstrap-datepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function() {

		$('.datetimepicker').each(function() {
			$(this).datetimepicker({
				defaultDate : new Date(),
				locale : 'it'
			});
		});

		console.log($('.takeAway'));

		$('.takeAway').on('click', function() {
			var type = "takeAway";
			var pizzeria = $(this).data('pizzeria');
			var date = $(this).closest('.row').find('input').val();

			var pizzeriaCartBook = pizzeria.concat(";", type, ";", date);
			alert(pizzeriaCartBook);
			$.ajax({
				type : "POST",
				url : "/userBooking/book",
				data : {
					pizzeriaCartBook : pizzeriaCartBook

				},
				success : function(response) {
					console.log(response)
					window.location = 'userBooking'
				}
			});

		});

		$('.delivery').on('click', function() {
			var pizzeria = $(this).data('pizzeria');
			var type = "delivery";
			var date = $(this).closest('.row').find('input').val();
			var pizzeriaCartBook = pizzeria.concat(";", type, ";", date);
			alert(pizzeriaCartBook);
			$.ajax({
				type : "POST",
				url : "/userBooking/book",
				data : {
					pizzeriaCartBook : pizzeriaCartBook

				},
				success : function(response) {

					console.log(response)
					window.location = 'userBooking'

				}
			});
		});

	});
</script>
<script type="text/javascript" src="resources/js/user/cart.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap-datepicker.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp"></jsp:include>

	<div id="container">
		<div class="row">
			<jsp:include page="includes/navUserMenu.jsp"></jsp:include>
			<div class="col-xs-9">
				<div id="content" class="container">
					<jsp:include page="includes/cartBook.jsp"></jsp:include></div>
			</div>
		</div>
	</div>
</body>
</html>
