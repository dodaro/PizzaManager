<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>

<script type="text/javascript" src="resources/js/jquery.js"></script>

<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/pizzaManager.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="resources/js/bootstrap.js"></script>

<script >

</script>
<html>
<head>
<title>Cart</title>
</head>
<body>
	<h1>LOGO</h1>
	<nav class="navbar navbar-inverse">
		<div id="navBoard" class="container-fluid">
			<div>
				<ul id="navBar" class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown"> User1 <span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#">User Panel</a></li>
							<li><a href="#">My orders</a></li>
							<li><a href="#">Settings</a></li>
							<li class="diveder"></li>
							<li><a href="#">Log out</a></li>
						</ul></li>

					<!-- dashboard da riempire -->
					<li id="offerNav"><a href="#">Offers</a></li>
					<li id="cartNav"><a href="#">Cart</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li id="searchNav"><form class="navbar-form" role="search">


							<a id="searchIt" href="#"><i style="font-size: 1.6em;"
								class="glyphicon glyphicon-search"></i></a> <input type="text"
								class="form-control" placeholder="Search" name="q">

						</form></li>
				</ul>
			</div>
		</div>
	</nav>
	<div id=mainContent class="row row-body">
		<div id="left-Board" class="col-md-2 sidebar-nav left-side">
			<div>
				<table>
					<tr>
						<th>Restaurant Name</th>
					</tr>
					<tr>
						<td>Address</td>
					</tr>
					<tr>
						<td>Average Waiting Time</td>
					</tr>
					<tr>
						<td><a href="#">Menù</a></td>
					</tr>
				</table>
			</div>

		</div>
		<div class="col-md-1 center-side"></div>

		<div id="center-list">
			<ol class="noNumber">

				<c:forEach var="pizzaC" items="${pizzaCart}" varStatus="status">
					<li id="pizzaInCart"><div class="col-md-3 center-side">
							<table>
								<tr>
									<th><span><a href="#">${pizzaC}</a></span></th>
								</tr>
								<tr>
									<td><span>Ingredients a long list of ingredients</span></td>

								</tr>
								<tr>
									<td></td>

								</tr>
							</table>
						</div>
						<div class="col-md-3 center-side">
							<table>
								<tr>
									<th><span>Price</span></th>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td class="priceAllign">${status.index}</td>
								</tr>
							</table>
						</div></li>

				</c:forEach>

			</ol>
		</div>


		<div class="col-md-3 right-side">
			<h3>
				<b>Search Pizza</b>
			</h3>
			<form>
				<label for="pizzaName">Pizza Name</label> <input id="pizzaName"
					type="text">
				<!-- other option -->
				<input type="button" value="Search" onclick="">
			</form>
		</div>
	</div>
	<h3>
		<b>Suggested For You</b>
	</h3>
	<div id="bottomContent" class="row">
		<div class="col-md-3 "></div>
		<div id="carouselPizza" class="carousel slide col-md-6"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselPizza" data-slide-to="0" class="active"></li>
				<li data-target="#carouselPizza" data-slide-to="1"></li>
			</ol>
			<div class="carousel-inner" role="listbox">
				<div class="item active">
					<div class="row">
						<div class="col-md-3">
							<img class="imgCarousle" src="resources/img/margherita.jpg">
							<div class="carousel-caption">
								<h3>Margherita</h3>
								<p>5,00 euro</p>
							</div>
						</div>
						<div class="col-md-2"></div>
						<div class="col-md-3">

							<img class="imgCarousle" src="resources/img/capricciosa.jpg">
							<div class="carousel-caption">
								<h3>Capricciosa</h3>
								<p>7,00 euro</p>

							</div>
						</div>
					</div>
				</div>
				<div class="item">
					<div class="row">
						<div class="col-md-3">
							<img class="imgCarousle" src="resources/img/diavola.jpg">
							<div class="carousel-caption">
								<h3>Diavola</h3>
								<p>6,50 euro</p>
							</div>
						</div>
						<div class="col-md-2"></div>
						<div class="col-md-3">

							<img class="imgCarousle" src="resources/img/speciale.jpg">
							<div class="carousel-caption">
								<h3>Speciale</h3>
								<p>8,00 euro</p>

							</div>
						</div>
					</div>
				</div>

			</div>


			<a class="left carousel-control" href="#carouselPizza" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#carouselPizza"
				role="button" data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
</body>
</html>