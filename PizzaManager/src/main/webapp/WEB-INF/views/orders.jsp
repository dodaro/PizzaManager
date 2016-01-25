<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Pizza Manager</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$(".moreDetails").click(function(event) {
							$(this).find("b").text(function(i, old) {
								return old == '+' ? '-' : '+';
							});
						});
						$(".paypalButton")
								.on(
										'click',
										function() {
											var idBooking = $(this).data('id');
											var token = $(this).data('token');
											if (token == null) {
												$
														.ajax({
															type : "POST",
															url : "/payment/createPayment",
															data : {
																bookingId : idBooking
															},
															success : function(response) {
																console.log(response);
																if (response == "Failed Creation")
																	console.log(response);
																else if (response == "Failed inizialization")
																	console.log(response);
																else
																	window.location = response;
															}
														});
											} else {
												var redirect = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
														+ token;
												console.log(redirect);
												window.location = redirect;
											}
										});
						$(".removeItem").on('click', function() {
							var item = $(this).data('id');
							var booking = $(this).data('booking');
							$.ajax({
								type : "POST",
								url : "/orders/removeItem",
								data : {
									iditem : item,
									idbooking : booking
								},
								success : function(response) {
									console.log(response);
									window.location = "orders";
								}
							});
						});
					});
</script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/pageCSS/orders.css" />
</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<jsp:include page="includes/navUserMenu.jsp"></jsp:include>
			<div class="col-xs-9 wrapper">
				<div class="bubble">
					<div class="bubble-title">My Orders</div>
					<c:if test="${not empty bookings}">
						<c:forEach var="b" items="${bookings}">
							<div class="row itemRow">
								<div class="col-xs-4 pizzeria-name-container">
									<span data-toggle="collapse" data-target="#${b.identifier}"
										class="glyphicon glyphicon-plus moreDetails clickable col-xs-1"></span><a
										href="pizzeriamainview?id=${b.pizzeriaId}">${b.pizzeria}</a>
								</div>
								<div class="col-xs-2">${b.bookingType}</div>
								<div class="col-xs-2">${b.date}</div>
								<div class="col-xs-2">
									<c:choose>
										<c:when test="${b.actived}">
									${b.completationTime}</c:when>
										<c:otherwise>Not yet active</c:otherwise>
									</c:choose>

								</div>
								<%-- <div class="col-xs-1">${b.preparationTime}</div> --%>
								<div class="col-xs-1 price-container">${b.getBillLabel()}&#8364</div>
								<div class="col-xs-1 pay-container">
									<c:choose>
										<c:when test="${not b.payed}">
											<a data-id="${b.id}" data-token="${b.token}" class="btn btn-success paypalButton">Buy</a>
										</c:when>
										<c:otherwise>Paid</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div id="${b.identifier}" class="collapse">
								<c:if test="${not empty b.items}">
									<div>
										<c:forEach var="i" items="${b.items}">
											<div class="row singleOrderBooking">
												<div class="col-xs-1"></div>
												<div class="col-xs-7">
													<span class="quantity">${i.number}</span> <span class="item-name">${i.itemName}</span>
												</div>
												<div class="col-xs-1 price-container-small">
													<span>${i.getCostLabel()} &#8364</span>
												</div>
												<div class="col-xs-3 remove-container">
													<button data-id="${i.id}" data-booking="${b.id}"
														class="btn btn-danger btn-sm removeItem">Remove</button>
												</div>
											</div>
										</c:forEach>
									</div>
								</c:if>
							</div>
						</c:forEach>
					</c:if>

				</div>
			</div>
		</div>
	</div>
</body>
</html>