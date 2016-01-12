<script type="text/javascript">

$(document).ready(function(){
    $( "#button" ).button();
    $( "#button" ).prop("disabled",true);
    
    $( "#passwordInput").on('change',function(){
    	var currentString = $( "#passwordInput").val();
    	 if(currentString.length > 5 ){
    		 $("#passwordButton").prop("disabled",false);
    	 }
    });
    
    $( "#emailInput").on('change',function(){
    	var currentString = $( "#emailInput").val();
    	// la stringa deve contenere @
    	 if(currentString.length > 5){
    		 $("#emailButton").prop("disabled",false);
    	 }
    });
    
    $( "#addressInput").on('change',function(){
    	var currentString = $( "#addressInput").val(); 
    	 if(currentString.length > 5 ){
    		 $("#addressButton").prop("disabled",false);
    	 }
    });
});

function modifyPassword() {
	
	var modifiedPassword = $('#passwordInput').val();
	alert(modifiedPassword)
}

</script>
<div class="wrapper">
	<div class="bubble">
		<div class="row">
			<div class="col-xs-9">
				<b>Modify Password</b>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<input id="passwordInput" type="password" />
			</div>
			<div class="col-xs-3">
				<button id="passwordButton" onclick="modifyPassword()">Confirm</button>
			</div>
		</div>
	</div>


	<div class="bubble">
		<div class="row">
			<div class="col-xs-9">
				<b>Change Email</b>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<input id="emailInput" type="text" />
			</div>
			<div class="col-xs-3">
				<button id="emailButton" onclick="">Confirm</button>
			</div>
		</div>
	</div>



	<div class="bubble">
		<div class="row">
			<div class="col-xs-9">
				<b>Edit Address</b>
			</div>
		</div>
		<div class=row>
			<div class="col-xs-6">
				<input id="addressInput" type="text" />
			</div>
			<div class="col-xs-3">
				<button id="addressButton" onclick="">Confirm</button>
			</div>
		</div>
	</div>
</div>