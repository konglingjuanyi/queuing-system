$(document).ready(function () {
	$.ajax({
	      url: "interviewer/getAllYears",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      success: function (set) {
	    	  $.each(set, function(i, year){
	    		  if(i == 0){
	    			  getPhases(year);
	    		  }
	    		  $("#year").append("<option value=''>"+year+"</option>");
	    	  });
		  },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
	$("#year").change(function(){
		 var year = $("#year").find("option:selected").text();
		 getPhases(year);
		
	 });
	 $("#phase").change(function(){
		 var year = $("#year").find("option:selected").text();
		 var phaseName = $("#phase").find("option:selected").text();
		 getCitys(year, phaseName);
	 	});

	 $("#city").change(function(){
		 var city = $("#city").find("option:selected").text();
		 var vars = {};
		 vars["city"] = city;
	     var params = {"vars": vars};
		 $.ajax({
		      url: "interviewer/updateOprateCity",
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
	 $("#startInterview").click(function(){
		 window.location.href = '/interview/getNextStudent';
	 });
});

function getPhases(year){
	 var vars = {};
	 vars["year"] = year;
     var params = {"vars": vars};
	$.ajax({
	      url: "interviewer/getPhasesByYear",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      data: JSON.stringify(params),
	      success: function (list) {
	    	  $("#phase").empty();
	    	  $.each(list, function(i, phase){
    			  $("#phase").append("<option value=''>"+phase+"</option>");
	    		  if(i == 0){
	    			  getCitys(year, phase);
	    		  }
	    	  });
	      },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
}

function getCitys(year, phase){
	 var vars = {};
	 vars["year"] = year;
	 vars["phase"] = phase;
    var params = {"vars": vars};
	$.ajax({
	      url: "interviewer/getCityByYearAndPhase",
	      type: "POST",
	      dataType: "json",
	      contentType: 'application/json; charset=utf-8',
	      data: JSON.stringify(params),
	      success: function (list) {
	    	  $("#city").empty();
	    	  $.each(list, function(i, city){
   			  $("#city").append("<option value=''>"+city+"</option>");
	    	  });
	      },
	      error: function () {
	           alert("系统发生了错误请稍后重试");
	      }
	    });
}




