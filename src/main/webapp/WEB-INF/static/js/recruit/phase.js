$(document).ready(function () {
	$('#phase_list').click(function(){
		window.location.href="/hr/getPhaseInfos";
	});
	
	
	$('#showPhase').click(function(){
		$("#addphaseform").modal("show");
	});
	
	$('#addPhase').click(function(){
		doAddPhase();
	});
	
	$('#addcity').click(function(){
		doAddCity();
	});
});

function doclose(){
	$('input[type="text"]').val("");
	$("#addphaseform").modal("hide");
}

function doAddPhase(){
	var yearInfo=$("#yearInfo").val().trim();
	var phaseName=$("#phaseName").val().trim();
	if(yearInfo==''){
		alert("年份不能为空");
		return false;
	}
	if(phaseName==''){
		alert("批次名称不能为空");
		return false;
	}
	var vars = {};
	vars["yearInfo"] = yearInfo;
	vars["phaseName"] = phaseName;
    var params = {"vars": vars};
    $.ajax({
          url: "/hr/addPhaseInfo",
          type: "POST",
          dataType: "json",
          contentType: 'application/json; charset=utf-8',
          data: JSON.stringify(params),
          success: function (returnedData) {
        	  if(returnedData.errorCode==0){
        		  $("#addphaseform").modal("hide");
            	  $('#content').load('/hr/getPhaseInfos'); 
        	  }else{
        		  alert(returnedData.errorMessage);
        	  }
		  },
          error: function () {
               alert("系统发生了错误请稍后重试");
          }
   });
}

function addOrupd(yearinfo,phasename){
	window.location.href='/hr/gotoaddPhaseInfos?yearinfo='+yearinfo+'&phasename='+phasename; 
}

function doAddCity(){
	var cityname=$("#cityname").val().trim();
	var success=true;
	if(cityname==''){
		alert("城市不能为空");
		return false;
	}
	$("#bodyid tr").each(function(){
		if($(this).children("td:eq(0)").text()==cityname){
			alert("城市已经存在");
			success=false;
			return false;
		}
	});
	if(success==false){
		return false;
	}else{
		var href_ = "showView('td"+cityname+"')";
		$("#bodyid").append("<tr>"
				+"<td>"+cityname+"</td>"
				+"<td><a href='javascript:void(0)' onclick="+href_+">添加面试官</a></td>"
				+"<td id=td"+cityname+"></td>"
				+"<td><a href='#' onclick='removetr(this)'>删除</a></td>"
				+"</tr>"
		);
	}
	$("#cityname").val("");
}

function showView(id){
	$("#viewid").val(id);
	$("#allview").css("display","");
}

function doaddview(){
	var viewid=$("#viewid").val().trim();
	var view_name =[];
	$('input[name="viewnames"]:checked').each(function(){    
		view_name.push($(this).val());    
	});
	if(view_name.length==0){
		alert("至少选择一个面试官");
		return false;
	}
	for(var i=0;i<view_name.length;i++){
		$("#"+viewid).append("<label class='control-label' for='inputEmail'>"
				+view_name[i]+"<input class='input-mini' type='text' id='in"+view_name[i]+"' placeholder='房间号'>"
				+"</label>"
		);
	}
	doclose();
}

function doclose(){
	$("#allview").css("display","none");
	$("input[type=checkbox]").each(function(){
		$(this).attr("checked",false);
	});
}

function removetr(obj){
	 $(obj).parent().parent().remove(); 
}


/*<tr>
<td>北京</td>
<td><a href="#">添加面试官</a></td>
<td>
	<label class="control-label" for="inputEmail">
		刘玥
		<input class="input-mini" type="text" id="inputEmail" placeholder="房间号">
	</label>
	<label class="control-label" for="inputEmail">
		孙立
		<input class="input-mini" type="text" id="inputEmail" placeholder="房间号">
	</label>
	<label class="control-label" for="inputEmail">
		三丰
		<input class="input-mini" type="text" id="inputEmail" placeholder="房间号">
	</label>
</td>
<td><a href="#">删除</a></td>
</tr>*/
