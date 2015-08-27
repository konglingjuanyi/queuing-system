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

