<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>


<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<title>Home User</title>

<meta name="viewport" content="width=device-width" />

<style type="text/css">


#map {

  	
  	margin-left: 50px;
	height: 50%;
	width: 235%;
}

</style>
</head>
<body>
	<jsp:include page="includes/navbarAccount.jsp" />

	<div class="container">

		<div class="row">
			<div class="col-xs-2">
				<div class="card">
					<ul class="nav nav-pills nav-stacked">
						<!-- <li class="active"><a href="#">Home</a></li> -->
						<li><a href="#">Impostazioni</a></li>
						<li><a href="#">Ultimi Ordini</a></li>
						<li data-content="index"><a href="#">Your Reviews</a></li>
					</ul>
				</div>
			</div>
			<div id="wrapper" class="col-xs-10">
				<div class="card">
					<div id="content">Home User ${user.id}</div>
				</div>
			</div>
			<div class="col-sm-3 col-sm-push-3">
			<div id="map"></div>

	<script type="text/javascript">
		var map;
		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				center : {
					lat : -34.397,
					lng : 150.644
				},
				
				zoom : 8
			});
		}
	</script>
	<script async defer src="https://maps.googleapis.com/maps/api/js?callback=initMap">
		
	</script>
	</div>
  			 <div class="col-sm-3 col-sm-pull-3">
  			 <div class="image">
  			<img src="resources/images/no-image.png" class="img-circle">
  			<div class="col-sm-offset-2">Age:</div>
  			<div class="col-sm-offset-2">Location:</div>
  			
 		</div>
</div>
</div>
	</div>
</body>
</html>

