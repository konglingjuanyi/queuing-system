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
	
	$('#dosubmitHr').click(function(){
		var id=$("#stuId").val().trim();
		var hrName=$("#hrName").val().trim();
		var salay=$("#hrSuggestSalary").val().trim();
		var hrdetail=$("#hrDetailIdea").val().trim();
	    $('#hrform').ajaxSubmit({
            url:"/hr/AddHrStudentAssess",
            cache:false,
            dataType:'html',
            data:{'id':id,'hrName':hrName,'salay':salay,'hrdetail':hrdetail},
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
});

function getAddStudentYearPhaseAndCity(){
	var year = $("#year").find("option:selected").text();
	$("#year_").append("<option value='"+year+"'>"+year+"</option>");
	var phase = $("#phase").find("option:selected").text();
	$("#phase_").append("<option value='"+phase+"'>"+phase+"</option>");
	var city = $("#city").find("option:selected").text();
	$("#city_").append("<option value='"+city+"'>"+city+"</option>");
	 $("#city_").click(function(){
		 var vars = {};
		 vars["year"] = year;
		 vars["phase"] = phase;
	     var params = {"vars": vars};
		 $.ajax({
		      url: "/getCitysByYearAndPhase",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      data: JSON.stringify(params),
		      success: function (result) {
		    	  phase_info = result[0];
		    	  city_info = phase_info.cityName;
		    	  setCityOption3(city_info);
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

function setCityOption3(phaseName){
//	
	var city = $("#city_").find("option:selected").text();
	$("#city_").empty();
	$("#city_").append("<option value='"+city+"'>"+city+"</option>");
	if(!phaseName)
		return;
	array = phaseName.split("|");
	$.each(array, function(i, cityName){
		if(cityName != city){
			 $("#city_").append("<option value='"+cityName+"'>"+cityName+"</option>");
		}
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

