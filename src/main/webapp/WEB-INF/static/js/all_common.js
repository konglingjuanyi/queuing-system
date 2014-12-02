/**
 * Created by zhenqingwang on 12/1/14.
 */

function generateNoticeMsg(msg) {
    return $.parseStr('<div class="alert alert-info bigger-110" style="margin-bottom: 0px; margin-top: 5px;">%s</div>', msg);
}

function __generateLIHeader(header, icon) {
    return $.parseStr('<li class="%s" style="color: #307ecc; padding-bottom: 10px;">%s</li>', icon, header);
}

function __generateLIData(data) {
    return $.parseStr('<li>%s</li>', data);
}

function __generateDetailHtml(header, header_icon, data_list) {
    var html = '';
    html += '<div class="space-4"></div>';
    html += __generateLIHeader(header, header_icon);
    for(var i = 0; i < data_list.length; i++) {
        html += __generateLIData(data_list[i]);
    }
    html += '</ul>';
    return html;
}