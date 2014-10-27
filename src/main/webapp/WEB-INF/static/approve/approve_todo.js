function loadCurrentApproveTable() {
	var url = "http://nuby.zhang.qunar.com:8080/engine/approve/query/todo";
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
            {"sTitle": '<lable><input id="current-approve-table-select-all" ' +
                'type="checkbox" value="ALL" class="ace"><span class="lbl"></span></label>',
            "sWidth": "30px", "bSortable": false},
            {"sTitle": '所属系统', "sWidth": "200px"},
			{"sTitle": '申请人', "sWidth": "120px"},
            {"sTitle": '描述'},
            {"sTitle": '申请时间' , "sWidth": "150px"},
			{"sTitle": '', "sWidth": "60px"},
        ],
        aaSorting: [[ 1, "desc" ]],
        sAjaxSource: url,
        fnServerData : function(sSource, aDataSet, fnCallback) {
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
                            	if(_PROCESS_DEFINE == null || _PROCESS_DEFINE.type == 's'){
                            		list.push('&nbsp;&nbsp;&nbsp;&nbsp;');
                            	}else{
                            		list.push(String.format('<label><input value="{0}" type="checkbox" class="ace"> <span class="lbl"></span></label>', info[1]));
                            	}
                            	list.push(info[2]);
                            	list.push(info[3]);
                            	list.push(info[4]);
                            	list.push(info[5]);
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

function approvePass(oTable) {
    var url = "http://nuby.zhang.qunar.com:8080/engine/approve/task/complet";
    approveProxy(oTable, url, "", "通过");
}

function approveReject(oTable) {
    var url = "http://nuby.zhang.qunar.com:8080/engine/approve/task/reject";
    var dialog_width = Math.min(Math.round(window.screen.width*0.7), 900);
    try {
        $("#reject-msg-dialog").empty();
        $("#reject-msg-dialog").append(createRejectMsgForm());
        var dialog = $("#reject-msg-dialog").removeClass('hide').dialog({
            modal: true,
            width: dialog_width,
            height: 200,
            title: '<div style="color: DodgerBlue; padding: 4px; height: 28px; font-size: 14px; font-weight: bold"><li class="icon-user"></li> 驳回理由</div>',
            title_html: true,
            closeOnEscape: false,
            open: function(event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            buttons: [
                {
                    id: "reject-msg-submit",
                    text: "提交",
                    "class": "btn btn-primary btn-xs",
                    click: function(e) {
                        var Me = this;
                        enableCurrentApproveToolbarButton(false);
                        try {
                            var this_form = $("#reject-msg-dialog").find("form");
                            var postData = this_form.serializeArray();
                            for (var i in postData) {
                                if (postData[i]['name'] == 'msg') {
                                    if (postData[i]['value'].length >= 100) {
                                        enableAccountApplicationDialogButton(true);
                                        showCommonNoticeDialog('申请理由错误', 'icon-warning-sign',
                                            generateNoticeMsg('申请理由不能超过100个字符'), 300);
                                        return;
                                    }
                                }
                                msg = postData[i]['value'];
                            }
                            approveProxy(oTable, url, msg, "驳回");
                            e.preventDefault(); //STOP default action
                            $(this).dialog("close"); 
                        } catch (e) {
                            enableAccountApplicationDialogButton(true);
                            showCommonNoticeDialog('网络错误', 'icon-warning-sign',
                                generateNoticeMsg('网络错误，请刷新后重试!'), 300);
                        }
                    } 
                },
                {
                    id: "reject-msg-cancel",
                    text: "取消",
                    "class" : "btn btn-xs",
                    click: function() {
                        $(this).dialog("close"); 
                    } 
                }
            ]
        });
    } catch (e) {
        showCommonNoticeDialog('网络错误', 'icon-warning-sign',
            generateNoticeMsg('网络错误，请刷新后重试!'), 300);
    }
}

function approveProxy(oTable, url, msg, action) {
    try {
    
        $("#approve-page").mask("加载中...");
        enableCurrentApproveToolbarButton(false);
        var approve_list = new Array(); 
        $("input:checked", oTable.fnGetNodes()).each(function() {
            var row = $(this).parent().parent().parent();
            var nTds = $('td', row);
            var id = $(this).val();
            approve_list += id+",";
        });
        var params = {
            "taskIds": approve_list,
        }
        if (approve_list.length < 1) {
            $("#approve-page").unmask();
            enableCurrentApproveToolbarButton(true);
            showApproveResultDialog('审批', 'icon-lightbulb', 
                generateNoticeMsg('请选择要审批的条目!'), 300);
            return;
        }
    	params = {};
    	vars = {};
    	vars["taskIds"] = approve_list;
    	vars["reason"] = msg;
    	params["userId"] = "nuby";
    	params["vars"] = vars;
    	
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            url: url,
            type: "post",
            data: JSON.stringify(params),
            success: function(resp) {
                var title_html_str = '';
                try {
                    if (resp.errorCode == 0) {
                        title_html_str = '<div class="dialog_title"><li class="icon-info-sign"></li> 审批'+action+'</div>';
                        var succeed_html = '<b><li class="icon-check"></li> 审批'+action+'成功</b><br/>';
                        $("#approve-result-dialog").append(generateNoticeMsg(succeed_html));
                    } else {
                        title_html_str = '<div class="dialog_title"><li class="icon-exclamation-sign"></li>审批'+action+'</div>';
                        $("#approve-result-dialog").append(generateNoticeMsg(resp.errorMessage));
                    }
                } catch (e) {
                }
                $("#approve-page").unmask();
                var dialog = $("#approve-result-dialog").removeClass('hide').dialog({
                    modal: true,
                    width: 400,
                    resizable: true,
                    title: title_html_str,
                    title_html: true,
                    closeOnEscape: false,
                    buttons: [
                        {
                            text: "确定",
                            "class" : "btn btn-primary btn-xs",
                            click: function(){
                                enableCurrentApproveToolbarButton(true);
                                $("#approve-result-dialog").empty()
                                $("#approve-result-dialog").dialog('destroy')
                            }
                        }
                    ],
                    close: function() {
                        enableCurrentApproveToolbarButton(true);
                        $("#approve-result-dialog").empty();
                        $("#approve-application-result-dialog").dialog('destroy');
                    }
                });
                reloadCurrentApproveTable();
            },
            "error": function(jqxhr, textstatus, errorthrown) {
                $("#approve-page").unmask();
                enableCurrentApproveToolbarButton(true);
                showApproveResultDialog('网络错误', 'icon-warning-sign', 
                    generateNoticeMsg('网络错误，请刷新后重试!'), 300);
            }
        });
    } catch(e) {
        $("#approve-page").unmask();
    }
}

