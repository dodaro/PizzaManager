<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".addToCart").on('click', function() {
			var id = parseInt($(this).data("id"), 10);
			var pizzeria = parseInt($(this).data("pizzeria"), 10);
			alert($(this).data("pizzeria"));
			$.ajax({
				type : "POST",
				url : "/cart/addItem",
				data : {
					itemToBook : id

				},
				success : function(response) {
					$.ajax({
						type : "GET",
						url : "/menu",
						data : {
							id : pizzeria

						},
						success : function(response) {

						}
					});
				}
			});
		});

	});
</script>

<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<title>Menù View</title>

<meta name="viewport" content="width=device-width" />

<style type="text/css">
#boxReview {
	height: 40%;
	width: 45%;
}
</style>
</head>
<body>
	<jsp:include page="includes/navbar${typeSession}.jsp" />

	<div class="container">

		<div class="row">
			<div id="wrapper" class="col-xs-10">
				<div class="card">
					<div id="content">
						<h2>Menù Pizzeria ${pizzeriaResult.getName()}</h2>
					</div>
				</div>
			</div>
			<div class="col-xs-3 col-sm-3 col-sm-push-3">
				<h2>Menù</h2>
				<div class="pre-scrollable">
					<div id="boxReview">
						<c:forEach var="r" items="${menuResult}">
							<div class="row">
								<a>${r.pizza.name}_____________${r.price}</a> <a
									data-id="${r.id}" data-pizzeria="${pizzeriaResult.id}"
									class="addToCart btn btn-primary">Add to Cart</a>
							</div>
						</c:forEach>
					</div>
				</div>

			</div>

		</div>
	</div>
</body>
</html>

