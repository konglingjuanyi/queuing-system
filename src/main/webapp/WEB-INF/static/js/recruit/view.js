$(document).ready(function () {
	$(document).bind("contextmenu",function(e) { 
		return false; 
	}); 
	$(document).bind("keydown",function(e){ 
		e=window.event||e; 
		if(e.keyCode==116){ 
		e.keyCode = 0; 
		return false; 
		} 
	}); 
	$('#view_list').click(function(){
//		window.location.href = '/hr/getInterviewers';
		showViewerMonitor();
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
	
	$('#student_list').click(function(){
		active($('#stupage'))
	    $.ajax({
	      url: "/hr/lead2StudentPage",
	      type: "POST",
	      dataType: "html",
	      contentType: 'application/json; charset=utf-8',
	      success: function (returnedData) {
	    	  $('#content').html(returnedData);
	    	  $.ajax({
	    	      url: "/hr/getAllStudentInfos",
	    	      type: "POST",
	    	      dataType: "html",
	    	      contentType: 'application/json; charset=utf-8',
	    	      success: function (returnedData) {
	    	    	  $('#studentInfoInner').html(returnedData);
	    		  },
	    	      error: function () {
	    	           alert("系统发生了错误请稍后重试");
	    	      }
	    	    });
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	});
	
	$('#phase_list').click(function(){
		//window.location.href="/hr/getPhaseInfos";
		active($('#phasepage'))
	    $.ajax({
	      url: "/hr/getPhaseInfos",
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


	if($('#importmessage').val()!='${importmessage}'){
		active($('#stupage'))
	    $.ajax({
	      url: "/hr/getAllStudentInfos",
	      type: "POST",
	      dataType: "html",
	      contentType: 'application/json; charset=utf-8',
	      success: function (returnedData) {
	    	  $('#content').html(returnedData);
	    	  alert($('#importmessage').val());
	    	  $('#importmessage').val("");
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	}

});

function showViewerMonitor(){
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
}

function backToIndex(){
	showViewerMonitor();
}

function active(obj){
	$('#firstpage').removeClass("active");
	$('#viewsatge').removeClass("active");
	$('#viewpage').removeClass("active");
	$('#managepage').removeClass("active");
	$('#stupage').removeClass("active");
	$('#phasepage').removeClass("active");
	obj.addClass("active");
}

