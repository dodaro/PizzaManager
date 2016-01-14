<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div>

	<div class="row">
		<div class="col-xs-12">
			<table id="liveRestaurantTable" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th></th>
						<th>User</th>
						<th>Name</th>
						<th>Data</th>
						<th>Time</th>
						<th>Payment</th>
						<th>Bill</th>
						<th>Id</th>
					</tr>
					<!-- nei dettagli: type prenotazione, eventuale tavolo o indirizzo , lista bevande e pizze -->
				</thead>
			</table>
		</div>
		
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="buttons-container controls">
					<button id="completeButtonLiveRestaurant" type="submit" class="btn btn-primary button-add">
						<span class="glyphicon glyphicon-plus"></span> Collected
					</button>
					<button id="restoreButtonLiveRestaurant" type="submit" class="btn btn-success button-update">
						<span class="glyphicon glyphicon-refresh"></span> Send back
					</button>
					<button id="removeButtonLiveRestaurant" type="submit" class="btn btn-danger button-delete">
						<span class="glyphicon glyphicon-remove"></span> Delete
					</button>
					<button id="payButtonLiveRestaurant" type="submit" class="btn btn-warning button-pay">
						<span class="glyphicon glyphicon-eur"></span> Pay
					</button>
			</div>
		</div>
	</div>

	</div>

<script type="text/javascript" class="init">
LiveRestaurant.init();
</script>