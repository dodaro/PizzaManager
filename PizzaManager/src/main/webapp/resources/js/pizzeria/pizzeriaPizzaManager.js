pizzeriaPizzaManager = function() {
	var table = function() {
		var $dataTable;

		return {
			selectRow : function($row) {
				var $pizzasTable = $row.closest('#pizza-manager #pizzas-table');
				$pizzasTable.find('.selected').removeClass('selected');
				$row.addClass('selected');
			},

			clearRowSelection : function() {
				$('#pizza-manager #pizzas-table tr.selected').removeClass('selected');
			},

			getSelectedRow : function() {
				var $selectedRow = $('#pizza-manager #pizzas-table tr.selected');

				/*
				 * This method returns undefined to be consistent with the other
				 * getRow methods.
				 */
				if ($selectedRow.length > 0) {
					return $selectedRow;
				}
			},

			getRowData : function($row) {
				return $dataTable.row($row).data();
			},

			isRowSelected : function($row) {
				return $row.hasClass('selected');
			},

			initDataTable : function() {
				$dataTable = $("#pizzas-table").DataTable({
					paging : false,
					searching : false,
					ajax : {
						url : "/pizzeria/pizzasList",
						dataSrc : '',
					},
					columns : [ {
						'data' : 'id'
					}, {
						'data' : 'pizzaId'
					}, {
						'data' : 'pizzaName'
					}, {
						'data' : 'size'
					}, {
						'data' : 'preparationTime'
					}, {
						'data' : 'glutenFree'
					}, {
						'data' : 'price'
					} ],
					/*
					 * Renders "Yes" or "No" instead of "true" or "false" in the
					 * "Available" column.
					 */
					columnDefs : [ {
						/*
						 * Turns the PizzaSize value all lowercase, then changes
						 * only the first letter to uppercase.
						 */
						render : function(data, type, row) {
							return data.toLowerCase().replace(/\b\w/g, function(m) {
								return m.toUpperCase();
							});
						},
						targets : 4
					}, {
						render : function(data, type, row) {
							return data ? 'Yes' : 'No';
						},
						targets : 5
					} ]
				});
			}
		};
	}();

	var form = function() {
		var $pizzaSelect;
		var $sizeSelect;
		var $preparationTimePicker;

		return {
			setButtonEnabled : function(buttonClass, enabled) {
				if (enabled) {
					$('#pizza-manager .' + buttonClass).removeAttr('disabled');
				} else {
					$('#pizza-manager .' + buttonClass).attr('disabled', 'disabled');
				}
			},

			clearForm : function() {
				$('#pizza-manager .edit-pizza-form input').val(null);
				$pizzaSelect.val(null).trigger('change');
				$sizeSelect.val(null).trigger('change');
			},

			fillForm : function(rowData) {
				$pizzaSelect.val(rowData.pizzaId).trigger('change');
				$sizeSelect.val(rowData.size).trigger('change');
				$('#pizza-manager #pizza-preparation-time').val(rowData.preparationTime);
				$('#pizza-manager #pizza-gluten-free').prop('checked', rowData.glutenFree);
				$('#pizza-manager #pizza-price').val(rowData.price);
			},

			isFilled : function() {
				return $pizzaSelect.val().length > 0 && $sizeSelect.val().length > 0
						&& $('#pizza-manager #pizza-preparation-time').val().length > 0
						&& $('#pizza-manager #pizza-price').val().length > 0;
			},

			getFormData : function() {
				return {
					pizzaId : parseInt($pizzaSelect.val()),
					size : $sizeSelect.val(),
					preparationTime : $('#pizza-manager #pizza-preparation-time').val(),
					glutenFree : $('#pizza-manager #pizza-gluten-free').prop('checked'),
					price : parseFloat($('#pizza-manager #pizza-price').val())
				}
			},

			setChangeListener : function(onChange) {
				$pizzaSelect.on('change', function() {
					onChange();
				});

				$sizeSelect.on('change', function() {
					onChange();
				});

				$preparationTimePicker.on('dp.change', function() {
					onChange();
				});

				$('#pizza-manager .edit-pizza-form input').on('input propertychange', function() {
					onChange();
				});

				$('#pizza-manager .edit-pizza-form #pizza-gluten-free').on('change', function() {
					onChange();
				});
			},

			initForm : function() {
				$pizzaSelect = $('.edit-pizza-form #pizza').select2({
					placeholder : "Select a pizza",
				});

				$sizeSelect = $('.edit-pizza-form #pizza-size').select2({
					placeholder : 'Select a size'
				});

				$preparationTimePicker = $('.edit-pizza-form #pizza-preparation-timepicker')
						.datetimepicker({
							format : 'mm:ss',
							useCurrent : false,
						});
			}
		};
	}();

	var canAdd = function() {
		/* Check if all the form fields are filled. */
		if (!form.isFilled()) {
			return false;
		}

		/* TODO - Check if the user is trying to add a row which already exists. */

		return true;
	};

	var canUpdate = function() {
		/* Check if all the form fields are filled. */
		if (!form.isFilled()) {
			return false;
		}

		/*
		 * TODO - Check if the data the user is trying to insert isn't already
		 * in another row.
		 */

		return true;
	};

	var formDataEqualsSelectedRowData = function() {
		var formData = form.getFormData();
		var $selectedRow = table.getSelectedRow();

		if ($selectedRow != undefined) {
			var rowData = table.getRowData($selectedRow);
			return formData.pizzaId == rowData.pizzaId && formData.size == formData.size
					&& formData.preparationTime == rowData.preparationTime
					&& formData.glutenFree == rowData.glutenFree && formData.price == rowData.price;
		}

		return false;
	};

	var getDataForRequest = function() {
		var id;
		var $selectedRow = table.getSelectedRow();

		if ($selectedRow != undefined) {
			id = table.getRowData($selectedRow).id;
		} else {
			id = -1;
		}

		var formData = form.getFormData();

		return {
			id : id,
			pizzaId : formData.pizzaId,
			size : formData.size,
			preparationTime : formData.preparationTime,
			glutenFree : formData.glutenFree,
			price : formData.price
		};
	}

	var sendRequest = function(action, data, onSuccess) {
		console.log(data);
		$.ajax({
			method : 'post',
			url : '/pizzeria/pizza',
			dataType : 'json',
			data : {
				action : action,
				id : data.id,
				pizzaId : data.pizzaId,
				size : data.size,
				preparationTime : data.preparationTime,
				glutenFree : data.glutenFree,
				price : data.price
			},
			success : function(response) {
				console.log(response);
				onSuccess(response);
			}
		});
	};

	var addPizza = function() {
		sendRequest('add', getDataForRequest(), function(response) {
			console.log("ResponseListener");
		});
	}

	var onRowClick = function($row) {
		if (table.isRowSelected($row)) {
			table.clearRowSelection();
			form.clearForm();
			form.setButtonEnabled('button-add', false);
			form.setButtonEnabled('button-update', false);
			form.setButtonEnabled('button-delete', false);
		} else {
			var rowData = table.getRowData($row);
			table.selectRow($row);
			form.fillForm(rowData);
			form.setButtonEnabled('button-add', false);
			form.setButtonEnabled('button-update', false);
			form.setButtonEnabled('button-delete', true);
		}
	}

	var onFormChange = function() {
		var $selectedRow = table.getSelectedRow();

		/*
		 * If a row is selected, check if canUpdate, else check if canAdd.
		 * Enable or disable the buttons accordingly.
		 */
		if ($selectedRow != undefined) {
			form.setButtonEnabled('button-update', canUpdate());
		} else {
			form.setButtonEnabled('button-add', canAdd());
		}

		/*
		 * Delete button is always disabled unless the form AND the selected row
		 * contain the same data.
		 */
		form.setButtonEnabled('button-delete', formDataEqualsSelectedRowData());
	}

	var initListeners = function() {
		$('#pizza-manager #pizzas-table tbody').on('click', 'tr', function() {
			var $row = $(this);
			onRowClick($row);
		});

		form.setChangeListener(onFormChange);

		$('#pizza-manager .button-add').on('click', function() {
			addPizza();
		});

		$('#pizza-manager .button-update').on('click', function() {
			// TODO
		});

		$('#pizza-manager .button-delete').on('click', function() {
			// TODO
		});
	};

	return {
		init : function() {
			table.initDataTable();
			form.initForm();
			initListeners();
		}
	};
}();