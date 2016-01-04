$(function() {
	$('.nav-pills li').on('click', function(event) {
		event.preventDefault();
		var $clickedElement = $(this);
		var contentToLoad = $clickedElement.data('content');

		$('#content').load(contentToLoad, function() {
			$clickedElement.addClass("active");
		});
	});
});