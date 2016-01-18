var Statistics = function(){
	Chart.defaults.global.showTooltips=false;
	
	var dataPerYearFromServer;
	var dataPerMonthFromServer;
	var rgb;
	var labelsYear=["January", "February", "March", "April", "May", "June", "July","August","September","October","November","December"];
	var labelsMonth=["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
	                 "16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"];

	var options={
		    ///Boolean - Whether grid lines are shown across the chart
		    scaleShowGridLines : true,
		    //String - Colour of the grid lines
		    scaleGridLineColor : "rgba(0,0,0,.05)",
		    //Number - Width of the grid lines
		    scaleGridLineWidth : 1,
		    //Boolean - Whether to show horizontal lines (except X axis)
		    scaleShowHorizontalLines: true,
		    //Boolean - Whether to show vertical lines (except Y axis)
		    scaleShowVerticalLines: true,
		    //Boolean - Whether the line is curved between points
		    bezierCurve : true,
		    //Number - Tension of the bezier curve between points
		    bezierCurveTension : 0.4,
		    //Boolean - Whether to show a dot for each point
		    pointDot : true,
		    //Number - Radius of each point dot in pixels
		    pointDotRadius : 4,
		    //Number - Pixel width of point dot stroke
		    pointDotStrokeWidth : 1,
		    //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
		    pointHitDetectionRadius : 20,
		    //Boolean - Whether to show a stroke for datasets
		    datasetStroke : true,
		    //Number - Pixel width of dataset stroke
		    datasetStrokeWidth : 2,
		    //Boolean - Whether to fill the dataset with a colour
		    datasetFill : true
	};
	
	var initRgba = function(){
		var rgbSet=new Array();
		rgbSet.push([200,200,200]);
		rgbSet.push([150,0,150]);
		rgbSet.push([100,200,200]);
		return rgbSet;
	}
	
	
	var initCharts = function(idChart,actionString, data,typeChart){
		console.log("vengo chiamat")
		var ctx= $("#"+idChart).get(0).getContext("2d");
		var chart;
		
		if(actionString=="yearAction"){
			
			switch ($('#yearButtons .active > input').val()) {
			case "Radar":
				chart = new Chart(ctx).Radar(data, options);				
				break;
			case "Bar":
				chart = new Chart(ctx).Bar(data, options);							
				break;
			case "Line":
				chart = new Chart(ctx).Line(data, options);			
				break;
			default:
				break;
			}
		}
		else if(actionString=="monthAction"){
			switch ($('#monthButtons .active > input').val()) {
			case "Radar":
				chart = new Chart(ctx).Radar(data, options);				
				break;
			case "Bar":
				chart = new Chart(ctx).Bar(data, options);							
				break;
			case "Line":
				chart = new Chart(ctx).Line(data, options);			
				break;
			default:
				break;
			}		
		}
			
		$("#"+idChart+'-legend').html(chart.generateLegend());
	}
	var initControls = function(){
		
		rgb=initRgba();
		var d = new Date();
		var month = d.getMonth();
		var day = d.getDate();
		var year = d.getFullYear();

		$(".select-pizza-multiple").select2();
		
		$('#datetimepickerYears').datetimepicker({
			format: 'YYYY',
		});
		$("#datetimepickerYears").data("DateTimePicker").date(new Date(year, month, day, 00, 01));
		
		$('#datetimepickerMonths').datetimepicker({
			format: 'MM/YYYY',
		});
		$("#datetimepickerMonths").data("DateTimePicker").date(new Date(year, month, day, 00, 01));
		
		sendAction("yearAction",$("#datetimepickerYears").data("DateTimePicker").date().format('YYYY'));
		sendAction("monthAction",$("#datetimepickerMonths").data("DateTimePicker").date().format('MM/YYYY'));
	}
	
	var initListeners = function(){
		
		$("#datetimepickerYears").on("dp.change", function(e) {
			sendAction("yearAction",$("#datetimepickerYears").data("DateTimePicker").date().format('YYYY'));
	    });
		
		$("#datetimepickerMonths").on("dp.change", function(e) {
			sendAction("monthAction",$("#datetimepickerMonths").data("DateTimePicker").date().format('MM/YYYY'));
		});
		
		$('#pizzasForYears.select-pizza-multiple').on('change',function(e) {
			filterChart("yearAction");
		});
		
		$('#pizzasForMonths.select-pizza-multiple').on('change',function(e) {
			filterChart("monthAction");
		});
		
		$('#yearButtons').on('change',function(e) {
			console.log($('#yearButtons .active > input').val());
			filterChart("yearAction");
		});
		
		$('#monthButtons').on('change',function(e) {
			console.log($('#monthButtons .active > input').val());
			filterChart("monthAction");
		});
	}
	
	var filterChart = function(actionString){
		if(actionString=="yearAction"){
			
			var filterPizzas=$('#pizzasForYears.select-pizza-multiple').val();
			var data=createData("yearAction",rgb);
			
			if(filterPizzas==undefined){
				initCharts("yearChart","yearAction",data);
				return;
			}
			
			var dataFiltered=new Object();
			dataFiltered.labels=data.labels;
			dataFiltered.datasets=new Array();
			
			
			for (var int = 0; int < filterPizzas.length; int++) {
				var pizza=filterPizzas[int]
				for (var int2 = 0; int2 < data.datasets.length; int2++) {
					if(data.datasets[int2].label==pizza)
						dataFiltered.datasets.push(data.datasets[int2]);
				}
			}
			console.log(dataFiltered)
			//var typeChart=$('#yearButtons .active > input').val();
			//console.log(typeChart);
			initCharts("yearChart","yearAction",dataFiltered);
		}
		else if(actionString=="monthAction"){
			
			var filterPizzas=$('#pizzasForMonths.select-pizza-multiple').val();
			var data=createData("monthAction",rgb);
			
			if(filterPizzas==undefined){
				initCharts("monthsChart","monthAction",data);
				return;
			}
			
			var dataFiltered=new Object();
			dataFiltered.labels=data.labels;
			dataFiltered.datasets=new Array();
			
			for (var int = 0; int < filterPizzas.length; int++) {
				var pizza=filterPizzas[int]
				for (var int2 = 0; int2 < data.datasets.length; int2++) {
					if(data.datasets[int2].label==pizza)
						dataFiltered.datasets.push(data.datasets[int2]);
				}
			}
			console.log(dataFiltered)
			//var typeChart=$('#monthButtons .active > input').val();
			//console.log(typeChart);
			initCharts("monthsChart","monthAction",dataFiltered);
		}
	}
	
	var sendAction = function(actionString, dateString){
		
		$.ajax({
			url : "/pizzeriastatisticsAjax",
			type : 'GET',
			data : {
				action : actionString,
				date : dateString
			},
			success : function(data) {
				if(actionString=="yearAction"){
					dataPerYearFromServer=data;
					filterChart("yearAction");
					//initCharts("yearsChart",actionString,createData(actionString,rgb));
					console.log(dataPerYearFromServer)
				}
				else if(actionString=="monthAction"){
					dataPerMonthFromServer=data;
					filterChart("monthAction");
					//initCharts("monthsChart",actionString,createData(actionString,rgb));
					console.log(dataPerMonthFromServer);
				}
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});
	}
	
	var createData= function(actionString, rgb){
		
		var labels,dataFromServer,data;
		if(actionString=="yearAction"){
			labels=labelsYear;
			dataFromServer=dataPerYearFromServer;
		}
		else if(actionString=="monthAction"){
			labels=labelsMonth;
			dataFromServer=dataPerMonthFromServer;
		}
		
		data=new Object();
		data.labels=labels;
		data.datasets=new Array();
		
		var keys = [];
		for(var k in dataFromServer) keys.push(k);
		
		for (var int = 0; int < keys.length; int++) {
			var object=new Object();
			object.label=keys[int];
			object.fillColor= "rgba("+rgb[int][0]+","+rgb[int][1]+","+rgb[int][2]+",0.2)";
			object.strokeColor= "rgba("+rgb[int][0]+","+rgb[int][1]+","+rgb[int][2]+",1)";
			object.pointColor= "rgba("+rgb[int][0]+","+rgb[int][1]+","+rgb[int][2]+",1)";
			object.pointStrokeColor= "#fff";
			object.pointHighlightFill= "#fff";
			object.pointHighlightStroke= "rgba("+rgb[int][0]+","+rgb[int][1]+","+rgb[int][2]+",1)";
			object.data= dataFromServer[keys[int]];
			data.datasets.push(object);
		}
		
		return data;
	}
	
	return {
		init : function() {
			initControls();
			initListeners();
		}
	}
	
}();