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
						url : "/pizzeria/pizzas",
						dataSrc : '',
					},
					columns : [ {
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
					} ]
				});
			}
		};
	}();

	var form = function() {
		return {
			clearForm : function() {
				$('#pizza-manager .edit-pizza-form input').val(null);
			},

			fillForm : function(rowData) {
				$('#pizza-manager #pizza').val("a");
				$('#pizza-manager #pizza-size').val("a");
				$('#pizza-manager #pizza-preparation-time').val("a");
				$('#pizza-manager #pizza-gluten-free').prop('checked', true);
				$('#pizza-manager #pizza-price').val("a");
			},

			initForm : function() {

			}
		};
	}();

	var onRowClick = function($row) {
		if (table.isRowSelected($row)) {
			table.clearRowSelection();
			form.clearForm();
			// form.setAddButtonEnabled(false);
			// form.setUpdateButtonEnabled(false);
			// form.setDeleteButtonEnabled(false);
		} else {
			var rowData = table.getRowData($row);
			table.selectRow($row);
			form.fillForm(rowData);
			// form.setAddButtonEnabled(false);
			// form.setUpdateButtonEnabled(false);
			// form.setDeleteButtonEnabled(true);
		}

		// form.setNumberFieldHighlighted(false);
		// form.setSeatsFieldsHighlighted(false);
	}

	var initListeners = function() {
		$('#pizza-manager #pizzas-table tbody').on('click', 'tr', function() {
			var $row = $(this);
			onRowClick($row);
		});

		$('#pizza-manager .edit-pizza-form input').on('input propertychange', function() {
			// TODO
		});

		$('#pizza-manager .button-add').on('click', function() {
			// TODO
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
			initListeners();
		}
	};
}();