var flag=-1;
var determine="";
$(document).ready(function () {
	
	$.ajax({
	      url: "/interviewer/getCurrentViewInfo",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function (result) {
	    	  if(result.errorCode == 0){
	    		  data = result.data;
		    	  inter = data[0];
		    	  stu=data[2];
		    	  phase_inter = data[1];
		    	  var access=data[3];
		    	  determine=inter.determine;
		    	  if(phase_inter.status=='等待面试'){
			    	  if(stu.state=='初试中' || stu.state=='等待初试'){
			    		  message=1;
			    	  }else{
			    		  message=2;
			    	  }
			    	  flag=message;
			    	  if(message!=1 && message!=2){
			    		  alert(message);
			    		  setAllVal();
			    		  $("#beginToInterview").hide();
				    	  $("#finishInterview").hide();
				    	  $("#nocome").hide();
				    	  $("#getOneInterview").attr("disabled", false);
			    	  }else{
				    	  if(message!=1){
				    		  var access=data[3];
				    		  /***渲染评估表****/
				    		  if(access!=null){
				    			  addValue(access);
				    		  }
				    		  disabledOne();
				    	  }else{
				    		  setAllVal();
				    		  disabledTwo();
				    	  }
				    	  $("#already_inter").text(parseInt(phase_inter.oneCount)+parseInt(phase_inter.twoCount));
				    	  $("#student_name").text(stu.name);
				    	  $("#jobTitle").text(stu.job);
				    	  if(message!=1){
				    		  $("#first_viewer").text(stu.firstTry);
				    		  $("#second_viewer").text(phase_inter.intervierName);
				    	  }else{
				    		  $("#first_viewer").text(phase_inter.intervierName);
				    	  }
				    	  $("#interviewer_name").text(inter.userName);
				    	  $("#interviewer_room").text(phase_inter.room);
				    	  $("#getOneInterview").attr("disabled", true);
				    	  $("#beginToInterview").attr("disabled", false);
				    	  $("#beginToInterview").show();
				    	  $("#finishInterview").show();
				    	  $("#nocome").show();
				    	  $("#finishInterview").attr("disabled", true);
			    	  }
		    	  }else{
		    		  if(stu.state=='初试中' || stu.state=='等待初试'){
			    		  message=1;
			    	  }else{
			    		  message=2;
			    	  }
		    		  flag=message;
			    	  if(message!=1 && message!=2){
			    		  alert(message);
			    		  setAllVal();
			    		  $("#beginToInterview").hide();
				    	  $("#finishInterview").hide();
				    	  $("#nocome").hide();
				    	  $("#getOneInterview").attr("disabled", false);
			    	  }else{
				    	  if(message!=1){
				    		  var access=data[3];
				    		  /***渲染评估表****/
				    		  if(access!=null){
				    			  addValue(access);
				    		  }
				    		  disabledOne();
				    	  }else{
				    		  setAllVal();
				    		  disabledTwo();
				    	  }
				    	  $("#already_inter").text(parseInt(phase_inter.oneCount)+parseInt(phase_inter.twoCount));
				    	  $("#student_name").text(stu.name);
				    	  $("#jobTitle").text(stu.job);
				    	  if(message!=1){
				    		  $("#first_viewer").text(stu.firstTry);
				    		  $("#second_viewer").text(phase_inter.intervierName);
				    	  }else{
				    		  $("#first_viewer").text(phase_inter.intervierName);
				    	  }
				    	  $("#interviewer_name").text(inter.userName);
				    	  $("#interviewer_room").text(phase_inter.room);
				    	  $("#beginToInterview").show();
				    	  $("#finishInterview").show();
				    	  $("#nocome").show();
			    	  }
			    	  $("#beginToInterview").attr("disabled", true);
		    		  $("#getOneInterview").attr("disabled", true);
		    		  $("#finishInterview").attr("disabled", false);
		    		  clock();
		    	  }
	    	  }else{
		    	  data = result.data;
		    	  inter = data[0];
		    	  phase_inter = data[1];
		    	  determine=inter.determine;
		    	  $("#interviewer_name").text(inter.userName);
		    	  $("#interviewer_room").text(phase_inter.room);
		    	  $("#already_inter").text(parseInt(phase_inter.oneCount)+parseInt(phase_inter.twoCount));
		    	  $("#beginToInterview").hide();
		    	  $("#finishInterview").hide();
		    	  $("#nocome").hide();
	    	  }
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	
	$("#getOneInterview").click(function(){
		$.ajax({
		      url: "/interviewer/getOneInterview",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      success: function (set) {
		    	  data=set.data;
		    	  var message=data.message;
		    	  flag=message;
		    	  if(message!=1 && message!=2){
		    		  alert(message);
		    		  setAllVal();
		    		  $("#beginToInterview").hide();
			    	  $("#finishInterview").hide();
			    	  $("#nocome").hide();
			    	  $("#getOneInterview").attr("disabled", false);
		    	  }else{
		    		  var stu=data.student;
			    	  var inter=data.phaseInterviewer;
			    	  if(message!=1){
			    		  var access=data.assess;
			    		  /***渲染评估表****/
			    		  if(access!=null){
			    			  addValue(access);
			    		  }
			    		  disabledOne();
			    	  }else{
			    		  setAllVal();
			    		  disabledTwo();
			    	  }
			    	  $("#already_inter").text(parseInt(inter.oneCount)+parseInt(inter.twoCount));
			    	  $("#student_name").text(stu.name);
			    	  $("#jobTitle").text(stu.job);
			    	  if(message!=1){
			    		  $("#first_viewer").text(stu.firstTry);
			    		  $("#second_viewer").text(inter.intervierName);
			    	  }else{
			    		  $("#first_viewer").text(inter.intervierName);
			    	  }
			    	  $("#getOneInterview").attr("disabled", true);
			    	  $("#beginToInterview").attr("disabled", false);
			    	  $("#beginToInterview").show();
			    	  $("#finishInterview").show();
			    	  $("#nocome").show();
			    	  $("#finishInterview").attr("disabled", true);
		    	  }
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	});
	
	$("#beginToInterview").click(function(){
		$("#beginToInterview").attr("disabled", true);
		$("#getOneInterview").attr("disabled", true);
		$("#finishInterview").attr("disabled", false);
		$("#nocome").hide();
		clock();
		$.ajax({
		      url: "/interviewer/beginToInterview",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      success: function (set) {
		    	  
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	});
	
	$("#finishInterview").click(function(){
		var stujob=$("#jobTitle").text();
		var oneCon=$("#one_conclusion").val();
		 if(flag==1){
			 if($("#one_conclusion").val()==''){
				alert("请选择初试结论");
				return false;
			 }
		 }else if(flag==2){
			 	if($("#two_conclusion").val()==''){
			 		alert("请选择复试结论");
					return false;
			 	}
		 }else{
			 alert("不能结束面试");
			 return false;
		 }
		 if(flag==1){
			 if(determine!="undifined" && determine!=null && determine!="" && determine.indexOf(stujob)!=-1 && oneCon!='不通过'){
				 $("#noview").css("display","");
			 }
		 }
		 $("#goon").css("display","");
		 $("#havarest").css("display","");
		 $("#donotView").prop("checked",false);
	 });
	
	 $("#finishAndContinue").click(function(){
		 var decheckbox;
		 $("input[name='determine']:checked").each(function(){    
			 decheckbox=$(this).val();    
		 });    
		 clearInterval(times);
		 timeIndex=0;
		 setTime();
		 param = getSubmitParam(decheckbox);
		 $.ajax({
		      url: "/interviewer/finishAndContinue",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      data: JSON.stringify(param),
		      success: function (set) {
		    	  data=set.data;
		    	  var message=data.message;
		    	  flag=message;
		    	  if(message!=1 && message!=2){
		    		  alert(message);
		    		  setAllVal();
		    		  $("#beginToInterview").hide();
			    	  $("#finishInterview").hide();
			    	  $("#nocome").hide();
			    	  $("#goon").hide();
			    	  $("#havarest").hide();
			    	  $("#noview").hide();
			    	  $("#student_name").text("");
			    	  $("#jobTitle").text("");
			    	  $("#first_viewer").text("");
			    	  $("#second_viewer").text("");
			    	  $("#already_inter").text(parseInt($("#already_inter").text())+1);
			    	  $("#getOneInterview").attr("disabled", false);
		    	  }else{
		    		  var stu=data.student;
			    	  var inter=data.phaseInterviewer;
			    	  if(message!=1){
			    		  var access=data.assess;
			    		  /***渲染评估表****/
			    		  if(access!=null){
			    			  addValue(access);
			    		  }
			    		  disabledOne();
			    	  }else{
			    		  setAllVal();
			    		  disabledTwo();
			    	  }
			    	  $("#already_inter").text(parseInt(inter.oneCount)+parseInt(inter.twoCount));
			    	  $("#student_name").text(stu.name);
			    	  $("#jobTitle").text(stu.job);
			    	  if(message!=1){
			    		  $("#first_viewer").text(stu.firstTry);
			    		  $("#second_viewer").text(inter.intervierName);
			    	  }else{
			    		  $("#first_viewer").text(inter.intervierName);
			    	  }
			    	  $("#getOneInterview").attr("disabled", true);
			    	  $("#beginToInterview").show();
			    	  $("#beginToInterview").attr("disabled", false);
			    	  $("#finishInterview").show();
			    	  $("#nocome").show();
			    	  $("#finishInterview").attr("disabled", true);
			    	  $("#goon").hide();
			    	  $("#havarest").hide();
			    	  $("#noview").hide();
		    	  }
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	 });
	 
	 $("#finishAndRest").click(function(){
		 var decheckbox;
		 $("input[name='determine']:checked").each(function(){    
			 decheckbox=$(this).val();    
		 }); 
		 clearInterval(times);
		 timeIndex=0;
		 setTime();
		 param = getSubmitParam(decheckbox);
		 $.ajax({
		      url: "/interviewer/finishAndRest",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      data: JSON.stringify(param),
		      success: function (set) {
		    	  $("#noview").css("display","none");
		    	  $("#goon").css("display","none");
		    	  $("#havarest").css("display","none");
		    	  $("#nocome").css("display","none");
		    	  $("#beginToInterview").hide();
		    	  $("#getOneInterview").attr("disabled", false);
		    	  $("#finishInterview").hide();
		    	  setAllVal();
//		    	  alert(parseInt($("#already_inter").text))
		    	  $("#already_inter").text(parseInt($("#already_inter").text())+1);
		    	  $("#student_name").text("");
		    	  $("#jobTitle").text("");
		    	  $("#first_viewer").text("");
		    	  $("#second_viewer").text("");
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	 });
	 
	 $("#noComeFinish").click(function(){
		 $.ajax({
		      url: "/interviewer/noComeFinish",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      success: function (set) {
		    	  data=set.data;
		    	  var message=data.message;
		    	  flag=message;
		    	  if(message!=1 && message!=2){
		    		  alert(message);
		    		  setAllVal();
		    		  $("#beginToInterview").hide();
			    	  $("#finishInterview").hide();
			    	  $("#nocome").hide();
			    	  $("#getOneInterview").attr("disabled", false);
			    	  $("#student_name").text("");
			    	  $("#jobTitle").text("");
			    	  $("#first_viewer").text("");
		    		  $("#second_viewer").text("");
		    	  }else{
		    		  var stu=data.student;
			    	  var inter=data.phaseInterviewer;
			    	  if(message!=1){
			    		  var access=data.assess;
			    		  /***渲染评估表****/
			    		  if(access!=null){
			    			  addValue(access);
			    		  }
			    		  disabledOne();
			    	  }else{
			    		  setAllVal();
			    		  disabledTwo();
			    	  }
			    	  $("#student_name").text(stu.name);
			    	  $("#jobTitle").text(stu.job);
			    	  if(message!=1){
			    		  $("#first_viewer").text(stu.firstTry);
			    		  $("#second_viewer").text(inter.intervierName);
			    	  }else{
			    		  $("#first_viewer").text(inter.intervierName);
			    	  }
			    	  $("#getOneInterview").attr("disabled", true);
			    	  $("#beginToInterview").show();
			    	  $("#finishInterview").show();
			    	  $("#nocome").show();
			    	  $("#finishInterview").attr("disabled", true);
		    	  }
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	 });
	 
	 setInterval(showOneAndTwoStu, 5000);
	 
});


function showOneAndTwoStu(){
	$.ajax({
	      url: "/interviewer/getOneAndTwoStu",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function (set) {
	    	  var data=set.data;
	    	  var one=data[0];
	    	  var two=data[1];
	    	  $("#oneDev").text(one[0]);
	    	  $("#twoDev").text(two[0]);
	    	  $("#oneQa").text(one[2]);
	    	  $("#twoQa").text(two[2]);
	    	  $("#oneFe").text(one[1]);
	    	  $("#twoFe").text(two[1]);
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
}

var timeIndex = 0;
function clock(){
	setTime();
	times = setInterval(setTime, 1000);    //每隔1秒执行函数
}
function setTime(){
	var hour = parseInt(timeIndex / 3600);    // 计算时 
	var minutes = parseInt((timeIndex % 3600) / 60);    // 计算分 
	var seconds = parseInt(timeIndex % 60);    // 计算秒  
	hour = hour < 10 ? "0" + hour : hour;
	minutes = minutes < 10 ? "0" + minutes : minutes;
	seconds = seconds < 10 ? "0" + seconds : seconds;
	$("#showTime").text(hour + ":" + minutes + ":" + seconds);
	timeIndex++;
}

function getSubmitParam(decheckbox){
//	alert($("#one_code").find("option:selected").text());
//	var vars=$("#submitForm").serializeObject();
//	alert(JSON.stringify(vars))
//	console.dir(JSON.stringify(vars));
//	alert(vars);
	vars = {};
	vars['one_code'] = $("#one_code").find("option:selected").text();
	vars['one_code_detail'] = $("#one_code_detail").val();
	vars['one_algorithm'] = $("#one_algorithm").find("option:selected").text();
	vars['one_algorithm_detail'] = $("#one_algorithm_detail").val();
	vars['one_network'] = $("#one_network").find("option:selected").text();
	vars['one_network_detail'] = $("#one_network_detail").val();
	vars['one_experience'] = $("#one_experience").find("option:selected").text();
	vars['one_experience_detail'] = $("#one_experience_detail").val();
	vars['one_other'] = $("#one_other").find("option:selected").text();
	vars['one_other_detail'] = $("#one_other_detail").val();
	vars['one_logic'] = $("#one_logic").find("option:selected").text();
	vars['one_logic_detail'] = $("#one_logic_detail").val();
	vars['one_creative'] = $("#one_creative").find("option:selected").text();
	vars['one_creative_detail'] = $("#one_creative_detail").val();
	vars['one_team'] = $("#one_team").find("option:selected").text();
	vars['one_team_detail'] = $("#one_team_detail").val();
	vars['one_continuouslearning'] = $("#one_continuouslearning").find("option:selected").text();
	vars['one_continuouslearning_detail'] = $("#one_continuouslearning_detail").val();
	vars['one_outstanding'] = $("#one_outstanding").find("option:selected").text();
	vars['one_outstanding_detail'] = $("#one_outstanding_detail").val();
	vars['two_code'] = $("#two_code").find("option:selected").text();
	vars['two_code_detail'] = $("#two_code_detail").val();
	vars['two_algorithm'] = $("#two_algorithm").find("option:selected").text();
	vars['two_algorithm_detail'] = $("#two_algorithm_detail").val();
	vars['two_network'] = $("#two_network").find("option:selected").text();
	vars['two_network_detail'] = $("#two_network_detail").val();
	vars['two_experience'] = $("#two_experience").find("option:selected").text();
	vars['two_experience_detail'] = $("#two_experience_detail").val();
	vars['two_other'] = $("#two_other").find("option:selected").text();
	vars['two_other_detail'] = $("#two_other_detail").val();
	vars['two_logic'] = $("#two_logic").find("option:selected").text();
	vars['two_logic_detail'] = $("#two_logic_detail").val();
	vars['two_creative'] = $("#two_creative").find("option:selected").text();
	vars['two_creative_detail'] = $("#two_creative_detail").val();
	vars['two_team'] = $("#two_team").find("option:selected").text();
	vars['two_team_detail'] = $("#two_team_detail").val();
	vars['two_continuouslearning'] = $("#two_continuouslearning").find("option:selected").text();
	vars['two_continuouslearning_detail'] = $("#two_continuouslearning_detail").val();
	vars['two_outstanding'] = $("#two_outstanding").find("option:selected").text();
	vars['two_outstanding_detail'] = $("#two_outstanding_detail").val();
	vars['one_conclusion'] = $("#one_conclusion").find("option:selected").text();
	vars['one_suggest_salary'] = $("#one_suggest_salary").val();
	vars['two_suggest_salary'] = $("#two_suggest_salary").val();
	vars['one_suggest_salary_detail'] = $("#one_suggest_salary_detail").val();
	vars['two_conclusion'] = $("#two_conclusion").find("option:selected").text();
	vars['two_conclusion_detail'] = $("#two_conclusion_detail").val();
	vars['one_allocation_idea'] = $("#one_allocation_idea").val();
	vars['two_allocation_idea'] = $("#two_allocation_idea").val();
	vars['hr_name'] = $("#hr_name").val();
	vars['one_sum'] = $("#one_sum").val();
	vars['two_sum'] = $("#two_sum").val();
	vars['hr_suggest_salary'] = $("#hr_suggest_salary").val();
	vars['hr_detail_idea'] = $("#hr_detail_idea").val();
	vars['decheckbox'] = decheckbox;
	var params = {"vars": vars};
	return params;
}


function addValue(access){
	$("#one_code").val(access.oneCode);
	$("#one_code_detail").val(access.oneCodeDetail);
	$("#one_algorithm").val(access.oneAlgorithm);
	$("#one_algorithm_detail").val(access.oneAlgorithmDetail);
	$("#one_network").val(access.oneNetwork);
	$("#one_network_detail").val(access.oneNetworkDetail);
	$("#one_experience").val(access.oneExperience);
	$("#one_experience_detail").val(access.oneExperienceDetail);
	$("#one_other").val(access.oneOther);
	$("#one_other_detail").val(access.oneOtherDetail);
	$("#one_logic").val(access.oneLogic);
	$("#one_logic_detail").val(access.oneLogicDetail);
	$("#one_creative").val(access.oneCreative);
	$("#one_creative_detail").val(access.oneCreativeDetail);
	$("#one_team").val(access.oneTeam);
	$("#one_team_detail").val(access.oneTeamDetail);
	$("#one_continuouslearning").val(access.oneContinuouslearning);
	$("#one_continuouslearning_detail").val(access.oneContinuouslearningDetail);
	$("#one_outstanding").val(access.oneOutstanding);
	$("#one_outstanding_detail").val(access.oneOutstandingDetail);
	$("#two_code").val(access.twoCode);
	$("#two_code_detail").val(access.twoCodeDetail);
	$("#two_algorithm").val(access.twoAlgorithm);
	$("#two_algorithm_detail").val(access.twoAlgorithmDetail);
	$("#two_network").val(access.twoNetwork);
	$("#two_network_detail").val(access.twoNetworkDetail);
	$("#two_experience").val(access.twoExperience);
	$("#two_experience_detail").val(access.twoExperienceDetail);
	$("#two_other").val(access.twoOther);
	$("#two_other_detail").val(access.twoOtherDetail);
	$("#two_logic").val(access.twoLogic);
	$("#two_logic_detail").val(access.twoLogicDetail);
	$("#two_creative").val(access.twoCreative);
	$("#two_creative_detail").val(access.twoCreativeDetail);
	$("#two_team").val(access.twoTeam);
	$("#two_team_detail").val(access.twoTeamDetail);
	$("#two_continuouslearning").val(access.twoContinuouslearning);
	$("#two_continuouslearning_detail").val(access.twoContinuouslearningDetail);
	$("#two_outstanding").val(access.twoOutstanding);
	$("#two_outstanding_detail").val(access.twoOutstandingDetail);
	$("#one_conclusion").val(access.oneConclusion);
	$("#one_suggest_salary_detail").val(access.oneSuggestSalaryDetail);
	$("#two_conclusion").val("");
	$("#two_conclusion_detail").val(access.twoConclusionDetail);
	$("#one_allocation_idea").val(access.oneAllocationIdea);
	$("#two_allocation_idea").val(access.twoAllocationIdea);
	$("#hr_name").val(access.hrName);
	$("#one_sum").val(access.oneSum);
	$("#two_sum").val("");
	$("#hr_suggest_salary").val(access.hrSuggestSalary);
	$("#hr_detail_idea").val(access.hrDetailIdea);
	if(access.oneConclusion=='卓越'){
		if(access.oneSuggestSalary!=null && access.oneSuggestSalary!=''){
			$("#one_suggest_salary").val(access.oneSuggestSalary);
			$("#one_suggest").css("display","");
		}else{
			$("#one_suggest_salary").val("");
			$("#one_suggest").css("display","");
		}
	}else{
		$("#one_suggest_salary").val("");
		$("#one_suggest").css("display","none");
	}
	$("#two_suggest_salary").val("");
	$("#two_suggest").css("display","none");
	
}

function setAllVal(){
	$("#one_code").val("0");
	$("#one_code_detail").val("");
	$("#one_algorithm").val("0");
	$("#one_algorithm_detail").val("");
	$("#one_network").val("0");
	$("#one_network_detail").val("");
	$("#one_experience").val("0");
	$("#one_experience_detail").val("");
	$("#one_other").val("0");
	$("#one_other_detail").val("");
	$("#one_logic").val("0");
	$("#one_logic_detail").val("");
	$("#one_creative").val("0");
	$("#one_creative_detail").val("");
	$("#one_team").val("0");
	$("#one_team_detail").val("");
	$("#one_continuouslearning").val("0");
	$("#one_continuouslearning_detail").val("");
	$("#one_outstanding").val("0");
	$("#one_outstanding_detail").val("");
	$("#two_code").val("0");
	$("#two_code_detail").val("");
	$("#two_algorithm").val("0");
	$("#two_algorithm_detail").val("");
	$("#two_network").val("0");
	$("#two_network_detail").val("");
	$("#two_experience").val("0");
	$("#two_experience_detail").val("");
	$("#two_other").val("0");
	$("#two_other_detail").val("");
	$("#two_logic").val("0");
	$("#two_logic_detail").val("");
	$("#two_creative").val("0");
	$("#two_creative_detail").val("");
	$("#two_team").val("0");
	$("#two_team_detail").val("");
	$("#two_continuouslearning").val("0");
	$("#two_continuouslearning_detail").val("");
	$("#two_outstanding").val("0");
	$("#two_outstanding_detail").val("");
	$("#one_conclusion").val("");
	$("#one_suggest_salary").val("");
	$("#two_suggest_salary").val("");
	$("#one_suggest_salary_detail").val("");
	$("#two_conclusion").val("");
	$("#two_conclusion_detail").val("");
	$("#one_allocation_idea").val("");
	$("#two_allocation_idea").val("");
	$("#hr_name").val("");
	$("#one_sum").val("");
	$("#two_sum").val("");
	$("#hr_suggest_salary").val("");
	$("#hr_detail_idea").val("");
	$("#one_suggest").css("display","none");
	$("#two_suggest").css("display","none");
}

function sumOneCount(){
	var one_code=parseInt($("#one_code").val());
	var one_algorithm=parseInt($("#one_algorithm").val());
	var one_network=parseInt($("#one_network").val());
	var one_experience=parseInt($("#one_experience").val());
	var one_other=parseInt($("#one_other").val());
	var one_logic=parseInt($("#one_logic").val());
	var one_creative=parseInt($("#one_creative").val());
	var one_team=parseInt($("#one_team").val());
	var one_continuouslearning=parseInt($("#one_continuouslearning").val());
	var one_outstanding=parseInt($("#one_outstanding").val());
	var sum=one_code+one_algorithm+one_network+one_experience+one_other+one_logic+one_creative+one_team+one_continuouslearning+one_outstanding;
	$("#one_sum").val(sum);
	
	
}

function sumTwoCount(){
	var two_code=parseInt($("#two_code").val());
	var two_algorithm=parseInt($("#two_algorithm").val());
	var two_network=parseInt($("#two_network").val());
	var two_experience=parseInt($("#two_experience").val());
	var two_other=parseInt($("#two_other").val());
	var two_logic=parseInt($("#two_logic").val());
	var two_creative=parseInt($("#two_creative").val());
	var two_team=parseInt($("#two_team").val());
	var two_continuouslearning=parseInt($("#two_continuouslearning").val());
	var two_outstanding=parseInt($("#two_outstanding").val());
	var sum=two_code+two_algorithm+two_network+two_experience+two_other+two_logic+two_creative+two_team+two_continuouslearning+two_outstanding;
	$("#two_sum").val(sum);
}

function showSuggest(flag1){
	var stujob=$("#jobTitle").text().trim();
	if(flag1==1){
		if($("#one_conclusion").val()=='卓越'){
			$("#one_suggest").css("display","");
		}else{
			$("#one_suggest").css("display","none");
		}
		if(determine!="undifined" && determine!=null && determine!="" && determine.indexOf(stujob)!=-1){
			if($("#one_conclusion").val()=='不通过'){
				$("#noview").css("display","none");
			}else{
				$("#noview").css("display","");
			}
		 }
	}else{
		if($("#two_conclusion").val()=='卓越'){
			$("#two_suggest").css("display","");
		}else{
			$("#two_suggest").css("display","none");
		}
	}
	
}

function disabledTwo(){
	$("#one_code").attr("disabled",false);
	$("#one_code_detail").attr("disabled",false);
	$("#one_algorithm").attr("disabled",false);
	$("#one_algorithm_detail").attr("disabled",false);
	$("#one_network").attr("disabled",false);
	$("#one_network_detail").attr("disabled",false);
	$("#one_experience").attr("disabled",false);
	$("#one_experience_detail").attr("disabled",false);
	$("#one_other").attr("disabled",false);
	$("#one_other_detail").attr("disabled",false);
	$("#one_logic").attr("disabled",false);
	$("#one_logic_detail").attr("disabled",false);
	$("#one_creative").attr("disabled",false);
	$("#one_creative_detail").attr("disabled",false);
	$("#one_team").attr("disabled",false);
	$("#one_team_detail").attr("disabled",false);
	$("#one_continuouslearning").attr("disabled",false);
	$("#one_continuouslearning_detail").attr("disabled",false);
	$("#one_outstanding").attr("disabled",false);
	$("#one_outstanding_detail").attr("disabled",false);
	$("#one_conclusion").attr("disabled",false);
	$("#one_suggest_salary").attr("disabled",false);
	$("#one_suggest_salary_detail").attr("disabled",false);
	$("#one_allocation_idea").attr("disabled",false);
	$("#one_sum").attr("disabled",false);
	$("#one_suggest").attr("disabled",false);
	$("#two_suggest").attr("disabled",true);
	
	$("#two_code").attr("disabled",true);
	$("#two_code_detail").attr("disabled",true);
	$("#two_algorithm").attr("disabled",true);
	$("#two_algorithm_detail").attr("disabled",true);
	$("#two_network").attr("disabled",true);
	$("#two_network_detail").attr("disabled",true);
	$("#two_experience").attr("disabled",true);
	$("#two_experience_detail").attr("disabled",true);
	$("#two_other").attr("disabled",true);
	$("#two_other_detail").attr("disabled",true);
	$("#two_logic").attr("disabled",true);
	$("#two_logic_detail").attr("disabled",true);
	$("#two_creative").attr("disabled",true);
	$("#two_creative_detail").attr("disabled",true);
	$("#two_team").attr("disabled",true);
	$("#two_team_detail").attr("disabled",true);
	$("#two_continuouslearning").attr("disabled",true);
	$("#two_continuouslearning_detail").attr("disabled",true);
	$("#two_outstanding").attr("disabled",true);
	$("#two_outstanding_detail").attr("disabled",true);
	$("#two_suggest_salary").attr("disabled",true);
	$("#two_conclusion").attr("disabled",true);
	$("#two_conclusion_detail").attr("disabled",true);
	$("#two_allocation_idea").attr("disabled",true);
	$("#hr_name").attr("disabled",true);
	$("#two_sum").attr("disabled",true);
	$("#hr_suggest_salary").attr("disabled",true);
	$("#hr_detail_idea").attr("disabled",true);
}

function disabledOne(){
	$("#one_code").attr("disabled",true);
	$("#one_code_detail").attr("disabled",true);
	$("#one_algorithm").attr("disabled",true);
	$("#one_algorithm_detail").attr("disabled",true);
	$("#one_network").attr("disabled",true);
	$("#one_network_detail").attr("disabled",true);
	$("#one_experience").attr("disabled",true);
	$("#one_experience_detail").attr("disabled",true);
	$("#one_other").attr("disabled",true);
	$("#one_other_detail").attr("disabled",true);
	$("#one_logic").attr("disabled",true);
	$("#one_logic_detail").attr("disabled",true);
	$("#one_creative").attr("disabled",true);
	$("#one_creative_detail").attr("disabled",true);
	$("#one_team").attr("disabled",true);
	$("#one_team_detail").attr("disabled",true);
	$("#one_continuouslearning").attr("disabled",true);
	$("#one_continuouslearning_detail").attr("disabled",true);
	$("#one_outstanding").attr("disabled",true);
	$("#one_outstanding_detail").attr("disabled",true);
	$("#one_conclusion").attr("disabled",true);
	$("#one_suggest_salary").attr("disabled",true);
	$("#one_suggest_salary_detail").attr("disabled",true);
	$("#one_allocation_idea").attr("disabled",true);
	$("#one_sum").attr("disabled",true);
	$("#one_suggest").attr("disabled",true);
	
	$("#two_code").attr("disabled",false);
	$("#two_code_detail").attr("disabled",false);
	$("#two_algorithm").attr("disabled",false);
	$("#two_algorithm_detail").attr("disabled",false);
	$("#two_network").attr("disabled",false);
	$("#two_network_detail").attr("disabled",false);
	$("#two_experience").attr("disabled",false);
	$("#two_experience_detail").attr("disabled",false);
	$("#two_other").attr("disabled",false);
	$("#two_other_detail").attr("disabled",false);
	$("#two_logic").attr("disabled",false);
	$("#two_logic_detail").attr("disabled",false);
	$("#two_creative").attr("disabled",false);
	$("#two_creative_detail").attr("disabled",false);
	$("#two_team").attr("disabled",false);
	$("#two_team_detail").attr("disabled",false);
	$("#two_continuouslearning").attr("disabled",false);
	$("#two_continuouslearning_detail").attr("disabled",false);
	$("#two_outstanding").attr("disabled",false);
	$("#two_outstanding_detail").attr("disabled",false);
	$("#two_suggest_salary").attr("disabled",false);
	$("#two_conclusion").attr("disabled",false);
	$("#two_conclusion_detail").attr("disabled",false);
	$("#two_allocation_idea").attr("disabled",false);
	$("#hr_name").attr("disabled",true);
	$("#two_sum").attr("disabled",false);
	$("#hr_suggest_salary").attr("disabled",true);
	$("#hr_detail_idea").attr("disabled",true);
	$("#two_suggest").attr("disabled",false);
}

function doQuit(){
	if(confirm("请确保面试完毕再退出系统")){
		window.location.href="/interviewer/quit";
	}
}



