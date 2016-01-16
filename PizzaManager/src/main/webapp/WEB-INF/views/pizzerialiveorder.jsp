<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="live-order-tool">
	<div class="row booking-data">
		<div class="col-md-4 left-col">
			<div class="form-group">
				<label for="bookingUserInput">User</label> <input type="text" class="form-control"
					id="bookingUserInput" placeholder="User">
			</div>
			<div class="form-group">
				<label for="bookingNameInput">Name (if it's not a User)</label> <input type="text"
					class="form-control" id="bookingNameInput" placeholder="Name">
			</div>
			<div class="form-group">
				<label for="datetimepicker1">Booking date and hour</label>
				<div class='input-group date' id='datetimepicker1'>
					<input type='text' class="form-control" /> <span class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>
		</div>
		<div class="col-md-4 middle-col">
			<label>Address to delivery (if delivery)</label>
			<div class="form-group">
				<input type="text" class="form-control address" id="bookingCityInput" placeholder="City">
			</div>
			<div class="form-group">
				<input type="text" class="form-control address" id="bookingStreetInput" placeholder="Street">
			</div>
			<div class="form-group">
				<input type="text" class="form-control address" id="bookingNumberInput" placeholder="Number">
			</div>
			<div class="form-group">
				<select id="tables" class="js-example-basic-multiple" multiple="multiple">
					<c:choose>
							<c:when test="${pizzeria.getTables().size() > 0}">
								<c:forEach var="table" items="${pizzeria.getTables()}">
									<option value="${table.getId()}">N: ${table.getNumber()} - Min:${table.getMinSeats()} - Max:${table.getMaxSeats()}</option>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<div>There are no pizzas into the database.</div>
							</c:otherwise>
					</c:choose>
				</select>
			</div>
		</div>
		<div class="col-md-4 right-col">
			<div class="form-group">
				<div>
					<label for="">TakeAway</label>
				</div>
				<input type="radio" value="takeAway" name="radio1" checked id="takeaway-switch"
					class="switch-radio1 form-control" data-on-label="TakeAway" data-off-label="NO">
			</div>
			<div class="form-group">
				<div>
					<label for="delivery-switch">Delivery</label>
				</div>
				<input type="radio" value="delivery" name="radio1" id="delivery-switch"
					class="switch-radio1 form-control" data-on-label="Delivery" data-off-label="NO">
			</div>
			<div class="form-group">
				<div>
					<label for="table-switch">Table</label>
				</div>
				<input type="radio" value="table" id="table-switch" name="radio1"
					class="switch-radio1 form-control" data-on-label="Table" data-off-label="NO">
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-xs-8 table-column">
			<table id="resumeTablePizza" class="display">
				<thead>
					<tr>
						<th></th>
						<th>Pizza</th>
						<th>GlutenFree</th>
						<th>Size</th>
						<th>Number</th>
						<th>Id</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="col-xs-4">
			<div id="pizzas" class="itemControls">
				<div id="pizzaControls">
					<div class="form-group">
						<label for="pizzaList">Pizza</label> <select id="pizzaList" class="js-example-basic-single">
							<option value="notSelectable">Select Pizza</option>
							<c:choose>
								<c:when test="${pizzeria.getPizzasPriceList().size() > 0}">
									<c:forEach var="pizza_prezzo" items="${pizzeria.getPizzasPriceList()}">
										<option value="${pizza_prezzo.getPizza().getId()}">${pizza_prezzo.getPizza().getName()}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div>There are no pizzas into the database.</div>
								</c:otherwise>
							</c:choose>
						</select>
					</div>
					<div class="form-group">
						<div class="pizzaButtons">
							<label>Gluten</label>
							<div id="glutenButtons" class="btn-group buttonPizzaControls" data-toggle="buttons">
								<label class="btn btn-primary active"> <input type="radio" name="options"
									value="yes" id="option1" autocomplete="off" checked> Yes
								</label> <label class="btn btn-primary"> <input type="radio" name="options" value="no"
									id="option2" autocomplete="off"> No
								</label>
							</div>
						</div>
						<div class="pizzaButtons">
							<label>Size</label>
							<div id="sizeButtons" class="btn-group buttonPizzaControls" data-toggle="buttons">
								<label class="btn btn-primary active"> <input type="radio" name="options" value="s"
									id="option11" autocomplete="off" checked> S
								</label> <label class="btn btn-primary"> <input type="radio" name="options" value="m"
									id="option22" autocomplete="off"> M
								</label> <label class="btn btn-primary"> <input type="radio" name="options" value="l"
									id="option33" autocomplete="off"> L
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="addIngredients">Add ingredients</label> <select id="addIngredients"
							class="js-example-basic-multiple" multiple="multiple">
						</select>
					</div>
					<div class="form-group">
						<label for="removeIngredients">Remove ingredients</label> <select id="removeIngredients"
							class="js-example-basic-multiple" multiple="multiple">
						</select>
					</div>
					<div class="form-group">
						<label>Number of elements</label>
						<div class="buttonPizzaControls incrementable">
							<button id="decrementCounterPizza" type="submit" class="btn btn-default">-</button>
							<input id="counterPizza" class="counter form-control" value="1">
							<button id="incrementCounterPizza" type="submit" class="btn btn-default">+</button>
						</div>
					</div>
					<div class="buttons-container">
						<button id="confermeButtonPizza" type="submit" class="btn btn-primary button-add">
							<span class="glyphicon glyphicon-plus"></span> Add
						</button>
						<button id="editButtonPizza" type="submit" class="btn btn-success button-update">
							<span class="glyphicon glyphicon-refresh"></span> Update
						</button>
						<button id="removeButtonPizza" type="submit" class="btn btn-danger button-delete">
							<span class="glyphicon glyphicon-remove"></span> Delete
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-8 table-column">
			<table id="resumeTableBeverage" class="display">
				<thead>
					<tr>
						<th>Beverage</th>
						<th>Name</th>
						<th>Container</th>
						<th>Size</th>
						<th>Number</th>
						<th>Id</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="col-xs-4">
			<div id="beverages" class="itemControls">
				<div class="form-group">
					<label for="beverageList">Beverage</label>
					<div>
						<select id="beverageList" class="js-example-basic-single">
							<option value="notSelectable">Select Beverage</option>
						</select>
					</div>
				</div>
				<div id="beverageControls">
					<div class="form-group">
						<label>Number of elements</label>
						<div class="buttonBeverageControls incrementable">
							<button id="decrementCounterBeverage" type="submit" class="btn btn-default ">-</button>
							<input id="counterBeverage" class="counter form-control" value="1">
							<button id="incrementCounterBeverage" type="submit" class="btn btn-default">+</button>
						</div>
					</div>
					<div class="buttons-container">
						<button id="confermeButtonBeverage" type="submit" class="btn btn-primary button-add">
							<span class="glyphicon glyphicon-plus"></span> Add
						</button>
						<button id="editButtonBeverage" type="submit" class="btn btn-success button-update">
							<span class="glyphicon glyphicon-refresh"></span> Update
						</button>
						<button id="removeButtonBeverage" type="submit" class="btn btn-danger button-delete">
							<span class="glyphicon glyphicon-remove"></span> Delete
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<button id="confermeOrder" type="submit" class="btn btn-default">Complete</button>
	</div>
</div>
<div id="myModal" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Error</h4>
			</div>
			<div class="modal-body">
				<p>Select pizza or beverage, please.</p>
			</div>
			<div class="modal-footer"></div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script type="text/javascript">
	console.log("vengo chiamato")
	LiveOrderTool.init();
</script>