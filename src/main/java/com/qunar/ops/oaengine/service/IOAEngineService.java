package com.qunar.ops.oaengine.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.activiti.engine.ActivitiException;

import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.ErrorParamterException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.GroupManager.GroupInfo;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.result.EmployeeInfo;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.dailysubmit.AlertInfo;
import com.qunar.ops.oaengine.result.dailysubmit.ApprovalInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;

public interface IOAEngineService {
	/**
	 * 要点：
	 * 1、所有exception都需要记录log
	 * 2、return容器的方法不允许返回null
	 * 3、public方法不允许修改参数值
	 * 4、所有exception必须抛出
	 */
	
	/****************************************************
	 *  工单页面：
	 *  页面内容包括：
	 *  。申请人信息
	 *  。报销信息
	 *  。修改日志（对报销信息的修改日志）
	 *  。操作历史（对工单的操作历史，如：创建、通过、拒绝、退回、加签等）
	 *  角色：申请发起人
	 *  动作：
	 *  	暂存草稿
	 *  	保存并提交
	 *  	取消申请
	 *  
	 ***************************************************/
	/**
	 * 创建工单
	 * @param processKey
	 * @param userId
	 * @param forminfo
	 * @return 工单ID
	 * @throws 系统错误
	 * FE:
	 * 需要验证，验证格式、必填永念填写
	 * 
	 * BE:
	 * 需要记录修改日志
	 */
	public int createForm(String processKey, String userId, FormInfo forminfo);
	
	/**
	 * 创建工单并发起流程
	 * @param processKey
	 * @param userId
	 * @param forminfo
	 * @return 工单ID
	 * @throws RemoteAccessException 
	 * @throws 数据库保存错误、工作流启动错误、系统错误
	 * FE:
	 * BE:
	 * 需要记录修改日志；需要记录操作历史
	 */
	public int createFormAndstart(String processKey, String userId, FormInfo forminfo) throws RemoteAccessException;
	
	/**
	 * 更新工单
	 * @param processKey
	 * @param userId
	 * @param formId
	 * @param forminfo
	 * @return
	 * @throws CompareModelException 
	 * @throws 工单没有找到；工单被锁定(更新人与工单提交人不一致)；系统错误
	 * FE:
	 * BE：
	 * 需要记录修改日志
	 */
	public FormInfo updateFormInfo(String processKey, String userId, String formId, FormInfo forminfo) throws CompareModelException;
	
	/**
	 * 获取工单信息
	 * @param processKey
	 * @param userId
	 * @param formId
	 * @return 没有找到返回 null
	 */
	public FormInfo getFormInfo(String processKey, String userId, String formId);
	
	/**
	 * 删除、取消工单 
	 * @param processKey
	 * @param userId
	 * @param formId
	 * @return
	 * @throws 工单没有找到；工单锁定（历史工单不允许删除、仅允许创建人删除）； 系统错误
	 * 需要记录操作历史
	 */
	public void deleteFormInfo(String processKey, String userId, String formId);
	
	/**
	 * 获取修改日志列表
	 * @param processKey
	 * @param formId
	 * @return ListInfo<AlertInfo> 表单异动信息列表
	 */
	public ListInfo<AlertInfo> getAlertHisList(String processKey, String formId, int pageNo, int pageSize);
	
	/**
	 * 获取操作历史
	 * @param processKey
	 * @param formId
	 * @return AlertInfo 审批信息
	 */
	public ListInfo<ApprovalInfo> getApprovalInfo(String processKey, String formId, int pageNo, int pageSize);
	
	/**
	 * 催办
	 * @param process
	 * @param userId
	 * @param formId
	 * @throws Exception
	 */
	public void reminder(String process, String userId, String formId);
	
	/**
	 * 获取员工信息
	 * @param userId
	 * @return
	 * @throws RemoteAccessException 
	 */
	public EmployeeInfo getEmployeeInfo(String userId) throws RemoteAccessException;
	
	/**
	 * 获取工时
	 * @param userId
	 * @param day
	 * @return
	 * @throws RemoteAccessException 
	 */
	public float getLaborHour(String userId, Date day) throws RemoteAccessException;
	
	
	/****************************************************
	 *  本人申请工单列表页面：
	 *  角色：申请发起人
	 *  。处理中的申请
	 *  。 结束的申请
	 *  
	 ***************************************************/
	/**
	 * 获取用户申请流程中列表
	 * @param processKey
	 * @param userId
	 * @param startTime - 允许null
	 * @param endTime - 允许null
	 * @param pageNo
	 * @param pageSize - 默认20
	 * @return FormInfoList 用户申请流程中列表
	 * 
	 */
	public FormInfoList getUserApplyList(String processKey, String userId, Date startTime, Date endTime, int pageNo, int pageSize);
	
