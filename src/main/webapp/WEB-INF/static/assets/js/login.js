$(document).ready(function () {
	if($("#msg").html()=='${message}'){
		$("#msg").text("");
	}else{
		$("#msg").css("display","");
	}
});
function checkUp(){
	var username = $("#username").val();
	var password = $("#password").val();
	if(username=='' || password==''){
		$("#msg").text("请输入用户名和密码");
		$("#msg").css("display","");
		return false;
	}
}