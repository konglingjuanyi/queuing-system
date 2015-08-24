$(document).ready(function () {
	if($("#showError").html()=='${message}'){
		$("#showError").css("background-color","");
		$("#showError").text("");
		setTimeout("hidDiv()",3000);
	}else{
		$("#showError").css("display","");
		$("#showError").css("background-color","#cccccc");
		setTimeout("hidDiv()",3000);
	}
	$("#regist").click(function(){
		var phone = $("#phone").val();
		var username = $("#name").val();
		if(phone=='' || username==''){
			$("#showError").text("请输入电话和姓名");
			$("#showError").css("display","");
			$("#showError").css("background-color","#cccccc");
			setinterval("hidDiv()",3000);
			return false;
		}else{
			$.ajax({
			    type: 'POST',
			    url: '/student/register',
			    dataType: 'json',
			    success:function(data) {   
			    	alert(data);
			    	setTimeout("refreshPage()",3000);
			     },    
			     error : function() {    
			          alert("出现异常！请稍后重试！");    
			     }
			});
		}
	});
});

function hidDiv(){
	$("#showError").css("display","none");
	$("#showError").text("");
}

function refreshPage(){
	window.location.href="/student/refresh";
}