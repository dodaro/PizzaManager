pizzeriaBeverageManager = function() {
	var table = function() {
		var $dataTable;

		return {
			initDataTable : function() {
				$dataTable = $("#beverages-table").DataTable({
					paging : false,
					searching : false,
					ajax : {
						url : "/pizzeria/beveragesList",
						dataSrc : '',
					},
					columns : [ {
						'data' : 'id'
					}, {
						'data' : 'beverageId'
					}, {
						'data' : 'name'
					}, {
						'data' : 'brand'
					}, {
						'data' : 'container'
					}, {
						'data' : 'size'
					}, {
						'data' : 'type'
					}, {
						'data' : 'price'
					} ]
				});
			}
		};
	}();

	var form = function() {

	};

	return {
		init : function() {
			table.initDataTable();
		}
	};
}();