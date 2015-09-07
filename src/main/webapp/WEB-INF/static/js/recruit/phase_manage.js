$(document).ready(function () {
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
	var vars = {};
	vars["yearinfo"] = yearinfo;
	vars["phasename"] = phasename;
    var params = {"vars": vars};
	active($('#phasepage'))
    $.ajax({
      url: "/hr/gotoaddPhaseInfos",
      type: "POST",
      dataType: "html",
      data:JSON.stringify(params),
      contentType: 'application/json; charset=utf-8',
      success: function (returnedData) {
    	  $('#content').html(returnedData);
	  },
      error: function () {
           alert("系统发生了错误请稍后重试");
      }
    });
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
				+"<td id=td"+cityname+" style='width:1052px;'></td>"
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
				+view_name[i]+"<input class='input-mini' type='text' name='rooms' id='in"+view_name[i]+"' placeholder='房间号'>"
				+"</label>"
		);
	}
	doclose();
}

function saveviewsAndcitys(){
	var year=$("#yearid").val().trim();
	var phase=$("#phaseid").val().trim();
	var trlen=$("#bodyid tr").length;
	var vars = {};
	if(trlen==0){
		alert("至少添加一个城市");
		return false;
	}
	var viewsuccess=true;
	$('#bodyid tr').each(function () {                
        if($(this).children('td').eq(2).html()==''){
        	viewsuccess=false;
        	return false;
        }
    });
	if(viewsuccess==false){
		alert("请选择面试官");
		return false;
	}
	var success=true;
	$("input[name=rooms]").each(function(){    
		if($(this).val()==''){
			success=false;
			return false;
		}   
	});
	if(success==false){
		alert("房间号不能为空");
		return false;
	}
	var all_value ="";
	for(var i=0;i<trlen;i++){
		var city=$("#bodyid tr:eq("+i+")").children("td").eq(0).text().trim();
		var la=$("#bodyid tr:eq("+i+")").children("td").eq(2).children("label").length;
		var view_value ="";
		for(var j=0;j<la;j++){
			var view=$("#bodyid tr:eq("+i+")").children("td").eq(2).children("label").eq(j).text().trim();
			var room=$("#bodyid tr:eq("+i+")").children("td").eq(2).children("label").eq(j).children("input").val().trim();
			view_value=view_value+city+"_"+view+"_"+room+",";
		}
		var view_value_tmp=view_value.substr(0,view_value.length-1);
		all_value=all_value+view_value_tmp+"|";
	}
	vars['all_value']=all_value.substr(0,all_value.length-1).trim();
	vars['year']=year;
	vars['phase']=phase;
	var params = {"vars": vars};
	$.ajax({
	      url: "/hr/addAllcity",
	      type: "POST",
	      dataType: "json",
	      data:JSON.stringify(params),
	      contentType: 'application/json; charset=utf-8',
	      success: function (returnedData) {
	    	  if(returnedData.errorCode==0){
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
/*<tr>
<td>北京</td>
<td><a href="javascript:void(0);" onclick="showView('td北京')">添加面试官</a></td>
<td id="td北京">
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
<td><a href="#" onclick="removetr(this)">删除</a></td>
</tr>
*/
function docancle(){
	$("#allview").css("display","none");
	$("input[type=checkbox]").each(function(){
		$(this).attr("checked",false);
	});
}

function removetr(obj){
	 $(obj).parent().parent().remove(); 
}

function setOver(year,name){
	if(confirm("确定要归档吗？")){
		$.ajax({
		      url: "/hr/setOverPhaseInfo",
		      type: "POST",
		      dataType: "html",
		      data:{'year':year,'name':name},
		      success: function (returnedData) {
		    	  $('#content').html(returnedData);
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		});
	}
}


