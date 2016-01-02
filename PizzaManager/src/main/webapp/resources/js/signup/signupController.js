var signupController = function() {

	var validationDelay = 500;
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
	 * Validates password, just checking for its length.
	 */
	var validatePassword = function(password) {
		return password.length >= 8
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
		var $emailContainer = $(".js-email-container");

		if (data.taken) {
			setValidationError($emailContainer, "taken");
		} else {
			setValidationSuccess($emailContainer);
		}
	};

	/*
	 * Sets the validation state of a form field to "error". Takes as an
	 * argument the class of the message to show.
	 */
	var setValidationError = function($object, messageClass) {
		var $formGroup = $object.find(".form-group");
		$formGroup.removeClass("has-success");
		$formGroup.addClass("has-error");

		var $glyphicon = $object.find(".glyphicon");
		$glyphicon.removeClass("glyphicon-ok");
		$glyphicon.addClass("glyphicon-remove");

		var $currentMessage = $object.find(".message.visible");
		var $newMessage = $object.find(".message." + messageClass);

		$currentMessage.removeClass("visible");
		$newMessage.addClass("visible");

		$object.find(".loader").hide();
	};

	/*
	 * Sets the validation state of the email form field to "success".
	 */
	var setValidationSuccess = function($object) {
		var $formGroup = $object.find(".form-group");
		$formGroup.removeClass("has-error");
		$formGroup.addClass("has-success");

		var $glyphicon = $object.find(".glyphicon");
		$glyphicon.removeClass("glyphicon-remove");
		$glyphicon.addClass("glyphicon-ok");

		var $validMessage = $object.find(".message.valid");
		var $currentMessage = $object.find(".message.visible");
		$currentMessage.removeClass("visible");
		$validMessage.addClass("visible");

		$object.find(".loader").hide();
	};

	/*
	 * Clears the validation state of the email form field. It will show the
	 * loading icon if showLoader is true, or hide it if showLoader is false.
	 */
	var clearValidationState = function($object, showLoader) {
		var $formGroup = $object.find(".form-group");
		$formGroup.removeClass("has-success");
		$formGroup.removeClass("has-error");

		var $glyphicon = $object.find(".glyphicon");
		$glyphicon.removeClass("glyphicon-ok");
		$glyphicon.removeClass("glyphicon-remove");

		var $currentMessage = $object.find(".message.visible");
		$currentMessage.removeClass("visible");

		/*
		 * If the loader is shown, validation is occurring, so the right message
		 * must be shown as well.
		 */
		if (showLoader) {
			$object.find(".loader").show();
			$object.find(".message.validating").addClass("visible");
		} else {
			$object.find(".loader").hide();
			$object.find(".message.hint").addClass("visible");
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
			var $emailContainer = $(".js-email-container");

			if (email.length == 0) {
				clearValidationState($emailContainer, false);
			} else if (validateEmail(email)) {
				clearValidationState($emailContainer, true);
				console.log("Email ok, sending request.")
				emailTimeout = setTimeout(function() {
					sendEmailRequest(email);
				}, validationDelay);
			} else {
				setValidationError($emailContainer, "error");
			}
		},

		/*
		 * Each time the user types in a character into the password input,
		 * check for its validity.
		 */
		onPasswordChanged : function(password) {
			var $passwordContainer = $(".js-password-container");

			console.log("Password changed in " + password);

			if (password.length == 0) {
				clearValidationState($passwordContainer, false);
			} else if (validatePassword(password)) {
				setValidationSuccess($passwordContainer);
			} else {
				setValidationError($passwordContainer, "error");
			}
		}
	};
}();