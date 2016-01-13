<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="beverage-manager">
	<div class="row">
		<div class="col-md-8">
			<table id="beverages-table" class="display home-table non-selectable">
				<thead>
					<tr>
						<th>Name</th>
						<th>Brand</th>
						<th>Container</th>
						<th>Size</th>
						<th>Type</th>
						<th>Price</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="col-md-4">
			<form class="edit-beverage-form home-form">
				<div class="form-group">
					<label for="beverage" class="control-label">Beverage</label> <select id="beverage"
						class="form-control">
						<option />
						<!-- Empty option tag for select2 placeholder -->
						<c:forEach items="${beverages}" var="beverage">
							<option value="${beverage.id}">${beverage.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="pizza-price" class="control-label">Price</label>
					<div class="input-group">
						<input type="number" id="pizza-price" class="form-control"><span
							class="input-group-addon">&euro;</span>
					</div>
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
	pizzeriaBeverageManager.init();
</script>