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
		    	  $("#noview").hide();
		    	  $("#goon").hide();
		    	  $("#havarest").hide();
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
		    	  alert(data.mesage);
		    	  alert(data.student);
		    	  alert(data.student.name);
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	});
	
	$("#beginToInterview").click(function(){
		$.ajax({
		      url: "interviewer/beginToInterview",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      success: function (set) {
		    	  clock();
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
		    	  clock();
			  },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	 });
	 $("#noComeFinish").click(function(){
		 $.ajax({
		      url: "interviewer/noComeFinish",
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
var timeIndex = 0;
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

function clock(){
	setInterval(setTime, 1000);
}

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


$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {   
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};  

