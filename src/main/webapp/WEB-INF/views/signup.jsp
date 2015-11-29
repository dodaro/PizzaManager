<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/signup/signup.js"></script>
<script type="text/javascript" src="resources/js/signup/signupController.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/signup.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Sign Up to Pizza Manager</title>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="content">
		<h2>Sign up to Pizza Manager!</h2>
			<form:form id="signUpForm" action="signup" method="post" commandName="signUpForm">
				<div id="inputEmailContainer" class="form-group has-feedback">
					<form:input type="text" path="email" id="inputEmail" class="form-control" placeholder="Email" />
					<span class="glyphicon form-control-feedback"></span>
					<img class="loader form-control-feedback" src="resources/img/loader.gif" />
				</div>
				<div class="form-group">
					<form:input type="text" path="password" id="inputPassword" class="form-control"
						placeholder="Password" />
				</div>
				<button type="submit" class="btn btn-primary">Sign Up</button>
			</form:form>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>