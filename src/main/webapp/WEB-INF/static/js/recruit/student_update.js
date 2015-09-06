$(document).ready(function () {
	var refuse = $("#refuse").val().trim();
	if(refuse!=null && refuse!=''){
		$('input[name="refusetype"]').each(function(){    
			if(refuse.indexOf($(this).val())!=-1){
				$(this).attr("checked",true);
			}
		});
	}
});
