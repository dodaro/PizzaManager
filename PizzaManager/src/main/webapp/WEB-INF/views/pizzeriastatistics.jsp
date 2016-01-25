<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="row">
		<div class="col-xs-8"></div>
		<div class="col-xs-4">
			<div class="form-group">
				<label class="control-label" for="datetimepicker">Select
					Year</label>
				<div class='input-group date' id='datetimepicker'>
					<input type='text' class="form-control" /> <span
						class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#home"
				aria-controls="home" role="tab" data-toggle="tab">Pizza</a></li>
			<li role="presentation"><a href="#profile"
				aria-controls="profile" role="tab" data-toggle="tab">Booking</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">
	
				<div class="row graphicChart">
					<h3>Month</h3>
					<div id="canvasContenitormonthsChart" class="col-xs-10">
						<canvas id="monthsChart" width="730" height="380"></canvas>
					</div>
					<div class="col-xs-2 table-column">
						<div class="form-group">
							<label class="control-label" for="pizzasForMonths">Pizza
								filter</label> <select id="pizzasForMonths"
								class="select-pizza-multiple" multiple="multiple">
								<c:choose>
									<c:when test="${pizzeria.getPizzasPriceList().size() > 0}">
										<c:forEach var="pizza_prezzo"
											items="${pizzeria.getPizzasPriceList()}">
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
							<div id="monthButtons" class="btn-group buttonPizzaControls"
								data-toggle="buttons">
								<label class="btn btn-primary active"> <input
									type="radio" name="options" value="Line" id="option11"
									autocomplete="off" checked> Lin
								</label> <label class="btn btn-primary"> <input type="radio"
									name="options" value="Bar" id="option22" autocomplete="off">
									Bar
								</label> <label class="btn btn-primary"> <input type="radio"
									name="options" value="Radar" id="option33" autocomplete="off">
									Rad
								</label>
							</div>
						</div>
					</div>
				</div>
							<div class="row graphicChart">
					<h3>Year</h3>
					<div id="canvasContenitoryearChart" class="col-xs-10">
						<canvas id="yearChart" width="730" height="380"></canvas>
					</div>
					<div class="col-xs-2 table-column">

						<div class="form-group">
							<label class="control-label" for="pizzasForYears">Pizza
								filter</label> <select id="pizzasForYears" class="select-pizza-multiple"
								multiple="multiple">
								<c:choose>
									<c:when test="${pizzeria.getPizzasPriceList().size() > 0}">
										<c:forEach var="pizza_prezzo"
											items="${pizzeria.getPizzasPriceList()}">
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
							<div id="yearButtons" class="btn-group buttonPizzaControls"
								data-toggle="buttons">
								<label class="btn btn-primary active"> <input
									type="radio" name="options" value="Line" id="option11"
									autocomplete="off" checked> Lin
								</label> <label class="btn btn-primary"> <input type="radio"
									name="options" value="Bar" id="option22" autocomplete="off">
									Bar
								</label> <label class="btn btn-primary"> <input type="radio"
									name="options" value="Radar" id="option33" autocomplete="off">
									Rad
								</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div role="tabpanel" class="tab-pane" id="profile">
				<div class="row graphicChart">
					<h3>Month</h3>
					<div id="canvasContenitorbookingMonthChart" class="col-xs-10">
						<canvas id="bookingMonthChart"></canvas>
					</div>
					<div class="col-xs-2 table-column">
						<div class="form-group">
							<div id="bookingMonthChart-legend" class="chart-legend"></div>
						</div>
					</div>
				</div>
				<div class="row graphicChart">
					<h3>Year</h3>
					<div id="canvasContenitorbookingYearChart" class="col-xs-10">
						<canvas id="bookingYearChart"></canvas>
					</div>
					<div class="col-xs-2 table-column">
						<div class="form-group">
							<div id="bookingYearChart-legend" class="chart-legend"></div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
Statistics.init();
</script>