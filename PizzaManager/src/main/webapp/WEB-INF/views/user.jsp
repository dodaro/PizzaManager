<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Title</title>
<link rel="stylesheet" type="text/css"
	href="/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/resources/css/bootstrap-theme.css">
<script src="/resources/js/bootstrap.js"></script>
<script src="/resources/js/jquery.js"></script>

</head>

<body>
	<div id="header">
		<div id="imageHeader">
			<img class="imageHeader" src="/resources/img/dowload.jpg"></img>
		</div>
		<div id="navigationBar">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#">Cart</a></li>
					</ul>
				</div>
			</nav>

		</div>
	</div>
	<div id="mainPage" class="row">
		<div id="dropdownMenu" class="col-xs-3">
			<div class="panel-group">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" href="#collapseMenu">${user.userName}</a>
						</h4>
					</div>
					<div id="collapseMenu" class="panel-collapse collapse">
						<div class="panel-body">MyCart</div>
						<div class="panel-body">MyFavourites</div>
						<div class="panel-body">MyOrders</div>
						<div class="panel-body">Settings</div>
					</div>
				</div>
			</div>
		</div>
		<div id="mainContent" class=col-xs-8></div>
	</div>

</body>




</html>