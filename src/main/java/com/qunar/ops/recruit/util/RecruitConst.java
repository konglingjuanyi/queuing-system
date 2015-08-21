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
	public static final String ALREADY_REGIST_ERROR_MSG = "已经注册排队！";
	public static final int NOT_REGIST_ERROR = 104;
	public static final int NO_USER_ERROR = 105;
	public static final String NOT_REGIST_ERROR_MSG = "未注册";
	public static final String NO_USER_ERROR_MSG = "没有用户";
	public static final int PHONE_NAME_MISS_MATCH = 106;
	public static final String PHONE_NAME_MISS_MATCH_MSG = "手机号与姓名不匹配";
	public static final int AUTHORITY_ERROR = 107;
	public static final String AUTHORITY_ERROR_MSG = "没有访问权限";
	public static final int PARAMETER_NULL = 108;
	public static final String PARAMETER_NULL_ERROR = "参数不能为空";
}
