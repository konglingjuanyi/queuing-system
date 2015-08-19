package com.qunar.ops.recruit.util;

public class RecruitConst {

    public static int RTX_ID_IS_NULL = 100;
    public static String RTX_ID_IS_NULL_MSG = "登陆用户为空，无法获取员工信息!";
    public static int ACL_LIMIT_ERROR = 101;
    public static String ACL_LIMIT_ERROR_MSG = "ACL限制，获取员工信息失败!";

    public static int DATE_FORMAT_ERROR = 102;
    public static String DATE_FORMAT_ERROR_MSG = "转换时间失败，请检查输入是否为yyyy-MM-dd格式!";
    public static int FORM_NOT_FOUND_ERROR = 201;
    public static String FORM_NOT_FOUND_ERROR_MSG = "表单未找到，请检查！";
    public static int ACTIVITI_ERROR = 202;
    public static String ACTIVITI_ERROR_MSG = "工作流系统错误,请检查!";
    public static int MANAGER_LOCK_ERROR = 203;
    public static String MANAGER_LOCK_ERROR_MSG = "工单锁定，不允许删除";

    public static int TASK_NOT_FOUND_ERROR = 301;
    public static String TASK_NOT_FOUND_ERROR_MSG = "任务不存在,请检查!";

    public static int APPROVE_BACK_ERROR = 401;
    public static String APPROVE_BACK_ERROR_MSG = "没有前置审批节点，无法回退，请选择拒绝";
}
