<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Pizza Manager</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-switch.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('input.removeButton').on('click', function() {
			var clickedElement = $(this);
			var id = clickedElement.data('id');
			$.ajax({
				type : "POST",
				url : "/cart/removeItem",
				data : {
					id : id
				},
				success : function(response) {
					window.location = "cart";
				}
			});
		});
		$(".number-control").on('change',function(){
			var value=$(this).val();
			if(value<1)
				$(this).val(1);
			else if(value>99)
				$(this).val(99);
		});
		$('#bookCart').on('click', function() {
			var clickedElement = $(this);
			var items = "";
			$(".number-control").each(function(index, element) {
				var id = $(this).data('id');
				var number = $(this).val();
				var item = id + "-" + number;
				items = items + ";" + item;

			});

			var itemToBook = items.substring(1, items.length);

			$.ajax({
				type : "POST",
				url : "/bookCart",
				data : {
					itemToBook : itemToBook,
				},
				success : function(response) {
					
					window.location = 'userBooking'

				}
			})
		});
	});
</script>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/pageCSS/cart.css" />
<body>
	<jsp:include page="includes/navbarAccount.jsp"></jsp:include>

	<div id="container" class="container">
		<div class="row">
			<jsp:include page="includes/navUserMenu.jsp"></jsp:include>
			<div class="col-xs-9">
				<div class="wrapper">
					<div id="content" class="cartContainer">
						<c:choose>
							<c:when test="${not empty cart.items}">
								<c:forEach var="i" varStatus="stat" items="${cart.items}">
									<div class="row itemDisplay">
										<div class="col-xs-3">
											<div class="item-name">${i.itemName}</div>
										</div>
										<div class="col-xs-3">
											<div class="item-pizzeria">${i.pizzeria}</div>
										</div>
										<div class="col-xs-1">
											<input class="form-control number-control" data-id="${i.id}"
												type="number" value="${i.number}">
										</div>
										<div class="col-xs-3">
											<div class="item-price">${i.getCostLabel()}&#8364</div>
										</div>
										<div class="col-xs-2">
											<input class="btn btn-default removeButton" type="button" value="Remove"
												data-id="${i.id}">
										</div>
									</div>
								</c:forEach>
								<div class="row confirm-container">
									<input id="bookCart" class="btn btn-default pull-right"
										type="submit" value="Confirm" />
								</div>
							</c:when>
							<c:otherwise>Your cart is empty.</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>