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
						<th>Payment</th>
						<th>Type</th>
						<th>Tables</th>
						<th>Id</th>
					</tr>
					<!-- nei dettagli: typo prenotazione, eventuale tavolo o indirizzo , lista bevande e pizze -->
				</thead>
			</table>
		</div>
		
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="controls">
				<button id="completeButtonLiveRestaurant" type="submit" class="btn btn-default" >Complete</button>
				<button id="restoreButtonLiveRestaurant" type="submit" class="btn btn-default" >Restore</button>
				<button id="removeButtonLiveRestaurant" type="submit" class="btn btn-default">Remove</button>
				<button id="payButtonLiveRestaurant" type="submit" class="btn btn-default">Pay</button>		
			</div> 
		</div>
	</div>

	</div>

<script type="text/javascript" class="init">
LiveRestaurant.init();
</script>