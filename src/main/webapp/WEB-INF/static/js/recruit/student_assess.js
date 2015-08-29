$(document).ready(function () {
	$.ajax({
	      url: "/interviewer/getCurrentViewInfo",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function (result) {
//	    	  console.dir(result);
	    	  if(result.errorCode == 0){
	    		  
	    	  }else{
		    	  data = result.data;
		    	  inter = data[0];
		    	  phase_inter = data[1];
		    	  $("#interviewer_name").text(inter.userName);
		    	  $("#interviewer_room").text(phase_inter.room);
		    	  $("#already_inter").text(parseInt(phase_inter.oneCount)+parseInt(phase_inter.twoCount));
		    	  //$("#first_viewer_div").hide();
		    	  //$("#second_viewer_div").hide();
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
		    	  var stu=data.student;
		    	  var inter=data.phaseInterviewer;
		    	  if(message!=1){
		    		  var access=data.assess;
		    		  /***渲染评估表****/
		    		  addValue(access);
		    	  }
		    	  $("#student_name").text(stu.name);
		    	  $("#jobTitle").text(stu.job);
		    	  $("#first_viewer").text(inter.intervierName);
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	});
	
	$("#beginToInterview").click(function(){
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
		 param = getSubmitParam();
		 $.ajax({
		      url: "/interviewer/finishInterview",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      data: JSON.stringify(param),
		      success: function (set) {
		    	 
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	 });
	 $("#finishInterview").click(function(){
		 param = getSubmitParam();
		 $.ajax({
		      url: "/interviewer/finishInterview",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      data: JSON.stringify(param),
		      success: function (set) {
		    	 
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
		    	 
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	 });

});

function getSubmitParam(){
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
	vars['one_suggest_salary'] = $("#one_suggest_salary").find("option:selected").text();
	vars['one_suggest_salary_detail'] = $("#one_suggest_salary_detail").val();
	vars['two_conclusion'] = $("#two_conclusion").find("option:selected").text();
	vars['two_conclusion_detail'] = $("#two_conclusion_detail").val();
	vars['one_allocation_idea'] = $("#one_allocation_idea").val();
	vars['two_allocation_idea'] = $("#two_allocation_idea").val();
	vars['hr_name'] = $("#hr_name").val();
	vars['hr_suggest_salary'] = $("#hr_suggest_salary").val();
	vars['hr_detail_idea'] = $("#hr_detail_idea").val();
	var params = {"vars": vars};
	return params;
}


function addValue(access){
	$("#one_code").find("option:selected").text();
	$("#one_code_detail").val();
	$("#one_algorithm").find("option:selected").text();
	$("#one_algorithm_detail").val();
	$("#one_network").find("option:selected").text();
	$("#one_network_detail").val();
	$("#one_experience").find("option:selected").text();
	$("#one_experience_detail").val();
	$("#one_other").find("option:selected").text();
	$("#one_other_detail").val();
	$("#one_logic").find("option:selected").text();
	$("#one_logic_detail").val();
	$("#one_creative").find("option:selected").text();
	$("#one_creative_detail").val();
	$("#one_team").find("option:selected").text();
	$("#one_team_detail").val();
	$("#one_continuouslearning").find("option:selected").text();
	$("#one_continuouslearning_detail").val();
	$("#one_outstanding").find("option:selected").text();
	$("#one_outstanding_detail").val();
	$("#two_code").find("option:selected").text();
	$("#two_code_detail").val();
	$("#two_algorithm").find("option:selected").text();
	$("#two_algorithm_detail").val();
	$("#two_network").find("option:selected").text();
	$("#two_network_detail").val();
	$("#two_experience").find("option:selected").text();
	$("#two_experience_detail").val();
	$("#two_other").find("option:selected").text();
	$("#two_other_detail").val();
	$("#two_logic").find("option:selected").text();
	$("#two_logic_detail").val();
	$("#two_creative").find("option:selected").text();
	$("#two_creative_detail").val();
	$("#two_team").find("option:selected").text();
	$("#two_team_detail").val();
	$("#two_continuouslearning").find("option:selected").text();
	$("#two_continuouslearning_detail").val();
	$("#two_outstanding").find("option:selected").text();
	$("#two_outstanding_detail").val();
	$("#one_conclusion").find("option:selected").text();
	$("#one_suggest_salary").find("option:selected").text();
	$("#one_suggest_salary_detail").val();
	$("#two_conclusion").find("option:selected").text();
	$("#two_conclusion_detail").val();
	$("#one_allocation_idea").val();
	$("#two_allocation_idea").val();
	$("#hr_name").val();
	$("#hr_suggest_salary").val();
	$("#hr_detail_idea").val();
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

function showSuggest(flag){
	if(flag==1){
		if($("#one_conclusion").val()=='卓越'){
			$("#one_suggest").css("display","");
		}
	}else{
		if($("#two_conclusion").val()=='卓越'){
			$("#two_suggest").css("display","");
		}
	}
	
}



