$(document).ready(function () {
	$('#view_list').click(function(){
		$("#firstpage").removeClass("active");
		$("#viewsatge").removeClass("active");
		$("#stupage").removeClass("active");
		$("#phasepage").removeClass("active");
		$("#viewpage").addClass("active");
		$('#content').load('/hr/getInterviewers');
	});
	
	$('#doadd').click(function(){
		$("#addform").modal("show");
	});
	
	$('#addview').click(function(){
		addViewer();
	});
	
	$('#updview').click(function(){
		updViewer();
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
            	  $('#content').load('/hr/getInterviewers'); 
        	  }else{
        		  alert(returnedData.errorMessage);
        	  }
		  },
          error: function () {
               alert("系统发生了错误请稍后重试");
          }
   });
}

function doUpd(id,password){
	var name=$('#tr'+id).children('td').eq(0).text();
	var job=$('#tr'+id).children('td').eq(1).text();
	var rd=$('#tr'+id).children('td').eq(2).text();
	var fe=$('#tr'+id).children('td').eq(3).text();
	var qa=$('#tr'+id).children('td').eq(4).text();
	$("#updid").val(id);
	$("#upduserName").val(name);
	$("#updjob").val(job);
	$("#updpassword").val(password);
	if(rd!=''){
		var rds=rd.split(",");
		for(var i=0;i<rds.length;i++){
			$('input[name="updfirstname"]').each(function(){
				if($(this).val()==rds[i]){
					$(this).prop("checked",'true');
				}   
			});
		}
	}
	if(fe!=''){
		var rds=fe.split(",");
		for(var i=0;i<rds.length;i++){
			$('input[name="updsecondname"]').each(function(){    
				if($(this).val()==rds[i]){
					$(this).prop("checked",'true');
				}   
			});
		}
	}
	if(qa!=''){
		var rds=qa.split(",");
		for(var i=0;i<rds.length;i++){
			$('input[name="updfinishname"]').each(function(){    
				if($(this).val()==rds[i]){
					$(this).prop("checked",'true');
				}   
			});
		}
	}
	$("#updform").modal("show");
}

function updViewer(){
	var updid=$("#updid").val().trim();
	var username=$("#upduserName").val().trim();
	var job=$("#updjob").val().trim();
	var password=$("#updpassword").val().trim();
	var first_value =[];
	var second_value =[];
	var finish_value =[];
	$('input[name="updfirstname"]:checked').each(function(){    
		first_value.push($(this).val());    
	});
	$('input[name="updsecondname"]:checked').each(function(){    
		second_value.push($(this).val());    
	});
	$('input[name="updfinishname"]:checked').each(function(){    
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
	vars["updid"] = updid;
	vars["username"] = username;
	vars["job"] = job;
	vars["password"] = password;
	vars["first_value"] = first_value.join(",");
	vars["second_value"] = second_value.join(",");
	vars["finish_value"] = finish_value.join(",");
    var params = {"vars": vars};
    $.ajax({
          url: "/hr/updateInterviewer",
          type: "POST",
          dataType: "json",
          contentType: 'application/json; charset=utf-8',
          data: JSON.stringify(params),
          success: function (returnedData) {
        	  	if(returnedData.errorCode==0){
        	  		$("#updform").modal("hide");
            	  	$('#content').load('/hr/getInterviewers');
          	  }else{
          		  alert(returnedData.errorMessage);
          	  }
		  },
          error: function () {
                alert("系统发生了错误请稍后重试");
          }
   });
}

function doDel(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
	        url: "/hr/deleteInterviewer",
	        type: "POST",
	        dataType: "json",
	        data: {'id':id},
	        success: function (returnedData) {
	      	  	$('#content').load('/hr/getInterviewers');
			  },
	        error: function () {
	              alert("系统发生了错误请稍后重试");
	        }
	 });
	}
}

function doclose(){
	$('input[type="text"]').val("");
	$('input[type="password"]').val("");
	$("input[type=checkbox]").each(function(){
		$(this).attr("checked",false);
	});
	$("#updform").modal("hide");
	$("#addform").modal("hide");
	$("#getform").modal("hide");
}

function doSel(id){
	$.ajax({
        url: "/hr/getInterviewerInfo",
        type: "POST",
        dataType: "json",
        data: {'id':id},
        success: function (returnedData) {
        	$("#getuserName").val(returnedData.data.userName);
        	$("#getjob").val(returnedData.data.job);
        	$("#getpassword").val(returnedData.data.password);
        	$("#getpassword").val(returnedData.data.password);
        	$("#firstrd").text(returnedData.data.pi.firstRd);
        	$("#firstfe").text(returnedData.data.pi.firstFe);
        	$("#firstqa").text(returnedData.data.pi.firstQa);
        	$("#secondrd").text(returnedData.data.pi.secondRd);
        	$("#secondfe").text(returnedData.data.pi.secondFe);
        	$("#secondqa").text(returnedData.data.pi.secondQa);

        	console.dir(returnedData)
        	rd = returnedData.data.oneView;
        	fe = returnedData.data.twoView;
        	qa = returnedData.data.determine;
        	if(rd!=''){
        		var rds=rd.split(",");
        		for(var i=0;i<rds.length;i++){
        			$('input[name="getfirstname"]').each(function(){
        				if($(this).val()==rds[i]){
        					$(this).prop("checked",'true');
        				}   
        			});
        		}
        	}
        	if(fe!=''){
        		var rds=fe.split(",");
        		for(var i=0;i<rds.length;i++){
        			$('input[name="getsecondname"]').each(function(){    
        				if($(this).val()==rds[i]){
        					$(this).prop("checked",'true');
        				}   
        			});
        		}
        	}
        	if(qa!=''){
        		var rds=qa.split(",");
        		for(var i=0;i<rds.length;i++){
        			$('input[name="getfinishname"]').each(function(){    
        				if($(this).val()==rds[i]){
        					$(this).prop("checked",'true');
        				}   
        			});
        		}
        	}
        	$("#getform").modal("show");
      	  	
		},
        error: function () {
              alert("系统发生了错误请稍后重试");
        }
 });
}
