$(document).ready(function () {
	$('#view_list').click(function(){
		$('#content').load('/hr/getInterviewers');
	});
	
	$('#doadd').click(function(){
		$("#addform").modal("show");
	});
	
	$('#addview').click(function(){
		addViewer();
	});
});

function addViewer(){
	var username=$("#userName").val().trim();
	var job=$("#job").val().trim();
	var password=$("#password").val().trim();
	var first_value =[];
	var second_value =[];
	var finish_value =[];
	$('input[name="firstname"]:checked').each(function(){    
		first_value.push($(this).val());    
	});
	$('input[name="secondname"]:checked').each(function(){    
		second_value.push($(this).val());    
	});
	$('input[name="finishname"]:checked').each(function(){    
		finish_value.push($(this).val());    
	});
	var vars = {};
	vars["username"] = username;
	vars["job"] = job;
	vars["password"] = password;
	vars["first_value"] = first_value.join(",");
	vars["second_value"] = second_value.join(",");
	vars["finish_value"] = finish_value.join(",");
    var params = {"vars": vars};
    $.ajax({
          url: "/hr/addInterviewers",
          type: "POST",
          dataType: "json",
          contentType: 'application/json; charset=utf-8',
          data: JSON.stringify(params),
          success: function (returnedData) {
        	  	$("#addform").modal("hide");
        	  	$('#content').load('/hr/getInterviewers');
		  },
          error: function () {
                alert("系统发生了错误请稍后重试");
          }
   });
}
