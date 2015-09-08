$(document).ready(function () {
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
	    		  $("#year").append("<option value=''>"+year+"</option>");
	    	  });
	    	showViewerMonitor();
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	$("#year").change(function(){
		 var year = $("#year").find("option:selected").text();
		 getPhaseAndCity(year);
		
	 });
	 $("#phase").change(function(){
		 var phaseName = $("#phase").find("option:selected").text();
		 var vars = {};
		 vars["year"] = $("#year").find("option:selected").text();
		 vars["phaseName"] = phaseName;
	     var params = {"vars": vars};
		 $.ajax({
		      url: "/getCityByYearAndPhase",
		      type: "POST",
		      dataType: "json",
		      contentType: 'application/json; charset=utf-8',
		      data: JSON.stringify(params),
		      success: function (phase) {
		    	  setCityOption(phase.cityName)
		 		  backToIndex();
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
		 		   backToIndex();
		      },
		      error: function () {
		           alert("系统发生了错误请稍后重试");
		      }
		    });

	 });
});

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
	    	  $("#phase").empty();
	    	  $.each(list, function(i, phase){
    			  $("#phase").append("<option value=''>"+phase.phaseName+"</option>");
	    		  if(i == 0){
	    			  setCityOption(phase.cityName);
	    		  }
	    	  });
	 		 backToIndex();
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
//	alert('setCityOption in recordphasecity.js');
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


