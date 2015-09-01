$(document).ready(function () {
	alert(1);
	//setInterval("refreshPage()",5000);
});

function refreshPage(){
	alert(2);
	for(var j=0;j<3;j++){
		alert(j);
		showViewAndStu();
		sleep(5000);
	}
}

function showViewAndStu(){
	alert("111111111111");
}

function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
	now = new Date();
	if (now.getTime() > exitTime)
		return;
	}
}

/*function refreshPage(){
	 $.ajax({    
	      type:'post',        
	      url:'/pc/selectViewAndStudent',    
	      dataType:'json',    
	      success:function(data){  
	    	  alert("123123123");
	    	  var lmap=data.viewAndStu;
	    	  var arr=[];
	    	  for(var i=0;i<lmap.length;i++){
	    		  if(lmap[i].status=='等待面试'){
	    			  arr.push(lmap[i]);
	    		  }
	    	  }
	    	  for(var j=0;j<arr.length;j++){
	    		  setInterval("showViewAndStu()",5000);
	    	  }
	      }    
	 });   
}
*/



