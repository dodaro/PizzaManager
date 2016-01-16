$(function() {
	$('.js-email-input').on('input propertychange', function() {
		signupController.onEmailChanged($(this).val());
	});

	$('.js-password-input').on('input propertychange', function() {
		signupController.onPasswordChanged($(this).val());
	});

	$('#signup-additional li').on('click', function(event) {
		signupController.onPillClicked($(this));
		event.preventDefault();
	});

	maps.initMaps('maps-autocomplete-input');
	maps.setOnPlaceChangedListener(function(latitude, longitude) {
		console.log(latitude + " " + longitude);
	});
});