<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="pizza-manager">
	<div class="row">
		<div class="col-md-8">
			<table id="pizzas-table" class="display home-table non-selectable">
				<thead>
					<tr>
						<th>Id</th>
						<th>PizzaId</th>
						<th>Pizza</th>
						<th>Size</th>
						<th>Prep Time</th>
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
					<label for="pizza" class="control-label">Pizza</label> <select id="pizza" class="form-control">
						<option />
						<!-- Empty option tag for select2 placeholder -->
						<c:forEach items="${pizzas}" var="pizza">
							<option value="${pizza.id}">${pizza.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="pizza-size" class="control-label">Size</label> <select id="pizza-size"
						class="form-control">
						<option />
						<!-- Empty option tag for select2 placeholder -->
						<c:forEach items="${sizes}" var="size">
							<option value="${size}">${size.string}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="pizza-preparation-time" class="control-label">Preparation time</label>
					<div class='input-group date' id='pizza-preparation-timepicker'>
						<input type="text" id="pizza-preparation-time" class="form-control" placeholder="Pick a time">
						<span class="input-group-addon"> <span class="glyphicon glyphicon-time"></span></span>
					</div>
				</div>
				<div class="checkbox">
					<label class="checkbox-label control-label"> <input type="checkbox"
						id="pizza-gluten-free"> Gluten free
					</label>
				</div>
				<div class="form-group">
					<label for="pizza-price" class="control-label">Price</label> <input type="number"
						id="pizza-price" class="form-control">
				</div>
			</form>
			<div class="buttons-container">
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