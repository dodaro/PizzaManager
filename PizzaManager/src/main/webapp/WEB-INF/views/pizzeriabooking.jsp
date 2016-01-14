<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div>
		<!--  <button id="demo-modal" type="button" class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal">Launch demo modal</button>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Modal title</h4>
					</div>
					<div class="modal-body">
						<div></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary">Save changes</button>
					</div>
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
					<button id="saveButtonBooking" type="submit" class="btn btn-info button-save">
						<span class="glyphicon glyphicon-floppy-save"></span> Save
					</button>
			</div>
		</div>
	</div>

	</div>

<script type="text/javascript" class="init">
Booking.init();
</script>