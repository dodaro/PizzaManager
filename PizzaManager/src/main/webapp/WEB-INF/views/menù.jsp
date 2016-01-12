<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>


<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />

<title>Menù View</title>

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
					<div id="content"><h2>View Pizzeria ${pizzeria.name}</h2></div>
				</div>
			</div>
				<div class="col-xs-3 col-sm-3 col-sm-push-3">
<h2>Menù</h2>
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

</div>
	</div>
</body>
</html>

