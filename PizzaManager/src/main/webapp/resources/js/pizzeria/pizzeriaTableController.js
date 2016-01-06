var pizzeriaTableController = function() {

	var selectRow = function($row) {
		var $tablesTable = $row.closest('#pizzeria-table #tables-table');
		$tablesTable.find('.selected').removeClass('selected');
		$row.addClass('selected');
	};

	var fillForm = function(rowData) {
		$('#pizzeria-table #table-number').val(rowData.number);
		$('#pizzeria-table #table-min-seats').val(rowData.minSeats);
		$('#pizzeria-table #table-max-seats').val(rowData.maxSeats);
	};

	return {
		onAddTable : function() {
			console.log("OnAddTable");
		},

		onUpdateTable : function() {
			console.log("OnUpdateTable");
		},

		onDeleteTable : function() {
			console.log("OnDeleteTable");
		},

		onSelectRow : function($row, rowData) {
			selectRow($row);
			fillForm(rowData);
		}
	}
}();