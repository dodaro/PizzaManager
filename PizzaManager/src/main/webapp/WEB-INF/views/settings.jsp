<head>
<title>Pizza Manager</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript"
	src="resources/js/signup/signupController.js"></script>
<script type="text/javascript" src="resources/js/signup/signup.js"></script>
<script type="text/javascript" src="resources/js/user/cart.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#confirmEmailButton").on('click', function() {
							var email = $("#emailInpunt").val();
							var password = $("#passwordEmailConfirm").val();
							alert(mail + " " + password);
							var emailsetting = {
								email : email,
								password : password
							}
							$.ajax({
								type : "POST",
								url : "/settings/email",
								data : {
									emailsetting : emailsetting

								},
								success : function(response) {
									window.location = "settings"
								}
							});

						});

						$("#confirmPasswordButton")
								.on(
										'click',
										function() {
											var oldpassword = $(
													"#oldpasswordInput").val();
											var newpassword = $(
													"#newpasswordInput").val();
											var confirmpassword = $(
													"#confirmpasswordInput")
													.val();
											if (newpassword == confirmpassword) {
												var passwordsetting = {
													oldPassword : oldpassword,
													newPassword : newpassword
												}
												$
														.ajax({
															type : "POST",
															url : "/settings/email",
															data : {
																passwordsetting : passwordsetting

															},
															success : function(
																	response) {
																window.location = "settings"
															}
														});
											} else {
												$("#passwordErrorMessage").innerHtml = "Confirm password is different from New Password.";
											}
										});
						
						$("#confirmAddressButton").on('click', function() {
							var email = $("#emailInpunt").val();
							var password = $("#passwordEmailConfirm").val();
							alert(mail + " " + password);
							var emailsetting = {
								email : email,
								password : password
							}
							$.ajax({
								type : "POST",
								url : "/settings/email",
								data : {
									emailsetting : emailsetting

								},
								success : function(response) {
									window.location = "settings"
								}
							});

						});
					});
</script>
</head>

<body>
	<jsp:include page="includes/navbarAccount.jsp"></jsp:include>

	<div id="container">
		<div class="row">
			<jsp:include page="includes/navUserMenu.jsp"></jsp:include>
			<div class="col-xs-9">
				<div id="content" class="wrapper">
					<div class="bubble">
						<div class="row">
							<div class="col-xs-9">

								<div class="row">
									<div class="col-xs-6">
										<label for="emailInput ">Email:</label> <input
											id="emailInpunt" type="text" class="js-email-input" />
									</div>
									<div class="col-xs-3">
										<div class="message hint visible">Insert your email.</div>
										<div class="message valid">Email seems ok.</div>
										<div class="message validating">Validating...</div>
										<div class="message taken">An user with this email
											already exists.</div>
										<div class="message error">Invalid email.</div>
									</div>
								</div>
								<div class="row">
									<label for="passwordEmailConfirm">Confirm Password:</label> <input
										id="passwordEmailConfirm" type="text" />
								</div>
								<div class="row">
									<input id="confirmEmailButton" class="btn btn-default"
										 value="confirm" />
								</div>

							</div>
						</div>
						<div class="row">
							<div class="col-xs-9">
								<div class="row">
									<label for="oldpasswordInput">Password:</label> <input
										type="password" id="oldpasswordInpunt" />
								</div>
								<div class="row">
									<div class="col-xs-6">
										<label for="newpasswordInput"> New Password:</label> <input
											type="password" id="newpasswordInpunt"
											class="js-password-input" />
									</div>
									<div class="col-md-7">
										<div class="message hint visible">Your password must be
											at least 8 characters long.</div>
										<div class="message valid">Password is ok.</div>
										<div class="message error">Your password must be at
											least 8 characters long.</div>
									</div>
								</div>
								<div class="row">
									<label for="confirmpasswordInput"> Confirm Password:</label> <input
										type="password" id="confirmpasswordInpunt" />
									<div id="passwordErrorMessage"></div>
								</div>
								<div class="row">
									<input id="confirmPasswordButton" class="btn btn-default"
										 value="confirm" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-9">

								<div class="row">
									<div class="col-xs-3">
										<label for="streetInput">Street:</label> <input
											id="streetInpunt" type="text" />
									</div>
									<div class="col-xs-3">
										<label for="numberInput">Number:</label>

									</div>
								</div>
								<div class="row">
									<label for="numberInput">Number:</label> <input
										id="streetInpunt" type="text" />
								</div>
								<input id="confirmAddressButton" class="btn btn-default" value="confirm" />

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>




