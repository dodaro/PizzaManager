var pizzeriaTable = function() {
	return {
		init : function() {
			$('#tables-table').DataTable({
				"ajax": "/pizzeria/tablesList",
				"columns": [
				    { "data": "number" },
				    { "data": "minSits" }
				]
			});
		}
	}
}();
