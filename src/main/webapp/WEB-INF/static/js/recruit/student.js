$(document).ready(function () {
	$('#student_list').click(function(){
		active($('#stupage'))
	    $.ajax({
	      url: "/hr/getAllStudentInfos",
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
