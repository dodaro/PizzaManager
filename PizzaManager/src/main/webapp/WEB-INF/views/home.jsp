<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />

<title>Home</title>

<style>
	.navbar {
/* 	background-color: #0033cc; */
/* 	color: #FFFFFF !important; */
	}
</style>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
					data-target="#id-navbar-collapse" aria-expanded="false">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">PizzaManager</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="id-navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Link</a></li>
					<li><a href="#">Link</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">
							Dropdown
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul>
					</li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Link</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">User <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Profile</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<h1>Welcome to PizzaManager!</h1>

	<div>List of users:</div>
	<c:choose>
		<c:when test="${users.size() > 0}">
			<c:forEach var="user" items="${users}">
				<div>${user.toString()}</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>There are no users into the database.</div>
		</c:otherwise>
	</c:choose>

	<a href="signup"><button class="btn btn-primary">Sign Up</button></a>
	<a href="pizzeriabooking"><button class="btn btn-primary">pizzeria</button></a>

</body>
</html>
