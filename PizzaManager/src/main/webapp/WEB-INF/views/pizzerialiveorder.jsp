<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
     <div class='col-xs-8'>
	     <div class="row">
			 TakeAway:           
              <input type="radio" value="takeAway" name="radio1" checked class="switch-radio1" data-on-label="TakeAway" data-off-label="NO">
             Delivery:
              <input type="radio" value="delivery" name="radio1" class="switch-radio1" data-on-label="Delivery" data-off-label="NO">
             Table:
              <input type="radio" value="table" name="radio1" class="switch-radio1" data-on-label="Table" data-off-label="NO">
          </div>
     
     	<div class="form-group">
		    <label for="bookingNameInput">Nome del prenotante(tradurre in inglese)</label>
		    <input type="text" class="form-control" id="bookingNameInput" placeholder="Name">
	 	</div>
         <div class="form-group">
         	<label for="datetimepicker1">Booking date and hour</label>
             <div class='input-group date' id='datetimepicker1'>
                 <input type='text' class="form-control" />
                 <span class="input-group-addon">
                     <span class="glyphicon glyphicon-calendar"></span>
                 </span>
             </div>
         </div>
         <div class="form-group">
		    <label for="bookingAddresInput">Address to delivery(in caso di delivery booking)</label>
		    <input type="text" class="form-control" id="bookingAddressInput" placeholder="Name">
	 	</div>
	 	<div class="form-group">
	 		<label for="tables">Select table(in caso di prenotazione in pizzeria)</label>
		    <select id="tables" class="form-control">
			  <option>1</option>
			  <option>2</option>
			  <option>3</option>
			  <option>4</option>
			  <option>5</option>
			</select>
	 	</div>
         
         
     </div>
</div>
<hr>
<div class="row">
	<div class="col-xs-8">
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
				<h4>Pizza:</h4>
				<div>
					<select id="pizzaList" class="js-example-basic-single">
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

				<div>
					<div class="pizzaButtons">
						<h5>Gluten:</h5>
						<div id="glutenButtons" class="btn-group buttonPizzaControls" data-toggle="buttons">
							<label class="btn btn-primary active"> <input type="radio" name="options" value="yes"
								id="option1" autocomplete="off" checked> Yes
							</label> <label class="btn btn-primary"> <input type="radio" name="options" value="no"
								id="option2" autocomplete="off"> No
							</label>
						</div>
					</div>
					<div class="pizzaButtons"> 
						<h5>Size:</h5>
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


					<h5>Add ingredients:</h5>
					<select id="addIngredients" class="js-example-basic-multiple" multiple="multiple">
					</select>
					<h5>Remove ingredients:</h5>
					<select id="removeIngredients" class="js-example-basic-multiple" multiple="multiple">
					</select>

					<h5>Number of element:</h5>
					<div class="buttonPizzaControls incrementable">
						<button id="decrementCounterPizza" type="submit" class="btn btn-default">-</button>
						<input id="counterPizza" class="counter" value="1">
						<button id="incrementCounterPizza" type="submit" class="btn btn-default">+</button>
					</div>

					<button id="confermeButtonPizza" type="submit" class="btn btn-default">Add Pizza</button>
					<button id="editButtonPizza" type="submit" class="btn btn-default">Update</button>
					<button id="removeButtonPizza" type="submit" class="btn btn-default">Remove</button>
					<hr>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-8">
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
			<h4>Beverage:</h4>
			<div>
				<select id="beverageList" class="js-example-basic-single">
					<option value="notSelectable">Select Beverage</option>
				</select>
			</div>
			<div id="beverageControls">
				<h5>Number of element:</h5>
				<div class="buttonBeverageControls incrementable">
					<button id="decrementCounterBeverage" type="submit" class="btn btn-default ">-</button>
					<input id="counterBeverage" class="counter" value="1">
					<button id="incrementCounterBeverage" type="submit" class="btn btn-default">+</button>
				</div>

				<button id="confermeButtonBeverage" type="submit" class="btn btn-default">Add Beverage</button>
				<button id="editButtonBeverage" type="submit" class="btn btn-default">Update</button>
				<button id="removeButtonBeverage" type="submit" class="btn btn-default">Remove</button>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<hr>
	<div class="col-xs-8"></div>
	<div class="col-xs-4">
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
LiveOrderTool.init();
</script>