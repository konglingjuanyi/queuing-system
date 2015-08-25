$(document).ready(function () {
	$('#phase_list').click(function(){
		$('#content').load('/hr/getPhaseInfos');
	});
	
	
	$('#addphase').click(function(){
		$("#addform").modal("show");
	});
});