function enableCurrentApproveToolbarButton(enable) {
    if (enable) {
        $("#approve-pass").removeAttr('disabled');
        $("#approve-reject").removeAttr('disabled');
    } else {
        $("#approve-pass").attr('disabled', 'true');
        $("#approve-reject").attr('disabled', 'true');
    }
}

function showApproveResultDialog(header, header_icon, msg, width) {
    $("#approve-result-dialog").append(msg);
    title_html_str = String.format('<div class="dialog_title"><li class="{0}"></li> {1}</div>', header_icon, header);
    var edit_dialog = $("#approve-result-dialog").removeClass('hide').dialog({
        modal: true,
        width: width,
        resizable: true,
        title: title_html_str,
        title_html: true,
        closeOnEscape: false,
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        buttons: [
            {
                text: "确定",
                "class" : "btn btn-primary btn-xs",
                click: function(){
                    $("#approve-result-dialog").empty()
                    $("#approve-result-dialog").dialog('destroy')
                }
            }
        ],
        close: function() {
            $("#approve-result-dialog").empty()
            $("#approve-result-dialog").dialog('destroy')
        }
    });
}

function createRejectMsgForm() {
    var tips = 'Tips: 拒绝理由请不要超过100个字符';
    var form = '<form style="width: 100%; background-color: white"> ';
    form += '<div>';
    form += String.format('<textarea class="form-control" name="msg" style="height: {0}px;" placeholder="{1}"></textarea>', 100, tips);
    form += '</div>';
    form += '</form>';
    return form;
}