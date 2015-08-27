var popUp = {
	init: function function_name (argument) {
		var self = this;
		var template = '<div id="pop1" class="m-pop m-pop-big alert" style="display: none">'+
			'<button type="button" class="close" data-dismiss="alert">&times;</button>'+
			'<div class="m-pop-hd"><h2 class="title"></h2></div>'+
			'<div class="m-pop-bd">'+
	            '<div class="item">'+
	                '<span class="label">考核点</span>'+
	                '<div class="detail checkpoint"></div>'+
	            '</div>'+
	            '<div class="item">'+
	                '<span class="label">打分标准</span>'+
	                '<div class="detail standard"></div>'+
	            '</div>'+
	            '<div class="item">'+
	                '<span class="label">常用问题</span>'+
	                '<div class="detail issue"></div>'+
	           '</div>'+
	        '</div>'+
	    '</div>';

    	self.temp = $(template);
    	self.closeBtn = self.temp.find(".close");
		self.title = self.temp.find(".title");
    	self.checkpoint = self.temp.find(".checkpoint");
    	self.standard = self.temp.find(".standard");
    	self.issue = self.temp.find(".issue");
    	self.temp.appendTo("body");
    	self.event();
	},
	event: function(){
		var self = this;
		self.closeBtn.on("click", function(){
			self.hide();
		});
	},
	show: function(){
		var self = this;
		self.temp.show();
	},
	hide: function(){
		var self = this;
		self.temp.hide();
	},
	renderData: function(name, data){
		var self = this;
		self.title.html(name);
		self.checkpoint.html(data["考核点"]);
		self.standard.html(data["打分标准"]);
		self.issue.html(data["常用问题"]);
		self.show();
	}
};
$(function(){
	popUp.init();

	var pop2 = $("#pop2"), pop3 = $("#pop3");

	pop2.find(".close").on("click",function(){
		pop2.hide();
	});
	pop3.find(".close").on("click",function(){
		pop3.hide();
	});

	var job = $("#jobTitle").html(),dataObj;
	switch (job){
		case "DEV":
		dataObj = devData;
		break;
		case "FE":
		dataObj = feData;
		break;
		case "QA":
		dataObj = qaData;
		break;
		default:
		dataObj = devData;
		break;
	};

	$("#table1").find("tbody").find("tr").find("td:first a").on("click",function(){
		pop2.hide();
		pop3.hide();
		var name = $(this).attr("data-name");
		popUp.renderData(name, dataObj[name]);
	});

	
	$("#table2").find("tbody").find("tr").find("td:first a").on("click",function(){
		popUp.hide();
		pop3.hide();
		var index = $(this).attr("data-num");
		pop2.show().find("table").eq(index).show().siblings().hide();

	});
	$(".table").find("thead").find("tr").find(".w_2 a").on("click",function(){
		pop2.hide();
		popUp.hide();
		pop3.show();
	});
});
