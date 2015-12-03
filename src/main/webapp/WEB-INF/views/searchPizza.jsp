<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="true"%>

<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/star-rating.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/pizzaManager.css" />


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="resources/js/bootstrap.js"></script>





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
			<!-- some left bar with information -->
		</div>

		<div class="col-md-1 center-side"></div>

		<div id="center-list">
			<div class="row">
				<h3>
					<b>Results:</b>
				</h3>
				<ol class="noNumber">
					<li class="col-md-6 "><c:forEach var="pizzaL"
							items="${results}" varStatus="status">
							<table class="resultTable">
								<tr>
									<td><span><img src="resources/img/margherita.jpg"
											class="imageIcon"></img></span></td>
									<td class="col-md-4"><a href="#"><b>${pizzaL}</b></a>
										<p>Description and List of ingredients</p></td>
									<td class="span"><p>${status.index}</p> <input class="btn"
										type="button" value="Add to Cart"></td>
									<!-- rating button -->
								</tr>
							</table>
						</c:forEach></li>
				</ol>
			</div>
			<div class="row">
				<ul class="pager">
					<li class="previous"><a id="prevBtn"
						href="/searchPizza?pages=1">Previous</a></li>
					<li class="next "><a id="nextBtn" href="/searchPizza?pages=2">Next</a></li>
				</ul>
			</div>
		</div>

		<div class="col-md-3 right-side">
			<h3>
				<b>Search</b>
			</h3>
			<form>
				<table>
					<tr>
						<td><input id="restRadio" type="radio" name="typeSearch">
							<label for="restRadio">Restaurant</label></td>
					</tr>
					<tr>
						<td><input id="pizzaRadio" type="radio" name="typeSearch">
							<label for="pizzaRadio">Pizza</label></td>
					</tr>
					<tr>
						<td><label for="pizzaName">Name</label> <input id="pizzaName"
							type="text"></td>
					</tr>
					<!-- other option -->
				</table>
				<input type="button" value="Search" onclick="">
			</form>
		</div>

	</div>


</body>
</html>