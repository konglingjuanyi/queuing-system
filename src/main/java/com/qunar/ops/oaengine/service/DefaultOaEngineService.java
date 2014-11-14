package com.qunar.ops.oaengine.service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;

import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.ErrorParamterException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.DelegationManager;
import com.qunar.ops.oaengine.manager.Form0114Manager;
import com.qunar.ops.oaengine.manager.GroupManager;
import com.qunar.ops.oaengine.manager.GroupManager.GroupInfo;
import com.qunar.ops.oaengine.manager.LogManager;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.result.EmployeeInfo;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;
import com.qunar.ops.oaengine.result.dailysubmit.AlertInfo;
import com.qunar.ops.oaengine.result.dailysubmit.ApprovalInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfo;
import com.qunar.ops.oaengine.result.dailysubmit.FormInfoList;
import com.qunar.ops.oaengine.util.Constants;


@Component
public class DefaultOaEngineService implements IOAEngineService {
	
	@Autowired
	private WorkflowManager workflowManager;
	@Autowired
	private DelegationManager delegationManager;
	@Autowired
	private GroupManager groupManager;
	@Autowired
	private LogManager logManager;
	@Autowired
	private Form0114Manager form0114Manager;
	@Autowired
	private MailSenderService mailSenderService;
	@Autowired
	private EmployeeInfoService employeeInfoService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;

	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int createForm(String processKey, String userId, FormInfo formInfo){
		FormInfo info = formInfo;
		info.setFinishedflag(Constants.PROC_GRIFT);
		form0114Manager.createFormInfo(userId, info);
		return Constants.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int createFormAndstart(String processKey, String userId,
			FormInfo formInfo) throws RemoteAccessException, CompareModelException, FormNotFoundException{
		this.createForm(processKey, userId, formInfo);
		_startProcess(processKey, userId, formInfo);
		return Constants.SUCCESS;
	}
	
	private void _startProcess(String processKey, String userId, FormInfo formInfo) throws RemoteAccessException, FormNotFoundException{
		FormInfo info = formInfo;
		Request request = new Request();
		//request.setOid(formInfo.getOid());
		request.setOid(""+formInfo.getId());
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
		Object[] res = workflowManager.startWorkflow(processKey, userId, request);
		//增加启动日志到审批日志表中
		if(res != null && res.length == 2){
			String processInstanceId = (String)res[0];
			TaskResult tr = (TaskResult)res[1];
			logManager.appendApproveLog(userId, formInfo.getId(), "start", tr, "");
			
			//修改状态
			form0114Manager.updateFormFinishedFlag(userId, info.getId(), Constants.PROCESSING);
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public FormInfo updateFormInfo(String processKey, String userId,
			String formId, FormInfo formInfo, Boolean start) throws CompareModelException, FormNotFoundException, RemoteAccessException{
		
		FormInfo res = form0114Manager.updateFormInfo(userId, Long.valueOf(formId), formInfo);
		if(!workflowManager.findProcessInstanceCountByBusinessKey(processKey, formId)){
			if(start){
				_startProcess(processKey, userId, formInfo);
			}
		}
		
		return res;
		
	}

	@Override
	public FormInfo getFormInfo(String processKey, String userId, String formId) throws FormNotFoundException {
		FormInfo formInfo = new FormInfo();
		formInfo = form0114Manager.getFormInfo(Long.valueOf(formId));
		return formInfo;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteFormInfo(String processKey, String userId, String formId){
		form0114Manager.deleteFormInfo(userId, Long.valueOf(formId));
	}

	@Override
	public ListInfo<AlertInfo> getAlertHisList(String processKey, String formId, int pageNo, int pageSize) {
		return form0114Manager.getAlertInfos(Long.valueOf(formId), pageNo, pageSize);
	}

	@Override
	public ListInfo<ApprovalInfo> getApprovalInfo(String processKey, String formId, int pageNo, int pageSize) {
		return logManager.getApproveLogs(Long.valueOf(formId), pageNo, pageSize);
	}

	@Override
	public void reminder(String processKey, String userId, String formId, String approveId, String memo) {
		ApprovalInfo info = logManager.getApprovalInfo(Long.valueOf(approveId));
		String[] to = info.getNextCandidate().split(",");
		String[] to_mail = new String[to.length];
		for(int i = 0; i < to.length; i++){
			to_mail[i] = to[i] + "@qunar.com";
		}
		String title = userId + "请你尽快处理日常报销，附言：" + memo;
		String content = title;
		mailSenderService.sender("oa@qunar.com", to_mail, null, title, content);
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
	public FormInfoList getUserDraftList(String processKey, String userId, int pageNo, int pageSize) {
		return form0114Manager.getUserDraftList(userId, pageNo, pageSize);
	}
	
	@Override
	public FormInfoList getUserApplyList(String processKey, String userId,
			Date startTime, Date endTime, int pageNo, int pageSize) {
		return form0114Manager.getUserApplyList(userId, startTime, endTime, pageNo, pageSize);
	}

	@Override
	public FormInfoList getUserApplyHisList(String processKey, String userId,
			Date startTime, Date endTime, int pageNo, int pageSize) {
		return form0114Manager.getUserApplyHisList(userId, startTime, endTime, pageNo, pageSize);
	}

	@Override
	public FormInfoList todoList(String processKey, String userId,
			Date startTime, Date endTime, String owner, int pageNo, int pageSize) throws FormNotFoundException {
		ListInfo<TaskInfo> taskInfos = workflowManager.todoList(processKey, userId, startTime, endTime, owner, pageNo, pageSize);
		List<TaskInfo> _taskInfos = taskInfos.getInfos();
		FormInfoList res = new FormInfoList();
		List<FormInfo> formInfos = new ArrayList<FormInfo>();
		FormInfo formInfo;
		for(int i = 0; i < _taskInfos.size(); i++){
			TaskInfo taskInfo = _taskInfos.get(i);
			String proc_inst_id = taskInfo.getProcessInstanceId();
			formInfo = form0114Manager.getFormInfo(proc_inst_id);
			formInfo.setTaskId(taskInfo.getTaskId());
			formInfos.add(formInfo);
		}
		res.setPageCount((int)taskInfos.getCount());
		res.setPageNo(pageNo);
		res.setPageSize(pageSize);
		return res;
	}

	@Override
	public FormInfoList historyList(String processKey, String userId,
			Date startTime, Date endTime, String owner, int pageNo, int pageSize) throws FormNotFoundException {
		ListInfo<TaskInfo> taskInfos = workflowManager.historyList(processKey, userId, startTime, endTime, owner, pageNo, pageSize);
		List<TaskInfo> _taskInfos = taskInfos.getInfos();
		FormInfoList res = new FormInfoList();
		List<FormInfo> formInfos = new ArrayList<FormInfo>();
		FormInfo formInfo;
		for(int i = 0; i < _taskInfos.size(); i++){
			TaskInfo taskInfo = _taskInfos.get(i);
			String proc_inst_id = taskInfo.getProcessInstanceId();
			formInfo = form0114Manager.getFormInfo(proc_inst_id);
			formInfo.setTaskId(taskInfo.getTaskId());
			formInfos.add(formInfo);
		}
		res.setPageCount((int)taskInfos.getCount());
		res.setPageNo(pageNo);
		res.setPageSize(pageSize);
		return res;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void pass(String processKey, String userId, long formId, String taskId, String memo) throws FormNotFoundException, ActivitiException, IllegalAccessException, InvocationTargetException {
		TaskResult tr = this._pass(processKey, userId, formId, taskId, memo);
		this.sendMail(userId, "同意", tr.getOwner(), tr.getNextTasks(), memo);
	}
	
	@Override
	public List<Long> batchPass(String processKey, String userId, List<Long> formIds, List<String> taskIds, String memo) {
		List<Long> errorFormIds = new ArrayList<Long>();
		Map<String, String> owner = new HashMap<String, String>();
		Map<String, String> approver = new HashMap<String, String>();
		String form = "oa@qunar.com";
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		String now = sdf.format(new Date());
		for(int i=0; i<taskIds.size(); i++){
			String taskId = taskIds.get(i);
			long formId = formIds.get(i);
			try{
				TaskResult tr = this._pass(processKey, userId, formId, taskId, memo);
				
				String content = userId+" 于 ["+now+"]处理了《"+tr.getOwner()+"-日常报销》 [同意]";
				if(memo != null) content += " 附言:"+memo;
				owner.put(tr.getOwner(), content);
				
				if(tr != null && tr.getNextTasks() != null)for(TaskInfo info : tr.getNextTasks()){
					String candidate = info.getCandidate();
					if(candidate == null) continue;
					for(String c : candidate.split(",")){
						if(StringUtils.trim(c).length() == 0) continue;
						approver.put(StringUtils.trim(c), "您有《日常报销》申请需要处理");
					}
				}
				
			}catch(Exception e){
				errorFormIds.add(formId);
			}
		}
		for(Map.Entry<String, String> e : owner.entrySet()){
			mailSenderService.sender(form, new String[]{e.getKey()+"@qunar.com"}, null, e.getValue(), e.getValue());
		}
		for(Map.Entry<String, String> e : approver.entrySet()){
			mailSenderService.sender(form, new String[]{e.getKey()+"@qunar.com"}, null, e.getValue(), e.getValue());
		}
		return errorFormIds;
	}
	
	private TaskResult _pass(String processKey, String userId, long formId, String taskId, String memo) throws FormNotFoundException, ActivitiException, IllegalAccessException, InvocationTargetException {
		TaskResult tr = this.workflowManager.pass(taskId, userId);
		if(tr == null) throw new FormNotFoundException("任务没有找到", this.getClass());
		this.logManager.appendApproveLog(userId, formId, "pass", tr, memo);
		if(tr.isFinished()){
			this.form0114Manager.deleteFormInfo(userId, formId);
		}
		return tr;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void back(String processKey, String userId, long formId, String taskId, String refuseReason) throws FormNotFoundException, ActivitiException {
		TaskResult tr = this.workflowManager.back(userId, taskId, refuseReason);
		if(tr == null) throw new FormNotFoundException("任务没有找到", this.getClass());
		this.logManager.appendApproveLog(userId, formId, "back", tr, refuseReason);
		this.sendMail(userId, "退回", tr.getOwner(), tr.getNextTasks(), refuseReason);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void endorse(String processKey, String userId, long formId, String taskId, String assignees, String memo) throws FormNotFoundException, ActivitiException {
		TaskResult tr = this.workflowManager.endorse(taskId, userId, assignees);
		if(tr == null) throw new FormNotFoundException("任务没有找到", this.getClass());
		if(memo == null) memo = "";
		memo += "[加签给："+assignees+"]";
		this.logManager.appendApproveLog(userId, formId, "endorse", tr, memo);
		this.sendMail(userId, "加签", tr.getOwner(), tr.getNextTasks(), memo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void refuse(String processKey, String userId, long formId, String taskId, String refuseReason) throws FormNotFoundException, ActivitiException, IllegalAccessException, InvocationTargetException {
		TaskResult tr = this.workflowManager.cancel(processKey, Long.toString(formId), userId, refuseReason);
		if(tr != null){
			this.form0114Manager.deleteFormInfo(userId, formId);
			this.logManager.refuseLog(userId, formId, refuseReason);
		}else{
			throw new FormNotFoundException("任务没有找到", this.getClass());
		}
		this.sendMail(userId, "拒绝", tr.getOwner(), null, refuseReason);
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
	
	private void sendMail(String userId, String action, String owner, List<TaskInfo> infos, String memo){
		if(owner == null) return;
		String form = "oa@qunar.com";
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		String now = sdf.format(new Date());
		
		String content = userId+" 于 ["+now+"]处理了《"+owner+"-日常报销》 ["+action+"]";
		if(memo != null){
			content += " 附言:"+memo;
		}
		mailSenderService.sender(form, new String[]{owner+"@qunar.com"}, null, content, content);
		
		if(infos == null) return;
		Set<String> to = new HashSet<String>();
		String tn = "";
		for(TaskInfo info : infos){
			//tn = info.getTaskName();
			String candidate = info.getCandidate();
			if(candidate == null) continue;
			for(String c : candidate.split(",")){
				if(StringUtils.trim(c).length() == 0) continue;
				to.add(StringUtils.trim(c)+"@qunar.com");
			}
		}
		if(to != null){
			content = "["+now+"]您有《"+owner+"-日常报销》 需要处理"+tn;
			if(memo != null){
				content += " 附言:"+memo;
			}
			mailSenderService.sender(form, to.toArray(new String[]{}), null, content, content);
		}
	}

	@Override
	public TreeMap<String, Integer> getMenu(String processKey, String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
