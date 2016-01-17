<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?libraries=places"></script>
<script type="text/javascript" src="resources/js/maps.js"></script>
<script type="text/javascript" src="resources/js/signup/signup.js"></script>
<script type="text/javascript" src="resources/js/signup/signupController.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css" href="resources/css/signup.css" />

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Sign Up to Pizza Manager</title>
</head>
<body>
	<jsp:include page="includes/navbarLogin.jsp" />

	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-7">
				<div class="bubble">
					<h2>Sign up to Pizza Manager!</h2>
					<form:form action="signup" method="post" modelAttribute="signUpForm">
						<div class="row js-email-container">
							<div class="col-md-5 input-container">
								<div class="form-group has-feedback js-email-form">
									<form:input type="text" path="email" class="form-control js-email-input"
										placeholder="Email" />
									<span class="glyphicon form-control-feedback"></span> <img
										class="loader form-control-feedback" src="resources/img/loader.gif" />
								</div>
							</div>
							<div class="col-md-7">
								<div class="message hint visible">Insert your email.</div>
								<div class="message valid">Email seems ok.</div>
								<div class="message validating">Validating...</div>
								<div class="message taken">An user with this email already exists.</div>
								<div class="message error">Invalid email.</div>
							</div>
						</div>
						<div class="row js-password-container">
							<div class="col-md-5 input-container">
								<div class="form-group has-feedback js-password-form">
									<form:input type="text" path="password" class="form-control js-password-input"
										placeholder="Password" />
									<span class="glyphicon form-control-feedback"></span>
								</div>
							</div>
							<div class="col-md-7">
								<div class="message hint visible">Your password must be at least 8 characters long.</div>
								<div class="message valid">Password is ok.</div>
								<div class="message error">Your password must be at least 8 characters long.</div>
							</div>
						</div>
						<div id="signup-additional">
							<div>You are creating an account for...</div>
							<div>
								<ul class="nav nav-pills nav-justified">
									<li data-additional="user"><a href="#">User</a></li>
									<li data-additional="pizzeria"><a href="#">Pizzeria</a></li>
								</ul>
							</div>
						</div>
						<div class="additional-user additional">
							<div class="row js-username-container">
								<div class="col-md-5 input-container">
									<div class="form-group has-feedback js-username-form">
										<form:input type="text" path="username" class="form-control js-username-input"
											placeholder="Username" />
										<span class="glyphicon form-control-feedback"></span>
									</div>
								</div>
								<div class="col-md-7">
									<!-- <div class="message hint visible">Your username must be at least 8 characters long.</div> -->
									<div class="message valid">Username is ok.</div>
									<div class="message error">Your username must be at least 4 characters long.</div>
								</div>
							</div>
							<div class="row js-first-name-container">
								<div class="col-md-5 input-container">
									<div class="form-group has-feedback js-first-name-form">
										<form:input type="text" path="firstName" class="form-control js-first-name-input"
											placeholder="First name" />
										<span class="glyphicon form-control-feedback"></span>
									</div>
								</div>
								<div class="col-md-7">
									<!-- <div class="message hint visible">Your username must be at least 8 characters long.</div> -->
									<!-- <div class="message valid">Username is ok.</div> -->
									<div class="message error">Insert your first name</div>
								</div>
							</div>
							<div class="row js-last-name-container">
								<div class="col-md-5 input-container">
									<div class="form-group has-feedback js-last-name-form">
										<form:input type="text" path="lastName" class="form-control js-last-name-input"
											placeholder="Last name" />
										<span class="glyphicon form-control-feedback"></span>
									</div>
								</div>
								<div class="col-md-7">
									<!-- <div class="message hint visible">Your username must be at least 8 characters long.</div> -->
									<!-- <div class="message valid">Username is ok.</div> -->
									<div class="message error">Insert your last name</div>
								</div>
							</div>
						</div>
						<div class="additional-pizzeria additional">
							<div class="row">
								<input type="text" id="maps-autocomplete-input" class="form-control" />
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>