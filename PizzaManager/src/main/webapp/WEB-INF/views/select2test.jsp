<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/select2.js"></script>

<link href="resources/css/bootstrap.css" rel="stylesheet" />
<link href="resources/css/select2.css" rel="stylesheet" />
<link href="resources/css/select2-bootstrap.css" rel="stylesheet" />
</head>

<style>
body {
	margin: 20px 20px 20px 20px;
}
</style>
<body>
	<div id="container">
		<select class="select2 form-control">
			<option value="AL">Alabama</option>
			<option value="WY">Wyoming</option>
		</select>
	</div>

	<script type="text/javascript">
		$(".select2").select2();
	</script>

</body>
</html>