_TABLE_PADDING = 200;
_PROCESS_DEFINE = {};

jQuery(function($) {
    $('.date').datepicker({dateFormat: "yy-mm-dd"});
    loadSystemCheckboxList();
});

function loadSystemCheckboxList() {
	var url = "http://nuby.zhang.qunar.com:8080/engine/approve/processlist";
    $.ajax({
        dataType: 'json',
        url: url,
        type: "POST",
        success: function(resp) {
            if (resp.errorCode == 0) {
                var processKeys = resp.data;
                var html = ''
                for (var i=0; i<processKeys.length; i++) {
                	v = processKeys[i];
		    		_PROCESS_DEFINE[v.key] = v;
                    html += String.format('<label style="margin-right: 10px;"><input name="process-keys" value="{0}" type="checkbox" class="ace"> <span class="lbl"> {1}</span></label>', v.key, v.name);
                }
                $('#system-checkbox-list').html(html);
                $('#system-checkbox-list input').prop('checked', "true");
                reloadCurrentApproveTable();
            } else {
                showCommonNoticeDialog('获取系统列表失败', 'icon-warning-sign', generateNoticeMsg(resp.errorMessage), 300);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            showCommonNoticeDialog('网络错误', 'icon-warning-sign', generateNoticeMsg('网络错误，请刷新后重试!'), 300);
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

String.format = function(src) {  
    if (arguments.length == 0) return null;  
    var args = Array.prototype.slice.call(arguments, 1);  
    return src.replace(/\{(\d+)\}/g, function(m, i){  
        return args[i];  
    }); 
};

function approveDetail(url) {
	var dialog_width = Math.min(Math.round(window.screen.width*0.7), 900);
    $("#approve-detail-dialog").empty();
    $("#approve-detail-dialog").append("<iframe style='height:380px; width:"+(dialog_width-35)+"px' src='"+url+"'></iframe>");
    var dialog = $("#approve-detail-dialog").removeClass('hide').dialog({
        modal: true,
        width: dialog_width,
        height: 500,
        title: '<div style="color: DodgerBlue; padding: 4px; height: 28px; font-size: 14px; font-weight: bold"><li class="icon-user"></li>申请详情</div>',
        title_html: true,
        closeOnEscape: false,
        open: function(event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        },
        buttons: [
            {
                id: "reject-msg-cancel",
                text: "关闭",
                "class" : "btn btn-xs",
                click: function() {
                    $(this).dialog("close"); 
                } 
            }
        ]
    });
}