maps = function() {
	var autocomplete;

	return {
		initMaps : function(inputId) {
			autocomplete = new google.maps.places.Autocomplete((document.getElementById(inputId)),
					{
						types : [ 'geocode' ]
					});
		},

		setOnPlaceChangedListener : function(onPlaceChanged) {
			autocomplete.addListener('place_changed', function() {
				var place = autocomplete.getPlace();
				
				var latitude = place.geometry.location.lat();
				var longitude = place.geometry.location.lng();

				onPlaceChanged(latitude, longitude);
			});
		}
	};
}();