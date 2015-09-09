$(document).ready(function () {
	setInterval("refreshPage()",15000);
});

function refreshPage(){
	 $.ajax({    
	      type:'post',        
	      url:'/pc/selectViewAndStudent',    
	      dataType:'json',    
	      success:function(data){
	    	  var list=data.data;
	    	  var stuAndRoom=[];
	    	  for(var i=0;i<list.length;i++){
	    		  var stuname="";
	    		  var stujob="";
	    		  if(list[i][1]!=null && list[i][1]!=''){
	    			  stuname=list[i][1].name;
	    			  stujob=list[i][1].job; 
	    		  }
	    		  var val="";
	    		  if(list[i][0].status=='等待面试' && list[i][1]!=null && list[i][1]!=''){
	    			  val=list[i][0].room+"_"+stuname;
	    			  stuAndRoom.push(val);
	    		  }
	    	  }
	    	  if(stuAndRoom.length>0){
	    		  for(var m=0;m<stuAndRoom.length;m++){
	    			  var showstrs=stuAndRoom[m].split("_");
	    			  $("#name").text(showstrs[1]);
	    			  $("#room").text(showstrs[0]);
	    			  $("#showView").html("");
	    			  for(var i=0;i<list.length;i++){
		    			  var stuname="";
			    		  var stujob="";
			    		  if(list[i][1]!=null && list[i][1]!=''){
			    			  stuname=list[i][1].name;
			    			  stujob=list[i][1].job; 
			    		  }
			    		  if(list[i][0].status=='等待面试'){
			    			  if(i<7){
				    			  $("#showView").append("<li class='item item-wait'>"
					    		        	+"<div class='room'>"+list[i][0].room+"</div>"
					    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
					    		        	+"<div class='name'>-"+stuname+"-</div>"
					    		        	+"<div class='job'>-"+stujob+"-</div>"
					    		        	+"</li>");
				    		  }else if(i==7){
				    			  $("#showView").append("<li class='item item-last item-wait'>"
					    		        	+"<div class='room'>"+list[i][0].room+"</div>"
					    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
					    		        	+"<div class='name'>-"+stuname+"-</div>"
					    		        	+"<div class='job'>-"+stujob+"-</div>"
					    		        	+"</li>");
				    		  }else if(i>7 && i<15){
				    			  $("#showView").append("<li class='item item-last-bottom item-wait'>"
					    		        	+"<div class='room'>"+list[i][0].room+"</div>"
					    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
					    		        	+"<div class='name'>-"+stuname+"-</div>"
					    		        	+"<div class='job'>-"+stujob+"-</div>"
					    		        	+"</li>");
				    		  }else if(i==15){
				    			  $("#showView").append("<li class='item item-last-bottom item-last item-wait'>"
					    		        	+"<div class='room'>"+list[i][0].room+"</div>"
					    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
					    		        	+"<div class='name'>-"+stuname+"-</div>"
					    		        	+"<div class='job'>-"+stujob+"-</div>"
					    		        	+"</li>");
				    		  }
			    		  }else{
			    			  if(i<7){
				    			  $("#showView").append("<li class='item'>"
					    		        	+"<div class='room'>"+list[i][0].room+"</div>"
					    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
					    		        	+"<div class='name'>-"+stuname+"-</div>"
					    		        	+"<div class='job'>-"+stujob+"-</div>"
					    		        	+"</li>");
				    		  }else if(i==7){
				    			  $("#showView").append("<li class='item item-last'>"
					    		        	+"<div class='room'>"+list[i][0].room+"</div>"
					    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
					    		        	+"<div class='name'>-"+stuname+"-</div>"
					    		        	+"<div class='job'>-"+stujob+"-</div>"
					    		        	+"</li>");
				    		  }else if(i>7 && i<15){
				    			  $("#showView").append("<li class='item item-last-bottom'>"
					    		        	+"<div class='room'>"+list[i][0].room+"</div>"
					    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
					    		        	+"<div class='name'>-"+stuname+"-</div>"
					    		        	+"<div class='job'>-"+stujob+"-</div>"
					    		        	+"</li>");
				    		  }else if(i==15){
				    			  $("#showView").append("<li class='item item-last-bottom item-last'>"
					    		        	+"<div class='room'>"+list[i][0].room+"</div>"
					    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
					    		        	+"<div class='name'>-"+stuname+"-</div>"
					    		        	+"<div class='job'>-"+stujob+"-</div>"
					    		        	+"</li>");
				    		  }
			    		  }
			    	  }
	    			  sleep(2000);
	    		  }
	    	  }else{
	    		  $("#showView").html("");
	    		  $("#name").text("");
    			  $("#room").text("");
	    		  for(var i=0;i<list.length;i++){
	    			  var stuname="";
		    		  var stujob="";
		    		  if(list[i][1]!=null && list[i][1]!=''){
		    			  stuname=list[i][1].name;
		    			  stujob=list[i][1].job; 
		    		  }
		    		  if(list[i][0].status=='等待面试'){
		    			  if(i<7){
			    			  $("#showView").append("<li class='item item-wait'>"
				    		        	+"<div class='room'>"+list[i][0].room+"</div>"
				    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
				    		        	+"<div class='name'>-"+stuname+"-</div>"
				    		        	+"<div class='job'>-"+stujob+"-</div>"
				    		        	+"</li>");
			    		  }else if(i==7){
			    			  $("#showView").append("<li class='item item-last item-wait'>"
				    		        	+"<div class='room'>"+list[i][0].room+"</div>"
				    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
				    		        	+"<div class='name'>-"+stuname+"-</div>"
				    		        	+"<div class='job'>-"+stujob+"-</div>"
				    		        	+"</li>");
			    		  }else if(i>7 && i<15){
			    			  $("#showView").append("<li class='item item-last-bottom item-wait'>"
				    		        	+"<div class='room'>"+list[i][0].room+"</div>"
				    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
				    		        	+"<div class='name'>-"+stuname+"-</div>"
				    		        	+"<div class='job'>-"+stujob+"-</div>"
				    		        	+"</li>");
			    		  }else if(i==15){
			    			  $("#showView").append("<li class='item item-last-bottom item-last item-wait'>"
				    		        	+"<div class='room'>"+list[i][0].room+"</div>"
				    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
				    		        	+"<div class='name'>-"+stuname+"-</div>"
				    		        	+"<div class='job'>-"+stujob+"-</div>"
				    		        	+"</li>");
			    		  }
		    		  }else{
		    			  if(i<7){
			    			  $("#showView").append("<li class='item'>"
				    		        	+"<div class='room'>"+list[i][0].room+"</div>"
				    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
				    		        	+"<div class='name'>-"+stuname+"-</div>"
				    		        	+"<div class='job'>-"+stujob+"-</div>"
				    		        	+"</li>");
			    		  }else if(i==7){
			    			  $("#showView").append("<li class='item item-last'>"
				    		        	+"<div class='room'>"+list[i][0].room+"</div>"
				    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
				    		        	+"<div class='name'>-"+stuname+"-</div>"
				    		        	+"<div class='job'>-"+stujob+"-</div>"
				    		        	+"</li>");
			    		  }else if(i>7 && i<15){
			    			  $("#showView").append("<li class='item item-last-bottom'>"
				    		        	+"<div class='room'>"+list[i][0].room+"</div>"
				    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
				    		        	+"<div class='name'>-"+stuname+"-</div>"
				    		        	+"<div class='job'>-"+stujob+"-</div>"
				    		        	+"</li>");
			    		  }else if(i==15){
			    			  $("#showView").append("<li class='item item-last-bottom item-last'>"
				    		        	+"<div class='room'>"+list[i][0].room+"</div>"
				    		        	+"<div class='state'>-"+list[i][0].status+"-</div>"
				    		        	+"<div class='name'>-"+stuname+"-</div>"
				    		        	+"<div class='job'>-"+stujob+"-</div>"
				    		        	+"</li>");
			    		  }
		    		  }
		    	  }
	    	  }
	    	  
	      }    
	 });   
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



