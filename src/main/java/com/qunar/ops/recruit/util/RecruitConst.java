package com.qunar.ops.recruit.util;

public class RecruitConst {

 


	public static final String DATE_FORMAT_ERROR_MSG = "转换时间失败，请检查输入是否为yyyy-MM-dd格式!";
    public static final int DATE_FORMAT_ERROR = 102;
    
    public static final String LATE = "迟到";
    public static final String IN_NORMAL = "非正常";

    public static final String ADVANCE = "提前到";
    public static final String NORMAL = "正常";
    
    public static final long INTERVIEW_TIME = 60*60000;
    public static final long MAY_ADVANCE_TIME = 30*60000;
    
	public static final int ALREADY_REGIST_ERROR = 103;
	public static final String ALREADY_REGIST_ERROR_MSG = "已经注册排队";
	public static final int NOT_REGIST_ERROR = 104;
	public static final int NO_USER_ERROR = 105;
	public static final String NOT_REGIST_ERROR_MSG = "未注册";
	public static final String NOT_LOGIN_ERROR_MSG = "未注册";
	public static final String NO_USER_ERROR_MSG = "没有用户";
	public static final int PHONE_NAME_MISS_MATCH = 106;
	public static final String PHONE_NAME_MISS_MATCH_MSG = "手机号与姓名不匹配";
	public static final int AUTHORITY_ERROR = 107;
	public static final String AUTHORITY_ERROR_MSG = "没有访问权限";
	public static final int NO_LOGIN = 108;
	public static final String NO_LOGIN_MSG = "没有登录";
	public static final int PARAMETER_NULL = 109;
	public static final String PARAMETER_NULL_ERROR_MSG = "参数不能为空";
	
	public static final String INTERVIEW_STATE_ING = "面试中";
	public static final String INTERVIEW_STATE_WAIT = "等待面试";
	public static final String INTERVIEW_STATE_REJECT = "已回绝";
	public static final String INTERVIEW_STATE_ACCEPT = "待发Offer";
	public static final String WAITING_FOR_INTERVIEW_IS_EMPTY = "没有等待面试的学生";
	public static final int ALREADY_EXIST_USER_ERROR = 110;
	public static final String ALREADY_EXIST_USER_ERROR_MSG = "用户名已经存在";
	public static String USERNAM_OR_PASSWORD_ERROR_MSG = "用户名或密码错误";
	public static String phase;
	public static String city;
	public static final String ALREADY_EXIST_YEAR_PHASE_ERROR_MSG = "批次已经存在";
}
