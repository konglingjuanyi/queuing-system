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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qunar.ops.oaengine.exception.AgentAlreadyExistsException;
import com.qunar.ops.oaengine.exception.CompareModelException;
import com.qunar.ops.oaengine.exception.FormNotFoundException;
import com.qunar.ops.oaengine.exception.ManagerFormException;
import com.qunar.ops.oaengine.exception.RemoteAccessException;
import com.qunar.ops.oaengine.manager.DelegationManager;
import com.qunar.ops.oaengine.manager.Form0114Manager;
import com.qunar.ops.oaengine.manager.GroupManager;
import com.qunar.ops.oaengine.manager.GroupManager.GroupInfo;
import com.qunar.ops.oaengine.manager.LogManager;
import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.model.Delegation;
import com.qunar.ops.oaengine.model.FormApproveLog;
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
import com.qunar.ops.oaengine.util.OAControllerUtils;



@Component
public class DefaultOaEngineService implements IOAEngineService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	private PaymentService paymentService;

	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Long createForm(String processKey, String userId, FormInfo formInfo){
		FormInfo info = formInfo;
		info.setStartMemberId(userId);
		info.setFinishedflag(Constants.PROC_GRIFT);
		form0114Manager.createFormInfo(userId, info);
		formInfo.setId(info.getId());
		return info.getId();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Long createFormAndstart(String processKey, String userId, String cname, FormInfo formInfo) throws RemoteAccessException, CompareModelException, FormNotFoundException{
		this.createForm(processKey, userId, formInfo);
		_startProcess(processKey, userId, cname, formInfo);
		return formInfo.getId();
	}
	
	private void _startProcess(String processKey, String userId, String cname, FormInfo formInfo) throws RemoteAccessException, FormNotFoundException, CompareModelException{
		FormInfo info = formInfo;
		Request request = new Request();
		//request.setOid(formInfo.getOid());
		request.setOid(""+formInfo.getId());
		if("是".equals(formInfo.getIsDirectVp())){
			request.setReport2vp(true);
		}else{
			request.setReport2vp(false);
		}
		request.setAmountMoney(formInfo.getMoneyAmount());
		request.setTbMoney(formInfo.getSumEmployeeRelationsFees());
		request.setHosMoney(formInfo.getSumHospitalityAmount());
		//五级部门从员工信息中获取
		EmployeeInfo employeeInfo = getEmployeeInfo(userId);
		request.setDepartment(employeeInfo.getDepartmentI());
		request.setDepartmentII(employeeInfo.getDepartmentII());
		request.setDepartmentIII(employeeInfo.getDepartmentIII());
		request.setDepartmentIV(employeeInfo.getDepartmentIV());
		request.setDepartmentV(employeeInfo.getDepartmentV());
		Object[] res = workflowManager.startWorkflow(processKey, userId, formInfo.getApplyUser(), request);
		//增加启动日志到审批日志表中
		if(res != null && res.length == 2){
			String processInstanceId = (String)res[0];
			TaskResult tr = (TaskResult)res[1];
			logManager.appendApproveLog(userId, cname, formInfo.getId(), "start", tr, "");
			//修改状态同时回写进程ID
			form0114Manager.updateFormFinishedFlag(userId, info.getId(), Constants.PROCESSING, processInstanceId, true);
			this.sendMail(null, null, userId, tr.getNextTasks(), null, OAControllerUtils.centMoneyToYuan(formInfo.getMoneyAmount()));
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public FormInfo updateFormInfo(String processKey, String userId, String cname, String formId, FormInfo formInfo, Boolean start) throws CompareModelException, FormNotFoundException, RemoteAccessException, ManagerFormException{
		FormInfo _formInfo = form0114Manager.getFormInfo(Long.valueOf(formId));
		String _userId = _formInfo.getStartMemberId();
		if(!userId.equals(_userId)){
			boolean ratify = this.groupManager.inGroups(new String[]{"fin_check", "fin_check_mdd"}, userId);
			if(!ratify){
				throw new ManagerFormException("不是你的申请，你无权修改", DefaultOaEngineService.class);
			}
		}
		FormInfo res = form0114Manager.updateFormInfo(userId, Long.valueOf(formId), formInfo);
		
		if(_formInfo.getFinishedflag() != Constants.PROCESSING){
			if(start){
				_startProcess(processKey, userId, cname, formInfo);
			}
		}
		
		return res;
	}

	@Override
	public FormInfo getFormInfo(String processKey, String userId, String formId) throws FormNotFoundException {
		FormInfo formInfo = form0114Manager.getFormInfo(Long.valueOf(formId));
		return formInfo;
	}

	@Override
	public FormInfo getHistoryFormInfo(String processKey, String userId, String formId) throws FormNotFoundException {
		FormInfo formInfo = form0114Manager.getFormInfoHistory(Long.valueOf(formId));
		return formInfo;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteFormInfo(String processKey, String userId, String formId) throws FormNotFoundException, ManagerFormException{
		//判断是否为申请人，草稿状态
		FormInfo formInfo = form0114Manager.getFormInfo(Long.valueOf(formId));
		String _userId = formInfo.getStartMemberId();
		int finishedflag = formInfo.getFinishedflag();
		if(userId.equals(_userId) && finishedflag == Constants.PROC_GRIFT){
			form0114Manager._deleteFormInfo(Long.valueOf(formId), true);
		}else if(!userId.equals(_userId)){
			throw new ManagerFormException("不是你的申请，你无权删除", DefaultOaEngineService.class);
		}else{
			throw new ManagerFormException("不为草稿状态，无法删除", DefaultOaEngineService.class);
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void cloneToDraft(String userId, String formId) throws FormNotFoundException, ManagerFormException {
		FormInfo formInfo = form0114Manager.getFormInfoHistory(Long.valueOf(formId));
		String _userId = formInfo.getStartMemberId();
		if(userId.equals(_userId)){
			form0114Manager.cloneFormInfo(formInfo);
		}else{
			throw new ManagerFormException("不是你的申请，你无权复制", DefaultOaEngineService.class);
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void cancelFormInfo(String processKey, String userId, String cname, String formId) throws FormNotFoundException, ManagerFormException, ActivitiException{
		FormInfo formInfo = form0114Manager.getFormInfo(Long.valueOf(formId));
		String _userId = formInfo.getStartMemberId();
		int finishedflag = formInfo.getFinishedflag();
		if(userId.equals(_userId) && finishedflag == Constants.PROCESSING){
			TaskResult cancel = workflowManager.cancel(processKey, formId, userId, "");
			if(cancel == null){
				throw new FormNotFoundException("申请流程没有找到", this.getClass());
			}
			this.logManager.appendApproveLog(userId, cname, Long.valueOf(formId), "cancel", cancel, "用户取消申请");
			form0114Manager.deleteFormInfo(_userId, Long.valueOf(formId), Constants.CANCEL);
		}else if(!userId.equals(_userId)){
			throw new ManagerFormException("不是你的申请，你无权撤销", DefaultOaEngineService.class);
		}else{
			throw new ManagerFormException("不是处理中状态，无法撤销", DefaultOaEngineService.class);
		}
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
	public String reminder(String processKey, String userId, String formId, String approveId, String memo) {
		ApprovalInfo info = logManager.getApprovalInfo(Long.valueOf(approveId));
		String nextCandidates = info.getNextCandidate();
		String[] to = nextCandidates.split(",");
		String[] to_mail = new String[to.length];

		for(int i = 0; i < to.length; i++){
			to_mail[i] = to[i] + "@qunar.com";
		}
		String title = userId + "请你尽快处理日常报销，附言：" + memo;
		mailSenderService.sender("oa@qunar.com", to_mail, null, title, title+"[http://baoxiao.corp.qunar.com]");
		return nextCandidates;
	}

	@Override
	public EmployeeInfo getEmployeeInfo(String userId) throws RemoteAccessException {
		return employeeInfoService.getEmployee(userId);
	}
	
	@Override
	public List<String[]> getLoans(String userId) throws RemoteAccessException {
		return employeeInfoService.getLoans(userId);
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
	public FormInfoList getUserApplyList(String processKey, String userId, int start, int length) {
		return form0114Manager.getUserApplyList(userId, start, length);
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
			formInfo = form0114Manager.getFormInfoByInst(proc_inst_id);
			if(formInfo == null)
				continue;
			formInfo.setTaskId(taskInfo.getTaskId());
			formInfo.setTaskKey(taskInfo.getTaskKey());
			formInfo.setIsEndorse(taskInfo.isEndorse());
			formInfo.setTaskCreateTime(taskInfo.getTaskCreateTime());
			formInfos.add(formInfo);
		}
		//res.setCount((int)taskInfos.getCount());
		res.setCount((int)formInfos.size());
		res.setPageNo(pageNo);
		res.setPageSize(pageSize);
		res.setFormInfos(formInfos);
		return res;
	}
	
	public FormInfoList todoList(String processKey, String userId, String owner, int start, int length) throws FormNotFoundException {
		ListInfo<TaskInfo> taskInfos = workflowManager.todoList(processKey, userId, owner, start, length);
		List<TaskInfo> _taskInfos = taskInfos.getInfos();
		FormInfoList res = new FormInfoList();
		List<FormInfo> formInfos = new ArrayList<FormInfo>();
		FormInfo formInfo;
		for(int i = 0; i < _taskInfos.size(); i++){
			TaskInfo taskInfo = _taskInfos.get(i);
			String proc_inst_id = taskInfo.getProcessInstanceId();
			formInfo = form0114Manager.getFormInfoByInst(proc_inst_id);
			if(formInfo == null) continue;
			formInfo.setTaskId(taskInfo.getTaskId());
			formInfo.setTaskKey(taskInfo.getTaskKey());
			formInfo.setIsEndorse(taskInfo.isEndorse());
			formInfo.setTaskCreateTime(taskInfo.getTaskCreateTime());
			if(!(formInfo.isEndorse() && userId.equals(formInfo.getStartMemberId()))){
				formInfos.add(formInfo);
			}
		}
		//res.setCount((int)taskInfos.getCount());
		res.setFormInfos(formInfos);
		return res;
	}


	@Override
	@Transactional(rollbackFor=Exception.class)
	public void pass(String processKey, String userId, String cname, long formId, String taskId, String memo) throws FormNotFoundException, ActivitiException, IllegalAccessException, InvocationTargetException, CompareModelException {
		TaskResult tr = this._pass(processKey, userId, cname, formId, taskId, memo);
		FormInfo formInfo = form0114Manager.getFormInfo(Long.valueOf(formId));
		this.sendMail(userId, "同意", tr.getOwner(), tr.getNextTasks(), memo, OAControllerUtils.centMoneyToYuan(formInfo!=null?formInfo.getMoneyAmount():0));
	}
	
	@Override
	public List<Long> batchPass(String processKey, String userId, String cname, List<Long> formIds, List<String> taskIds, String memo) {
		List<Long> errorFormIds = new ArrayList<Long>();
		Map<String, String> owner = new HashMap<String, String>();
		Map<String, String> approver = new HashMap<String, String>();
		String form = "oa@qunar.com";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String now = sdf.format(new Date());
		for(int i=0; i<taskIds.size(); i++){
			String taskId = taskIds.get(i);
			long formId = formIds.get(i);
			try{
				FormInfo formInfo = form0114Manager.getFormInfo(formId);
				String amount = OAControllerUtils.centMoneyToYuan(formInfo.getMoneyAmount());
				TaskResult tr = this._pass(processKey, userId, cname, formId, taskId, memo);
				String content = userId+" 于 ["+now+"]处理了《"+tr.getOwner()+"-日常报销  总计:"+amount+"元》 [同意]";
				if(memo != null) content += " 附言:"+memo;
				owner.put(tr.getOwner(), content);
				
				if(tr != null && tr.getNextTasks() != null)for(TaskInfo info : tr.getNextTasks()){
					String candidate = info.getCandidate();
					if(candidate == null) continue;
					for(String c : candidate.split(",")){
						if(StringUtils.trim(c).length() == 0) continue;
						approver.put(StringUtils.trim(c), "您有《"+tr.getOwner()+"-日常报销  总计:"+amount+"元》申请需要处理");
					}
				}
				
			}catch(Exception e){
				errorFormIds.add(formId);
				e.printStackTrace();
			}
		}
		for(Map.Entry<String, String> e : owner.entrySet()){
			mailSenderService.sender(form, new String[]{e.getKey()+"@qunar.com"}, null, e.getValue(), e.getValue());
		}
		for(Map.Entry<String, String> e : approver.entrySet()){
			mailSenderService.sender(form, new String[]{e.getKey()+"@qunar.com"}, null, e.getValue(), e.getValue()+"[http://baoxiao.corp.qunar.com]");
		}
		return errorFormIds;
	}
	
	private TaskResult _pass(String processKey, String userId, String cname, long formId, String taskId, String memo) throws FormNotFoundException, ActivitiException, IllegalAccessException, InvocationTargetException, CompareModelException {
		FormApproveLog formApproveLog =this.logManager.getLastApproveLog(formId);
		String fincheckUser=this.logManager.getFinCheckedUser(formId,"财务报销审核组");
		TaskResult tr = null;
		if(formApproveLog!=null){
			if( !"".equals(fincheckUser)&& "加签操作".equals(formApproveLog.getNextTaskName())&&!this.groupManager.inGroups(new String[] {"fin_check", "fin_check_mdd" }, userId)){
				
				formApproveLog.setApproveUser(fincheckUser);
				tr = this.workflowManager.passNew(taskId, userId,formApproveLog.getApproveUser());
				tr.getCurrentTask().setName("加签操作");
				
			}else{
				tr= this.workflowManager.pass(taskId, userId);
			}
		}
				
		if(tr == null) throw new FormNotFoundException("任务没有找到", this.getClass());
		this.logManager.appendApproveLog(userId, cname, formId, "pass", tr, memo);
		if("fin_check".equals(tr.getCurrentTask().getTaskDefinitionKey()) || "fin_check_mdd".equals(tr.getCurrentTask().getTaskDefinitionKey())){
			this.form0114Manager.recordKeyOperation(cname+"("+userId+")", "check", formId);
		}
		if(tr.isFinished()){
			if(this.groupManager.inGroups(new String[]{"cashier"}, userId)){
				this.form0114Manager.recordKeyOperation(cname+"("+userId+")", "cashier", formId);
			}else{
				this.form0114Manager.recordKeyOperation("出纳(出纳)", "cashier", formId);
			}
			this.form0114Manager.deleteFormInfo(userId, formId, Constants.PROC_END);
			paymentService.payment(formId);
		}
		return tr;
	}

	/*
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void back(String processKey, String userId, String cname, long formId, String taskId, String refuseReason) throws FormNotFoundException, ActivitiException {
		TaskResult tr = this.workflowManager.back(userId, taskId, refuseReason);
		if(tr == null) throw new FormNotFoundException("任务没有找到", this.getClass());
		this.logManager.appendApproveLog(userId, cname, formId, "back", tr, refuseReason);
		this.sendMail(userId, "退回", tr.getOwner(), tr.getNextTasks(), refuseReason);
	}*/

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void endorse(String processKey, String userId, String cname, long formId, String taskId, String assignees, String memo) throws FormNotFoundException, ActivitiException {
		//获取到表单信息  --lee.guo
		FormInfo formInfo = form0114Manager.getFormInfo(formId);
		if(formInfo == null) throw new FormNotFoundException("工单没有找到", this.getClass());
		
		Request request = new Request();
		request.setOid(""+formInfo.getId());
		//是否直接向vp汇报
		if("是".equals(formInfo.getIsDirectVp())){
			request.setReport2vp(true);
		}else{
			request.setReport2vp(false);
		}
		
		String tk = this.workflowManager.getTaskKey(taskId);
		//被加签人为非(fin_check)同组成员，非同意后，还需加签人再次同意
		if(tk != null 
				&& (tk.equals("fin_check"))
				&& !assignees.equals(formInfo.getStartMemberId())
				&& !userId.equals(formInfo.getStartMemberId()) 
				&& !this.groupManager.inGroups(new String[] {tk}, assignees)){
			
			FormApproveLog log = this.logManager.getLastPassApproveLog(formId, assignees);
			if(log == null){
				throw new FormNotFoundException("没有找到上级节点，不能加签", this.getClass());
			}
			TaskResult tr = this.workflowManager.dragNew(userId,taskId, log.getTaskId(), assignees, memo,"加签操作");
			if(tr == null) throw new FormNotFoundException("任务没有找到", this.getClass());
			if(memo == null) memo = "";
			memo += "[加签给："+assignees+"]";
			this.logManager.appendApproveLog(userId, cname, formId, "endorse", tr, memo);
			this.sendMail(userId, "加签", tr.getOwner(), tr.getNextTasks(), memo, OAControllerUtils.centMoneyToYuan(formInfo.getMoneyAmount()));	
		}
		//被加签人为同组成员，同意后 跳过加签人
		else{
			//金额总计
			request.setAmountMoney(formInfo.getMoneyAmount());
			//员工关系费合计
			request.setTbMoney(formInfo.getSumEmployeeRelationsFees());
			//招待费合计
			request.setHosMoney(formInfo.getSumHospitalityAmount());
			//一级部门
			request.setDepartment(formInfo.getFirstDep());
			request.setDepartmentII(formInfo.getSecDep());
			request.setDepartmentIII(formInfo.getThridDep());
			request.setDepartmentIV(formInfo.getFourthDep());
			request.setDepartmentV(formInfo.getFivethDep());
			
			TaskResult tr = this.workflowManager.endorse(taskId, userId, assignees, request);
			if(tr == null) throw new FormNotFoundException("任务没有找到", this.getClass());
			
			if(tr.getNextTasks().get(0).getCandidate().equals(assignees) && !userId.equals(tr.getOwner())){
				tr.getNextTasks().get(0).setTaskName("加签操作");
			}
			if(memo == null) memo = "";
			memo += "[加签给："+assignees+"]";
			this.logManager.appendApproveLog(userId, cname, formId, "endorse", tr, memo);
			this.sendMail(userId, "加签", tr.getOwner(), tr.getNextTasks(), memo, OAControllerUtils.centMoneyToYuan(formInfo.getMoneyAmount()));
		}
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void recall(String processKey, String userId, String cname, long formId, String reason) throws Exception {
		
		FormInfo formInfo = form0114Manager.getFormInfo(formId);
		if(formInfo == null) throw new FormNotFoundException("工单没有找到", this.getClass());
		
		if(formInfo.getFinishedflag() > 0){
			throw new FormNotFoundException("流程已经结束，不能召回", this.getClass());
		}
		
		FormApproveLog log = this.logManager.getLastApproveLog(formId, userId);
		if(log == null){
			throw new FormNotFoundException("您没有处理过此申请，不能召回", this.getClass());
		}
		String type = log.getManagerType();
		String taskId = log.getTaskId();
		if("recall".equals(type)){
			taskId = log.getNextTaskId();
		}
		TaskResult tr = this.workflowManager.recall(""+formId, taskId, userId);
		if(tr == null) throw new FormNotFoundException("任务没有找到", this.getClass());
		if(reason == null) reason = "";
		reason += "[召回："+userId+"]";
		this.logManager.appendApproveLog(userId, cname, formId, "recall", tr, reason);
		
		
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void back(String processKey, String userId, String cname, String fromTaskId, long formId, String reason,String assignees) throws Exception {
		
		FormInfo formInfo = form0114Manager.getFormInfo(formId);
		if(formInfo == null) throw new FormNotFoundException("工单没有找到", this.getClass());
		
		if(formInfo.getFinishedflag() > 0){
			throw new FormNotFoundException("流程已经结束，不能退回", this.getClass());
		}
		FormApproveLog log = this.logManager.getLastPassApproveLog(formId, assignees);
		if(log == null){
			throw new FormNotFoundException("没有找到上级节点，不能退回", this.getClass());
		}
		String tk = this.workflowManager.getTaskKey(fromTaskId);
		
		TaskResult tr;
		if(tk != null 
				&& (tk.equals("fin_check"))){
			 tr = this.workflowManager.dragNew(userId,fromTaskId, log.getTaskId(), assignees, reason,"退回操作");
		}else{
			 tr = this.workflowManager.drag(userId,fromTaskId, log.getTaskId(), assignees, reason);
		}
		
		
		if(reason == null) reason = "";
		reason += "[退回给："+assignees+"]";
		
		this.logManager.appendApproveLog(userId, cname, formId, "back", tr, reason);
		
		this.sendMail(userId, "回退", tr.getOwner(), tr.getNextTasks(), reason, OAControllerUtils.centMoneyToYuan(formInfo.getMoneyAmount()));	
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void refuse(String processKey, String userId, String cname, long formId, String taskId, String refuseReason) throws FormNotFoundException, ActivitiException {
		TaskResult tr = this.workflowManager.refuse(processKey, taskId, userId, refuseReason);
		FormInfo formInfo = form0114Manager.getFormInfo(Long.valueOf(formId));
		if(tr != null && formInfo != null ){
			this.logManager.appendApproveLog(userId, cname, formId, "refuse", tr, refuseReason);
			this.form0114Manager.deleteFormInfo(userId, formId, Constants.REFUSE);
		}else{
			throw new FormNotFoundException("任务没有找到", this.getClass());
		}
		this.sendMail(userId, "拒绝", tr.getOwner(), null, refuseReason, OAControllerUtils.centMoneyToYuan(formInfo.getMoneyAmount()));
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public boolean appendCandidate(String processKey, String ownerId, List<String> userIds) throws AgentAlreadyExistsException {
		try {
			this.delegationManager.appendDelegation(ownerId, userIds);
		} catch (AgentAlreadyExistsException e) {
			throw new AgentAlreadyExistsException(e.getMessage(), this.getClass());
		}
		//this.workflowManager.appendCandidate(ownerId, userIds);
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
	
	private void sendMail(String userId, String action, String owner, List<TaskInfo> infos, String memo, String amount){
		if(owner == null) return;
		String form = "oa@qunar.com";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String now = sdf.format(new Date());
		String content = "";
		if(userId != null && action != null){
			content = userId+" 于 ["+now+"]处理了《"+owner+"-日常报销   总计:"+amount+"元》 ["+action+"]";
			if(memo != null){
				content += " 附言:"+memo;
			}
			mailSenderService.sender(form, new String[]{owner+"@qunar.com"}, null, content, content);
		}
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
			content = "["+now+"]您有《"+owner+"-日常报销  总计:"+amount+"元》 需要处理"+tn;
			if(memo != null){
				content += " 附言:"+memo;
			}
			mailSenderService.sender(form, to.toArray(new String[]{}), null, content, content+"[http://baoxiao.corp.qunar.com]");
		}
	}

	@Override
	public TreeMap<String, Integer> getMenu(String processKey, String userId) throws FormNotFoundException {
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		map.put("applyCount", getUserApplyCount(processKey, userId));
		map.put("applyHisCount", getUserApplyHisCount(processKey, userId));
		map.put("applyDraftCount", getUserDraftCount(processKey, userId));
		map.put("todoCount", todoCount(processKey, userId, null, null, userId, 0, 0));
		return map;
	}
	
	private int getUserApplyCount(String processKey, String userId) {
		return form0114Manager.getUserApplyCount(userId);
	}

	private int getUserApplyHisCount(String processKey, String userId) {
		return form0114Manager.getUserApplyHisCount(userId);
	}

	private int getUserDraftCount(String processKey, String userId) {
		return form0114Manager.getUserDraftCount(userId);
	}
	
	private int todoCount(String processKey, String userId,
			Date startTime, Date endTime, String owner, int pageNo, int pageSize) throws FormNotFoundException {
		ListInfo<TaskInfo> taskInfos = workflowManager.todoList(processKey, userId, startTime, endTime, owner, pageNo, pageSize);
		return (int)taskInfos.getCount();
	}

	@Override
	public List<Object> findHistoryForm(Date start, Date end) {
		return form0114Manager.findHistoryForm(start, end);
	}

	@Override
	public FormInfoList historyList(String userId, Date startTime, Date endTime, String owner, int pageNo, int pageSize) {
		return this.form0114Manager.historyList(userId, startTime, endTime, owner, pageNo, pageSize);
	}
	public FormInfoList historyListII(String userId, Date startTime, Date endTime, String owner, int start, int length) {
		return this.form0114Manager.historyListII(userId, startTime, endTime, owner, start, length);
	}

	@Override
	public FormInfoList search(String approveUser,String approveRtx, String approveNo,
			Date approvtStartTime, Date approveEndTime, String checkUser,
			Date checkStartTime, Date checkEndTime, String payUser,
			Date payStartTime, Date payEndTime, String status, int pageNo, int pageSize) {
		return this.form0114Manager.search(approveUser,approveRtx, approveNo,
				approvtStartTime, approveEndTime, checkUser,
				checkStartTime, checkEndTime, payUser,
				payStartTime, payEndTime, status, pageNo, pageSize);
	}

}
