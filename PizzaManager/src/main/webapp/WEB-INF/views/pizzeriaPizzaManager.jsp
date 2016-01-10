<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="pizza-manager">
	<div class="row">
		<div class="col-md-8">
			<table id="pizzas-table" class="display home-table non-selectable">
				<thead>
					<tr>
						<th>PizzaId</th>
						<th>Pizza</th>
						<th>Size</th>
						<th>Preparation Time</th>
						<th>Gluten Free</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="col-md-4">
			<form class="edit-pizza-form home-form">
				<div class="form-group">
					<label for="pizza" class="control-label">Pizza</label> <input type="text" id="pizza"
						class="form-control">
				</div>
				<div class="form-group">
					<label for="pizza-size" class="control-label">Size</label> <input type="text" id="pizza-size"
						class="form-control">
				</div>
				<div class="form-group">
					<label for="pizza-preparation-time" class="control-label">Preparation time</label> <input
						type="text" id="pizza-preparation-time" class="form-control">
				</div>
				<div class="checkbox">
					<label> <input type="checkbox" id="pizza-gluten-free"> Gluten free
					</label>
				</div>
				<div class="form-group">
					<label for="pizza-price" class="control-label">Price</label> <input type="text"
						id="pizza-price" class="form-control">
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
	pizzeriaPizzaManager.init();
</script>