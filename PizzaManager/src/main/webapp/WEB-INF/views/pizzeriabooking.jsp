<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.js"></script>
<script type="text/javascript" src="resources/js/jquery.dataTables.js"></script>

<script type="text/javascript" class="init">
	function format(d) {
		//console.log(d[0]);
		// `d` is the original data object for the row
		return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'
				+ '<tr>' + '<td>Full name:</td>' + '<td>' + d.orderItems[0].cost + '</td>'
				+ '</tr>' + '<tr>' + '<td>Extension number:</td>' + '<td>' + "milla" + '</td>'
				+ '</tr>' + '<tr>' + '<td>Extra info:</td>'
				+ '<td>And any further details here (images etc)...</td>' + '</tr>' + '</table>';
	}

	$(document).ready(function() {
		var table = $('#example').DataTable({
			ajax : {
				url : '/pizzeriabookingAjax',
				dataSrc : ''
			},
			columns : [ {
				"className" : 'details-control',
				"orderable" : false,
				"data" : null,
				"defaultContent" : ''
			}, {
				"data" : "date"
			}, {
				"data" : "time"
			}, {
				"data" : "id"
			}, {
				"data" : "confirmed"
			}, {
				"data" : "payment"
			}, {
				"data" : "priority"
			}, {
				"data" : "pizzeria"
			}, {
				"data" : "person"
			} ],
			order : [ [ 1, 'asc' ] ]
		});

		$('#example tbody').on('click', 'td.details-control', function() {
			console.log("chiamata");
			console.log($(this));
			//il closest mi da il primo parent che matcha con 'tr'
			//$(this) in questo caso è il td su cui si ha cliccato
			var tr = $(this).closest('tr');
			console.log(tr);
			var row = table.row(tr);
			//la funzione row() mi ritorna tutta la riga relativo all'elemento tr
			console.log(row.data());

			//se applico row.child() ottengo tutti i figli diretti di row;
			//se applico row.child(codeHTML) aggiungo il codice alla riga;
			if (row.child.isShown()) {
				// This row is already open - close it
				row.child.hide();
				tr.removeClass('shown');
			} else {
				// Open this row
				row.child(format(row.data())).show();
				tr.addClass('shown');
			}
		});

		$('#example tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				table.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
		});

		$('#demo-modal').click(function() {
			var dataRow = table.row('.selected').data();
			alert(dataRow.time);

		});

		var data;
		$.ajax({
			url : "/pizzeriabookingAjax",
			type : 'GET',
			success : function(data) {
				//alert(data);
				console.log(data);
			},
			error : function(data, status, er) {
				alert("error: " + data + " status: " + status + " er:" + er);
			}
		});

	});
</script>

<link rel="stylesheet" type="text/css" href="resources/css/jquery.dataTables.css" />
<!-- il css datables.css è trascurabile in quanto fornisce solo uno stile più bello a tutta la pagina, compresa la tabella
	bisogna attribuire questo stile solo alla tabella -->
<link rel="stylesheet" type="text/css" href="resources/css/dataTables.css" />
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<button id="demo-modal" type="button" class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal">Launch demo modal</button>

		<!-- Modal -->
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
		</div>


		<table id="example" class="display" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th></th>
					<th>Date</th>
					<th>Time</th>
					<th>Id</th>
					<th>Confirmed</th>
					<th>Person</th>
					<th>Payment</th>
					<th>Priority</th>
					<th>Pizzeria</th>
				</tr>
			</thead>
			<tfoot>
				<tr>
					<th></th>
					<th>Date</th>
					<th>Time</th>
					<th>Id</th>
					<th>Confirmed</th>
					<th>Person</th>
					<th>Payment</th>
					<th>Priority</th>
					<th>Pizzeria</th>
				</tr>
			</tfoot>
			<!--<tbody>
        <c:choose>
			<c:when test="${bookings.size() > 0}">
				<c:forEach var="booking" items="${bookings}">
				<tr>
					<td class=" details-control"></td>
					<td>${booking.getDate()}</td>
					<td>${booking.getTime()}</td>
					<td>${booking.getConfirmed()}</td>
					<td>${booking.getPerson()}</td>
					<td>${booking.getPayment()}</td>
					<td>${booking.getPriority()}</td>
					<td>${booking.getPizzeria()}</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div>There are no bookings into the database.</div>
			</c:otherwise>
		</c:choose>
        
        	<tr>
                <td>Tiger Nixon</td>
                <td>System Architect</td>
                <td>Edinburgh</td>
                <td>61</td>
                <td>2011/04/25</td>
                <td>$320,800</td>
            </tr>
            
        </tbody>-->
		</table>

	</div>

</body>
</html>