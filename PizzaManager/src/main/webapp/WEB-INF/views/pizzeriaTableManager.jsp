<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="pizzeria-table">
	<div class="row">
		<div class="col-md-7">
			<table id="tables-table" class="display">
				<thead>
					<tr>
						<!-- <th>Table id</th> -->
						<th>Table number</th>
						<th>Minimum seats</th>
						<th>Maximum seats</th>
						<th>Available</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="col-md-5">
			<form class="edit-table-form form-horizontal">
				<div class="form-group">
					<label for="table-number" class="col-sm-offset-1 col-sm-5 control-label">Table number</label>
					<div class="col-sm-5">
						<input type="number" id="table-number" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="table-min-seats" class="col-sm-offset-1  col-sm-5 control-label">Minimum
						seats</label>
					<div class="col-sm-5">
						<input type="number" id="table-min-seats" class="form-control">
					</div>
				</div>
				<div class="form-group">
					<label for="table-max-seats" class="col-sm-offset-1 col-sm-5 control-label">Maximum
						seats</label>
					<div class="col-sm-5">
						<input type="number" id="table-max-seats" class="form-control">
					</div>
				</div>
			</form>
			<div class="buttons-container col-sm-offset-1 col-sm-11">
				<button class="btn btn-primary button-add" disabled>
					<span class="glyphicon glyphicon-plus"></span> Add
				</button>
				<button class="btn btn-success button-update" disabled>
					<span class="glyphicon glyphicon-refresh"></span> Update
				</button>
				<button class="btn btn-danger button-delete" disabled>
					<span class="glyphicon glyphicon-remove"></span> Delete
				</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	pizzeriaTableManager.init();
</script>