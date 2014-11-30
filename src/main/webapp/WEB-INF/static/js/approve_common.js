/**
 * Created by zhenqingwang on 11/28/14.
 */
function showApproveTableInfo(url) {
    var ex = document.getElementById("approve_todo");
    if ($.fn.dataTable.fnIsDataTable(ex)) {
        $(ex).dataTable().fnDestroy();
    }
    var aoColumns = [{
        "sTitle": '<lable><input id="current-approve-table-select-all" ' +
        'type="checkbox" value="ALL" class="ace"><span class="lbl"></span></label>',
        "sWidth": "30px", "bSortable": false, "sClass": "center"
    },
        {"sTitle": '申请人', "sWidth": "120px", "sClass": "center"},
        {"sTitle": '申请部门', "sWidth": "120px", "sClass": "center"},
        {"sTitle": '申请时间', "sWidth": "150px", "sClass": "center"},
        {"sTitle": '金额', "sWidth": "150px", "sClass": "center"}];
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
            $.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": url,
                "data": aDataSet,
                "success": function (resp) {
                    var data = resp.data;
                    var iTotalRecords = data.count || 0;
                    var iTotalDisplayRecords = iTotalRecords;
                    var aaData = showTableInfo(data);
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
                }
            });
        },
        fnInitComplete: function() {
            $('#current-approve-table-select-all').prop('checked', false);
            $('#current-approve-table-select-all').attr('checked', false);
            toggleCheck(oTable);
        }

    })
}

function showTableInfo(data){
    var aaData = [];
    var needData = data.infos;
    var d_len = data.count;
    for (var i = 0; i < d_len; i++) {
        var money = parseFloat(needData[i][3]);
        money /= 100;
        var checkbox = '<label><input value="" type="checkbox" class="ace"> <span class="lbl"></span></label>';
        aaData.push([checkbox, needData[i][1], needData[i][2], needData[i][3], money.toFixed(2)]);
    }
    return aaData;
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