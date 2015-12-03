var signupController = function() {

	var validationDelay = 750;
	var emailTimeout;

	/*
	 * Provides a simple regex to validate email syntax. It doesn't try to be
	 * overly smart and it just checks for syntax.
	 */
	var validateEmail = function(email) {
		if (email.length < 5) {
			return false;
		}

		var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		return regex.test(email);
	};

	/*
	 * Sends an AJAX request to the server to check if the email provided has
	 * already been taken.
	 */
	var sendEmailRequest = function(email) {
		console.log("Sending request for email " + email);
		$.ajax({
			type : "get",
			url : "signup/emailTaken",
			data : {
				email : email
			},
			success : function(data) {
				console.log("Response success.");
				processEmailResponse(data);
			},
			dataType : "json"
		});
	};

	/*
	 * Processes the response of the server after the sendEmailRequest method.
	 */
	var processEmailResponse = function(data) {
		$inputEmailContainer = $("#inputEmailContainer");

		if (data.taken) {
			setValidationError($inputEmailContainer);
		} else {
			setValidationSuccess($inputEmailContainer);
		}
	};

	/*
	 * Sets the validation state of the email form field to "error".
	 */
	var setValidationError = function($object) {
		$object.removeClass("has-success");
		$object.addClass("has-error");

		$glyphicon = $object.find(".glyphicon");
		$glyphicon.removeClass("glyphicon-ok");
		$glyphicon.addClass("glyphicon-remove");

		$object.find(".loader").hide();
	};

	/*
	 * Sets the validation state of the email form field to "success".
	 */
	var setValidationSuccess = function($object) {
		$object.removeClass("has-error");
		$object.addClass("has-success");

		$glyphicon = $object.find(".glyphicon");
		$glyphicon.removeClass("glyphicon-remove");
		$glyphicon.addClass("glyphicon-ok");

		$object.find(".loader").hide();
	};

	/*
	 * Clears the validation state of the email form field. It will show the
	 * loading icon if showLoader is true, or hide it if showLoader is false.
	 */
	var clearValidationState = function($object, showLoader) {
		$object.removeClass("has-success");
		$object.removeClass("has-error");

		$glyphicon = $object.find(".glyphicon");
		$glyphicon.removeClass("glyphicon-ok");
		$glyphicon.removeClass("glyphicon-remove");

		if (showLoader) {
			$object.find(".loader").show();
		} else {
			$object.find(".loader").hide();
		}
	}

	return {
		/*
		 * Each time the user types a character into the email input, check for
		 * its validity. Then, wait for the user to type in something else. If
		 * the user doesn't type anything for some time (validation delay) fire
		 * a request to validate the email again server-side and to check if the
		 * email has already been taken.
		 */
		onEmailChanged : function(email) {
			clearTimeout(emailTimeout);
			$inputEmailContainer = $("#inputEmailContainer");

			if (email.length == 0) {
				clearValidationState($inputEmailContainer, true);
			} else if (validateEmail(email)) {
				clearValidationState($inputEmailContainer, true);
				console.log("Email ok.")
				emailTimeout = setTimeout(function() {
					sendEmailRequest(email);
				}, validationDelay);
			} else {
				setValidationError($inputEmailContainer);
			}
		},

		/*
		 * Each time the user types in a character into the password input,
		 * check for its validity.
		 */
		onPasswordChanged : function(password) {
			console.log("Password changed in " + password);
		}
	};
}();