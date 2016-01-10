
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
	margin-top:60px;
}

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
					<div id="content"><h2>View Pizzeria ${pizzeria.name}</h2></div>
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
  			<h3>Pizzeria</h3>
  			<div>Via delle Banane n°69</div>
  			<div>80011</div>
  			<div>e-mail: pizzeria@banana.it</div> 
</div>
</div>
	</div>
</body>
</html>

