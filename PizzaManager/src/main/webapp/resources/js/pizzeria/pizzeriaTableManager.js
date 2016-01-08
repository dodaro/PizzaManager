pizzeriaTableManager = function() {

	table = function() {
		var $dataTable;

		return {
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
			},

			isRowSelected : function($row) {
				return $row.hasClass('selected');
			},

			getSelectedRow : function() {
				return $('#pizzeria-table #tables-table tr.selected');
			},

			containsTableNumber : function(tableNumber) {
				return _.contains($dataTable.column(0).data(), tableNumber);
			},

			getRowData : function($row) {
				return $dataTable.row($row).data();
			},

			selectRow : function($row) {
				var $tablesTable = $row.closest('#pizzeria-table #tables-table');
				$tablesTable.find('.selected').removeClass('selected');
				$row.addClass('selected');
			},

			clearRowSelection : function() {
				$('#pizzeria-table #tables-table tr.selected').removeClass('selected');
			}
		}
	}();

	form = function() {
		var setHighlighted = function($element, highlighted) {
			if (highlighted) {
				$element.closest('.form-group').addClass('has-error');
			} else {
				$element.closest('.form-group').removeClass('has-error');
			}
		}

		return {
			setAddButtonEnabled : function(enabled) {
				if (enabled) {
					$("#pizzeria-table .button-add").removeAttr("disabled");
				} else {
					$("#pizzeria-table .button-add").attr("disabled", "disabled");
				}
			},

			setUpdateDeleteButtonsEnabled : function(enabled) {
				if (enabled) {
					$("#pizzeria-table .button-update").removeAttr("disabled");
					$("#pizzeria-table .button-delete").removeAttr("disabled");
				} else {
					$("#pizzeria-table .button-update").attr("disabled", "disabled");
					$("#pizzeria-table .button-delete").attr("disabled", "disabled");
				}
			},

			setNumberFieldHighlighted : function(highlighted) {
				var $number = $('#pizzeria-table #table-number');

				setHighlighted($number, highlighted);
			},

			setSeatsFieldsHighlighted : function(highlighted) {
				var $minSeats = $('#pizzeria-table #table-min-seats');
				var $maxSeats = $('#pizzeria-table #table-max-seats');

				setHighlighted($minSeats, highlighted);
				setHighlighted($maxSeats, highlighted);
			},

			clearForm : function() {
				$('#pizzeria-table .edit-table-form input').val(null);
			},

			fillForm : function(rowData) {
				$('#pizzeria-table #table-number').val(rowData.number);
				$('#pizzeria-table #table-min-seats').val(rowData.minSeats);
				$('#pizzeria-table #table-max-seats').val(rowData.maxSeats);
			},

			getFormData : function() {
				return {
					number : parseInt($('#pizzeria-table #table-number').val()),
					minSeats : parseInt($('#pizzeria-table #table-min-seats').val()),
					maxSeats : parseInt($('#pizzeria-table #table-max-seats').val())
				}
			},

			isFilled : function() {
				var $inputs = $('#pizzeria-table .edit-table-form input');
				for (var i = 0; i < $inputs.length; i++) {
					if ($inputs.eq(i).val().length <= 0) {
						return false;
					}
				}

				return true;
			}
		}
	}();

	/**
	 * Validates the data in the form: returns true if the form contains a valid
	 * table that can be added, false otherwise.
	 * 
	 * This function also manages highlighting of wrong fields, if any.
	 */
	var canAdd = function() {
		/* Check if no row is selected. */
		if (table.getSelectedRow().length > 0) {
			return false;
		}

		/* Check if all the form fields are filled. */
		if (!form.isFilled()) {
			return false;
		}

		/*
		 * Check if minSeats <= maxSeats and vice versa. Form data can safely be
		 * retrieved since at this point we're sure the form is filled.
		 */
		var formData = form.getFormData();
		if (formData.minSeats > formData.maxSeats) {
			form.setSeatsFieldsHighlighted(true);
			return false;
		}

		/*
		 * Check if the user is trying to add a table with an already existing
		 * number.
		 */
		var newTableNumber = form.getFormData().number;
		if (table.containsTableNumber(newTableNumber)) {
			form.setNumberFieldHighlighted(true);
			return false;
		}

		/* Form is ok, remove existing highlighting */
		form.setNumberFieldHighlighted(false);
		form.setSeatsFieldsHighlighted(false);
		return true;
	}

	var getDataForRequest = function() {
		var data = new Object();
		var $selectedRow = table.getSelectedRow();

		if ($selectedRow.length) {
			data.id = table.getRowData($selectedRow).id;
		} else {
			data.id = null;
		}

		var formData = form.getFormData();

		data.number = formData.number;
		data.minSeats = formData.minSeats;
		data.maxSeats = formData.maxSeats;

		return data;
	}

	var processResponse = function(response) {
		console.log("Response: " + response);
	}

	/**
	 * Sends a request to the server to add, delete or update a table.
	 * 
	 * The action parameter can either be 'add', 'update' or 'delete'.
	 * 
	 * The data parameter is meant to be retrieved by the getDataForRequest
	 * function.
	 * 
	 * The onSuccess parameter is a callback function meant to be called when
	 * the response is successfully received.
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
			success : function(response) {
				onSuccess(response);
			}
		});
	};

	var onRowClick = function($row) {
		if (table.isRowSelected($row)) {
			table.clearRowSelection();
			form.clearForm();
			form.setAddButtonEnabled(false);
			form.setUpdateDeleteButtonsEnabled(false);
		} else {
			var rowData = table.getRowData($row);
			table.selectRow($row);
			form.fillForm(rowData);
			form.setAddButtonEnabled(false);
			form.setUpdateDeleteButtonsEnabled(true);
		}
	}

	var onInputChange = function() {
		form.setAddButtonEnabled(canAdd());
	}

	var addTable = function() {
		sendRequest('add', getDataForRequest(), function(data) {
			console.log(data);
		});
	}

	var updateTable = function() {
		sendRequest('update', getDataForRequest(), function(data) {
			console.log(data);
		});
	}

	var deleteTable = function() {
		sendRequest('delete', getDataForRequest(), function(data) {
			console.log(data);
		});
	}

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
	}

	return {
		init : function() {
			table.initDataTable();
			initListeners();
		}
	}
}();