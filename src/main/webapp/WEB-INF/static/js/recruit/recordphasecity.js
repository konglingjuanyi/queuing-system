$(document).ready(function () {
	/* $.ajax({
	      url: "/getFirstPhase",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function (returnedData) {
	    	  alert(returnedData);
	    	  
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });*/
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
	if(username==''){
		alert("用户名不能为空");
		return false;
	}
	if(job==''){
		alert("职位不能为空");
		return false;
	}
	if(password==''){
		alert("密码不能为空");
		return false;
	}
	if(first_value.length==0&&second_value.length==0&&finish_value.length==0){
		alert("至少选择一项面试项目");
		return false;
	}
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
        	  if(returnedData.errorCode==0){
        		  $("#addform").modal("hide");
            	  $('#content').load('/hr/getInterviewersForManage'); 
        	  }else{
        		  alert(returnedData.errorMessage);
        	  }
		  },
          error: function () {
               alert("系统发生了错误请稍后重试");
          }
   });
}
