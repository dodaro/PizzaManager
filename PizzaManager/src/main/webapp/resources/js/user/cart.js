function removeItem() {
	var $idItem = $(this).attr('id');

	$.ajax({
		url : "/removeItem",
		type : 'POST',
		data : {
			id : $(this).closest("div").id
		},
		success : function() {
			data
		}
	});

}
function bookCart() {
	var $contentToLoad = $(this).data('content');
	$('#content').load(contenToLoad);
	$.ajax({
		url : "/bookCart",
		type : 'POST',
		data :{
			
		},
		success : function() {
			data
		}
	});

}