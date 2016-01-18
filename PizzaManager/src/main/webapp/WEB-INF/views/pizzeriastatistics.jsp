<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="row graphicChart">
		<div id="canvasContenitoryearChart" class="col-xs-10">
			<canvas id="yearChart" width="730" height="380"></canvas>
		</div>
		<div class="col-xs-2 table-column">
			<div class="form-group">
				<label class="control-label" for="datetimepickerYears">Select Year</label>
				<div class='input-group date' id='datetimepickerYears'>
					<input type='text' class="form-control" /> <span class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>
			<div class="form-group">
				<select id="pizzasForYears" class="select-pizza-multiple" multiple="multiple">
				<c:choose>
					<c:when test="${pizzeria.getPizzasPriceList().size() > 0}">
						<c:forEach var="pizza_prezzo" items="${pizzeria.getPizzasPriceList()}">
							<option value="${pizza_prezzo.getPizza().getName()}">${pizza_prezzo.getPizza().getName()}</option>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div>There are no pizzas into the database.</div>
					</c:otherwise>
				</c:choose>
				</select>
			</div>
			<div class="form-group">
				<div id="yearChart-legend" class="chart-legend"></div>
			</div>
			<div class="chartButtons">
				<label></label>
				<div id="yearButtons" class="btn-group buttonPizzaControls" data-toggle="buttons">
					<label class="btn btn-primary active"> <input type="radio" name="options" value="Line"
						id="option11" autocomplete="off" checked> Line
					</label> <label class="btn btn-primary"> <input type="radio" name="options" value="Bar"
						id="option22" autocomplete="off"> Bar
					</label> <label class="btn btn-primary"> <input type="radio" name="options" value="Radar"
						id="option33" autocomplete="off"> Rad
					</label>
				</div>
			</div>
		</div>
	</div>
	<div class="row graphicChart">
		<div id="canvasContenitormonthsChart" class="col-xs-10">
			<canvas id="monthsChart" width="730" height="380"></canvas>
		</div>
		<div class="col-xs-2 table-column">
			<div class="form-group">
				<label class="control-label" for="datetimepickerMonths">Select Month</label>
				<div class='input-group date' id='datetimepickerMonths'>
					<input type='text' class="form-control" /> <span class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>
			<div class="form-group">
				<select id="pizzasForMonths" class="select-pizza-multiple" multiple="multiple">
				<c:choose>
					<c:when test="${pizzeria.getPizzasPriceList().size() > 0}">
						<c:forEach var="pizza_prezzo" items="${pizzeria.getPizzasPriceList()}">
							<option value="${pizza_prezzo.getPizza().getName()}">${pizza_prezzo.getPizza().getName()}</option>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div>There are no pizzas into the database.</div>
					</c:otherwise>
				</c:choose>
				</select>
			</div>
			<div class="form-group">
				<div id="monthsChart-legend" class="chart-legend"></div>
			</div>
			<div class="chartButtons">
				<label></label>
				<div id="monthButtons" class="btn-group buttonPizzaControls" data-toggle="buttons">
					<label class="btn btn-primary active"> <input type="radio" name="options" value="Line"
						id="option11" autocomplete="off" checked> Line
					</label> <label class="btn btn-primary"> <input type="radio" name="options" value="Bar"
						id="option22" autocomplete="off"> Bar
					</label> <label class="btn btn-primary"> <input type="radio" name="options" value="Radar"
						id="option33" autocomplete="off"> Rad
					</label>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
Statistics.init();
</script>