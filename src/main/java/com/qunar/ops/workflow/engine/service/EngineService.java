package com.qunar.ops.workflow.engine.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.qunar.ops.workflow.engine.command.TurnBackTaskCmd;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qunar.ops.workflow.engine.result.BaseResult;
import com.qunar.ops.workflow.engine.result.ListInfo;
import com.qunar.ops.workflow.engine.result.ProcessInstanceDetailInfo;
import com.qunar.ops.workflow.engine.result.ProcessInstanceInfo;
import com.qunar.ops.workflow.engine.result.SendToMQInfo;
import com.qunar.ops.workflow.engine.result.TaskInfo;
import com.qunar.ops.workflow.engine.util.QUtils;

@Component
public class EngineService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected IdentityService identityService;
	@Autowired
	protected FormService formService;
	@Autowired
	private SendToMqService sendToMqService;

	public long findProcessInstanceCountByBusinessKey(String processKey,
			String businessKey) {
		return this.historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processKey).processInstanceBusinessKey(businessKey).count();
	}

	public ProcessInstance findProcessInstanceByBusinessKey(String processKey,
			String businessKey) {
		return this.runtimeService.createProcessInstanceQuery()
				.processInstanceBusinessKey(businessKey, processKey)
				.singleResult();
	}

	public ProcessInstanceInfo startWorkflow(String processKey, String oid,
			String userId, Map<String, Object> vars, Map<String, String> form) throws Exception {
		if (vars == null)
			vars = new HashMap<String, Object>();
		if (form == null)
			form = new HashMap<String, String>();
		identityService.setAuthenticatedUserId(userId);
		vars.put("startedUser", userId);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, oid, vars);
		//TODO
		runtimeService.setVariables(processInstance.getId(), form);
		String processInstanceId = processInstance.getId();
		logger.debug(
				"start process of {key={}, bkey={}, pid={}, variables={}}",
				new Object[] { processKey, oid, processInstanceId, vars });
		ProcessInstanceInfo info = new ProcessInstanceInfo();
		info.setOid(oid);
		info.setProcessInstanceId(processInstanceId);
		info.setStartTime(new Date());
		info.setForm(new HashMap<String, Object>(form));
		info.setCurrentTasks(this.getCurrentTasks(processInstanceId));
		return info;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<ProcessInstanceInfo> batchStartWorkflow(String processKey, List<String> oids,
			String userId, Map<String, Object> vars, Map<String, String> form) throws Exception {
		JSONArray dataArray = new JSONArray();
		if (vars == null)
			vars = new HashMap<String, Object>();
		else{
			String jsonString = JSON.toJSONString(vars.get("data"));
			dataArray = JSONArray.parseArray(jsonString);
		}
		if (form == null)
			form = new HashMap<String, String>();
		List<ProcessInstanceInfo> infos = new ArrayList<ProcessInstanceInfo>();
		identityService.setAuthenticatedUserId(userId);
		
		Iterator iter = dataArray.iterator();
		Map<String, Object> vars_tmp = new HashMap<String, Object>();
		String oid = new String();
		while(iter.hasNext()){
			JSONObject o = (JSONObject)iter.next();
			oid = (String)o.get("oid");
			vars_tmp = (Map<String, Object>)o.get("vars");
			vars_tmp.put("startedUser", userId);
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, oid, vars_tmp);
			runtimeService.setVariables(processInstance.getId(), form);
			String processInstanceId = processInstance.getId();
			logger.debug(
					"start process of {key={}, bkey={}, pid={}, variables={}}",
					new Object[] { processKey, oid, processInstanceId, vars_tmp });
			ProcessInstanceInfo info = new ProcessInstanceInfo();
			info.setOid(oid);
			info.setProcessInstanceId(processInstanceId);
			info.setStartTime(new Date());
			info.setForm(new HashMap<String, Object>(form));
			info.setCurrentTasks(this.getCurrentTasks(processInstanceId));
			infos.add(info);
		}
