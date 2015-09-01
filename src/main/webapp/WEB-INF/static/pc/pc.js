$(document).ready(function () {
	setInterval("refreshPage()",60000);
});

function refreshPage(){
	 $.ajax({    
	      type:'post',        
	      url:'/pc/selectViewAndStudent',    
	      dataType:'json',    
	      success:function(data){  
	    	  alert("123123123");
	      }    
	 });   
}


