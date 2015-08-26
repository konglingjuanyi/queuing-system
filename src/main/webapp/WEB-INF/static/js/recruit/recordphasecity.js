$(document).ready(function () {
	$.ajax({
	      url: "/getFirstPhase",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function (list) {
//	    	  alert(returnedData.phaseName);
	    	 // $("#select_id").prepend("<option value='0'>请选择</option>");
//	    	  console.dir(returnedData);
//	    	  $("#phase").append("<option value=''>"+returnedData.phaseName+"</option>"); //为Select追加一个Option(下拉项) 
//	    	  $("#phase").val(returnedData.phaseName);
	    	  $.each(list, function(i, phase){
	    		  $("#phase").append("<option value=''>"+phase.phaseName+"</option>");
	    		  if(i == 0){
	    			  setCityOption(phase.cityName);
	    		  }
//	    		  console.dir(phase);
	    	  });
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	 $("#phase").change(function(){
		 var phaseName = $("#phase").find("option:selected").text();
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
	 });
	 $("#city").change(function(){
		 var city = $("#city").find("option:selected").text();
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
	 });
});

function setCityOption(phaseName){
	$("#city").empty();
	if(!phaseName)
		return;
	array = phaseName.split("|");
	$.each(array, function(i, cityName){
		  $("#city").append("<option value=''>"+cityName+"</option>");
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
//	    	  $("#phase").append("<option value=''>"+returnedData.phaseName+"</option>"); //为Select追加一个Option(下拉项) 
//	    	  $("#phase").val(returnedData.phaseName);
	    	  $.each(list, function(i, phase){
	    		  $("#phase").append("<option value=''>"+phase.phaseName+"</option>");
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
	 $("#phase").click(function(){
		 
	 });
}


