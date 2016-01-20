<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Pizza Manager</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-switch.js"></script>
<script type="text/javascript" src="resources/js/user/cart.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="resources/css/common.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/pageCSS/cart.css" />
<body>
	<jsp:include page="includes/navbarAccount.jsp"></jsp:include>

	<div id="container">
		<div class="row">
			<jsp:include page="includes/navUserMenu.jsp"></jsp:include>
			<div class="col-xs-9">
				<div class="wrapper">
					<c:if test="${not empty cart.items }">
						<div id="content" class="cartContainer">
							<c:forEach var="i" varStatus="stat" items="${cart.items}">
								<div class="row itemDisplay">
									<div class="col-xs-2">
										<img class="imgCart" src="" alt="" />
									</div>
									<div class="col-xs-2">
										<b>${i.itemName}</b>
									</div>
									<div class="col-xs-2">
										<b>${i.pizzeria}</b>
									</div>
									<div class="col-xs-2">
										<input class="form-control number-control" data-id="${i.id}"
											type="number" value="${i.number}">
									</div>
									<div class="col-xs-2">
										<div class="pull-right">
											<b>${i.getCostLabel()} &#8364</b>
										</div>
									</div>
									<div class="col-xs-2">
										<input class="btn btn-default removeButton" type="button"
											value="remove" data-id="${i.id}">
									</div>
								</div>
							</c:forEach>
							<div class="row">
								<input id="bookCart" class="btn btn-default pull-right" type="submit"
									value="Confirm" />
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

