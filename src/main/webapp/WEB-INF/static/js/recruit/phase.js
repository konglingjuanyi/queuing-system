$(document).ready(function () {
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
});
