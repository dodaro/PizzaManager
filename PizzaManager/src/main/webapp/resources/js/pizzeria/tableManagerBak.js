var tableManager = function() {

	var table = function() {
		var $dataTable;

		/** Returns the selected row. */
		var getSelectedRow = function() {
			return $('#pizzeria-table #tables-table tr.selected');
		}

		return {
			selectRow : function() {
				var $tablesTable = $row.closest('#pizzeria-table #tables-table');
				$tablesTable.find('.selected').removeClass('selected');
				$row.addClass('selected');

				/*
				 * Only "Update" and "Delete" action are allowed when a row is
				 * selected, so the "Add" button is being disabled.
				 */
				setAddEnabled(false);
				setUpdateDeleteEnabled(true);
			},

			clearRowSelection : function() {
				$('#pizzeria-table #tables-table tr.selected').removeClass('selected');
				setAddEnabled(false);
				setUpdateDeleteEnabled(false);
			},

			initDataTable : function() {
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
			}
		}
	}();

	var form = function() {
		return {

			/** Enables or disables the "Add" button. */
			setAddEnabled : function(enabled) {
				if (enabled) {
					$("#pizzeria-table .button-add").removeAttr("disabled");
				} else {
					$("#pizzeria-table .button-add").attr("disabled", "disabled");
				}
			},

			/** Enables or disables the "Update" and "Delete" buttons. */
			setUpdateDeleteEnabled : function(enabled) {
				if (enabled) {
					$("#pizzeria-table .button-update").removeAttr("disabled");
					$("#pizzeria-table .button-delete").removeAttr("disabled");
				} else {
					$("#pizzeria-table .button-update").attr("disabled", "disabled");
					$("#pizzeria-table .button-delete").attr("disabled", "disabled");
				}
			},

			clearForm : function() {
				$('#pizzeria-table .edit-table-form input').val(null);
			},

			fillForm : function($row) {
				var rowData = $dataTable.row($row).data();
				$('#pizzeria-table #table-number').val(rowData.number);
				$('#pizzeria-table #table-min-seats').val(rowData.minSeats);
				$('#pizzeria-table #table-max-seats').val(rowData.maxSeats);
			}
		}
	}();

	var getTableData = function() {
		var data = new Object();
		var $selectedRow = getSelectedRow();

		if ($selectedRow.length) {
			data.id = $dataTable.row($selectedRow).data().id;
		} else {
			data.id = null;
		}

		data.number = parseInt($('#pizzeria-table #table-number').val());
		data.minSeats = parseInt($('#pizzeria-table #table-min-seats').val());
		data.maxSeats = parseInt($('#pizzeria-table #table-max-seats').val());

		return data;
	}

	/**
	 * Sends a request to the server to add, delete or update a table. The
	 * action parameter can either be 'add', 'update' or 'delete'. The data
	 * parameter is meant to be retrieved by the getTableData function.
	 */
	var sendRequest = function(action, data, onSuccess) {
		$.ajax({
			method : 'post',
			url : '/pizzeria/tables',
			data : {
				action : action,
				id : data.id,
				number : data.number,
				minSeats : data.minSeats,
				maxSeats : data.maxSeats
			},
			success : function(data) {
				onSuccess(data);
			}
		});
	};

	var processResponse = function(data) {
		alert(data);
	}

	/**
	 * Validates the data in the form: returns true if the form contains a valid
	 * table that can be added, false otherwise.
	 * 
	 * If the function finds something wrong, it highlights the wrong form
	 * field.
	 */
	var canAdd = function() {
		/* Check if all the form fields are filled. */
		var $inputs = $('#pizzeria-table .edit-table-form input');
		for (var i = 0; i < $inputs.length; i++) {
			if ($inputs.eq(i).val().length <= 0) {
				return false;
			}
		}

		/* Check if no row is selected. */
		if (getSelectedRow().length > 0) {
			return false;
		}

		/*
		 * Check if the user is trying to add a table with an already existing
		 * number.
		 */
		var newTableNumber = parseInt($('#pizzeria-table #table-number').val());
		if (_.contains($dataTable.column(0).data(), newTableNumber)) {
			return false;
		}

		return true;
	}

	/** What happens when a row of the table is clicked. */
	var onRowClick = function($row) {
		if ($row.hasClass('selected')) {
			table.clearRowSelection();
			form.clearForm();
		} else {
			table.selectRow($row);
			form.fillForm($row)
		}
	}

	/** What happens when an input in a form field changes. */
	var onInputChange = function() {
		setAddEnabled(canAdd());
	}

	var addTable = function() {
		sendRequest('add', getTableData(), function(data) {
			console.log(data);
		});
	};

	var updateTable = function() {
		sendRequest('update', getTableData(), function(data) {
			console.log(data);
		});
	};

	var deleteTable = function() {
		sendRequest('delete', getTableData(), function(data) {
			console.log(data);
		});
	};

	var initListeners = function() {
		$('#pizzeria-table #tables-table tbody').on('click', 'tr', function() {
			var $row = $(this);
			onRowClick($row);
		});

		$('#pizzeria-table .edit-table-form input').on('input propertychange', function() {
			onInputChange();
		});

		$('#pizzeria-table .button-add').on('click', function() {
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
			table.initDataTable();
			initListeners();
		}
	}
}();
