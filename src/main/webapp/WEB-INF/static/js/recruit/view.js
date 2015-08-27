$(document).ready(function () {
	$('#view_list').click(function(){
//		window.location.href = '/hr/getInterviewers';
  	    active($('#viewpage'))
	    $.ajax({
	      url: "/hr/getInterviewersForMonitor",
	      type: "POST",
	      dataType: "html",
	      contentType: 'application/json; charset=utf-8',
	      success: function (returnedData) {
	    	  $('#content').html(returnedData);
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
		
	});
	$('#manage_list').click(function(){
  	  	active($('#managepage'))
		$.ajax({
		      url: "/hr/getInterviewersForManage",
		      type: "POST",
		      dataType: "html",
		      contentType: 'application/json; charset=utf-8',
		      success: function (returnedData) {
					$('#content').html(returnedData);
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	});
	$('#index_list').click(function(){
  	    backToIndex();
	});


});

function backToIndex(){
	active($('#firstpage'));
	 $('#content').html("");
}

function active(obj){
	$('#firstpage').removeClass("active");
	$('#viewsatge').removeClass("active");
	$('#viewpage').removeClass("active");
	$('#managepage').removeClass("active");
	$('#stupage').removeClass("active");
	$('#stupage').removeClass("phasepage");
	obj.addClass("active");
}

