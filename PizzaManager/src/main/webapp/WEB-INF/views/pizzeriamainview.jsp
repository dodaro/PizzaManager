<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

<script type="text/javascript" src="resources/js/maps.js"></script>

<script type="text/javascript" src="resources/js/pizzeria/pizzeriaMainView.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<title>Pizzeria View </title>

<meta name="viewport" content="width=device-width" />

<style type="text/css">
#boxInfo{
	margin-top:30px;
	margin-right: 40px;
}
#boxReview{
	height: 40%;
	width: 45%;
}
#boxButton{
	margin-top:410px;
}
</style>
</head>
<body>
	<jsp:include page="includes/navbar${typeSession}.jsp" />

	<div class="container">

		<div class="row">

			<div id="wrapper" class="col-xs-10">
				<div class="buble">
					<div id="content"><h2>${pizzeriaResult.getName()}</h2></div>
				</div>
			</div>
			<div class="col-sm-3 col-sm-push-3">
			<div id="map"></div>
	</div>
  				<div class="col-xs-3 col-sm-3 col-sm-pull-3">
<h2>Latest Reviews</h2>
<div class="pre-scrollable">
<div id="boxReview">
<c:forEach var="r" items="${pizzeriaResult.feedbacks}">
					<div class="row">
						<a class="userref" href="usermainview?id=${r.user.id}">${r.user.name}</a>
						<div>Fastnes: ${r.fastnessRating}</div>
						<div>Hospitality: ${r.hospitalityRating}</div>
						<div>Quality: ${r.qualityRating}</div>
						<div>Comment:</div>
						<div>${r.comment}</div>
						
					</div>
					</c:forEach>
  
</div>
</div>	
</div>
  			<div id="boxInfo">
  			<div class="col-xs-4 col-sm-4 col-sm-push-4">
  			<h3>${pizzeriaResult.name}</h3>
  			<div>${pizzeriaResult.getAddress()}</div>
  			<div>${pizzeriaResult.getPhoneNumber()}</div>
  			<div>e-mail: ${pizzeriaResult.getEmail()}</div> 
  			</div>
</div>


<div id="boxButton">
<a href="menu?id=${pizzeriaResult.id}" class="btn btn-success">
							Menù
						</a>
<a href="book?id=${pizzeriaResult.id}" class="btn btn-primary">Book</a>
</div>
	<div class="col-sm-4 col-sm-push-4">
<div class="pre-scrollable">
<input type="text" class="form-control" id="boxInputReview">

</div>

<a class="btn btn-primary">Leave Comment</a>
</div>
</div>
</div>
	<script>
		pizzeriaMainView.init({
			latitude : <c:out value="${pizzeriaResult.location.latitude}" />,
			longitude : <c:out value="${pizzeriaResult.location.longitude}" />
		});
	</script>
</body>
</html>