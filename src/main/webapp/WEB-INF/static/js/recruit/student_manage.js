$(document).ready(function () {
	
	$("#doimport").click(function(){
		//$("#selectfile").css("display","");
		$("#selectfile").modal("show");
	});
	
	$("#import").click(function(){
		$("#impform").submit();
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
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	});
	
	$('#dosubmitstu').click(function(){
	    //$("#doaddform").submit();
		var vars=$('#doaddform').serializeObject();
		active($('#stupage'));
		var params = {"vars": vars};
	    $.ajax({
	      url: "/hr/AddStudentInfo",
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
	});
	
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
	    		  $("#year_").append("<option value=''>"+year+"</option>");
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
});

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
   			  $("#phase_").append("<option value=''>"+phase.phaseName+"</option>");
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
		  $("#city_").append("<option value=''>"+cityName+"</option>");
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

