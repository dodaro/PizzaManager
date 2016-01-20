$(document).ready(function() {
	$('input.removeButton').on('click', function() {
		var clickedElement = $(this);
		var id = clickedElement.data('id');
		alert(id);
		$.ajax({
			type : "POST",
			url : "/cart/removeItem",
			data : {
				id : id
			},
			success : function(response) {
				console.log(response);
				window.location = "cart";
			}
		});
	});
	$('#bookCart').on('click', function() {
		var clickedElement = $(this);
		var items = "";
		$(".number-control").each(function(index, element) {
			var id = $(this).data('id');
			var number = $(this).val();
			var item = id + "-" + number;
			items = items + ";" + item;

		});
		alert(items);

		var itemToBook = items.substring(1, items.length);

		console.log(itemToBook);

		$.ajax({
			type : "POST",
			url : "/bookCart",
			data : {
				itemToBook : itemToBook,
			},
			success : function(response) {
				console.log(response);
				window.location = 'userBooking'

			}
		})
	});
});