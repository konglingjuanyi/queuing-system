function doSel(id){
	$.ajax({
        url: "/hr/getInterviewerInfo",
        type: "POST",
        dataType: "json",
        data: {'id':id},
        success: function (returnedData) {
        	inter = returnedData.data[0]
        	phaseInter = returnedData.data[1]
        	second = returnedData.data[2]
        	$("#getuserName").val(inter.userName);
        	$("#getjob").val(inter.job);
        	$("#getpassword").val(inter.password);
        	$("#firstrd").text(phaseInter.firstRd);
        	$("#firstfe").text(phaseInter.firstFe);
        	$("#firstqa").text(phaseInter.firstQa);
        	$("#secondrd").text(second[0]);
        	$("#secondfe").text(second[1]);
        	$("#secondqa").text(second[2]);
        	rd = inter.oneView;
        	fe = inter.twoView;
        	qa = inter.determine;
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

