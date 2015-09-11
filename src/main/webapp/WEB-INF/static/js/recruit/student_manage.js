$(document).ready(function () {
	$("#doimport").click(function(){
		//$("#selectfile").css("display","");
		$("#selectfile").modal("show");
	});
	
	$("#import").click(function(){
		//$("#impform").submit();
		active($('#stupage'));
		if($('#fileField').val()==""){
             alert("请先选择要导入文件！");
             return false;
        }else{
           $('#impform').ajaxSubmit({
                 url:"/hr/importStudentInfos",
                 cache:false,
                 dataType:'html',
                 success: function(data) {
                	 alert("导入成功");
                	 $("#selectfile").modal("hide");
                	 $('#studentInfoInner').html(data);
                 },
                 error:function(){
                     alert("error");
                 }
           });
      }
	});
	
	$("#assignView").click(function(){
		active($('#stupage'));
		var name=$("input[name='viewnames']:checked").val();
		var id=$("#stuid").val().trim();
		if(name=="" || name=="undefined"){
             alert("请选择一个指派的面试官");
             return false;
        }else{
           $.ajax({
                 url:"/hr/assignInterviewer",
                 cache:false,
                 dataType:'html',
                 data:{'id':id,'name':name},
                 success: function(data) {
                	 alert("指派成功");
                	 $("#viewDiv").hide();
                	 $('#studentInfoInner').html(data);
                 },
                 error:function(){
                     alert("error");
                 }
           });
      }
	});
	
	$('#addstu').click(function(){
		active($('#stupage'));
	    $.ajax({
	      url: "/hr/gotoAddStudentInfo",
	      type: "POST",
	      dataType: "html",
	      contentType: 'application/json; charset=utf-8',
	      success: function (returnedData) {
	    	  $('#content').html(returnedData);
	    	  getAddStudentYearPhaseAndCity();
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	});
	
	$('#dosubmitstu').click(function(){
	    $('#doaddform').ajaxSubmit({
            url:"/hr/AddStudentInfo",
            cache:false,
            dataType:'html',
            success: function(data) {
            	active($('#stupage'))
        	    $.ajax({
        	      url: "/hr/lead2StudentPage",
        	      type: "POST",
        	      dataType: "html",
        	      contentType: 'application/json; charset=utf-8',
        	      success: function (returnedData) {
        	    	  $('#content').html(returnedData);
        	    	  $.ajax({
        	    	      url: "/hr/getAllStudentInfos",
        	    	      type: "POST",
        	    	      dataType: "html",
        	    	      contentType: 'application/json; charset=utf-8',
        	    	      success: function (returnedData) {
        	    	    	  $('#studentInfoInner').html(returnedData);
        	    		  },
        	    	      error: function () {
        	    	           alert("系统发生了错误请稍后重试");
        	    	      }
        	    	    });
        		  },
        	      error: function () {
        	           alert("系统发生了错误请稍后重试");
        	      }
        	    });
            },
            error:function(){
                alert("error");
            }
      });
	});
	
	$('#doupdstu').click(function(){
	    $('#doupdform').ajaxSubmit({
            url:"/hr/updStudentInfo",
            cache:false,
            dataType:'html',
            success: function(data) {
            	active($('#stupage'))
        	    $.ajax({
        	      url: "/hr/lead2StudentPage",
        	      type: "POST",
        	      dataType: "html",
        	      contentType: 'application/json; charset=utf-8',
        	      success: function (returnedData) {
        	    	  $('#content').html(returnedData);
        	    	  $.ajax({
        	    	      url: "/hr/getAllStudentInfos",
        	    	      type: "POST",
        	    	      dataType: "html",
        	    	      contentType: 'application/json; charset=utf-8',
        	    	      success: function (returnedData) {
        	    	    	  $('#studentInfoInner').html(returnedData);
        	    		  },
        	    	      error: function () {
        	    	           alert("系统发生了错误请稍后重试");
        	    	      }
        	    	    });
        		  },
        	      error: function () {
        	           alert("系统发生了错误请稍后重试");
        	      }
        	    });
            },
            error:function(){
                alert("error");
            }
      });
	});
	
	$('#docancel').click(function(){
		active($('#stupage'))
	    $.ajax({
	      url: "/hr/lead2StudentPage",
	      type: "POST",
	      dataType: "html",
	      contentType: 'application/json; charset=utf-8',
	      success: function (returnedData) {
	    	  $('#content').html(returnedData);
	    	  $.ajax({
	    	      url: "/hr/getAllStudentInfos",
	    	      type: "POST",
	    	      dataType: "html",
	    	      contentType: 'application/json; charset=utf-8',
	    	      success: function (returnedData) {
	    	    	  $('#studentInfoInner').html(returnedData);
	    		  },
	    	      error: function () {
	    	           alert("系统发生了错误请稍后重试");
	    	      }
	    	    });
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	});
	
	$('#dosubmitHr').click(function(){
		var stuid=$("#studentid").val().trim();
		param = getSubmitParam(stuid);
	    $.ajax({
            url:"/hr/AddHrStudentAssess",
            type: "POST",
            dataType:'html',
		    data: JSON.stringify(param),
		    contentType: 'application/json; charset=utf-8',
            success: function(data) {
            	active($('#stupage'))
        	    $.ajax({
        	      url: "/hr/lead2StudentPage",
        	      type: "POST",
        	      dataType: "html",
        	      contentType: 'application/json; charset=utf-8',
        	      success: function (returnedData) {
        	    	  $('#content').html(returnedData);
        	    	  $.ajax({
        	    	      url: "/hr/getAllStudentInfos",
        	    	      type: "POST",
        	    	      dataType: "html",
        	    	      contentType: 'application/json; charset=utf-8',
        	    	      success: function (returnedData) {
        	    	    	  $('#studentInfoInner').html(returnedData);
        	    		  },
        	    	      error: function () {
        	    	           alert("系统发生了错误请稍后重试");
        	    	      }
        	    	    });
        		  },
        	      error: function () {
        	           alert("系统发生了错误请稍后重试");
        	      }
        	    });
            },
            error:function(){
                alert("error");
            }
      });
	});
});

function getAddStudentYearPhaseAndCity(){
	$.ajax({
	      url: "/getAllYears1",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function (set) {
	    	  $.each(set, function(i, year){
	    		  if(i == 0){
	    			  getPhaseAndCity1(year);
	    		  }
	    		  $("#year_").append("<option value='"+year+"'>"+year+"</option>");
	    	  });
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	$("#year_").change(function(){
		 var year = $("#year_").find("option:selected").text();
		 getPhaseAndCity1(year);
		
	 });
	 $("#phase_").change(function(){
		 var phaseName = $("#phase_").find("option:selected").text();
		 var vars = {};
		 vars["year"] = $("#year_").find("option:selected").text();
		 vars["phaseName"] = phaseName;
	     var params = {"vars": vars};
		 $.ajax({
		      url: "/getCityByYearAndPhase1",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      data: JSON.stringify(params),
		      success: function (phase) {
		    	  setCityOption2(phase.cityName)
		      },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	 });
	 $("#city_").change(function(){
		 var city = $("#city_").find("option:selected").text();
		 var vars = {};
		 vars["city"] = city;
	     var params = {"vars": vars};
		 $.ajax({
		      url: "/updateOprateCity1",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      data: JSON.stringify(params),
		      success: function (phase) {
		    	  
		      },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
	 });
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

function doclose(){
	$('input[type="file"]').val("");
	$("#selectfile").modal("hide");
}

function getPhaseAndCity1(year){
	 var vars = {};
	 vars["year"] = year;
    var params = {"vars": vars};
	$.ajax({
	      url: "/getPhaseAndCityByYear1",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      data: JSON.stringify(params),
	      success: function (list) {
	    	  $("#phase_").empty();
	    	  $.each(list, function(i, phase){
   			  $("#phase_").append("<option value='"+phase.phaseName+"'>"+phase.phaseName+"</option>");
	    		  if(i == 0){
	    			  setCityOption2(phase.cityName);
	    		  }
	    	  });
	      },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
}


function isExistOption(select,value) {
	var isExist = false;
	var count = select.find('option').length;
	for(var i=0;i<count;i++){
		if(select.get(0).options[i].value == value){
			isExist = true;
			break;
		}
	}
	return isExist;
}


function setCityOption2(phaseName){
	$("#city_").empty();
	if(!phaseName)
		return;
	array = phaseName.split("|");
	$.each(array, function(i, cityName){
		  $("#city_").append("<option value='"+cityName+"'>"+cityName+"</option>");
	});
}

function setCityOption1(phaseName){
	
	$.ajax({
	      url: "/getCityByPhase1",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      data: JSON.stringify(params),
	      success: function (list) {
//	    	  alert(returnedData.phaseName);
	    	 // $("#select_id").prepend("<option value='0'>请选择</option>");
//	    	  console.dir(returnedData);
//	    	  $("#phase_").append("<option value=''>"+returnedData.phaseName+"</option>"); //为Select追加一个Option(下拉项) 
//	    	  $("#phase_").val(returnedData.phaseName);
	    	  $.each(list, function(i, phase){
	    		  $("#phase_").append("<option value=''>"+phase.phaseName+"</option>");
	    		  if(i == 0){
	    			  setCityOption2(phase.phaseName);
	    		  }
//	    		  console.dir(phase);
	    	  });
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	 $("#phase_").click(function(){
		 
	 });
}

function showTime() {
    WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});
}

function doSel(){
	var finish_value =[];
	$('input[name="refusetype"]:checked').each(function(){    
		finish_value.push($(this).val());    
	});
	var refusetype="";
	if(finish_value.length>0){
		refusetype=finish_value.join(",");
	}
	$("#refuse").val(refusetype);
}

function dodelete(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
	        url: "/hr/deleteStudentInfo",
            cache:false,
            dataType:'html',
            data: {'id':id},
            success: function(data) {
           	 $("#selectfile").modal("hide");
           	 $('#studentInfoInner').html(data);
            },
            error:function(){
                alert("error");
            }
	 });
	}
}

function doSelect(id){
	active($('#stupage'));
    $.ajax({
      url: "/hr/gotoSelectStudentInfo",
      type: "POST",
      dataType: "html",
      data: {'id':id},
      success: function (returnedData) {
    	  $('#content').html(returnedData);
    	  var stuid=$("#studentid").val();
    	  var vars = {};
 		  vars["stuid"] = stuid;
 	      var params = {"vars": vars};
    	  $.ajax({
    	      url: "/hr/SelectStudentAssessInfo",
    	      type: "POST",
    	      dataType: "json",
    	      data: JSON.stringify(params),
    	      contentType: 'application/json; charset=utf-8',
    	      success: function (ret) {
    	    	  console.dir(ret);
    	    	  if(ret.errorCode==0){
    	    		  data=ret.data;
        	    	  var stu=data.student;
        	    	  var access=data.assess;
        	    	  if(access!=null){
    	    			  addValue(access);
    	    		  }
        	    	  $("#student_name").text(stu.name);
			    	  $("#jobTitle").text(stu.job);
			    	  
			    	  $("#first_viewer").text(stu.firstTry);
		    		  $("#second_viewer").text(stu.secondTry);
		    		  
			    	  $("#student_school").text(stu.school);
			    	  $("#student_profession").text(stu.profession);
			    	  $("#student_edu").text(stu.education);
			    	  $("#student_phone").text(stu.phone);
    	    	  }else{
    	    		  alert("没有查到任何信息");
    	    	  }
    		  },
    	      error: function () {
    	           alert("系统发生了错误请稍后重试");
    	      }
    	    });
	  },
      error: function () {
           alert("系统发生了错误请稍后重试");
      }
    });
}

function doAssign(id){
	active($('#stupage'));
	$("#viewDiv").show();
	$("#stuid").val(id);
}

function docancel(){
	active($('#stupage'));
	$("#viewDiv").hide();
	$("#stuid").val("");
}

function doUpdById(id){
	active($('#stupage'));
	$.ajax({
	      url: "/hr/gotoUpdStudentInfo",
	      type: "POST",
	      dataType: "html",
	      data: {'id':id},
	      success: function (returnedData) {
	    	  $('#content').html(returnedData);
	    	  getAddStudentYearPhaseAndCity();
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
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
	$("#two_conclusion").val(access.twoConclusion);
	$("#two_conclusion_detail").val(access.twoConclusionDetail);
	$("#one_allocation_idea").val(access.oneAllocationIdea);
	$("#two_allocation_idea").val(access.twoAllocationIdea);
	$("#hrName").val(access.hrName);
	$("#one_sum").val(access.oneSum);
	$("#two_sum").val(access.twoSum);
	$("#hrSuggestSalary").val(access.hrSuggestSalary);
	$("#hrDetailIdea").val(access.hrDetailIdea);
	$("#hr_conclusion").val(access.hrConclusion);
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
	if(access.twoConclusion=='卓越'){
		if(access.twoSuggestSalary!=null && access.twoSuggestSalary!=''){
			$("#two_suggest_salary").val(access.twoSuggestSalary);
			$("#two_suggest").css("display","");
		}else{
			$("#two_suggest_salary").val("");
			$("#two_suggest").css("display","");
		}
	}else{
		$("#two_suggest_salary").val("");
		$("#two_suggest").css("display","none");
	}
	
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
	}else{
		if($("#two_conclusion").val()=='卓越'){
			$("#two_suggest").css("display","");
		}else{
			$("#two_suggest").css("display","none");
		}
	}
	
}

function getSubmitParam(id){
	vars = {};
	vars['studentid'] = id;
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
	vars['hr_name'] = $("#hrName").val();
	vars['one_sum'] = $("#one_sum").val();
	vars['two_sum'] = $("#two_sum").val();
	vars['hr_suggest_salary'] = $("#hrSuggestSalary").val();
	vars['hr_detail_idea'] = $("#hrDetailIdea").val();
	vars['hr_conclusion'] = $("#hr_conclusion").find("option:selected").text();
	var params = {"vars": vars};
	return params;
}



