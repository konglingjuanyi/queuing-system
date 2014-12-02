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
        "sWidth": 200,
        "sClass": "center",
        "bVisible": false
    }, {
        "sTitle": "员工编号",
        "sWidth": 200,
        "sClass": "center"
    }, {
        "sTitle": "部门",
        "sWidth": 200,
        "sClass": "center"
    }, {
        "sTitle": "姓名",
        "sWidth": 200,
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
                showInfo(url);
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
        aaData.push([needData[i][0], needData[i][1], needData[i][2], needData[i][3], needData[i][4], money.toFixed(2), map]);
    }
    return aaData;
}

//显示编辑对话框
function showEditDialog(tableMap, vars) {
    $('#edit-form').html(edit_form);
    var fixedInfo = '<div style="text-align:center; vertical-align:middle;">';
    fixedInfo += fixedTableInfo(tableMap, vars);
    fixedInfo += '</div>';
    $('#edit_content').append(fixedInfo);
    $("#dialog-confirm").removeClass('hide').dialog({
        width: 1100,
        height: 1200,
        modal: true,
        draggable: true,
        resizable: false,
        title: "报销页详情",
        close: function () {
            $(this).dialog('destroy');
            $('#edit-form').empty();
        }
    });
}

function fixedTableInfo(tableMap, vars) {
    var tableList = ["table1", "table6", "table2", "table3", "table4", "table5"];
    var tableHeadList = ["出租车费明细(含汽车燃油费)", "通信费",
        "餐费明细（每人每餐报销不超过50元）",
        "招待费明细", "员工关系费明细", "其他费用"];
    //var tableForm = "<iframe style='height:1100px; width:1200px'></iframe>";
    var tableForm = "";
    tableForm += table0BodyForm(tableMap["table"], vars); //这里应该先加table信息
    for (var i = 0; i < 6; i++) {
        tableForm += tableHeadForm(tableHeadList[i]);
        tableForm += $.parseStr('<table frame="vsides" id="%s" style="width: 1067px;">', tableList[i]);
        var num1 = i;
        var num2 = i;
        if (i > 0) {
            num1 -= 1;
        } else {
            num2 += 1;
        }
        if (tableList[i] == "table6") {
            tableForm += table6BodyForm(vars);
        } else {
            tableForm += tableBodyForm(tableMap, tableList[i], num1);
        }
        tableForm += '</table>';
        if (tableList[i] != "table6") {
            tableForm += tableSumForm(num2, vars);
        }
    }
    tableForm += tableApproveBodyForm();
    $("#sum").val(vars["sum"]);
    return tableForm;
}

//  每个表的第一行中文字符。
function tableZeroNameDic() {
    var table1Name = ["日期", "起点", "终点", "具体时间", "用途", "同行人(姓名)", "工时", "金额", "备注"];
    var table2Name = ["日期", "就餐地点", "同行人（姓名）", "就餐人数", "实报金额", "人均餐费", "发票金额", "工时", "备注"];
    var table3Name = ["日期", "地点", "业务目的", "客户单位", "客户姓名", "参加人数", "金额"];
    var table4Name = ["日期", "地点", "同行人（姓名）", "活动目的", "金额", "备注"];
    var table5Name = ["费用项目", "金额", "备注"];
    var tableNames = [table1Name, table2Name, table3Name, table4Name, table5Name];
    return tableNames;
}

function tableHeadForm(headName) {
    var tableHead = '';
    tableHead += '<div style="border:solid 1px; background-color: #f0f0f0;text-align: center;">';
    tableHead += $.parseStr('<span style="font-size:18px">%s</span>', headName);
    tableHead += '</div>';
    return tableHead;
}

