package com.qunar.ops.oaengine.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.DelegationManager;
import com.qunar.ops.oaengine.manager.FormManager;
import com.qunar.ops.oaengine.manager.GroupManager;
import com.qunar.ops.oaengine.manager.GroupManager.GroupInfo;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.result.EmployeeInfo;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.dailysubmit.AlertInfo;
import com.qunar.ops.oaengine.result.dailysubmit.ApprovalInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;

@Component
public class DefaultOaEngineService implements IOAEngineService {
	
	@Autowired
	private WorkflowManager workflowManager;
	@Autowired
	private DelegationManager delegationManager;
	@Autowired
	private GroupManager groupManager;
	@Autowired
	private FormManager formManager;
	@Autowired
	private EmployeeInfoService employeeInfoService;

	@Override
	public int createForm(String processKey, String userId, FormInfo formInfo)
			throws Exception {
		return formManager.createFormInfo(userId, formInfo);
	}

	@Override
	public int createFormAndstart(String processKey, String userId,
			FormInfo formInfo) throws Exception {
		formManager.createFormInfo(userId, formInfo);
		Request request = new Request();
		request.setOid(formInfo.getOid());
		request.setReport2vp(Boolean.valueOf(formInfo.getIsDirectVp()));
		request.setAmountMoney(formInfo.getMoneyAmount());
		request.setTbMoney(formInfo.getSumEmployeeRelationsFees());
		//五级部门从员工信息中获取
		EmployeeInfo employeeInfo = getEmployeeInfo(userId);
		request.setDepartment(employeeInfo.getDepartmentI());
		request.setDepartmentII(employeeInfo.getDepartmentII());
		request.setDepartmentIII(employeeInfo.getDepartmentIII());
		request.setDepartmentIV(employeeInfo.getDepartmentIV());
		request.setDepartmentV(employeeInfo.getDepartmentV());
		workflowManager.startWorkflow(processKey, userId, request);
		return 0;
	}

	@Override
	public FormInfo updateFormInfo(String processKey, String userId,
			String formId, FormInfo formInfo) throws Exception {
		return formManager.updateFormInfo(userId, Long.valueOf(formId), formInfo);
	}

	@Override
	public FormInfo getFormInfo(String processKey, String userId, String formId) {
		FormInfo formInfo = new FormInfo();
		try {
			formInfo = formManager.getFormInfo(Long.valueOf(formId));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return formInfo;
	}

	@Override
	public void deleteFormInfo(String processKey, String userId, String formId)
			throws Exception {
		formManager.deleteFormInfo(userId, Long.valueOf(formId));
	}

	@Override
	public ListInfo<AlertInfo> getAlertHisList(String processKey, String formId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApprovalInfo getApprovalInfo(String processKey, String formId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reminder(String process, String userId, String formId) {
		// TODO Auto-generated method stub

	}

	@Override
	public EmployeeInfo getEmployeeInfo(String userId) throws RemoteAccessException {
		return employeeInfoService.getEmployee(userId);
	}

	@Override
	public float getLaborHour(String userId, Date day) throws RemoteAccessException {
		return employeeInfoService.getLaborHour(userId, day);
	}

	@Override
	public FormInfoList getUserApplyList(String processKey, String userId,
			Date startTime, Date endTime, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormInfoList getApprovalHisList(String processKey, String userId,
			Date startTime, Date endTime, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormInfoList todoList(String processKey, String userId,
			Date startTime, Date endTime, String owner, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormInfoList historyList(String processKey, String userId,
			Date startTime, Date endTime, String owner, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void pass(String processKey, String userId, String formId,
			String taskId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void back(String processKey, String userId, String formId,
			String taskId, String refuseReason) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void endorse(String processKey, String userId, String formId,
			String taskId, String assignees) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void refuse(String processKey, String userId, String formId,
			String taskId, String refuseReason) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean appendCandidate(String processKey, String ownerId, List<String> userIds) {
		this.delegationManager.appendDelegation(ownerId, userIds);
		this.workflowManager.appendCandidate(ownerId, userIds);
		return true;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean removeCandidate(String processKey, String ownerId,
			List<String> userIds) {
		this.delegationManager.deleteDelegation(ownerId, userIds);
		this.workflowManager.removeCandidate(ownerId, userIds);
		return true;
	}

	@Override
	public List<Delegation> findDelegation(String processKey, String ownerId) {
		return this.delegationManager.findDelegationByMaster(ownerId);
	}

	@Override
	public List<GroupInfo> findGroup(String groupKey) {
		return this.groupManager.getGroup(groupKey);
	}

	@Override
	public void appendMember(String groupKey, String memberUserId) {
		this.groupManager.appendMember(groupKey, memberUserId);
		
	}

	@Override
	public void removeMember(String groupKey, String memberUserId) {
		this.groupManager.removeMember(groupKey, memberUserId);
		
	}

}
