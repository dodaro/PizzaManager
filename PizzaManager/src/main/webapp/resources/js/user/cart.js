$(function() {
	$('.spinner .btn:first-of-type').on('click', function(event) {
		event.preventDefault();
		var $clickedElement = $(this);
		var $inputField = $clickedElement.closest("div").siblings("input");
		if ($inputField.val() < 99)
			$inputField.val(parseInt($inputField.val(), 10) + 1);
	});

	$('.spinner .btn:last-of-type').on('click', function(event) {
		event.preventDefault();
		var $clickedElement = $(this);
		var $inputField = $clickedElement.closest("input");
		if ($inputField.val() > 1)
			$inputField.val(parseInt($inputField.val(), 10) - 1);
	});
});
function removeItem() {
	var $idItem = $(this).attr('id');
	
	$.ajax({
		url : "/removeItem",
		type : 'GET',
		data : {
			id : $idItem
		}
	});
	
}
function bookCart() {
	var $contentToLoad= $(this).data('content');
	$('#content').load(contenToLoad);
	$.ajax({
		url : "/bookCart",
		type : 'GET',
	});

}