$(function() {
	$("#inputEmail").on("input propertychange", function() {
		signupController.onEmailChanged($(this).val());
	});

	$("#inputPassword").on("input propertychange", function() {
		signupController.onPasswordChanged($(this).val());
	});

});
