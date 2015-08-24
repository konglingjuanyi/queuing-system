$(document).ready(function () {
	if($("#showError").html()=='${message}'){
		$("#showError").text("");
	}else{
		$("#showError").css("display","");
		setTimeout("hidDiv()",3000);
	}
	$("#regist").click(function(){
		var phone = $("#phone").val();
		var username = $("#name").val();
		if(phone=='' || username==''){
			$("#showError").text("请输入电话和姓名");
			$("#showError").css("display","");
			setTimeout("hidDiv()",3000);
			return false;
		}else{
			$("#registForm").attr("action","/student/register");
			$("#registForm").submit();
		}
	});
});

function hidDiv(){
	$("#showError").css("display","none");
	$("#showError").text("");
}