function setToolTip(selector, duration) {
    $(function() {
        selector.tooltip({
            position: {
                my: "center bottom-20",
                at: "center top",
                using: function( position, feedback ) {
                    $( this ).css( position );
                    $( "<div>" )
                    .addClass( "arrow" )
                    .addClass( feedback.vertical )
                    .addClass( feedback.horizontal )
                    .appendTo( this );
                }
            },
            content: function () {
                return $(this).prop('title');
            },
            hide: {'duration': duration}
        });
    });
}

_TABLE_PADDING = 200;

String.format = function(src) {  
    if (arguments.length == 0) return null;  
    var args = Array.prototype.slice.call(arguments, 1);  
    return src.replace(/\{(\d+)\}/g, function(m, i){  
        return args[i];  
    }); 
}; 

jQuery(function($) {
    loadSystemCheckboxList();
    setToolTip($(document), 1500);
    $('#search-text').keypress(function(event){
        if (event.which == 13){
            getFilterParams();
        }
    });
});

function getFilterParams() {
    var params = {
        sys_filter: [],
        search_text: ''
    }
    // 获取要显示数据的系统列表
    $('#system-checkbox-list input:checked').each(function() {
        params.sys_filter.push($(this).attr('name'));
    });
    // 获取搜索文本
    params.search_text = $('#search-text').val();
    return params;
}

