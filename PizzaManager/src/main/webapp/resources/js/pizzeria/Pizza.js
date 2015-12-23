var Pizza=(function(){
	
	//proprietà private non accessibili
	var code;
	var name;
	var glutenFree;
	var size;

	
	function Pizza(settings){
		
		this.setCode=function(t){
			code=t;
		}
		this.getCode=function(){
			return code;
		}
		
		
		this.setSize=function(t){
			size=t;
		}
		this.getSize=function(){
			return size;
		}
		
		this.setGlutenFree=function(t){
			glutenFree=t;
		}
		this.getGlutenFree=function(){
			return glutenFree;
		}
		
		this.setName=function(t){
			name=t;
		}
		this.getName=function(){
			return name;
		}
	}
	
	return Pizza;//ritorna l'oggetto function Pizza
}());