function loadCurrentApproveTable() {
	var url = "http://nuby.zhang.qunar.com:8080/engine/approve/query/history";
    $("#approve-page").mask("加载中...");
    var wh = $(window).height();
    var sY = (wh - _TABLE_PADDING) + 'px';
    var oTable = $('#current-approve-table').dataTable({
        bFilter: false,
        bAutoWidth: true,
        bProcessing: false,
        bServerSide: true,
        sDom: 't<"row"<"col-sm-12"i>>',
        bSort: false,
        bScrollCollapse: true,
        iDisplayLength: 20,
        iDisplayStart: 0,
        bScrollInfinite: true,
        sScrollY: sY,
        aoColumns: [
            {"sTitle": '所属系统', "sWidth": "200px"},
			{"sTitle": '申请人', "sWidth": "120px"},
            {"sTitle": '描述'},
            {"sTitle": '申请时间' , "sWidth": "130px"},
            {"sTitle": '完成时间' , "sWidth": "130px"},
            {"sTitle": '状态' , "sWidth": "80px"},
			{"sTitle": '', "sWidth": "60px"},
        ],
        aaSorting: [[ 1, "desc" ]],
        sAjaxSource: url,
        fnServerData : function(sSource, aDataSet, fnCallback) {
            var startDate = $.trim($('#date-from').val());
		    var endDate = $.trim($('#date-to').val());
		    var approveUser = $.trim($('#approve-user').val());
		    var keywords = $.trim($('#search-text').val());
        	var start = 0;
        	var length = 0;
        	for(var i=0; i<aDataSet.length; i++){
        		if(aDataSet[i].name=='iDisplayStart'){
        			start = aDataSet[i].value;
        		}
        		if(aDataSet[i].name=='iDisplayLength'){
        			length = aDataSet[i].value;
        		}
        	}
		    var vars = {};
		    vars["start"] = ""+start;
		    vars["length"] = ""+length;
		    if(startDate != ''){
		    	vars["startTime"] = startDate;
		    }
		    if(endDate != ''){
		    	vars["endTime"] = endDate;
		    }
		    if(keywords != ''){
		    	vars["keywords"] = keywords;
		    }
		    if(approveUser != ''){
		    	vars["approveUser"] = approveUser;
		    }
		    var process_keys = '';
		    $("input[name='process-keys']").each(function() {
		    	var key_name = $(this).val();
		    	if($(this).prop("checked")){
            		process_keys += key_name + ',';
            	}
        	});
        	if(process_keys != ''){
        		vars['processKeys'] = process_keys.substring(0, process_keys.length-1);
        	}
        	var params = {};
		    params["userId"] = "nuby";
		    params["vars"] = vars;
            $.ajax({
                dataType: 'json',
                url: sSource,
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(params),
                success: function(resp) {
                    var aaData = [];
                    if (resp.errorCode == 0) {
                        var iTotalRecords = resp.data.count || 0;
                        var iTotalDisplayRecords = iTotalRecords;
                        var infos = resp.data.aaData;
                        var infos_len = infos.length;
                        if (infos_len > 0) {
                            for (var i = 0; i < infos_len; i++) {
                            	var list = [];
                            	var info = infos[i];
                            	var process = _PROCESS_DEFINE[info[6]];
                            	list.push(info[1]);
                            	list.push(info[2]);
                            	list.push(info[3]);
                            	list.push(info[4]);
                            	list.push(info[5]);
                            	list.push(info[7]);
                            	var map = '<a href="javascript:void(0);" onclick="approveDetail(\''+process.url+'\')"><i class="icon-link"></i></a>';
                            	list.push(map);
                                aaData.push(list);
                            }
                        }
                    }
                    var data = {
                        "aaData": aaData,
                        "iTotalRecords": (iTotalRecords || 0),
                        "iTotalDisplayRecords": (iTotalDisplayRecords || 0)
                    }
                    fnCallback(data);   
                    $("#approve-page").unmask();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    $("#approve-page").unmask();
					fnCallback({aaData: []});
                    showCommonNoticeDialog('网络错误', 'icon-warning-sign',
                        generateNoticeMsg('网络错误，请刷新后重试!'), 300);
                }
            })
        },
        fnInitComplete: function() {
            $('#current-approve-table-select-all').prop('checked', false);
            $('#current-approve-table-select-all').attr('checked', false);
            toggleCheck(oTable);
            hideThead();
            $('#current-approve-table').on('draw.dt', function () {
                hideThead();
            });
        },
        fnDrawCallback: function() {
            $("#search").unbind("click");
            $("#search").on("click", function(e) {
                reloadCurrentApproveTable();
            }).on("dblclick", function(e){ e.preventDefault(); });
            $("#approve-pass").unbind("click");
            $("#approve-pass").on("click", function(e) {
                approvePass(oTable);
            }).on("dblclick", function(e){ e.preventDefault(); });
            $("#approve-reject").unbind("click");
            $("#approve-reject").on("click", function(e) {
                approveReject(oTable);
            }).on("dblclick", function(e){ e.preventDefault(); });
            $("#approve-edit").unbind("click");
            $("#approve-edit").on("click", function(e) {
                approveEdit(oTable);
            }).on("dblclick", function(e){ e.preventDefault(); });
            $(window).unbind('resize', resizeOtable);
            $(window).bind('resize', {otable: oTable}, resizeOtable);
        }
    });
}