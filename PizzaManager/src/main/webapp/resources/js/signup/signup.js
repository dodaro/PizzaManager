$(function() {
	$(".js-email-input").on("input propertychange", function() {
		signupController.onEmailChanged($(this).val());
	});

	$(".js-password-input").on("input propertychange", function() {
		signupController.onPasswordChanged($(this).val());
	});

});
