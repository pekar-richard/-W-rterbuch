



// variable with information for search formular/ red/gelb/grun

function L(data){console.log(data);}

var el = null;

window.onload = function(){ 
	 
	}

function Get(url,params){
	
	var http = new XMLHttpRequest();
	http.open('POST', url, true);

	//Send the proper header information along with the request
	http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	        //alert(http.responseText);
	    }
	}
	http.send(params);
}

function getJson(url, params, callback){
	
	var http = new XMLHttpRequest();
	http.open('POST', url, true);

	http.setRequestHeader("Content-Type", "application/json");
	
	http.onreadystatechange = function() {
	    if(http.readyState == 4 && http.status == 200) {
	    	var obj = JSON.parse(http.responseText);  	       
	        callback(obj); 
	    }
	}
	
	http.send(JSON.stringify(params));	
}

function createWortRow(obj){
	
	var hiddeSk = ((obj.status==2 || obj.status==3)?'hidden':'');
	
	var block = '<div class="wort wort_'+obj.status+'" draggable="true" onclick="wortClick(event)" ondragstart="drag(event)" id="wort-'+obj.id+'" data-id="'+obj.id+'">'+
	'<span id="wortDE-'+obj.id+'">'+obj.wort_DE+'</span><br>'+
	'<span class="'+hiddeSk+'" id="wortSK-'+obj.id+'" >'+obj.wort_SK+'</span><br>'+
	'</div>';
	
	return createElementFromHTML(
		'<div class="row" id="row-'+obj.id+'">'+
			'<div class="col col-3.5 sm-3.5 lg-1 column" ondrop="drop(event)" onclick="columnClick(event)" ondragover="allowDropEvent(event)" data-id="'+obj.id+'" data-status="1">'+((obj.status==1)?block:'')+'</div>'+
			'<div class="col col-3.5 sm-3.5 lg-1 column" onclick="columnClick(event)" ondrop="drop(event)" ondragover="allowDropEvent(event)" data-id="'+obj.id+'" data-status="2">'+((obj.status==2)?block:'')+'</div>'+
			'<div class="col col-3.5 sm-3.5 lg-1 column" onclick="columnClick(event)" ondrop="drop(event)" ondragover="allowDropEvent(event)" data-id="'+obj.id+'" data-status="3">'+((obj.status==3)?block:'')+'</div>'+
		'</div>');    
}

function createElementFromHTML(htmlString) {
	  var div = document.createElement('div');
	  div.innerHTML = htmlString.trim();

	  // Change this to div.childNodes to support multiple top-level nodes
	  return div.firstChild; 
	}
	
function formSubmitHeader(){
		
	var Wort_de = document.getElementById("Wort_DE_header").value;
    var Wort_sk = document.getElementById("Wort_SK_header").value;
    var data = {wort_DE:Wort_de,wort_SK:Wort_sk,status:1};
    console.log(data);
    getJson("/Woerterbuch/add",data, function(obj){
    	var el = createWortRow(obj);
	    document.getElementById("body").prepend(el);
    });
    
	return true;

}

function formSubmitMain(){
	
   	var Wort_de = document.getElementById("Wort_DE_main").value;
    var Wort_sk = document.getElementById("Wort_SK_main").value;
    var data = {wort_DE:Wort_de,wort_SK:Wort_sk,status:1};
    console.log(data);
    getJson("/Woerterbuch/add",data, function(obj){
    	var el = createWortRow(obj);
	    document.getElementById("body").prepend(el);
    });
	return true;	
	
}

function formSubmitsearch(){
    var Wort_DE = document.getElementById("Wort_DE_search_main").value;
    var data = {wort_DE:Wort_DE,status:1};
    
   	//getSearchJson("/Woerterbuch/search",data);
   	
   	getJson("/Woerterbuch/searchalle", data, function(items){
   		document.getElementById("body").innerHTML="";
   		for (j = 0; j < items.length; j++) {
   			var el = createWortRow(items[j]);
   			document.getElementById("body").appendChild(el);
    	}
    });
   	
   	
	return true;
}

function formSubmitsearchRYG(){

    var Wort_DE = document.getElementById("Wort_DE_search_ryg").value;
    var messageStatus = document.getElementById("statusvaluergg").value;
    var data = {wort_DE:Wort_DE,status:messageStatus};
    
   	getJson("/Woerterbuch/search", data, function(items){
   		document.getElementById("body").innerHTML="";
   		for (j = 0; j < items.length; j++) {
   			var el = createWortRow(items[j]);
   			document.getElementById("body").appendChild(el);
    	}
    });
   	
	return true;
}

function wortClick(ev) {
	
	
		ev.stopPropagation();
		
		var prevEl = el;
		
		if (prevEl!= null){
			prevEl.classList.remove("myDiv");	
		}
		
		
		if(ev.target.classList.contains("wort")) {
			el=ev.target;
		} else {
			el = ev.target.parentElement;
		}
		
		
		if (el === prevEl) {
			el = null;			
		} else {
			el.classList.add("myDiv");
		}
}

	
function columnClick(ev){
	ev.stopPropagation();
	 if(el!= null && el.dataset.id == ev.target.dataset.id){	
		 ev.target.appendChild(el);

		 saveWort(el, ev.target);
		 statusWort(el, ev.target);
	 }	
}


function drag(ev) {
  el=ev.target;
}

function allowDropEvent(ev) {
	
	if(ev.target != el && el.dataset.id == ev.target.dataset.id){	
		ev.preventDefault();
	}	
}

function statusWort(source, target){
	
	 var text_SK =  document.getElementById("wortSK-"+source.dataset.id);
	
	  if(target.dataset.status==1){
		  el.style.backgroundColor  = "red";
		  text_SK.classList.remove("hidden");
	  }
	  else if (target.dataset.status==2){  
		  el.style.backgroundColor  = "yellow";
		  text_SK.classList.add("hidden");
	  }
	  else{
		  el.style.backgroundColor  = "green";
		  text_SK.classList.add("hidden");
	  }
	
}

function drop(ev) {
 ev.preventDefault();
 saveWort(el, ev.target);
 
}

function saveWort(source, target){
		  
	target.appendChild(source);
	  Get("/Woerterbuch/save","id="+ source.dataset.id +"+&status="+ target.dataset.status);
	  
	  statusWort(source, target);
	
	
}

 // Delete Wort
function allowDropDelEvent(ev){
	 
	ev.preventDefault();	 
 }
 
function dropdelete(ev) {
	
	if(el===null){return;}
	
	var wortDE = document.getElementById("wortDE-"+el.dataset.id);
	
	  var x = confirm("Wollen Sie das Wort '"+ wortDE.innerHTML +"' richtig löschen?");
	  
	  if (x){
		  ev.preventDefault();
		  Get("/Woerterbuch/delete","id="+ el.dataset.id);	
			//Get the child element node
		 	 var child =  document.getElementById("row-"+el.dataset.id);

		 	 // Remove the child element from the document
		  	child.parentNode.removeChild(child);
	  }
}