function loadSystemCheckboxList() {
    $.ajax({
        dataType: 'json',
        url: 'get_system_list',
        type: "GET",
        success: function(resp) {
            if (resp.success) {
                var system_list = resp.data;
                var html = ''
                for (var i in system_list) {
                    var sys_name = system_list[i]['name'];
                    var sys_cname = system_list[i]['cname'];
                    html += String.format('<label style="margin-right: 10px;"><input name="{0}" type="checkbox" class="ace"> <span class="lbl"> {1}</span></label>', sys_name, sys_cname);
                }
                $('#system-checkbox-list').html(html);
                $('#system-checkbox-list input').prop('checked', "true");
                reloadCurrentApproveTable();
            } else {
                showCommonNoticeDialog('获取系统列表失败', 'icon-warning-sign',
                    generateNoticeMsg(resp.msg), 300);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            showCommonNoticeDialog('网络错误', 'icon-warning-sign',
                generateNoticeMsg('网络错误，请刷新后重试!'), 300);
        }
    })
}

function reloadCurrentApproveTable() {
    var ex = document.getElementById('current-approve-table');
    if($.fn.dataTable.fnIsDataTable(ex)){
        $(ex).dataTable().fnDestroy();
    }
    loadCurrentApproveTable();
}

function loadCurrentApproveTable() {
    $("#approve-page").mask("加载中...");
    var wh = $(window).height();
    var sY = (wh - _TABLE_PADDING) + 'px';
    var oTable = $('#current-approve-table').dataTable({
        bFilter: false,
        sScrollY: sY,
        bScrollCollapse: true,
        iDisplayLength: -1,
        bLengthChange: false,
        bAutoWidth: false,
        bServerSide: false,
        bInfo: true,
        bScrollInfinite: true,
        sDom: 't<"row"<"col-sm-6"i><"col-sm-6"p>>',
        bSort: true,
        aoColumns: [
            {"sTitle": '<lable><input id="current-approve-table-select-all" ' +
                'type="checkbox" value="ALL" class="ace"><span class="lbl"></span></label>',
            "sWidth": "30px", "bSortable": false},
            {"sTitle": 'ID', "sWidth": "100px"},
            {"sTitle": '所属系统', "sWidth": "120px"},
            {"sTitle": '描述'}
        ],
        aaSorting: [[ 1, "desc" ]],
        aoColumnDefs: [
            {
                mRender: function (data, type, full) {
                    return String.format('<label><input value="{0}" type="checkbox" class="ace"> <span class="lbl"></span></label>', full[1]);
                },
                aTargets: [ 0 ]
            },
        ],
        // TODO 所有申请都从一个接口获取，这个接口中做相应的合并逻辑
        sAjaxSource: "approver/current_approve",
        fnServerData : function(sSource, aDataSet, fnCallback) {
            params = getFilterParams();
            $.ajax({
                dataType: 'json',
                url: sSource,
                type: "POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(params),
                success: function(resp) {
                    if (resp.success) {
                        var data = resp.data.length != 0 ? resp.data : {aaData: []};
                        fnCallback(data);
                    } else {
                        fnCallback({aaData: []});
                        showCommonNoticeDialog('获取审批列表失败', 'icon-warning-sign',
                            generateNoticeMsg(resp.msg), 300);
                    }
                    $("#approve-page").unmask();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    $("#approve-page").unmask();
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
            $("#current-approve-table tbody tr td").unbind("click");
            $("#current-approve-table tbody tr td").on("click", function(e) {
                try {
                    // 第一列不展开详情，只供选择
                    if ($(this).find('input').length != 0) {
                        return;
                    }
                    // 有时间重构吧……
                    var row = $(this).parent();
                    var nTds = $('td', row);
                    var id = $(nTds[1]).text();
                    var system = $(nTds[2]).text();
                    var oTr = $(this).parent();
                    var nTr = oTr[0];

                    if (!oTable.fnIsOpen(nTr)) {
                        oTr.children().css({
                            "color": "RoyalBlue",
                            "font-weight": "bold",
                            "background": "-webkit-gradient(linear, 0 0, 0 100%, from(#fff), to(#eee))",
                            "background": " -moz-linear-gradient(top, #fff, #eee)"
                        });
                        oTr.css({
                            "border-top": "3px solid #dedede",
                            "border-left": "3px solid #dedede",
                            "border-right": "3px solid #dedede",
                            "border-bottom": "3px solid #dedede",
                        });
                        showDetail(oTable, nTr, oTr, id, system);
                    }else{
                        if (oTr.next().children().first().hasClass("detail-table")){
                            oTable.fnClose(nTr);
                            oTr.children().removeAttr("style");
                            oTr.css({
                                "border": "1px solid #dedede"
                            });
                        }
                    }
                } catch (e) {
                }
            }).on("dblclick", function(e){ e.preventDefault(); });
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

function showCommonNoticeDialog(header, header_icon, msg, width) {
    $("#common-notice-dialog").append(msg);
    title_html_str = String.format('<div class="dialog_title"><li class="{0}"></li> {1}</div>', header_icon, header);
    var dialog = $("#common-notice-dialog").removeClass('hide').dialog({
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
                click: function() {
                    $("#common-notice-dialog").empty();
                    $("#common-notice-dialog").dialog('destroy');
                }
            }
        ],
        close: function() {
            $("#common-notice-dialog").empty();
            $("#common-notice-dialog").dialog('destroy');
        }
    });
    $(dialog).parent().find('a.ui-dialog-titlebar-close').remove();
}

function toggleCheck(oTable) {
    $('input', oTable.fnGetNodes()).unbind("click");
    $('input', oTable.fnGetNodes()).on("click", function() {
        $(this).prop('checked', this.checked);
        $(this).attr('checked', this.checked);
    });
    $('#current-approve-table-select-all').unbind("click");
    $('#current-approve-table-select-all').on("click", function() {
        $('input', oTable.fnGetNodes()).prop('checked', this.checked);
        $('input', oTable.fnGetNodes()).attr('checked', this.checked);
    });
}

function generateNoticeMsg(msg) {
    return String.format('<div class="alert alert-info bigger-110" style="margin-bottom: 0px; margin-top: 5px;">{0}</div>', msg);
}

function hideThead() {
    $('#current-approve-table > thead th').removeClass('sorting');
    $('#current-approve-table > thead th').removeClass('sorting_desc');
    $('#current-approve-table > thead th').removeClass('sorting_asc');
}

function resizeOtable(e){
    var wh = $(window).height();
    e.data.otable.fnSettings().oScroll.sY=(wh - _TABLE_PADDING) + 'px';
    e.data.otable.fnAdjustColumnSizing(false);
    hideThead();
}

function showDetail(oTable, nTr, oTr, id, system) {
    $.ajax({
        type: "GET",
        url: String.format("approve/detail?id={0}&system={1}", id, system),
        dataType: "json",
        data: {
            id: id,
            system: system
        },
        success: function(resp) {
            if (resp.success) {
                var table_data = resp.data;
                var table = '<table class="table table-bordered table-striped" style="width: 100%; background-color: #FFFAFA; margin-bottom: 0px;">';
                for (var i in table_data) {
                    var row = table_data[i]
                    if (row['is_title']) {
                        table += String.format('<tr><td><b>{0}</b></td></tr>', row['key']);
                    }else {
                        if(row['is_pre']){
                            table += String.format('<tr><th>{0}</th><td><pre>{1}</pre></td></tr>', row['key'], row['value']);
                        }else{
                            table += String.format('<tr><th>{0}</th><td>{1}</td></tr>', row['key'], row['value']);
                        }
                    }
                }
                table += '</table>'

                oTable.fnOpen(nTr, table, 'detail-table');
                oTr.next().find("tr").children().css({
                    "padding": "6px 8px",
                    "background-color": "#FFFAFA"
                });
                oTr.next().find("th").css({
                    "width": "200px", "text-align": "left",
                    "padding": "6px 16px 6px 8px",
                });
                oTr.next().find("tr").first().children().css("border-top", "0px");
                oTr.next().find(".detail-table").css({
                    "padding": "0px",
                    "border": "3px solid #dedede",
                    "border-top": "0px solid #dedede",
                });
                $(window).trigger('resize');
            } else {
                showCommonNoticeDialog('获取系统列表失败', 'icon-warning-sign',
                    generateNoticeMsg(resp.msg), 300);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            showCommonNoticeDialog('网络错误', 'icon-warning-sign',
                generateNoticeMsg('网络错误，请刷新后重试!'), 300);
        }
    })
}

function approveEdit(oTable) {
    $("#approve-page").mask("加载中...");
    enableCurrentApproveToolbarButton(false);
    var edit_list = new Array(); 
    $("input:checked", oTable.fnGetNodes()).each(function() {
        // 拿到row, 然后获取id和system
        var row = $(this).parent().parent().parent();
        var nTds = $('td', row);
        var id = $(nTds[1]).text();
        var system = $(nTds[2]).text();
        edit_list.push({id: id, system: system});
    });
    var edit_list_length = edit_list.length;
    if (edit_list_length != 1) {
        $("#approve-page").unmask();
        enableCurrentApproveToolbarButton(true);
        if (edit_list_length == 0) {
            showApproveResultDialog('修改', 'icon-lightbulb', 
                generateNoticeMsg('请选择一条要修改的申请记录!'), 300);
        } else {
            showApproveResultDialog('修改', 'icon-lightbulb', 
                generateNoticeMsg('不能同时修改多条申请记录!'), 300);
        }
        return;
    }
    approveEditDispatch(oTable, edit_list[0].id, edit_list[0].system);
}

function approvePass(oTable) {
    var url = "approver/approve/pass";
    var action = '同意';
    var msg = '';
    approveProxy(oTable, action, url, msg);
}

function approveReject(oTable) {
    var url = "approver/approve/reject";
    var action = '拒绝';
    var msg = 'TODO';
    var dialog_width = Math.min(Math.round(window.screen.width*0.7), 900);
    try {
        $("#reject-msg-dialog").empty();
        $("#reject-msg-dialog").append(createRejectMsgForm());
        var dialog = $("#reject-msg-dialog").removeClass('hide').dialog({
            modal: true,
            width: dialog_width,
            height: 200,
            title: '<div style="color: DodgerBlue; padding: 4px; height: 28px; font-size: 14px; font-weight: bold"><li class="icon-user"></li> 拒绝理由</div>',
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
                            approveProxy(oTable, action, url, msg);
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

function approveProxy(oTable, action, url, msg) {
    try {
        $("#approve-page").mask("加载中...");
        enableCurrentApproveToolbarButton(false);
        var approve_list = new Array(); 
        $("input:checked", oTable.fnGetNodes()).each(function() {
            // 拿到row, 然后获取id和system
            var row = $(this).parent().parent().parent();
            var nTds = $('td', row);
            var id = $(nTds[1]).text();
            var system = $(nTds[2]).text();
            approve_list.push({id: id, system: system});
        });
        var params = {
            "approve_list": approve_list,
            "msg": msg
        }

        if (approve_list.length < 1) {
            $("#approve-page").unmask();
            enableCurrentApproveToolbarButton(true);
            showApproveResultDialog('审批', 'icon-lightbulb', 
                generateNoticeMsg('请选择要审批的条目!'), 300);
            return;
        }
        $.ajax({
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            url: url,
            type: "post",
            data: JSON.stringify(params),
            success: function(resp) {
                var title_html_str = '';
                try {
                    if (resp.success) {
                        title_html_str = String.format('<div class="dialog_title"><li class="icon-info-sign"></li> {0}申请</div>', action);
                        var succeed_list = resp.data.succeed;
                        var failed_list = resp.data.failed;
                        if (failed_list.length != 0) {
                            var failed_list_html = '<b style="color: red;"><li class="icon-ban-circle"></li> 失败:</b><br/>';
                            for(var i = 0; i < failed_list.length; i++) { 
                                failed_list_html += '&nbsp;&nbsp;&nbsp;&nbsp;' + failed_list[i] + '<br/>';
                            } 
                            $("#approve-result-dialog").append(generateNoticeMsg(failed_list_html));
                        } else {
                            var succeed_html = '<b><li class="icon-check"></li> 成功</b><br/>';
                            $("#approve-result-dialog").append(generateNoticeMsg(succeed_html));
                        }
                    } else {
                        title_html_str = String.format('<div class="dialog_title"><li class="icon-exclamation-sign"></li> {0}申请</div>', action);
                        $("#approve-result-dialog").append(generateNoticeMsg(resp.msg));
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
        $("#approve-edit").removeAttr('disabled');
    } else {
        $("#approve-pass").attr('disabled', 'true');
        $("#approve-reject").attr('disabled', 'true');
        $("#approve-edit").attr('disabled', 'true');
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

