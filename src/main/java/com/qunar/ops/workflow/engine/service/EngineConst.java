package com.qunar.ops.workflow.engine.service;

public class EngineConst {
	public static int WF_ERROR_OID_IS_NULL 				= 100;
	public static String WF_ERROR_OID_IS_NULL_MSG 		= "业务对象ID不能为空";
	public static int WF_ERROR_USER_IS_NULL 			= 101;
	public static String WF_ERROR_USER_IS_NULL_MSG 		= "用户ID不能为空";
	
	public static int WF_ERROR_PROCESSINSTANCE_IS_EXIST 		= 200;
	public static String WF_ERROR_PROCESSINSTANCE_IS_EXIST_MSG 	= "流程实例已经存在";
	public static int WF_ERROR_PROCESSDEFINE_NOT_EXIST 			= 201;
	public static String WF_ERROR_PROCESSDEFINE_NOT_EXIST_MSG 	= "流程定义没有找到";
	public static int WF_ERROR_START_FAIL		 				= 202;
	public static String WF_ERROR_START_FAIL_MSG		 		= "流程实例启动失败";
	public static int WF_ERROR_PROCESSINSTANCE_NOT_EXIST 		= 203;
	public static String WF_ERROR_PROCESSINSTANCE_NOT_EXIST_MSG = "流程实例不存在";
	public static int WF_ERROR_PROCESSINSTANCE_WAS_UPDATED 		= 204;
	public static String WF_ERROR_PROCESSINSTANCE_WAS_UPDATED_MSG = "被另一个事务更新";
	public static int WF_ERROR_REMOVE_FAIL						= 205;
	public static String WF_ERROR_REMOVE_FAIL_MSG 				= "流程删除失败";
	
	public static int WF_ERROR_TASK_NOT_FOUNT 						= 300;
	public static String WF_ERROR_TASK_NOT_FOUNT_MSG 				= "任务不存在";
	public static int WF_ERROR_TASK_ALREADY_CLAIMED					= 301;
	public static String WF_ERROR_TASK_ALREADY_CLAIMED_MSG			= "任务已经被认领";
	public static int WF_ERROR_TASK_ASSIGNER_UNMATCHED				= 302;
	public static String WF_ERROR_TASK_ASSIGNER_UNMATCHED_MSG		= "执行人与指定人不匹配";
	public static int WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE			= 303;
	public static String WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG	= "执行人不在候选执行人中";
	public static int WF_ERROR_LASTTASK_NOT_FOUNT 					= 304;
	public static String WF_ERROR_LASTTASK_NOT_FOUNT_MSG 			= "上一个任务不存在";
	public static int WF_ERROR_NOT_ASSIGNEE		 					= 305;
	public static String WF_ERROR_NOT_ASSIGNEE_MSG 					= "没有指定处理人";
	public static int WF_ERROR_NOT_BACK		 						= 306;
	public static String WF_ERROR_NOT_BACK_MSG 						= "无路可退";
	
	public static int WF_ERROR_SEND_TO_MQ		 					= 399;
	public static String WF_ERROR_SEND_TO_MQ_MSG 					= "发送到MQ失败";
	
	public static int WF_ERROR_SYS 			= 500;
	public static String WF_ERROR_SYS_MSG 	= "系统错误";
}
