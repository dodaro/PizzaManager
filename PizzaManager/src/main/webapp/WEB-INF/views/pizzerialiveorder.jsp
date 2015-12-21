<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script> 
<!-- <script type="text/javascript" src="resources/js/bootstrap.js"></script> -->
<!-- <script type="text/javascript" src="resources/js/jquery.dataTables.js"></script> -->
<script type="text/javascript" src="resources/js/select2.min.js"></script><!-- select2 richiede jquery -->

<script type="text/javascript">
	/*var data = [{ id: 0, text: 'enhancement' }, { id: 1, text: 'bug' }, { id: 2, text: 'duplicate' }, { id: 3, text: 'invalid' }, { id: 4, text: 'wontfix' }];
	*/
	$(document).ready(function() {
		$(".js-example-basic-single").select2();
	});
</script>


<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/jquery.dataTables.css" /> -->
<!-- <!-- il css datables.css Ã¨ trascurabile in quanto fornisce solo uno stile piÃ¹ bello a tutta la pagina, compresa la tabella -->
<!-- 	bisogna attribuire questo stile solo alla tabella -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/dataTables.css" /> -->
<!-- <link rel="stylesheet" type="text/css" -->
<!-- 	href="resources/css/bootstrap.css" /> -->
<!-- <link rel="stylesheet" type="text/css" href="resources/css/pizzerialiveorder.css" />--> 
<link rel="stylesheet" type="text/css"
	href="resources/css/select2.min.css" />
	<link rel="stylesheet" type="text/css"
	href="resources/css/select2.bootstrap.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	
<select class="js-example-basic-single">
	<option value="AL">Alabama</option>
	<option value="WY">Wyoming</option>
</select>

	<!-- <div id="container">

		<div id="header"></div>

		<div id="wrapper">
			<div class="row">
				<div id="menu" class="col-xs-2">
					<div>ciao</div>
				</div>
				<div id="content" class="col-xs-10">

					<div id="resume">datatable</div>

					<div id="orderInstruments">
						
						<div id="pizzas">
							<div id="pizzaList">
								
									
						
							</div>
							<div id="pizzaControls"></div>
						</div>

						<div id="beverages">
							<div id="beverageList"></div>
							<div id="beverageControls"></div>
						</div>


						<div id="menus">
							<div id="menuList"></div>
							<div id="menuControls"></div>
						</div>
					</div>





				</div>
			</div>
		</div>

		<div id="footer"></div>
	</div>-->
</body>
</html>