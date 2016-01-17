<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div>

		<!--<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div>ciao</div>
				</div>
			</div>
		</div>-->

	<div class="row">
		<div class="col-xs-12">
			<table id="bookingTable" class="display" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th></th>
						<th>User</th>
						<th>Name</th>
						<th>Date</th>
						<th>Time</th>
						<th>Paid</th>
						<th>Id</th>
						<th>Type</th>
						<th>N.Tables</th>
						<th>Address</th>
						<th>Bill</th>
					</tr>
				</thead>
			</table>
		</div>
		
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="buttons-container controls">
					<button id="confermeButtonBooking" type="submit" class="btn btn-primary button-add">
						<span class="glyphicon glyphicon-plus"></span> Confirm
					</button>
					<button id="editButtonBooking" type="submit" class="btn btn-success button-update">
						<span class="glyphicon glyphicon-refresh"></span> Update
					</button>
					<button id="removeButtonBooking" type="submit" class="btn btn-danger button-delete">
						<span class="glyphicon glyphicon-remove"></span> Delete
					</button>
			</div>
		</div>
	</div>

	</div>

<script type="text/javascript" class="init">
Booking.init();
</script>