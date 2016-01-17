var maps = function() {
	var theMap;

	return {
		createAndShowMarker : function(position, color) {
			return new google.maps.Marker({
				map : theMap,
				position : {
					lat : position.latitude,
					lng : position.longitude
				},
				icon : 'http://maps.google.com/mapfiles/ms/icons/' + color + '-dot.png'
			});
		},

		attachInfoWindowToMarker : function(marker, pizzeria) {
			var infoWindow = new google.maps.InfoWindow({
				position : marker.getPosition(),
				content : pizzeria.name,
				disableAutoPan : true,
				pixelOffset : {
					width : 0,
					height : -35
				}
			});

			marker.addListener('mouseover', function() {
				// Shows the infoWindow.
				infoWindow.setMap(theMap);
			});

			marker.addListener('mouseout', function() {
				infoWindow.close();
			});

			marker.addListener('click', function() {
				window.location = 'pizzeriamainview?id=' + pizzeria.id;
			});
		},

		createAndShowCircle : function(position, radiusInKm) {
			return new google.maps.Circle({
				strokeColor : '#0000FF',
				strokeOpacity : 0.5,
				strokeWeight : 2,
				fillColor : '#0000FF',
				fillOpacity : 0.10,
				map : theMap,
				center : {
					lat : position.latitude,
					lng : position.longitude
				},
				radius : radiusInKm * 1000
			});
		},

		locate : function(onSuccess, onFailure) {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					onSuccess({
						latitude : position.coords.latitude,
						longitude : position.coords.longitude
					});
				}, function() {
					onFailure();
				});
			} else {
				onFailure();
			}
		},

		initMap : function(mapDivId, center, markerColor, zoom) {
			var latLngCenter = {
				lat : center.latitude,
				lng : center.longitude
			};

			theMap = new google.maps.Map(document.getElementById(mapDivId), {
				zoom : zoom
			});

			theMap.setCenter(latLngCenter);
			centerMarker = this.createAndShowMarker(center, markerColor);
		},

		initMapWithError : function(mapDivId) {
			var center = {
				lat : 0,
				lng : 0
			};

			theMap = new google.maps.Map(document.getElementById(mapDivId), {
				zoom : 1,
				center : center
			});

			var infoWindow = new google.maps.InfoWindow({
				map : theMap,
				position : center,
				content : 'Error: The Geolocation service failed.'
			});
		}
	};
}();