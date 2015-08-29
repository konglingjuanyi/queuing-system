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
});

function getAddStudentYearPhaseAndCity(){
	$.ajax({
	      url: "/getAllYears",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function (set) {
	    	  $.each(set, function(i, year){
	    		  if(i == 0){
	    			  getPhaseAndCity(year);
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
		 getPhaseAndCity(year);
		 backToIndex();
		
	 });
	 $("#phase_").change(function(){
		 var phaseName = $("#phase_").find("option:selected").text();
		 var vars = {};
		 vars["phaseName"] = phaseName;
	     var params = {"vars": vars};
		 $.ajax({
		      url: "/getCityByPhase",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      data: JSON.stringify(params),
		      success: function (phase) {
		    	  setCityOption(phase.cityName)
		      },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });
		 backToIndex();
	 });
	 $("#city_").change(function(){
		 var city = $("#city_").find("option:selected").text();
		 var vars = {};
		 vars["city"] = city;
	     var params = {"vars": vars};
		 $.ajax({
		      url: "/updateOprateCity",
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
		 backToIndex();
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

function getPhaseAndCity(year){
	 var vars = {};
	 vars["year"] = year;
    var params = {"vars": vars};
	$.ajax({
	      url: "/getPhaseAndCityByYear",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      data: JSON.stringify(params),
	      success: function (list) {
	    	  $("#phase_").empty();
	    	  $.each(list, function(i, phase){
   			  $("#phase_").append("<option value='"+phase.phaseName+"'>"+phase.phaseName+"</option>");
	    		  if(i == 0){
	    			  setCityOption(phase.cityName);
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


function setCityOption(phaseName){
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
	      url: "/getCityByPhase",
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
	    			  setCityOption(phase.phaseName);
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

