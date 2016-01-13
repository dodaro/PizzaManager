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
			alert(id);
			$.ajax({
				type : "POST",
				url : "/cart",
				data : {
					id : id
				},
				success : function() {
					data
				}
			});
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
			alert(items);

			var itemToBook = items.substring(1, items.length);

			console.log(itemToBook);

			$.ajax({
				type : "POST",
				url : "/bookCart",
				data : {
					itemToBook : itemToBook,
				},
				success : function(response) {
					if (response.success) {
						console.log(response)
						window.location = 'userBooking'
					}
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

	<div id="container">
		<div class="row">
			<jsp:include page="includes/navUserMenu.jsp"></jsp:include>
			<div class="col-xs-9">
				<div class="wrapper">
					<div id="content" class="cartContainer">
						<c:forEach var="i" varStatus="stat" items="${cart.items}">
							<div class="row itemDisplay">
								<div class="col-xs-2">
									<img class="imgCart" src="" alt="" />
								</div>
								<div class="col-xs-2">
									<b>${i.itemName}</b>
								</div>
								<div class="col-xs-2">
									<b>${i.pizzeria}</b>
								</div>
								<div class="col-xs-2">
									<input class="form-control number-control" data-id="${i.id}"
										type="number" value="${i.number}">
								</div>
								<div class="col-xs-2">
									<div class="pull-right">
										<b>${i.cost }</b>
									</div>
								</div>
								<div class="col-xs-2">
									<input class="btn btn-default removeButton" type="button"
										value="remove" data-id="${i.id}">
								</div>
							</div>
						</c:forEach>
						<div class="row">
							<input id="bookCart" class="pull-right" type="submit"
								value="Confirm" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