//		for(String oid : oids){
//			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, oid, vars);
//			runtimeService.setVariables(processInstance.getId(), form);
//			String processInstanceId = processInstance.getId();
//			logger.debug(
//					"start process of {key={}, bkey={}, pid={}, variables={}}",
//					new Object[] { processKey, oid, processInstanceId, vars });
//			ProcessInstanceInfo info = new ProcessInstanceInfo();
//			info.setOid(oid);
//			info.setProcessInstanceId(processInstanceId);
//			info.setStartTime(new Date());
//			info.setForm(new HashMap<String, Object>(form));
//			info.setCurrentTasks(this.getCurrentTasks(processInstanceId));
//			infos.add(info);
//		}
		return infos;
	}
	
	public ProcessInstanceInfo claim(Task task, String userId) throws Exception {
		ProcessInstance ins = runtimeService.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).singleResult();
		taskService.claim(task.getId(), userId);
		ProcessInstanceInfo info = new ProcessInstanceInfo();
		info.setOid(ins.getBusinessKey());
		info.setProcessInstanceId(ins.getId());
		info.setCurrentTasks(this.getCurrentTasks(ins.getId()));
		return info;
	}

	public ProcessInstanceInfo unclaim(Task task, String userId)
			throws Exception {
		ProcessInstance ins = runtimeService.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).singleResult();
		taskService.unclaim(task.getId());
		ProcessInstanceInfo info = new ProcessInstanceInfo();
		info.setOid(ins.getBusinessKey());
		info.setProcessInstanceId(ins.getId());
		info.setCurrentTasks(this.getCurrentTasks(ins.getId()));
		return info;
	}

	public ProcessInstanceInfo complet(Task task, String userId, Map<String, Object> vars, Map<String, String> form) throws Exception {
		ProcessInstance ins = runtimeService.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).singleResult();
		identityService.setAuthenticatedUserId(userId);
//		taskService.setVariablesLocal(task.getId(), vars);
//		formService.saveFormData(task.getId(), form);
		taskService.complete(task.getId(), vars);
		ProcessInstanceInfo info = new ProcessInstanceInfo();
		info.setOid(ins.getBusinessKey());
		info.setProcessInstanceId(task.getProcessInstanceId());
		info.setForm(new HashMap<String, Object>(form));
		info.setCurrentTasks(this.getCurrentTasks(task.getProcessInstanceId()));
		return info;
	}
	
	public ProcessInstanceInfo bComplet(Task task, String userId, Map<String, Object> vars, Map<String, String> form) throws Exception {
		ProcessInstance ins = runtimeService.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).singleResult();
		identityService.setAuthenticatedUserId(userId);
//		taskService.setVariablesLocal(task.getId(), vars);
//		formService.saveFormData(task.getId(), form);
		taskService.complete(task.getId(), vars);
		ProcessInstanceInfo info = new ProcessInstanceInfo();
		info.setOid(ins.getBusinessKey());
		info.setProcessInstanceId(task.getProcessInstanceId());
