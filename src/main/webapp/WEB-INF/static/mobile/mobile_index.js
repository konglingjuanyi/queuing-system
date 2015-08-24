$(document).ready(function () {
	setInterval("refreshPage()",3000);
});

function refreshPage(){
	 $.ajax({    
	      type:'post',        
	      url:'/student/refresh1',    
	      dataType:'json',    
	      success:function(data){  
	    	  $('#msg').html(data.data.message);
	      }    
	 });   
}
