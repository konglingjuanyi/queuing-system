$(document).ready(function () {
	
	$('#searchStudent').click(function(){
	    //$("#doaddform").submit();
		var vars={}
		active($('#stupage'));
		vars['name'] = $("#student_name").val();
		vars['school'] = $("#student_school").val();
		vars['profession'] = $("#student_profession").val();
		vars['state'] = $("#student_state").find("option:selected").text();
		var params = {"vars": vars};
	    $.ajax({
	      url: "/hr/getStudentInfos",
	      type: "POST",
	      dataType: "html",
	      data:JSON.stringify(params),
	      contentType: 'application/json; charset=utf-8',
	      success: function (returnedData) {
//	    	  console.dir(returnedData);
	    	  $('#studentInfoInner').html("");
	    	$('#studentInfoInner').html(returnedData);
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	});
	
});
