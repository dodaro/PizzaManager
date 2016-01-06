var pizzeriaTable = function() {

	var $dataTable;

	var initDataTable = function() {
		$dataTable = $('#pizzeria-table #tables-table').DataTable({
			paging : false,
			searching : false,
			ajax : {
				url : "/pizzeria/tablesList",
				dataSrc : '',
			},
			columns : [ {
				'data' : 'number'
			}, {
				'data' : 'minSeats'
			}, {
				'data' : 'maxSeats'
			} ]
		});
	};

	var initListeners = function() {
		$('#pizzeria-table #tables-table tbody').on('click', 'tr', function() {
			$row = $(this);
			pizzeriaTableController.onSelectRow($row, $dataTable.row($row).data());
		});

		$('#pizzeria-table .button-add').on('click', function() {
			pizzeriaTableController.onAddTable();
		});

		$('#pizzeria-table .button-update').on('click', function() {
			pizzeriaTableController.onUpdateTable();
		});
		$('#pizzeria-table .button-delete').on('click', function() {
			pizzeriaTableController.onDeleteTable();
		});
	};

	return {
		init : function() {
			initDataTable();
			initListeners();
		}
	}
}();
