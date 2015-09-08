package com.qunar.ops.recruit.util;

public class RecruitConst {

 
	public static final int PAGE_SIZE = 10;

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
	
	public static final String WAITING_FOR_INTERVIEW_IS_EMPTY = "没有等待面试的学生";
	public static final int ALREADY_EXIST_USER_ERROR = 110;
	public static final String ALREADY_EXIST_USER_ERROR_MSG = "用户名已经存在";
	public static String USERNAM_OR_PASSWORD_ERROR_MSG = "用户名或密码错误";
	public static String phase;
	public static String city;
	public static String year;
	public static final String ALREADY_EXIST_YEAR_PHASE_ERROR_MSG = "批次已经存在";
	public static final String ALREADY_EXIST_PHASE_INTERVIEW_ERROR_MSG = "添加失败";
	public static final String ALREADY_EXIST_IMPORT_ERROR_MSG = "请选择正确格式的Excel文件";
	public static final String ALREADY_EXIST_SIZE_ERROR_MSG = "选择导入的文件太大";
	public static final String ALREADY_EXIST_IMPORT_SUCCESS_MSG = "导入成功";
	public static final String ALREADY_EXIST_IMPORT_FAILE_MSG = "导入失败";
	public static final Object SUCCESS = "成功";

	public static String CITY_OR_PHASE_ERROR_MSG = "年份期次城市错误";
	
	public static final String STUDENT_STATE_NOT_REGIST = "未签到";
	public static final String STUDENT_STATE_REGIST = "签到";
	public static final String STUDENT_STATE_LATE = "迟到";
	public static final String STUDENT_STATE_PASS_ME = "过号";
	public static final String STUDENT_STATE_GOING2ONEROOM = "等待初试";
	public static final String STUDENT_STATE_ONE_VIEW = "初试中";
	public static final String STUDENT_STATE_ONE_NOT_PASS = "初试未通过";
	public static final String STUDENT_STATE_ONE_PASS = "初试通过";
	public static final String STUDENT_STATE_GOING2TWOROOM = "等待复试";
	public static final String STUDENT_STATE_TWO_VIEW = "复试中";
	public static final String STUDENT_STATE_TWO_NOT_PASS = "复试未通过";
	public static final String STUDENT_STATE_TWO_PASS = "复试通过";
	public static final String STUDENT_STATE_GET_OFFER = "已发Offer";
	public static final String STUDENT_STATE_NO_OFFER = "不发Offer";
	public static final String STUDENT_STATE_UNCERTAIN_OFFER = "待定Offer";
	public static final String STUDENT_STATE_FINISH = "面试结束";
	
	public static final String INTERVIEWER_STATE_VIEWING = "面试中";
	public static final String INTERVIEWER_STATE_WAITING = "等待面试";
	public static final String INTERVIEWER_STATE_REST = "忙碌";
	

	public static final String RESULT_NOT_PASS = "不通过";
	public static final String JOB_RD = "DEV";
	public static final String JOB_FE = "FE";
	public static final String JOB_QA = "QA";
	public static final long LATE_TIME = 15*60*1000;

	public static final String PROMPT_NOT_PASS = "今天面试结束<br/>感谢关注去哪儿网校园招聘！";

	public static final String PROMPT_PASS_ME = "你好，你已过号<br/>请联系驻场HR";

	public static final String PROMPT_TWO_PASS = "请联系HR进行最后一轮面试";
	
	public static final String PROMPT_FINISH = "今天面试结束<br/>感谢关注去哪儿网校园招聘！";
	
}
