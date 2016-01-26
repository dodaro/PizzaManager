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
	$(document).ready(
			function() {

				$('.datetimepicker').each(function() {
					$(this).datetimepicker({
						defaultDate : new Date(),
						locale : 'it'
					});
				});

				$('.takeAway').on(
						'click',
						function() {
							var button = $(this);
							var type = "takeAway";
							var pizzeria = $(this).data('pizzeria');
							var dateTime = $(this).closest('.row').find(
									'.datetimepicker').data('DateTimePicker')
									.date().format('DD/MM/YYYY HH:mm');
							var d = dateTime.split(" ")[0];
							var t = dateTime.split(" ")[1];
							$.ajax({
								type : "POST",
								url : "/userBooking/book",
								data : {
									pizzeria : pizzeria,
									type : type,
									date : d,
									time : t

								},
								success : function(response) {
								
									if (response == "dateError") {
										button.closest('.row').find(
												'.dateErrorMessage').text(
												"Select a valid date.");
									} else {
									
										window.location = 'userBooking'
									}
								}
							});

						});

				$('.delivery').on(
						'click',
						function() {
							var button = $(this);
							var pizzeria = $(this).data('pizzeria');
							var type = "delivery";
							var dateTime = $(this).closest('.row').find(
									'.datetimepicker').data('DateTimePicker')
									.date().format("DD/MM/YYYY HH:mm");
							var d = dateTime.split(" ")[0];
							var t = dateTime.split(" ")[1];
							
							$.ajax({
								type : "POST",
								url : "/userBooking/book",
								data : {
									pizzeria : pizzeria,
									type : type,
									date : d,
									time : t

								},
								success : function(response) {
									
									if (response == "dateError") {
										button.closest('.row').find(
												'.dateErrorMessage').text(
												"Select a valid date.");
									} else {
										
										window.location = 'userBooking'
									}
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
<link rel="stylesheet" type="text/css"
	href="resources/css/userBooking.css" />
</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp"></jsp:include>

	<div id="container" class="container">
		<div class="row">
			<jsp:include page="includes/navUserMenu.jsp"></jsp:include>
			<div class="col-xs-9 wrapper">
				<div id="content">





					<jsp:include page="includes/cartBook.jsp"></jsp:include></div>
			</div>
		</div>
	</div>
</body>
</html>
