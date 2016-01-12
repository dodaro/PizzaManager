<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="wrapper">
	<div class="panel-group" id="accordion">
		<c:forEach var="b" varStatus="status" items="bookings">

			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion"
							href="${b.number}"> ${b.getPizzeria().getName()}</a>
					</h4>
				</div>
				<div id="${ b.number}" class="panel-collapse collapse">
					<ul class="list-group">
						<c:forEach var="i" varStatus="Istatus" items="${b.getItems()}">
							<li class="list-group-item">${b.itemName(${i.getId()})}
								${i.getCost()}</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="pull-right">
				<button>Take Away </button>
				<button>Deliver</button>
				<%--on click pop-up take data e time --%>
			</div>

		</c:forEach>
	</div>
</div>