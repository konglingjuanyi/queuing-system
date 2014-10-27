function approveEditDispatch(oTable, id, system) {
    console.log('Edit');
    console.log(id)
    console.log(system)
    // TODO 根据不同业务，弹出对话框
    if (system == 'account') {
        showApproveResultDialog('修改', 'icon-lightbulb', 
            generateNoticeMsg('帐号自助系统不支持修改申请!'), 300);
    } else if (system == 'change') {
            showApproveResultDialog('修改', 'icon-lightbulb', 
                generateNoticeMsg('变更系统不支持修改申请记录!'), 300);
    } else {
            showApproveResultDialog('修改', 'icon-lightbulb', 
                generateNoticeMsg('TODO!'), 300);
    }
    $("#approve-page").unmask();
    enableCurrentApproveToolbarButton(true);
}
