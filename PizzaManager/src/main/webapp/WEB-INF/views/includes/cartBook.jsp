<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper">
	<c:if test="${not empty bookings }">
		<div class="panel-group" id="accordion">
			<c:forEach var="b" varStatus="status" items="${bookings}">

				<div class="panel panel-default">
					<div class="panel-heading">

						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#${b.identifier}"> ${b.pizzeria} </a>

						</h4>
					</div>

					<div id="${ b.identifier}" class="panel-collapse collapse bubble">
						<ul class="list-group">
						<li class="list-group-item row"><b
									class="col-xs-3">ItemName </b><b class="col-xs-3">
										N°Item </b><b class="col-xs-3"> Unit Price </b><b
									class="pull-right">Total</b></li>
							<c:forEach var="i" varStatus="Istatus" items="${b.items}">
								<li class="list-group-item row"><b
									class="col-xs-3">${i.itemName} </b><b class="col-xs-3">
										${i.number} </b><b class="col-xs-3"> ${i.getCostLabel()} &#8364</b><b
									class="pull-right">${i.getTotal()} &#8364</b></li>
							</c:forEach>
							<li class="list-group-item"><b>Total Book</b> <b
								class="pull-right">${b.performTotal()} &#8364</b></li>
						</ul>
						<div class="row">
							<div class='col-xs-3'>
								<div class="form-group">
									<div class='input-group date datetimepicker'>
										<input type='text' class="form-control" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="pull-right">
								<button data-pizzeria="${b.pizzeria}"
									class="btn btn-default takeAway">Take Away</button>
								<button data-pizzeria="${b.pizzeria}"
									class="btn btn-default delivery">Deliver</button>
								<%--on click pop-up take data e time --%>
							</div>
						</div>
					</div>

				</div>

			</c:forEach>
		</div>
	</c:if>
</div>