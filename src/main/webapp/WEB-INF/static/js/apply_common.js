/**
 * Created by zhenqing.wang on 11/28/14.
 */

//显示表格数据
function showInfo(url, tableName, rTableId, status) {
    var ex = document.getElementById(tableName);
    if ($.fn.dataTable.fnIsDataTable(ex)) {
        $(ex).dataTable().fnDestroy();
    }
    var aoColumns = [{
        "sTitle": "ID",
        "sWidth": 100,
        "sClass": "center",
        "bVisible": false
    }, {
        "sTitle": "员工编号",
        "sWidth": 150,
        "sClass": "center"
    }, {
        "sTitle": "部门",
        "sWidth": 300,
        "sClass": "center"
    }, {
        "sTitle": "姓名",
        "sWidth": 150,
        "sClass": "center"
    }, {
        "sTitle": "申请日期",
        "sWidth": 200,
        "sClass": "center"
    }, {
        "sTitle": "报销金额",
        "sWidth": 200,
        "sClass": "center"
    }, {
        "sTitle": "报销详情",
        "sWidth": 200,
        "sClass": "center"
    }, {
        "sTitle": "动作",
        "sWidth": 200,
        "sClass": "center"
    }];
    var wh = $(window).height();
    var sY = (wh - 100) + 'px';
    var oTable = $(ex).dataTable({
        "bFilter": false,
        "bAutoWidth": true,
        "bProcessing": false,
        "bServerSide": true,
        "sDom": 't<"row"<"col-sm-12"i>>',
        "bSort": false,
        "bScrollCollapse": true,
        "iDisplayLength": 20,
        "iDisplayStart": 0,
        "aoColumns": aoColumns,
        "sAjaxSource": "",
        "bScrollInfinite": true,
        "sScrollY": sY,
        "fnServerData": function (sSource, aDataSet, fnCallback) {
            var startDate = $.trim($('#date-from').val());
            var endDate = $.trim($('#date-to').val());
            var approveUser = $.trim($('#approve-user').val());
            var start = 0;
            var length = 0;
            for (var i = 0; i < aDataSet.length; i++) {
                if (aDataSet[i].name == 'iDisplayStart') {
                    start = aDataSet[i].value;
                }
                if (aDataSet[i].name == 'iDisplayLength') {
                    length = aDataSet[i].value;
                }
            }
            var vars = {};
            vars["start"] = start;
            vars["length"] = length;
            if (startDate != '') {
                vars["startTime"] = startDate;
            }
            if (endDate != '') {
                vars["endTime"] = endDate;
            }
            if (approveUser != '') {
                vars["approveUser"] = approveUser;
            }
            var params = {};
            params["vars"] = vars;
            params["tableMap"] = {};
            params["flag"] = "";
            $.ajax({
                "contentType": "application/json; charset=utf-8",
                "type": "POST",
                "url": url,
                "data": JSON.stringify(params),
                "success": function (resp) {
                    var data = resp.data;
                    var iTotalRecords = data.count || 0;
                    var iTotalDisplayRecords = iTotalRecords;
                    var aaData = showApplyInfo(data);
                    var data = {
                        "aaData": aaData,
                        "iTotalRecords": (iTotalRecords || 0),
                        "iTotalDisplayRecords": (iTotalDisplayRecords || 0)
                    };
                    fnCallback(data);
                },
                error: function () {
                    showCommonNoticeDialog('网络错误', 'icon-bolt', generateNoticeMsg('网络错误，请刷新后重试!'), 300);
                }
            });
        },
        "fnDrawCallback": function () {
            if (!oTable) {
                return;
            }

            $("#search").unbind("click");
            $("#search").on("click", function (e) {
                showInfo(url, tableName, rTableId, status);
            }).on("dblclick", function (e) {
                e.preventDefault();
            });
            oTable.$('tr.row_selected').removeClass('row_selected');
            oTable.$("tr").unbind('click');
            oTable.$("tr").click({
                oTable: oTable
            }, function (e) {
                if ($(this).hasClass('row_selected')) {
                    $(this).removeClass('row_selected');
                }
                else {
                    e.data.oTable.$('tr.row_selected').removeClass('row_selected');
                    $(this).addClass('row_selected');
                    var id = e.data.oTable.fnGetData(this)[0];
                    showApplyTodoOrHistoryDetailsList(id, rTableId, status);
                }
            });
        }
    })
}

function showApplyInfo(data) {
    var aaData = [];
    var needData = data.tableInfos;
    var tableMapList = data.tableMapList;
    var varsList = data.varsList;
    var d_len = needData.length;
    for (var i = 0; i < d_len; i++) {
        var money = parseFloat(needData[i][5]);
        money /= 100;
        var tempMap = JSON.stringify(tableMapList[i]);
        var tempVar = JSON.stringify(varsList[i]);
        var map = $.parseStr("<a href='javascript:void(0);' " +
        "onclick='showEditDialog(%s,%s);'><i class='icon-link'></i></a>", tempMap, tempVar);
        var push = '<a href="javascript:void(0);" role="button" ' +
            'onclick="sendEmailToCandidate(\'' + needData[i][0] + '\')" class="green"><i class="icon-user">催办</i></a>';

        aaData.push([needData[i][0], needData[i][1],
            needData[i][2], needData[i][3], needData[i][4], money.toFixed(2), map, push]);
    }
    return aaData;
}


function showApplyTodoOrHistoryDetailsList(id, tableId, status) {
    if (isNull(id)) {
        $("#" + tableId).html('');
        return;
    }
    var params = {
        "tableMap": {},
        "flag": "",
        "vars": {
            "formId": id
        }
    };
    $.ajax({
        "contentType": "application/json; charset=utf-8",
        "url": "approve_info",
        "type": "POST",
        "data": JSON.stringify(params),
        success: function (resp) {
            if (resp.errorCode == 0) {
                if (resp.data.length != 0) {
                    try {
                        var details = resp.data;
                        var html = '';
                        html += __generateDetailHtml('当前审批状态', 'icon-eye-open', [status]);
                        var len1 = details.length;
                        for (var i = 0; i < len1; i++) {
                            html += __generateDetailHtml('审批历史', 'icon-book', details[i]);
                        }
                        $("#" + tableId).html(html);
                    } catch (e) {
                    }
                } else {
                    $("#" + tableId).html('无详细信息');
                }
            } else {
                $("#" + tableId).html(resp.errorMessage);
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            showCommonNoticeDialog('网络错误', 'icon-warning-sign',
                generateNoticeMsg('网络错误，请刷新后重试!'), 300);
        }
    })
}

function sendEmailToCandidate(id){
    console.log(id);
    var params = {};
    params["vars"] = {"formId":id};
    params["tableMap"] = {};
    params["flag"] = "";
    $.ajax({
        "contentType": "application/json; charset=utf-8",
        "type": "POST",
        "url": "push_approve",
        "data": JSON.stringify(params),
        "success": function (resp) {
            console.log(resp);
            showSuccessTips(resp.data);
        },
        error: function () {
            showCommonNoticeDialog('网络错误', 'icon-bolt', generateNoticeMsg('网络错误，请刷新后重试!'), 300);
        }
    });
}
