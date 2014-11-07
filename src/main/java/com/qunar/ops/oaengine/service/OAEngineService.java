package com.qunar.ops.oaengine.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import com.qunar.ops.oaengine.command.TurnBackTaskCmd;
import com.qunar.ops.oaengine.result.AlertInfo;
import com.qunar.ops.oaengine.result.ApprovalInfo;
import com.qunar.ops.oaengine.result.FormInfo;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;

public class OAEngineService {

	/**
	 * 启动申请流程
	 * @param processKey
	 * @param userId
	 * @param request
	 * @return Object[]; 0-流程ID；1-当前任务信息
	 */
	public Object[] startWorkflow(String processKey, String userId, Request request){
		return null;
	}
	
	/**
	 * 待审批列表
	 * @param processKey
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param owner
	 * @param pageNo
	 * @param pageSize
	 * @return ListInfo<TaskInfo> 任务列表
	 */
	public ListInfo<TaskInfo> todoList(String processKey, String userId, Date startTime, Date endTime, String owner, int pageNo, int pageSize){
		return null;
	}
	
	/**
	 * 审批历史批列表
	 * @param processKey
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param owner
	 * @param pageNo
	 * @param pageSize
	 * @return ListInfo<TaskInfo> 任务列表
	 */
	public ListInfo<TaskInfo> historyList(String processKey, String userId, Date startTime, Date endTime, String owner, int pageNo, int pageSize){
		return null;
	}
	
	/**
	 * 审批通过
	 * @param taskIds
	 * @param userId
	 * @return List<TaskInfo> 当前任务信息
	 */
	public TaskResult pass(String taskId, String userId) {
		return null;
	}
	
	/**
	 * 退回
	 * @param taskId
	 * @param turnback_reason
	 */
	public TaskResult back(String userId, String taskId, String turnback_reason){
		return null;
	}
	
	/**
	 * 加签
	 * @param taskId
	 * @param userId
	 * @param assignees
	 * @return
	 */
	public TaskResult endorse(String taskId, String userId, String assignees){
		return new TaskResult(null, null);
	}
	

	
	/**
	 * 取消申请
	 * @param processKey
	 * @param oid
	 * @param userId
	 * @param reason
	 */
	public boolean cancel(String processKey, String oid, String userId, String reason){
		return true;
	}
	
	/**
	 * 增加代理审批人
	 * @param ownerId
	 * @param userIds
	 * @return
	 */
	public boolean appendCandidate(String ownerId, List<String> userIds){
		return true;
	}
	
	/**
	 * 取消代理审批人
	 * @param ownerId
	 * @param userIds
	 * @return
	 */
	public boolean removeCandidate(String ownerId, List<String> userIds){
		return true;
	}
	
	
	//-------------------------------流程表单信息------------------------------
	/**
	 * 获取用户申请流程中列表
	 * @param processKey
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return ListInfo<FormInfo> 用户申请流程中列表
	 */
	public ListInfo<FormInfo> getUserApplyList(String processKey, String userId, int pageNo, int pageSize){
		return null;
	}
	
	/**
	 * 获取用户申请历史列表
	 * @param processKey
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return ListInfo<FormInfo> 获取用户申请历史列表
	 */
	public ListInfo<FormInfo> getUserHisApplyList(String processKey, String userId, int pageNo, int pageSize){
		return null;
	}
	
	/**
	 * 获取表单数据
	 * @param processKey
	 * @param userId
	 * @param formId
	 * @return FormInfo 表单信息
	 */
	public FormInfo getFormInfo(String processKey, String userId, String formId){
		return null;
	}
	
	/**
	 * 修改表单数据
	 * @param processKey
	 * @param userId
	 * @param oid
	 * @param formId
	 * @param forminfo
	 * @return FormInfo 表单信息
	 */
	public FormInfo updateFormInfo(String processKey, String userId, String formId, FormInfo forminfo){
		return null;
	}
	
	/**
	 * 删除表单数据
	 * @param processKey
	 * @param userId
	 * @param oid
	 * @param formId
	 * @param request
	 * @return FormInfo 表单信息
	 */
	public FormInfo deleteFormInfo(String processKey, String userId, String formId){
		return null;
	}
	
	
	//-------------------------------异动信息------------------------------
	/**
	 * 获取表单异动信息列表
	 * @param processKey
	 * @param userId
	 * @param formId
	 * @param pageNo
	 * @param pageSize
	 * @return ListInfo<AlertInfo> 表单异动信息列表
	 */
	public ListInfo<AlertInfo> getAlertHisList(String processKey, String userId, String formId, int pageNo, int pageSize){
		return null;
	}
	
	/**
	 * 获取异动信息
	 * @param processKey
	 * @param userId
	 * @param alertId
	 * @return AlertInfo 异动信息
	 */
	public AlertInfo getAlertInfo(String processKey, String userId, String alertId){
		return null;
	}
	
	//-------------------------------审批信息------------------------------
	/**
	 * 获取表单审批信息列表
	 * @param processKey
	 * @param userId
	 * @param formId
	 * @param pageNo
	 * @param pageSize
	 * @return ListInfo<ApprovalInfo> 表单审批信息列表
	 */
	public ListInfo<ApprovalInfo> getApprovalHisList(String processKey, String userId, String formId, int pageNo, int pageSize){
		return null;
	}
	
	/**
	 * 获取审批信息
	 * @param processKey
	 * @param userId
	 * @param approvalId
	 * @return AlertInfo 审批信息
	 */
	public ApprovalInfo getApprovalInfo(String processKey, String userId, String approvalId){
		return null;
	}
	
	//-------------------------------私有函数之日志相关------------------------------
	private boolean addFormInfo(FormInfo formInfo){
		return false;
	}
	private boolean addApprovalInfo(ApprovalInfo approvalInfo){
		return false;
	}
	private boolean addAlertInfo(AlertInfo alertInfo){
		return false;
	}
}
