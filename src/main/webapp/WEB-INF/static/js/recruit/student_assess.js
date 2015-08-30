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
	    		  
	    	  }else{
		    	  data = result.data;
		    	  inter = data[0];
		    	  phase_inter = data[1];
		    	  determine=phase_inter.determine;
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
		    	  }else{
		    		  var stu=data.student;
			    	  var inter=data.phaseInterviewer;
			    	  if(message!=1){
			    		  var access=data.assess;
			    		  /***渲染评估表****/
			    		  if(access!=null){
			    			  addValue(access);
			    		  }
			    	  }else{
			    		  setAllVal();
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
	
	$("#beginToInterview").click(function(){
		$("#beginToInterview").attr("disabled", true);
		$("#getOneInterview").attr("disabled", true);
		$("#finishInterview").attr("disabled", false);
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
			 if(determine.indexOf(stujob)!=-1){
				 $("#noview").css("display","");
			 }
		 }
		 $("#goon").css("display","");
		 $("#havarest").css("display","");
	 });
	
	 $("#finishAndContinue").click(function(){
		 var decheckbox;
		 $("input[name='determine']:checked").each(function(){    
			 decheckbox$(this).val();    
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
		    	  }else{
		    		  var stu=data.student;
			    	  var inter=data.phaseInterviewer;
			    	  if(message!=1){
			    		  var access=data.assess;
			    		  /***渲染评估表****/
			    		  if(access!=null){
			    			  addValue(access);
			    		  }
			    	  }else{
			    		  setAllVal();
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
			 decheckbox$(this).val();    
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
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	 });
	 
	 $("#noComeFinish").click(function(){
		 clearInterval(times);
		 timeIndex=0;
		 setTime();
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
		    	  }else{
		    		  var stu=data.student;
			    	  var inter=data.phaseInterviewer;
			    	  if(message!=1){
			    		  var access=data.assess;
			    		  /***渲染评估表****/
			    		  if(access!=null){
			    			  addValue(access);
			    		  }
			    	  }else{
			    		  setAllVal();
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

});

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
	$("#one_code").attr("value",access.oneCode);
	$("#one_code_detail").val(access.oneCodeDetail);
	$("#one_algorithm").attr("value",access.oneAlgorithm);
	$("#one_algorithm_detail").val(access.oneAlgorithmDetail);
	$("#one_network").attr("value",access.oneNetwork);
	$("#one_network_detail").val(access.oneNetworkDetail);
	$("#one_experience").attr("value",access.oneExperience);
	$("#one_experience_detail").val(access.oneExperienceDetail);
	$("#one_other").attr("value",access.oneOther);
	$("#one_other_detail").val(access.oneOtherDetail);
	$("#one_logic").attr("value",access.oneLogic);
	$("#one_logic_detail").val(access.oneLogicDetail);
	$("#one_creative").attr("value",access.oneCreative);
	$("#one_creative_detail").val(access.oneCreativeDetail);
	$("#one_team").attr("value",access.oneTeam);
	$("#one_team_detail").val(access.oneTeamDetail);
	$("#one_continuouslearning").attr("value",access.oneContinuouslearning);
	$("#one_continuouslearning_detail").val(access.oneContinuouslearningDetail);
	$("#one_outstanding").attr("value",access.oneOutstanding);
	$("#one_outstanding_detail").val(access.oneOutstandingDetail);
	$("#two_code").attr("value",access.twoCode);
	$("#two_code_detail").val(access.twoCodeDetail);
	$("#two_algorithm").attr("value",access.twoAlgorithm);
	$("#two_algorithm_detail").val(access.twoAlgorithmDetail);
	$("#two_network").attr("value",access.twoNetwork);
	$("#two_network_detail").val(access.twoNetworkDetail);
	$("#two_experience").attr("value",access.twoExperience);
	$("#two_experience_detail").val(access.twoExperienceDetail);
	$("#two_other").attr("value",access.twoOther);
	$("#two_other_detail").val(access.twoOtherDetail);
	$("#two_logic").attr("value",access.twoLogic);
	$("#two_logic_detail").val(access.twoLogicDetail);
	$("#two_creative").attr("value",access.twoCreative);
	$("#two_creative_detail").val(access.twoCreativeDetail);
	$("#two_team").attr("value",access.twoTeam);
	$("#two_team_detail").val(access.twoTeamDetail);
	$("#two_continuouslearning").attr("value",access.twoContinuouslearning);
	$("#two_continuouslearning_detail").val(access.twoContinuouslearningDetail);
	$("#two_outstanding").attr("value",access.twoOutstanding);
	$("#two_outstanding_detail").val(access.twoOutstandingDetail);
	$("#one_conclusion").attr("value",access.oneConclusion);
	$("#one_suggest_salary").val(access.oneSuggestSalary);
	$("#two_suggest_salary").val(access.twoSuggestSalary);
	$("#one_suggest_salary_detail").val(access.oneSuggestSalaryDetail);
	$("#two_conclusion").attr("value",access.twoConclusion);
	$("#two_conclusion_detail").val(access.twoConclusionDetail);
	$("#one_allocation_idea").val(access.oneAllocationIdea);
	$("#two_allocation_idea").val(access.twoAllocationIdea);
	$("#hr_name").val(access.hrName);
	$("#one_sum").val(access.oneSum);
	$("#two_sum").val(access.twoSum);
	$("#hr_suggest_salary").val(access.hrSuggestSalary);
	$("#hr_detail_idea").val(access.hrDetailIdea);
}

function setAllVal(){
	$("#one_code").attr("value","0");
	$("#one_code_detail").val("");
	$("#one_algorithm").attr("value","0");
	$("#one_algorithm_detail").val("");
	$("#one_network").attr("value","0");
	$("#one_network_detail").val("");
	$("#one_experience").attr("value","0");
	$("#one_experience_detail").val("");
	$("#one_other").attr("value","0");
	$("#one_other_detail").val("");
	$("#one_logic").attr("value","0");
	$("#one_logic_detail").val("");
	$("#one_creative").attr("value","0");
	$("#one_creative_detail").val("");
	$("#one_team").attr("value","0");
	$("#one_team_detail").val("");
	$("#one_continuouslearning").attr("value","0");
	$("#one_continuouslearning_detail").val("");
	$("#one_outstanding").attr("value","0");
	$("#one_outstanding_detail").val("");
	$("#two_code").attr("value","0");
	$("#two_code_detail").val("");
	$("#two_algorithm").attr("value","0");
	$("#two_algorithm_detail").val("");
	$("#two_network").attr("value","0");
	$("#two_network_detail").val("");
	$("#two_experience").attr("value","0");
	$("#two_experience_detail").val("");
	$("#two_other").attr("value","0");
	$("#two_other_detail").val("");
	$("#two_logic").attr("value","0");
	$("#two_logic_detail").val("");
	$("#two_creative").attr("value","0");
	$("#two_creative_detail").val("");
	$("#two_team").attr("value","0");
	$("#two_team_detail").val("");
	$("#two_continuouslearning").attr("value","0");
	$("#two_continuouslearning_detail").val("");
	$("#two_outstanding").attr("value","0");
	$("#two_outstanding_detail").val("");
	$("#one_conclusion").attr("value","");
	$("#one_suggest_salary").val("");
	$("#two_suggest_salary").val("");
	$("#one_suggest_salary_detail").val("");
	$("#two_conclusion").attr("value","");
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
	if(flag1==1){
		if($("#one_conclusion").val()=='卓越'){
			$("#one_suggest").css("display","");
		}
	}else{
		if($("#two_conclusion").val()=='卓越'){
			$("#two_suggest").css("display","");
		}
	}
	
}



