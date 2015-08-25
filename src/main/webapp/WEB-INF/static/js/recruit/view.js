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
	alert(username+"="+job+"="+password+"="+first_value+"="+second_value+"="+finish_value);
	var vars = {};
	vars["username"] = username;
	vars["job"] = job;
	vars["password"] = password;
	vars["first_value"] = first_value;
	vars["second_value"] = second_value;
	vars["finish_value"] = finish_value;
    var params = {"vars": vars};
    $.ajax({
          type: "POST",
          contentType: 'application/json',
          dataType: "json",
          url: "/hr/addInterviewers",
          data: JSON.stringify(params),
          success: function (returnedData) {
              if( !ajaxReturn( returnedData ) ){
                  return;
              }else {
              	alert("创建成功");
              	$('#content').load('/hr/getInterviewers');
             }
		  },
          error: function () {
                alert("系统发生了错误请稍后重试");
          }
   });
}
