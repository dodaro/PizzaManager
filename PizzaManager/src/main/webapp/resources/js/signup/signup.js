$(function() {
	$(".js-email-input").on("input propertychange", function() {
		signupController.onEmailChanged($(this).val());
	});

	$(".js-password-input").on("input propertychange", function() {
		signupController.onPasswordChanged($(this).val());
	});

	maps.initMaps('maps-autocomplete-input');
	maps.setOnPlaceChangedListener(function(latitude, longitude) {
		console.log(latitude + " " + longitude);
	});
});
