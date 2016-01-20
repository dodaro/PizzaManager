<head>
<title>Pizza Manager</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>

<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=${apiKey}&libraries=places"></script>
<script type="text/javascript"
	src="resources/js/signup/signupController.js"></script>
<script type="text/javascript" src="resources/js/maps.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/pageCSS/settings.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<script type="text/javascript" src="resources/js/signup/signup.js"></script>
<script type="text/javascript" src="resources/js/user/settings.js"></script>
</head>

<body>
	<jsp:include page="includes/navbarAccount.jsp"></jsp:include>

	<div id="container">
		<div class="row">
			<jsp:include page="includes/navUserMenu.jsp"></jsp:include>
			<div class="col-xs-9">
				<div id="content" class="container">
					<div class="bubble row">
						<h2>Settings Panel</h2>
						<div class="passwordContainer bubble col-xs-9">
							<div class="row">
								<div class="col-xs-6">
									<input type="password" class="form-control"
										id="oldpasswordInput" placeholder="Password" />
								</div>
							</div>
							<div class="row js-password-container">
								<div class="col-xs-6 input-container">
									<div class="form-group has-feedback js-password-form">
										<input type="password" placeholder="New Password"
											id="newpasswordInput" class="form-control js-password-input" />
										<span class="glyphicon form-control-feedback"></span>
									</div>
								</div>
								<div class="passwordMessage col-xs-6">
									<div class="message hint visible">Your password must be
										at least 8 characters long.</div>
									<div class="message valid">Password is ok.</div>
									<div class="message error">Your password must be at least
										8 characters long.</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6">
									<input type="password" id="confirmpasswordInput"
										placeholder="Confirm Password" class="form-control" />
								</div>
								<div class="col-xs-6">
									<div id="passwordErrorMessage" class="misMatchMessage error">Password
										Mismatch</div>
								</div>
							</div>
							<div class="row">
								<input id="confirmPasswordButton"
									class="pull-right btn btn-default" value="confirm" />
							</div>
						</div>
						<div class="addressContainer bubble col-xs-9">

							<div class="row">
								<div class="col-xs-8">
									<input type="text" id="maps-autocomplete-input"
										class="form-control" />
								</div>
							</div>
							<div class="row">
								<input id="confirmAddressButton"
									class="pull-right btn btn-default" value="confirm" />
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>




