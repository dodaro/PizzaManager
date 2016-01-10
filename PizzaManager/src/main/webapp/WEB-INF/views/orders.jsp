<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$("#removeGly").on('click', function() {

	});
</script>


<div class="row">
	<div class="card">
		<div class="col-xs-3">
			<b>OrderNumber</b>
		</div>
		<div class="col-xs-3">
			<b>Time</b>
		</div>
		<div class="col-xs-3">
			<b>Active</b>
		</div>
	</div>
	<c:if test="${not empty ordersList}">
		<c:forEach var="f" items="${OrderList}">
			<div class="card">
				<div class="col-xs-3">
					<b>${f}</b>
				</div>
				<div class="col-xs-3"></div>
				<div class="col-xs-3"></div>
			</div>
		</c:forEach>
	</c:if>



</div>