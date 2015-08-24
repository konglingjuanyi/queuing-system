$(document).ready(function () {
	$("#regist").click(function(){
		var phone = $("#phone").val();
		var username = $("#name").val();
		if(phone=='' || username==''){
			$("#showError").css("display","");
			setTimeout("hidDiv()",3000);
			return false;
		}else{
			//alert(phone+"========"+username);
			$("#registForm").attr("action","/student/register");
			$("#registForm").submit();
		}
	});
});

function hidDiv(){
	$("#showError").css("display","none");
}