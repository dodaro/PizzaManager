<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper">


	<b>Cart</b>

	<div class="bubble">
		<c:if test="${not empty pizzaItems}">
			<c:forEach var="p" varStatus="status" items="${pizzaItems}">
				<div class="row">
					<div class="col-xs-2">
						<img class="imgCart" alt="" src="">
					</div>
					<div class="col-xs-4">
						<div class="row">
							<b>${p.pizzaName()}</b>
						</div>
						<div class="row">${p.pizzeriaName()}</div>
					</div>
					<div class="input-group spinner col-xs-3">
						<input type="text" class="form-control" value="${p.getNumber()}">
						<div class="input-group-btn-vertical">
							<button class="btn btn-default" type="button">
								<i class="fa fa-caret-up"></i>
							</button>
							<button class="btn btn-default" type="button">
								<i class="fa fa-caret-down"></i>
							</button>
						</div>

					</div>
					<div class="col-xs-3 pull-right">
						<button id="${p.id }" data-content="cartBook"
							class="btn btn-default" onclick="">Remove</button>
					</div>
				</div>
			</c:forEach>


		</c:if>
		<c:if test="${not empty beverageItems}">
			<c:forEach var="b" varStatus="status" items="${beverageItems}">
				<div class="row">
					<div class="col-xs-2">
						<img class="imgCart" alt="" src="">
					</div>
					<div class="col-xs-4">
						<div class="row">
							<b>${b.beverageName()}</b>
						</div>
						<div class="row">${b.pizzeriaName()}</div>
					</div>
					<div class="input-group spinner col-xs-3">
						<input type="text" class="form-control" value="${b.getNumber()}">
						<div class="input-group-btn-vertical">
							<button class="btn btn-default" type="button">
								<i class="fa fa-caret-up"></i>
							</button>
							<button class="btn btn-default" type="button">
								<i class="fa fa-caret-down"></i>
							</button>
						</div>

					</div>
					<div class="col-xs-3 pull-right">
						<button id="${b.id}" class="btn btn-default"
							onclick="removeItem()">Remove</button>
					</div>
				</div>
			</c:forEach>
		</c:if>

		<div class="pull-right">
			<button class="btn btn-default" onclick="bookCart()">Book</button>
		</div>
	</div>

</div>