//		info.setForm(new HashMap<String, Object>(form));
//		info.setCurrentTasks(this.getCurrentTasks(task.getProcessInstanceId()));
		int taskSize = this.getCurrentTasks(task.getProcessInstanceId()).size();
		if(taskSize == 1 && this.getCurrentTasks(task.getProcessInstanceId()).get(0).getType().equals("已结束")){
			info.setIsFinished("true");
		}else{
			info.setIsFinished("false");
		}
		return info;
	}
	
	public void completWithoutReturn(Task task, String userId, Map<String, Object> vars, Map<String, String> form) throws Exception {
		identityService.setAuthenticatedUserId(userId);
//		taskService.setVariablesLocal(task.getId(), vars);
//		formService.saveFormData(task.getId(), form);
		taskService.complete(task.getId(), vars);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<ProcessInstanceInfo> batchComplet(List<Task> tasks, String userId, Map<String, Object> vars, Map<String, String> form) throws Exception {
		List<ProcessInstanceInfo> list = new ArrayList<ProcessInstanceInfo>();
		//拼接发送到MQ的消息体
		SendToMQInfo mqInfo = new SendToMQInfo();
		Map<String, List<Map<String, Object>>> uTasks = new HashMap<String, List<Map<String, Object>>>();
		String startedUser = null;
		String processKey = null;
		List<Map<String, Object>> myTasks;
		for(Task task : tasks){
			ProcessInstance ins = runtimeService.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			if(task.getAssignee() == null){
				this.taskService.claim(task.getId(), userId);
			}
			identityService.setAuthenticatedUserId(userId);
//			taskService.setVariablesLocal(task.getId(), vars);
			taskService.complete(task.getId(), vars);
			ProcessInstanceInfo info = new ProcessInstanceInfo();
			String[] keys = ins.getProcessDefinitionId().split(":");
			info.setProcessKey(keys[0]);
			info.setOid(ins.getBusinessKey());
			info.setProcessInstanceId(task.getProcessInstanceId());
//			info.setForm(new HashMap<String, Object>(form));
//			info.setCurrentTasks(this.getCurrentTasks(task.getProcessInstanceId()));
			
			//拼接发送到MQ的消息体
			Map<String, Object> insVars = runtimeService.getVariables(ins.getId());
			processKey = ins.getProcessDefinitionId().split(":")[0];
			startedUser = (String)insVars.get("startedUser");
			Map<String, Object> myTask = new HashMap<String, Object>();
			myTask.put("oid", ins.getBusinessKey());
			
			int taskSize = this.getCurrentTasks(task.getProcessInstanceId()).size();
			if(taskSize == 1 && this.getCurrentTasks(task.getProcessInstanceId()).get(0).getType().equals("已结束")){
				info.setIsFinished("true");
				myTask.put("action", "complete");
			}else{
				info.setIsFinished("false");
				if(Integer.valueOf((String)vars.get("approve")) <=0 ){
					myTask.put("action", "reject");
				}else{
					myTask.put("action", "pass");
				}
			}
			
			//拼接发送到MQ的消息体
			if(uTasks.containsKey(startedUser)){
				uTasks.get(startedUser).add(myTask);
			}else{
				if(startedUser != null){
					myTasks = new ArrayList<Map<String, Object>>();
					myTasks.add(myTask);
					uTasks.put(startedUser, myTasks);
				}
			}
			
			list.add(info);
		}
		//发送到MQ
		Iterator iter = uTasks.entrySet().iterator();
		try {
			while(iter.hasNext()){
				Map.Entry<String, List<Map<String, Object>>> entry = (Map.Entry<String, List<Map<String, Object>>>)iter.next();
				mqInfo.setStartedUser(entry.getKey());
				mqInfo.setTasks(entry.getValue());
				sendToMqService.sendToMq(processKey, mqInfo);
			}
		} catch (Exception e) {
			throw new Exception("Send_To_MQ_Error");
		}
		
		return list;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public List<ProcessInstanceInfo> batchRemove(String[] pids, String userId, boolean isDelete, String reason) throws Exception {
		List<ProcessInstanceInfo> list = new ArrayList<ProcessInstanceInfo>();
		for(String pid : pids){
			ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
			if(pi == null){
				continue;
			}
			this.runtimeService.deleteProcessInstance(pid, reason);
			logger.warn(new Date() + "remove(主动删除)："+"["+pid+"]:");
			if(isDelete){
				this.historyService.deleteHistoricProcessInstance(pid);
			}
			ProcessInstanceInfo info = new ProcessInstanceInfo();
			String[] keys = pi.getProcessDefinitionId().split(":");
			info.setProcessKey(keys[0]);
			info.setOid(pi.getBusinessKey());
			info.setProcessInstanceId(pid);
			list.add(info);
		}
		return list;
	}
	
	public void removeWithoutReturn(String pid, String userId, boolean isDelete, String reason) throws Exception {
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
		if(pi == null){
			return;
		}
		this.runtimeService.deleteProcessInstance(pid, reason);
		if(isDelete){
			this.historyService.deleteHistoricProcessInstance(pid);
		}
	}
	
	public ProcessInstanceInfo turnTransition(Task task, String activityId, 
			Map<String, Object> vars, Map<String, String> form)
			throws Exception {
		String assignee = (String)vars.get("assignee");
		ActivityImpl currActivity = findActivitiImpl(task.getId(), null);
		List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);
		TransitionImpl newTransition = currActivity.createOutgoingTransition();
		ActivityImpl pointActivity = findActivitiImpl(task.getId(), activityId);
		newTransition.setDestination(pointActivity);
//		taskService.setVariablesLocal(task.getId(), vars);
		taskService.setAssignee(task.getId(), assignee);
		//TODO
		taskService.complete(task.getId(), vars);
		pointActivity.getIncomingTransitions().remove(newTransition);
		restoreTransition(currActivity, oriPvmTransitionList);
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		ProcessInstanceInfo info = new ProcessInstanceInfo();
		info.setOid(pi.getBusinessKey());
		info.setProcessInstanceId(pi.getId());
		info.setStartTime(new Date());
		info.setForm(new HashMap<String, Object>(form));
		info.setCurrentTasks(this.getCurrentTasks(pi.getId()));
		return info;
	}
	
	public ProcessInstanceInfo turnback(Task task, Map<String, Object> vars, Map<String, String> form) throws Exception{
		String turnback_reason = (String)vars.get("turnback_reason");
		List<HistoricActivityInstance> lastActs = this.findLastTasks(task);
		TaskServiceImpl taskService = (TaskServiceImpl)this.taskService;
		Map<String, String> destinationTasks = new HashMap<String, String>();
		for(HistoricActivityInstance lastAct : lastActs){
			destinationTasks.put(lastAct.getActivityId(), lastAct.getAssignee());
		}
		if(destinationTasks.isEmpty()){
			return null;
		}
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		//TODO
		taskService.setVariablesLocal(task.getId(), vars);
		runtimeService.setVariables(pi.getId(), form);
		Map<String, String> findFlowActivity = this.findFlowActivity(lastActs, task.getProcessDefinitionId());
		taskService.getCommandExecutor().execute(new TurnBackTaskCmd(task.getId(), destinationTasks, findFlowActivity, turnback_reason));
		ProcessInstanceInfo info = new ProcessInstanceInfo();
		info.setOid(pi.getBusinessKey());
		info.setProcessInstanceId(pi.getId());
		info.setStartTime(new Date());
		info.setForm(new HashMap<String, Object>(form));
		info.setCurrentTasks(this.getCurrentTasks(pi.getId()));
		return info;
	}
	

	public Object findeProcessInstanceByOwner(String processKey, String userId, Map<String, Object> vars){
		String sstartTime = (String)vars.get("startTime");
		String sendTime = (String)vars.get("endTime");
		String spageNo = (String)vars.get("pageNo");
		String spageSize = (String)vars.get("pageSize");
		int startTime = sstartTime==null?0:Integer.valueOf(sstartTime);
		int endTime = sendTime==null?0:Integer.valueOf(sendTime);
		int pageNo = spageNo==null?0:Integer.valueOf(spageNo);
		int pageSize = spageSize==null?0:Integer.valueOf(spageSize);
		pageNo = pageNo <= 0 ? 1 : pageNo;
		List<HistoricProcessInstance> ins = new ArrayList<HistoricProcessInstance>();
		HistoricProcessInstanceQuery query = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey(processKey).startedBy(userId);
		if (startTime > 0) {
			query.startedAfter(new Date(startTime));
		}
		if (endTime > 0) {
			query.startedBefore(new Date(endTime));
		}
		if (pageSize > 0) {
			ins = query.unfinished().orderByProcessInstanceStartTime().desc()
					.listPage((pageNo - 1) * pageSize, 2);
		} else {
			ins = query.unfinished().orderByProcessInstanceStartTime().desc()
					.list();
		}
		long count = query.unfinished().count();
		List<ProcessInstanceInfo> list = new ArrayList<ProcessInstanceInfo>();
		for (HistoricProcessInstance in : ins) {
			ProcessInstanceDetailInfo processInstanceInfo = new ProcessInstanceDetailInfo();
			List<HistoricVariableInstance> _vars = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(in.getId()).list();
			for(HistoricVariableInstance var : _vars){
				if(var.getTaskId() == null){
					processInstanceInfo.addFormItem(var.getVariableName(), var.getValue());
				}
			}
			processInstanceInfo.setOid(in.getBusinessKey());
			processInstanceInfo.setProcessInstanceId(in.getId());
			processInstanceInfo.setStartTime(in.getStartTime());
			processInstanceInfo.setEndTime(in.getEndTime());
			processInstanceInfo.setTaskTrace(this.getTaskTrace(in, _vars));
			list.add(processInstanceInfo);
		}
		if (pageSize > 0) {
			return new ListInfo(pageNo, pageSize, count, list);
		}
		return list;
	}
	
	

	public Object findeProcessInstanceByTodo(String processKey, String userId, Map<String, Object> vars) {
		Map<String, ProcessInstance> infos = new HashMap<String, ProcessInstance>();
		List<ProcessInstanceInfo> list = new ArrayList<ProcessInstanceInfo>();
		String sstartTime = (String) vars.get("startTime");
		String sendTime = (String) vars.get("endTime");
		String spageNo = (String) vars.get("pageNo");
		String spageSize = (String) vars.get("pageSize");
		int startTime = sstartTime == null ? 0 : Integer.valueOf(sstartTime);
		int endTime = sendTime == null ? 0 : Integer.valueOf(sendTime);
		int pageNo = spageNo == null ? 0 : Integer.valueOf(spageNo);
		int pageSize = spageSize == null ? 0 : Integer.valueOf(spageSize);
		pageNo = pageNo <= 0 ? 1 : pageNo;

		List<Task> tasks = new ArrayList<Task>();
		TaskQuery query = taskService.createTaskQuery().taskCandidateUser(userId);
		if(!"all".equalsIgnoreCase(processKey)){
			query.processDefinitionKey(processKey).taskCandidateUser(userId);
		}
		if (startTime > 0) {
			query.taskCreatedAfter(new Date(startTime));
		}
		if (endTime > 0) {
			query.taskCreatedBefore(new Date(endTime));
		}
		if (pageSize > 0) {
			tasks = query.orderByTaskCreateTime().desc()
					.listPage((pageNo - 1) * pageSize, pageSize);
		} else {
			tasks = query.orderByTaskCreateTime().desc().list();
		}
		long count = query.count();
		for (Task task : tasks) {
			ProcessInstance pi = infos.get(task.getProcessInstanceId());
			if(pi == null){
				pi = runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(task.getProcessInstanceId())
						.singleResult();
				infos.put(pi.getId(), pi);
			}
			ProcessInstanceInfo processInstanceInfo = new ProcessInstanceInfo();
			List<HistoricVariableInstance> _vars = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(pi.getId()).list();
			for(HistoricVariableInstance var : _vars){
				if(var.getTaskId() == null){
					processInstanceInfo.addFormItem(var.getVariableName(), var.getValue());
				}
			}
			processInstanceInfo.setOid(pi.getBusinessKey());
			processInstanceInfo.setProcessInstanceId(pi.getId());
			TaskInfo tinfo = new TaskInfo();
			tinfo.setAssign(task.getAssignee());
			tinfo.setStartTime(task.getCreateTime());
			tinfo.setEndTime(null);
			tinfo.setTaskId(task.getId());
			tinfo.setTaskName(task.getName());
			tinfo.setTaskKey(task.getTaskDefinitionKey());
			tinfo.setType("待处理");
			processInstanceInfo.addCurrentTasks(tinfo);
			list.add(processInstanceInfo);
		}
		if (pageSize > 0) {
			return new ListInfo(pageNo, pageSize, count, list);
		}
		return list;
	}

	public Object findeHistoryProcessInstanceByOwner(String processKey,
			String userId, Map<String, Object> vars) {
		List<ProcessInstanceDetailInfo> infos = new ArrayList<ProcessInstanceDetailInfo>();
		String sstartTime = (String) vars.get("startTime");
		String sendTime = (String) vars.get("endTime");
		String spageNo = (String) vars.get("pageNo");
		String spageSize = (String) vars.get("pageSize");
		int startTime = sstartTime == null ? 0 : Integer.valueOf(sstartTime);
		int endTime = sendTime == null ? 0 : Integer.valueOf(sendTime);
		int pageNo = spageNo == null ? 0 : Integer.valueOf(spageNo);
		int pageSize = spageSize == null ? 0 : Integer.valueOf(spageSize);
		pageNo = pageNo <= 0 ? 1 : pageNo;
		List<HistoricProcessInstance> list = new ArrayList<HistoricProcessInstance>();
		HistoricProcessInstanceQuery query = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey(processKey).startedBy(userId);
		if (startTime > 0) {
			query.startedAfter(new Date(startTime));
		}
		if (endTime > 0) {
			query.startedBefore(new Date(endTime));
		}
		if (pageSize > 0) {
			list = query.finished().orderByProcessInstanceEndTime().desc()
					.listPage((pageNo - 1) * pageSize, (pageNo * pageSize) - 1);
		} else {
			list = query.finished().orderByProcessInstanceEndTime().desc()
					.list();
		}
		long count = query.count();
		for (HistoricProcessInstance hi : list) {
			ProcessInstanceDetailInfo info = new ProcessInstanceDetailInfo();
			List<HistoricVariableInstance> _vars = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(hi.getId()).list();
			for(HistoricVariableInstance var : _vars){
				if(var.getTaskId() == null){
					info.addFormItem(var.getVariableName(), var.getValue());
				}
			}
			info.setOid(hi.getBusinessKey());
			info.setProcessInstanceId(hi.getId());
			info.setStartTime(hi.getStartTime());
			info.setEndTime(hi.getEndTime());
			info.setTaskTrace(this.getTaskTrace(hi, _vars));
			infos.add(info);
		}
		if (pageSize > 0) {
			return new ListInfo(pageNo, pageSize, count, infos);
		}
		return infos;
	}

	public Object findeHistoryProcessInstanceByInvolved(String processKey,
			String userId, Map<String, Object> vars) {
		String sstartTime = (String) vars.get("startTime");
		String sendTime = (String) vars.get("endTime");
		String spageNo = (String) vars.get("pageNo");
		String spageSize = (String) vars.get("pageSize");
		int startTime = sstartTime == null ? 0 : Integer.valueOf(sstartTime);
		int endTime = sendTime == null ? 0 : Integer.valueOf(sendTime);
		int pageNo = spageNo == null ? 0 : Integer.valueOf(spageNo);
		int pageSize = spageSize == null ? 0 : Integer.valueOf(spageSize);
		pageNo = pageNo <= 0 ? 1 : pageNo;
		List<ProcessInstanceDetailInfo> infos = new ArrayList<ProcessInstanceDetailInfo>();
		List<HistoricProcessInstance> list = new ArrayList<HistoricProcessInstance>();
		HistoricProcessInstanceQuery query = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey(processKey).involvedUser(userId);
		if (startTime > 0) {
			query.startedAfter(new Date(startTime));
		}
		if (endTime > 0) {
			query.startedBefore(new Date(endTime));
		}
		if (pageSize > 0) {
			list = query.finished().orderByProcessInstanceEndTime().desc()
					.listPage((pageNo - 1) * pageSize, (pageNo * pageSize) - 1);
		} else {
			list = query.finished().orderByProcessInstanceEndTime().desc()
					.list();
		}
		long count = query.count();
		for (HistoricProcessInstance hi : list) {
			ProcessInstanceDetailInfo info = new ProcessInstanceDetailInfo();
			List<HistoricVariableInstance> _vars = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(hi.getId()).list();
			for(HistoricVariableInstance var : _vars){
				if(var.getTaskId() == null){
					info.addFormItem(var.getVariableName(), var.getValue());
				}
			}
			info.setOid(hi.getBusinessKey());
			info.setProcessInstanceId(hi.getId());
			info.setStartTime(hi.getStartTime());
			info.setEndTime(hi.getEndTime());
			info.setTaskTrace(this.getTaskTrace(hi, _vars));
			infos.add(info);
		}
		if (pageSize > 0) {
			return new ListInfo(pageNo, pageSize, count, infos);
		}
		return infos;
	}


	public Object findeProcessInstance(String processKey, String userId, Map<String, Object> vars) {
		String sstartTime = (String) vars.get("startTime");
		String sendTime = (String) vars.get("endTime");
		String spageNo = (String) vars.get("pageNo");
		String spageSize = (String) vars.get("pageSize");
		String keys = (String)vars.get("keys");
		String startedUser = (String)vars.get("startedUser");
		int startTime = sstartTime == null ? 0 : Integer.valueOf(sstartTime);
		int endTime = sendTime == null ? 0 : Integer.valueOf(sendTime);
		int pageNo = spageNo == null ? 0 : Integer.valueOf(spageNo);
		int pageSize = spageSize == null ? 0 : Integer.valueOf(spageSize);
		pageNo = pageNo <= 0 ? 1 : pageNo;
		List<ProcessInstanceDetailInfo> infos = new ArrayList<ProcessInstanceDetailInfo>();
		List<HistoricProcessInstance> list = new ArrayList<HistoricProcessInstance>();
		HistoricProcessInstanceQuery query = historyService
				.createHistoricProcessInstanceQuery();
		if(keys != null){
			keys = QUtils.full2HalfChange(keys).trim();
			keys = keys.replace(" ", "").toLowerCase();
			for(String key : keys.split(",")){
				if("".equals(key)) continue;
				String[] strings = key.split("=");
				if(strings.length != 2) continue;
				String k = strings[0];
				String v = strings[1];
				query.variableValueEquals(k, v);
			}
		}
		if(startedUser != null){
			query.startedBy(startedUser);
		}
		if (startTime > 0) {
			query.startedAfter(new Date(startTime));
		}
		if (endTime > 0) {
			query.startedBefore(new Date(endTime));
		}
		if (pageSize > 0) {
			list = query.finished().orderByProcessInstanceEndTime().desc()
					.listPage((pageNo - 1) * pageSize, (pageNo * pageSize) - 1);
		} else {
			list = query.orderByProcessInstanceEndTime().desc().list();
		}
		long count = query.count();
		for (HistoricProcessInstance hi : list) {
			ProcessInstanceDetailInfo info = new ProcessInstanceDetailInfo();
			List<HistoricVariableInstance> _vars = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(hi.getId()).list();
			for(HistoricVariableInstance var : _vars){
				if(var.getTaskId() == null){
					info.addFormItem(var.getVariableName(), var.getValue());
				}
			}
			info.setOid(hi.getBusinessKey());
			info.setProcessInstanceId(hi.getId());
			info.setStartTime(hi.getStartTime());
			info.setEndTime(hi.getEndTime());
			info.setTaskTrace(this.getTaskTrace(hi, _vars));
			infos.add(info);
		}
		if (pageSize > 0) {
			return new ListInfo(pageNo, pageSize, count, infos);
		}
		return infos;
	}

	public ProcessInstanceDetailInfo traceProcess(String processInstanceId) {
		ProcessInstanceDetailInfo info = new ProcessInstanceDetailInfo();
		HistoricProcessInstance processInstance = historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		if (processInstance == null) {
			info.setOid(null);
			info.setProcessInstanceId(null);
			info.setStartTime(null);
			info.setEndTime(null);
			info.setTaskTrace(null);
		} else {
			List<HistoricVariableInstance> vars = this.historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
			for(HistoricVariableInstance var : vars){
				if(var.getTaskId() == null){
					info.addFormItem(var.getVariableName(), var.getValue());
				}
			}
			info.setOid(processInstance.getBusinessKey());
			info.setProcessInstanceId(processInstance.getId());
			info.setStartTime(processInstance.getStartTime());
			info.setEndTime(processInstance.getEndTime());
			info.setTaskTrace(this.getTaskTrace(processInstance, vars));
		}
		return info;
	}


//	public ProcessInstanceDetailInfo traceProcessByOid(String oid) {
//		HistoricProcessInstance processInstance = historyService
//				.createHistoricProcessInstanceQuery()
//				.processInstanceBusinessKey(oid)
//				.singleResult();
//		ProcessInstanceDetailInfo info = new ProcessInstanceDetailInfo();
//		info.setOid(processInstance.getBusinessKey());
//		info.setProcessInstanceId(processInstance.getId());
//		info.setStartTime(processInstance.getStartTime());
//		info.setEndTime(processInstance.getEndTime());
//		info.setTaskTrace(this.getTaskTrace(processInstance));
//		return info;
//	}
	
	private List<TaskInfo> getCurrentTasks(String processInstanceId) {
		List<Task> tasks = taskService.createTaskQuery()
				.processInstanceId(processInstanceId).list();
		List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
		if (tasks.size() > 0)
			for (Task _task : tasks) {
//				if(vars != null){
//					taskService.setVariablesLocal(_task.getId(), vars);
//				}
				String candidate = "";
				List<IdentityLink> ids = taskService
						.getIdentityLinksForTask(_task.getId());
				for (IdentityLink id : ids) {
					if (IdentityLinkType.CANDIDATE.equals(id.getType())) {
						candidate += id.getUserId() + ",";
					}
				}
				if (candidate.length() > 0) {
					candidate = candidate.substring(0, candidate.length() - 1);
				}
				TaskInfo tinfo = new TaskInfo();
				tinfo.setCandidate(candidate);
				tinfo.setAssign(_task.getAssignee());
				tinfo.setStartTime(_task.getCreateTime());
				tinfo.setEndTime(null);
				tinfo.setTaskId(_task.getId());
				tinfo.setTaskName(_task.getName());
				tinfo.setTaskKey(_task.getTaskDefinitionKey());
				tinfo.setType("待处理");
				//tinfo.setVars(taskService.getVariablesLocal(_task.getId()));
				taskInfos.add(tinfo);
			}
		else {
			TaskInfo tinfo = new TaskInfo();
			tinfo.setCandidate(null);
			tinfo.setAssign(null);
			tinfo.setStartTime(null);
			tinfo.setEndTime(null);
			tinfo.setTaskId(null);
			tinfo.setTaskName(null);
			tinfo.setType("已结束");
			tinfo.setVars(null);
			taskInfos.add(tinfo);
		}
		return taskInfos;
	}
	
  
	private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
		List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		for (PvmTransition pvmTransition : pvmTransitionList) {
			oriPvmTransitionList.add(pvmTransition);
		}
		pvmTransitionList.clear();
		return oriPvmTransitionList;
	}

	private void restoreTransition(ActivityImpl activityImpl,
			List<PvmTransition> oriPvmTransitionList) {
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		pvmTransitionList.clear();
		for (PvmTransition pvmTransition : oriPvmTransitionList) {
			pvmTransitionList.add(pvmTransition);
		}
	}

	private ActivityImpl findActivitiImpl(String taskId, String activityId) {
		TaskEntity task = findTaskById(taskId);
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(task.getProcessDefinitionId());
		if (activityId == null || activityId.length() == 0) {
			activityId = task.getTaskDefinitionKey();
		}
		if (activityId.toUpperCase().equals("END")) {
			for (ActivityImpl activityImpl : processDefinition.getActivities()) {
				List<PvmTransition> pvmTransitionList = activityImpl
						.getOutgoingTransitions();
				if (pvmTransitionList.isEmpty()) {
					return activityImpl;
				}
			}
		}
		ActivityImpl activityImpl = processDefinition.findActivity(activityId);
		return activityImpl;
	}

	private TaskEntity findTaskById(String taskId) {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery()
				.taskId(taskId).singleResult();
		return task;
	}


	public List<HistoricActivityInstance> findLastTasks(Task task) {
		List<HistoricActivityInstance> res = new ArrayList<HistoricActivityInstance>();
		List<String> acts = new ArrayList<String>();
		PvmActivity act = this.findActivitiImpl(task.getId(), null);
		this.findIncomingTasks(act, acts);
		for (String actid : acts) {
			/*
			List<HistoricTaskInstance> list = this.historyService
					.createHistoricTaskInstanceQuery().processInstanceId(task.getProcessInstanceId()).finished()
					.taskDefinitionKey(taskKey).orderByHistoricTaskInstanceEndTime()
					.desc().list();
			if (list.size() == 0) continue;
			tasks.add(list.get(0));*/
			
			List<HistoricActivityInstance> list = this.historyService.createHistoricActivityInstanceQuery().processInstanceId(task.getProcessInstanceId()).finished().activityId(actid).orderByHistoricActivityInstanceEndTime().desc().list();
			if (list.size() == 0) continue;
			res.add(list.get(0));
		}
		return res;
	}
	
	private void findIncomingTasks(PvmActivity act, List<String> taskKeys) {
		List<PvmTransition> incomings = act.getIncomingTransitions();
		if(incomings.isEmpty() && act.getParent() != null &&  act.getParent() instanceof PvmActivity) {
			PvmActivity parent = (PvmActivity)act.getParent();
			incomings = parent.getIncomingTransitions();
		}
		if (incomings.size() == 0)
			return;
		for (PvmTransition incoming : incomings) {
			PvmActivity source = incoming.getSource();
			if ("userTask".equals(source.getProperty("type"))) {
				taskKeys.add(source.getId());
			}else if("subProcess".equals(source.getProperty("type"))){
				taskKeys.add(source.getId());
				/*
				for(PvmActivity _act : source.getActivities()){
					if(((String)_act.getProperty("type")).toLowerCase().indexOf("endevent")>=0){
						this.findIncomingTasks(_act, taskKeys);
					}
				}*/
			}else{
				this.findIncomingTasks(source, taskKeys);
			}
		}
	}
	
	private Map<String, String> findFlowActivity(List<HistoricActivityInstance> acts, String processDefinitionId){
		Map<String, String> flowActivity = new HashMap<String, String>();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);
		for(HistoricActivityInstance act : acts){
			PvmActivity _act = processDefinition.findActivity(act.getActivityId());
			this.getFollowTasks(_act, flowActivity, true);
		}
		return flowActivity;
	}
	
	private void getFollowTasks(PvmActivity act, Map<String, String> acts, boolean init){
		if(!init){
			acts.put(act.getId(), (String)act.getProperty("type"));
		}
		List<PvmTransition> outgoingTransitions = act.getOutgoingTransitions();
		if(outgoingTransitions.isEmpty() && act.getParent() != null &&  act.getParent() instanceof PvmActivity) {
			PvmActivity parent = (PvmActivity)act.getParent();
			outgoingTransitions = parent.getOutgoingTransitions();
		}
		for(PvmTransition outgoingTransition : outgoingTransitions){
			PvmActivity dest = outgoingTransition.getDestination();
			if(dest == null) continue;
			if("subProcess".equals(dest.getProperty("type"))){
				acts.put(dest.getId(), (String)dest.getProperty("type"));
				for(PvmActivity _act : dest.getActivities()){
					this.getFollowTasks(_act, acts, false);
				}
				
			}else{
				this.getFollowTasks(outgoingTransition.getDestination(), acts, false);
			}
		}
	}
	
	public List<TaskInfo> getTaskTrace(HistoricProcessInstance ins, List<HistoricVariableInstance> vars) {
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(ins.getProcessDefinitionId());

		List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
		List<HistoricTaskInstance> histasks = historyService
				.createHistoricTaskInstanceQuery()
				.processInstanceId(ins.getId()).list();
		Map<String, Boolean> hisTasks = new HashMap<String, Boolean>();
		for (HistoricTaskInstance task : histasks) {
			hisTasks.put(task.getTaskDefinitionKey(), true);
			String candidate = "";
			if(task.getAssignee() == null || task.getAssignee().isEmpty()){
				List<HistoricIdentityLink> ids = historyService
						.getHistoricIdentityLinksForTask(task.getId());
				for (HistoricIdentityLink id : ids) {
					if (IdentityLinkType.CANDIDATE.equals(id.getType())) {
						candidate += id.getUserId() + ",";
					}
				}
				if (candidate.length() > 0) {
					candidate = candidate.substring(0, candidate.length() - 1);
				}
			}
			TaskInfo tinfo = new TaskInfo();
			tinfo.setAssign(task.getAssignee());
			tinfo.setCandidate(candidate);
			tinfo.setStartTime(task.getStartTime());
			tinfo.setEndTime(task.getEndTime());
			tinfo.setTaskId(task.getId());
			tinfo.setTaskName(task.getName());
			tinfo.setTaskKey(task.getTaskDefinitionKey());
			if(task.getEndTime() == null){
				tinfo.setType("待处理");
			}else{
				tinfo.setType("已处理");
			}
			if(vars != null)for(HistoricVariableInstance var : vars){
				if(task.getId().equals(var.getTaskId())){
					tinfo.addVar(var.getVariableName(), var.getValue());
				}
			}
			taskInfos.add(tinfo);
		}
		List<ActivityImpl> activitiList = processDefinition.getActivities();
		for (ActivityImpl activity : activitiList) {
			if (!(activity.getActivityBehavior() instanceof UserTaskActivityBehavior)) {
				continue;
			}
			if (hisTasks.containsKey(activity.getId()))
				continue;
			TaskInfo tinfo = new TaskInfo();
			tinfo.setAssign(null);
			tinfo.setStartTime(null);
			tinfo.setEndTime(null);
			tinfo.setTaskId(null);
			tinfo.setTaskName((String) activity.getProperty("name"));
			tinfo.setType("未处理");
			taskInfos.add(tinfo);
		}
		Collections.sort(taskInfos, new Comparator<TaskInfo>() {
			@Override
			public int compare(TaskInfo o1, TaskInfo o2) {
				if (o1.getStartTime() == null)
					return 1;
				if (o2.getStartTime() == null)
					return -1;
				if (o1.getStartTime().getTime() > o2.getStartTime().getTime())
					return 1;
				return -1;
			}
		});
		return taskInfos;
	}

}
