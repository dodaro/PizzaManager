var pizzeriaTable = function() {
	return {
		init : function() {
			$('#tables-table').DataTable({
				paging: false,
				ajax: "/pizzeria/tablesList",
				columns: [
				    { 'data': 'number' },
				    { 'data': 'minSits' }
				]
			});
		}
	}
}();