	/**
	 * 获取表单审批信息列表
	 * @param processKey
	 * @param userId
	 * @param formId
	 * @param startTime - 允许null
	 * @param endTime - 允许null
	 * @param pageNo
	 * @param pageSize - 默认20
	 * @return FormInfoList 表单审批信息列表
	 */
	public FormInfoList getApprovalHisList(String processKey, String userId, Date startTime, Date endTime, int pageNo, int pageSize);
	
	
	/****************************************************
	 *  申请审批列表页面：
	 *  角色：审批人
	 *  。待审批的申请
	 *  。 参与审批的申请历史
	 *  
	 ***************************************************/
	/**
	 * 待审批工单列表
	 * @param processKey
	 * @param userId
	 * @param startTime - 允许null
	 * @param endTime - 允许null
	 * @param owner
	 * @param pageNo
	 * @param pageSize - 默认20
	 * @return FormInfoList 工单列表
	 */
	public FormInfoList todoList(String processKey, String userId, Date startTime, Date endTime, String owner, int pageNo, int pageSize);
	
	/**
	 * 参与审批的历史工单列表
	 * @param processKey
	 * @param userId
	 * @param startTime - 允许null
	 * @param endTime - 允许null
	 * @param owner
	 * @param pageNo
	 * @param pageSize - 默认20
	 * @return FormInfoList 工单列表
	 */
	public FormInfoList historyList(String processKey, String userId, Date startTime, Date endTime, String owner, int pageNo, int pageSize);
	
	
	/****************************************************
	 *  申请审批页面：
	 *  角色：审批人
	 *  动作
	 *  。通过
	 *  。 退回上一个审批人
	 *  。拒绝 - 结束流程
	 *  。加签
	 *  
	 ***************************************************/
	/**
	 * 通过
	 * @param processKey
	 * @param userId
	 * @param formId
	 * @param taskId
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws 工单没有找到；任务没有找到；系统错误
	 * 需要记录操作历史
	 * 需要向发起人、下一节点审核人发送提醒邮件
	 */
	public void pass(String processKey, String userId, long formId, String taskId, String memo) throws FormNotFoundException, ActivitiException, IllegalAccessException, InvocationTargetException;
	
	/**
	 * 通过 - 批量操作
	 * @param processKey
	 * @param userId
	 * @param formIds
	 * @param taskIds
	 * @return 失败工单id列表
	 * 需要记录操作历史
	 * 需要向发起人、下一节点审核人发送提醒邮件
	 */
	public List<Long> batchPass(String processKey, String userId, List<Long> formId, List<String> taskId, String memo);
	
	/**
	 * 退回 - controller需要实现单个/批量操作
	 * @param processKey
	 * @param userId
	 * @param formId
	 * @param taskId
	 * @param turnback_reason
	 * @throws 工单没有找到；任务没有找到；系统错误
	 * 需要记录操作历史
	 * 需要向发起人、下一节点审核人发送提醒邮件
	 */
	public void back(String processKey, String userId, long formId, String taskId, String refuseReason) throws FormNotFoundException, ActivitiException;
	
	/**
	 * 加签
	 * @param processKey
	 * @param taskId
	 * @param formId
	 * @param userId
	 * @param assignees
	 * @throws 工单没有找到；任务没有找到；没有指定审批人; 系统错误
	 * 需要记录操作历史
	 * 需要向发起人、下一节点审核人发送提醒邮件
	 */
	public void endorse(String processKey, String userId, long formId, String taskId, String assignees, String memo) throws FormNotFoundException, ActivitiException;

	/**
	 * 拒绝
	 * @param processKey
	 * @param taskId
	 * @param formId
	 * @param userId
	 * @param refuseReason
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws 工单没有找到；任务没有找到；系统错误
	 * 需要记录操作历史
	 * 需要向发起人、下一节点审核人发送提醒邮件
	 */
	public void refuse(String processKey, String userId, long formId, String taskId, String refuseReason) throws FormNotFoundException, ActivitiException, IllegalAccessException, InvocationTargetException;
	
	
	/****************************************************
	 *  Setting页面：
	 *  角色：ALL
	 *  动作
	 *  。增加代理人
	 *  。 取消代理人
	 ***************************************************/
	/**
	 * 增加代理审批人
	 * @param processKey
	 * @param ownerId
	 * @param userIds
	 * @return
	 * 
	 * FE:
	 * userIds不允许为空，需要符合rtxid格式
	 */
	public boolean appendCandidate(String processKey, String ownerId, List<String> userIds);
	
	/**
	 * 取消代理审批人
	 * @param processKey
	 * @param ownerId
	 * @param userIds
	 * @return
	 * FE：
	 * userIds不允许为空，删除需要二次确认
	 */
	public boolean removeCandidate(String processKey, String ownerId, List<String> userIds);
	
	/**
	 * 获取代理审批人
	 * @param processKey
	 * @param ownerId - 允许null
	 * @param userIds
	 * @return
	 * FE:
	 * 不需要分页
	 */
	public List<Delegation> findDelegation(String processKey, String ownerId);
	
	/**
	 * 获取全部审核组以及组员
	 * @param groupKey null表示全部租
	 * @return
	 * FE:不需要分页
	 */
	public List<GroupInfo> findGroup(String groupKey);
	
	/**
	 * 添加组员
	 * @param groupKey
	 * @param memberUserId
	 * FE:页面判断组员是否存在
	 */
	public void appendMember(String groupKey, String memberUserId);
	
	/**
	 * 删除组员
	 * @param groupKey
	 * @param memberUserId
	 * FE:页面判断组员是否存在
	 */
	public void removeMember(String groupKey, String memberUserId);
}
