<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Pizza Manager</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".moreDetails").click(function(event) {
			$(this).text(function(i, old) {
				return old == '+' ? '-' : '+';
			});
		});

		$(".removeItem").on('click', function() {
			var item = $(this).data('id');
			var booking = $(this).data('booking');
			var toRemove = item + ";" + booking;
			alert(toRemove);
			$.ajax({
				type : "POST",
				url : "/orders/removeItem",
				data : {
					toRemove : toRemove
				},
				success : function(response) {
					console.log(response);
					window.location="orders";
				}
			});
		});
	});
</script>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp"></jsp:include>

	<div id="container">
		<div class="row">
			<jsp:include page="includes/navUserMenu.jsp"></jsp:include>
			<div class="col-xs-9">
				<div class="bubble">
					<h2>My Orders</h2>
					<c:if test="${not empty bookings}">
						<c:forEach var="b" items="${bookings}">
							<div class="row">
								<div data-toggle="collapse" data-target="#${b.identifier}"
									class="clickable col-xs-1">
									<b class="moreDetails">+</b>
								</div>
								<div class="col-xs-2">${b.pizzeria}</div>
								<div class="col-xs-1">${b.bookingType}</div>
								<div class="col-xs-2">${b.date}</div>
								<div class="col-xs-2">
									<c:choose>
										<c:when test="${not b.actived}">
									${b.preparationTime}</c:when>
										<c:otherwise>Not yet Active</c:otherwise>
									</c:choose>

								</div>
								<div class="col-xs-2">${b.getBillLabel()}&#8364</div>
								<div class="col-xs-2">
									<a href="${b.paypalUrl}" class="btn btn-default">Buy Now</a>
								</div>
							</div>
							<div id="${b.identifier}" class="collapse">
								<c:if test="${not empty b.items}">
									<div class="bubble">
										<c:forEach var="i" items="${b.items}">
											<div class="row">
												<div class="col-xs-1"></div>
												<div class="col-xs-1">
													<b>${i.number}</b>
												</div>
												<div class="col-xs-2">
													<b>${i.itemName}</b>
												</div>
												<div class="col-xs-2">
													<b>${i.getCostLabel()} &#8364</b>
												</div>
												<div class="col-xs-4">
													<b>${i.ingredients}</b>
												</div>
												<div class="col-xs-2">
													<button data-id="${i.id}" data-booking="${b.id}"
														class="btn btn-default removeItem">Remove</button>
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