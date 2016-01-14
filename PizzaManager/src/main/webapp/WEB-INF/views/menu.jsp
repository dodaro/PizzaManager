<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".addToCart").on('click',function(){
		alert($(this).data("id"));
	});
	
});
</script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<title>Men� View</title>

<meta name="viewport" content="width=device-width" />

<style type="text/css">


#boxReview{
	height: 40%;
	width: 45%;
}

</style>
</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp" />

	<div class="container">

		<div class="row">
<div id="wrapper" class="col-xs-10">
				<div class="card">
					<div id="content"><h2>Men� Pizzeria ${pizzeriaResult.getName()}</h2></div>
				</div>
			</div>
				<div class="col-xs-3 col-sm-3 col-sm-push-3">
<h2>Men�</h2>
<div class="pre-scrollable">
<div id="boxReview">
<c:forEach var="r" items="${menuResult}">
					<div class="row">
						<a>${r.pizza.name}_____________${r.price}</a>
						<a data-id="${r.id}" class="addToCart btn btn-primary">Add to Cart</a>
					</div>
					</c:forEach>
					</div>
					</div>
   
</div>	

</div>
	</div>
</body>
</html>

