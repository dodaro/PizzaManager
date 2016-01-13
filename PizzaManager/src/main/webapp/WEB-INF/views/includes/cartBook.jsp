<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper">
	<c:if test="${not empty bookings }">
		<div class="panel-group" id="accordion">
			<c:forEach var="b" varStatus="status" items="${bookings}">

				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="${b.number}"> ${b.pizzeria}</a>
						</h4>
					</div>
					<div id="${ b.number}" class="panel-collapse collapse">
						<ul class="list-group">
							<c:forEach var="i" varStatus="Istatus" items="${b.items}">
								<li class="list-group-item">${i.itemName}${i.cost}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="pull-right">
					<button id="take_away" class="btn btn-default">Take Away</button>
					<button id="delivery" class="btn btn-default">Deliver</button>
					<%--on click pop-up take data e time --%>
				</div>
			</c:forEach>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Select Booking Date</h4>

					</div>
					<div class="modal-body">
						<div class="col-md-12">
							<div class="row">
								<label for="bookingDate">Booking Date:</label>
								<div class="form-group">
									<div class='input-group date' id='datetimepicker'>
										<input id="bookingDate" type='text' class="form-control" /> <span
											class="input-group-addon"> <span
											class="glyphicon glyphicon-calendar"> </span>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button id="requestBook" type="button" class="btn btn-default" data-dismiss="modal">OK</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->

	</c:if>
</div>