<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/select2.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/select2.css" />
</head>

<style>
body {
	margin-left: 10px;
}
</style>
<body>

	<div class="test">
		<select class="js-example-basic-single">
			<option value="AL">Alabama</option>
			<option value="WY">Wyoming</option>
		</select>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".js-example-basic-single").select2({
				width : '100%'
			});
		});
	</script>
</body>
</html>