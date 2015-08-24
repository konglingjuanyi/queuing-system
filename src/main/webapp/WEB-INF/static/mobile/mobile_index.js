$(document).ready(function () {
	if($("#flag").val()==1){
		setInterval("refreshPage()",3000);
	}else{
		window.location.href='/student/register';
	}
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
