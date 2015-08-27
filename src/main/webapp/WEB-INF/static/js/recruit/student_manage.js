$(document).ready(function () {
	$("#doimport").click(function(){
		//$("#selectfile").css("display","");
		$("#selectfile").modal("show");
	});
	
	$("#import").click(function(){
		$("#impform").submit();
	});
});

function doclose(){
	$('input[type="file"]').val("");
	$("#selectfile").modal("hide");
}
