$(document).ready(function () {
	$('#phase_list').click(function(){
		$("#firstpage").removeClass("active");
		$("#viewsatge").removeClass("active");
		$("#stupage").removeClass("active");
		$("#viewpage").removeClass("active");
		$("#phasepage").addClass("active");
		$('#content').load('/hr/getPhaseInfos');
	});
	
	
	$('#showPhase').click(function(){
		$("#addphaseform").modal("show");
	});
	
	$('#addPhase').click(function(){
		doAddPhase();
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
	$('#content').load('/hr/gotoaddPhaseInfos?yearinfo='+yearinfo+'&phasename='+phasename); 
}
