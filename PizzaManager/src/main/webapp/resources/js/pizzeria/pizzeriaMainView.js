var pizzeriaMainView = function() {
	return {
		init : function(pizzeriaLocation) {
			maps.initMap('map', pizzeriaLocation, 'green', 15);
		}
	}
}();