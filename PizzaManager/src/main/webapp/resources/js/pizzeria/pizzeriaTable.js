var pizzeriaTable = function() {

	var $dataTable;

	var getSelectedRow = function() {
		return $('#pizzeria-table #tables-table tr.selected');
	}

	var selectRow = function($row) {
		var $tablesTable = $row.closest('#pizzeria-table #tables-table');
		$tablesTable.find('.selected').removeClass('selected');
		$row.addClass('selected');
	};

	var fillForm = function($row) {
		var rowData = $dataTable.row($row).data();
		$('#pizzeria-table #table-number').val(rowData.number);
		$('#pizzeria-table #table-min-seats').val(rowData.minSeats);
		$('#pizzeria-table #table-max-seats').val(rowData.maxSeats);
	};

	var getFormData = function() {
		return {
			number : $('#pizzeria-table #table-number').val(),
			minSeats : $('#pizzeria-table #table-min-seats').val(),
			maxSeats : $('#pizzeria-table #table-max-seats').val()
		}
	}

	var sendRequest = function(action, tableData, onSuccess) {
		$.ajax({
			method : 'post',
			url : '/pizzeria/tables',
			data : {
				action : action,
				number : tableData.number,
				minSeats : tableData.minSeats,
				maxSeats : tableData.maxSeats
			},
			success : function(data) {
				onSuccess(data);
			}
		});
	};

	var processResponse = function(data) {
		alert(data);
	}

	var addTable = function() {
		sendRequest('add', getFormData(), function(data) {
			console.log(data);
		});
	};

	var updateTable = function() {
		sendRequest('update', getFormData(), function(data) {
			console.log(data);
		});
	};

	var deleteTable = function() {
		sendRequest('delete', getFormData(), function(data) {
			console.log(data);
		});
	};

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
			var $row = $(this);
			selectRow($row);
			fillForm($row)
		});

		$('#pizzeria-table .button-add').on('click', function() {
			var $selectedRow = getSelectedRow();
			var tableNumbers = $dataTable.column(0).data();
			console.log(_.contains(tableNumbers, 6));
			addTable();
		});

		$('#pizzeria-table .button-update').on('click', function() {
			updateTable();
		});
		$('#pizzeria-table .button-delete').on('click', function() {
			deleteTable();
		});
	};

	return {
		init : function() {
			initDataTable();
			initListeners();
		}
	}
}();
