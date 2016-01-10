<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>


<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<title>Pizzeria View</title>

<meta name="viewport" content="width=device-width" />

<style type="text/css">


#map {
  	margin-left: 50px;
	height: 50%;
	width: 225%;
}

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
	<jsp:include page="includes/navbarAccount.jsp" />

	<div class="container">

		<div class="row">

			<div id="wrapper" class="col-xs-10">
				<div class="buble">
					<div id="content"><h2>View Pizzeria ${pizzeria.name}</h2></div>
				</div>
			</div>
			<div class="col-sm-3 col-sm-push-3">
			<div id="map">

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
	</div>
  				<div class="col-xs-3 col-sm-3 col-sm-pull-3">
<h2>Latest Reviews</h2>
<div class="pre-scrollable">
<div id="boxReview">
    <div>il contenuto qui
	dlfhb</div>
	<div>dfjnbòdj
	sdljgòldkmb,
	dijfgòdf</div>
	<div>sògkjbd</div>
	<div>slkgsòkmg</div>
	<div>dkflnglkdfj</div>
	<div>lkjglkf</div>
	<div>dkflnglkdfj</div>
	<div>lkjglkf</div><div>dkflnglkdfj</div>
	<div>lkjglkf</div><div>dkflnglkdfj</div>
	<div>lkjglkf</div>
	<div>lkjglkf</div><div>dkflnglkdfj</div>
	<div>lkjglkf</div>
    </div><!-- /.boxinner -->
</div><!-- /.box -->
</div>	

  			<div id="boxInfo">
  			<div class="col-xs-4 col-sm-4 col-sm-push-4">
  			<h3>Pizzeria</h3>
  			<div>Via Roma n°69</div>
  			<div>80011</div>
  			<div>e-mail: pizzeria@mail.com</div> 
  			</div>
</div>


<div id="boxButton">
<a href="menù" class="btn btn-success">
							Menù
						</a>
<a href="book" class="btn btn-primary">Book</a>
</div>
</div>
</div>
</body>
</html>

