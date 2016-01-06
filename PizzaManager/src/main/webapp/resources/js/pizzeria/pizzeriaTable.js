var pizzeriaTable = function() {
	return {
		init : function() {
			$('#tables-table').DataTable({
				paging: false,
				ajax: {
					url:"/pizzeria/tablesList",
					dataSrc : '',
				},
				columns: [
				    { 'data': 'number' },
				    { 'data': 'minSits' },
				    { 'data': 'maxSits' }
				]
			});
		}
	}
}();
