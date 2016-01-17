var homeUser = function() {

	var showPizzeriasMarkers = function(pizzerias) {
		for (var i = 0; i < pizzerias.length; i++) {
			var marker = maps.createAndShowMarker({
				latitude : pizzerias[i].latitude,
				longitude : pizzerias[i].longitude
			}, 'green');

			maps.attachInfoWindowToMarker(marker, pizzerias[i]);
		}
	};

	var showCircle = function(center, radius) {
		maps.createAndShowCircle(center, radius)
	}

	var requestPizzerias = function(center, radius) {
		$.ajax({
			method : 'post',
			url : '/pizzeria/nearby',
			dataType : 'json',
			data : {
				latitude : center.latitude,
				longitude : center.longitude,
				radius : radius
			},

			success : function(response) {
				if (response.success) {
					showPizzeriasMarkers(response.pizzerias);
					showCircle(center, response.radius);
				}
			}
		});
	};

	return {
		init : function() {
			maps.locate(function(location) {
				// onSuccess
				maps.initMap('map', location, 'red', 12);
				requestPizzerias(location, 5);
			}, function() {
				// onFailure
				maps.initMapWithError('map');
			});
		}
	};
}();

$(function() {
	homeUser.init();
});