function tableBodyForm(tableMap, tableId, num) {
    var tableNames = tableZeroNameDic();
    var headNameList = tableNames[num];
    var bodyForm = '';
    var headLen = headNameList.length;
    bodyForm += '<tr>';
    for (i = 0; i < headLen; i++) {
        bodyForm += '<td bgcolor="#96E0E2">';
        bodyForm += headNameList[i];
        bodyForm += '</td>';
    }
    bodyForm += '</tr>';
    var tableInfo = tableMap[tableId];
    var tableLen = tableInfo.length;
    if (tableLen == 0) {
        bodyForm += '<tr>';
        for (var i = 0; i < headLen; i++) {
            bodyForm += '<td>';
            bodyForm += '<input type="text" style="width: 100%" readonly="readonly" value="">';
            bodyForm += '</td>';
        }
        bodyForm += '</tr>';
    } else {
        for (var i = 0; i < tableLen; i++) {
            bodyForm += '<tr>';
            for (var j = 0; j < headLen; j++) {
                var value = tableInfo[i][j];
                if (isNull(value)) {
                    value = "";
                }
                bodyForm += '<td>';
                bodyForm += $.parseStr('<input type="text" style="width: 100%;color:#000000" readonly="readonly" value="%s">', value);
                bodyForm += '</td>';
            }
            bodyForm += '</tr>';
        }
    }
    return bodyForm;
}

function table6BodyForm(vars) {
    var form = '';
    form += '<tr>';
    form += '<td>金额</td>';
    form += '<td>';
    form += $.parseStr('<input type="text" value="%s" style="width: 100%;color:#000000" id="sum6">', vars["sum6"]);
    form += '</td>';
    form += '<td>备注(话费实际发生月份)</td>';
    form += '<td>';
    form += $.parseStr('<input type="text" value="%s" style="width: 100%;color:#000000" id="remark">', vars["remark"]);
    form += '</td>';
    form += '</tr>';
    return form;
}

function table0BodyForm(table0, vars) {
    var form = '';
    form += tableHeadForm("个人基本信息");
    form += '<table id="table" frame="vsides" rules="all" style="width: 1067px;">';
    var list1 = ["申请人", "人员编号", "申请日期"];
    var list2 = ["一级部门", "申请部门", "部门编号"];
    var list3 = ["是否直接向VP汇报", "银行卡", "开户银行"];
    var list4 = ["是否有借款", "借款单流水号", "借款金额"];
    var list = [list1, list2, list3, list4];
    var k = 0;
    for (var i = 0; i < 4; i++) {
        form += '<tr>';
        for (var j = 0; j < 3; j++) {
            form += $.parseStr('<td>%s</td>', list[i][j]);
            var value = table0[0][k];
            if (isNull(value)) {
                value = "";
            }
            form += $.parseStr('<td><input type="text"  style="width: 100%;color:#000000" readonly="readonly" value="%s"></td>', value);
            k++;
        }
        form += '</tr>';
    }
    form += tableSumForm("", vars);
    return form;
}

function tableApproveBodyForm(table0, vars) {
    var form = '';
    form += tableHeadForm("审批记录");
    form += '<table id="table7" style="width: 1067px;" border="1">';
    var list1 = ["本部门主管意见", "部门总监意见"];
    var list2 = ["部门VP意见", "财务总监意见"];
    var list3 = ["CFO意见", "CEO意见"];
    var list4 = ["报销审核组", "出纳"];
    var list = [list1, list2, list3, list4];
    var k = 0;
    for (var i = 0; i < 4; i++) {

        form += '<tr>';
        for (var j = 0; j < 2; j++) {
            form += '<td>';
            form += $.parseStr('<table><tr><td style="width: 100px;">%s</td><td><input type="text"></td><td><input type="text"></td></tr></table>', list[i][j]);
            form += '</td>';
        }
        form += '</tr>';

    }
    return form;
}

function tableSumForm(num, vars) {
    var sumOne = "sum" + num;
    var sum = vars[sumOne];
    var form = '';
    form += '<table style="border-collapse:collapse;width: 1067px;" border="1">';
    form += '<tr>';
    form += '<td>金额总计</td>';
    form += '<td>';
    form += $.parseStr('<input type="text" value="%s" id="%s" readonly="readonly"  style="width: 100%;color:#000000">',
        sum, sumOne);
    form += '</td>';
    form += '<tr>';
    form += '</table>';
    return form;
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
