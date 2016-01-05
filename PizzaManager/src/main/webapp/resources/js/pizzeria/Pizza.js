var Pizza=(function(){
	
	//proprietà private non accessibili
	/*var code;
	var name;
	var glutenFree;
	var size;
	var edited;//boolean value
	var ingredientsAdded=new Array();
	var ingredientsRemoved=new Array();

	
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
		
		this.setEdited=function(t){
			edited=t;
		}
		this.getEdited=function(){
			return edited;
		}
		
		this.getIngredientsAdded=function(){
			return ingredientsAdded;
		}
		
		this.getIngredientsRemoved=function(){
			return ingredientsRemoved;
		}
		
		this.toString = function(){
			return name+ " " + size;
		}
	}*/
	function Pizza(settings) {
		this.self = {
				code: "",
				name:"",
			    glutenFree:"",
				size:"",
				edited: false,//boolean value
				ingredientsAdded: new Array(),
				ingredientsRemoved: new Array()
		};

		this.init(settings || {});
	}
	
	Pizza.prototype.init = function(settings) {
		
	}
	
	Pizza.prototype.getCode = function() {
		return this.self.code;
	};
	
	Pizza.prototype.setCode = function(code) {
		this.self.code=code;
	};
	
	
	Pizza.prototype.getName = function() {
		return this.self.name;
	};
	
	Pizza.prototype.setName = function(name) {
		this.self.name=name;
	};
	
	Pizza.prototype.getGlutenFree = function() {
		return this.self.glutenFree;
	};
	
	Pizza.prototype.setGlutenFree = function(glutenFree) {
		this.self.glutenFree=glutenFree;
	};
	
	Pizza.prototype.getSize = function() {
		return this.self.size;
	};
	
	Pizza.prototype.setSize = function(size) {
		this.self.size=size;
	};
	
	Pizza.prototype.getEdited = function() {
		return this.self.edited;
	};
	
	Pizza.prototype.setEdited = function(edited) {
		this.self.edited=edited;
	};
	
	Pizza.prototype.getIngredientsAdded = function() {
		return this.self.ingredientsAdded;
	};
	
	Pizza.prototype.getIngredientsRemoved = function() {
		return this.self.ingredientsRemoved;
	};
	
	Pizza.prototype.toString = function() {
		var tostring= this.self.name +" "+ this.self.size +" "+ this.self.glutenFree+" - code: "+this.self.code+ '\n' +"INGR ADD:";
		if(this.self.ingredientsAdded!=undefined)
			for (var int = 0; int < this.self.ingredientsAdded.length; int++) {
				tostring+=this.self.ingredientsAdded[int].text+", ";
			}
		tostring+="\n"+"INGR REM:";
		if(this.self.ingredientsRemoved!=undefined)
			for (var int = 0; int < this.self.ingredientsRemoved.length; int++) {
				tostring+=this.self.ingredientsRemoved[int].text+", ";
			}
		
		return tostring;
		
	};
	
	Pizza.prototype.toStringIngredientsAdded = function() {
		var tostring="";
		if(this.self.ingredientsAdded!=undefined)
			for (var int = 0; int < this.self.ingredientsAdded.length; int++) {
				tostring+=this.self.ingredientsAdded[int].text;
				if(int<(this.self.ingredientsAdded.length)-1)
					tostring+=', ';
			}
		return tostring;
	};
	
	Pizza.prototype.toStringIngredientsRemoved = function() {
		var tostring="";
		if(this.self.ingredientsRemoved!=undefined)
			for (var int = 0; int < this.self.ingredientsRemoved.length; int++) {
				tostring+=this.self.ingredientsRemoved[int].text;
				if(int<(this.self.ingredientsRemoved.length)-1)
					tostring+=', ';
			}
		return tostring;
	};
	
	return Pizza;//ritorna l'oggetto function Pizza
}());