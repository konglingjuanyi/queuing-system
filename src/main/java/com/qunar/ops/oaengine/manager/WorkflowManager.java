package com.qunar.ops.oaengine.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.TaskServiceImpl;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.command.TurnBackTaskCmd;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.ProcessInstanceInfo;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;

@Component
public class WorkflowManager {

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
	
	public String getTaskKey(String taskId){
		Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task == null) return null;
		return task.getTaskDefinitionKey();
	}
	
	/**
	 * 启动申请流程
	 * @param processKey
	 * @param userId
	 * @param request
	 * @return Object[]; 0-流程ID；1-当前任务信息
	 */
	public Object[] startWorkflow(String processKey, String userId, String cname, Request request) throws ActivitiException{
		this.identityService.setAuthenticatedUserId(userId);
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("request", request);
		vars.put("owner", userId);
		vars.put("cname", cname);
		vars.put("startTime", new Date());
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, request.getOid(), vars);
		if(processInstance == null){
			logger.error("start process fail! {key={}, bkey={}}", new Object[]{processKey, request.getOid()});
			return null;
		}
		logger.debug(
				"start process of {key={}, bkey={}, pid={}}",
				new Object[] { processKey, request.getOid(), processInstance.getId()});
		TaskResult tr = new TaskResult(userId, cname, null, this.getCurrentTasks(processInstance.getId()));
		return new Object[]{processInstance.getId(), tr};
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
		pageNo = pageNo <= 0 ? 1 : pageNo;
		pageSize = pageSize > 0 ? pageSize : 20;
		
		TaskQuery query = this.taskService.createTaskQuery().processDefinitionKey(processKey).taskCandidateOrAssigned(userId);
		if(startTime != null){
			//query.taskCreatedAfter(startTime);
			query.processVariableValueGreaterThanOrEqual("startTime", startTime);
		}
		if(endTime != null){
			//query.taskCreatedBefore(endTime);
			query.processVariableValueLessThanOrEqual("startTime", endTime);
		}
		if(owner != null){
			query.processVariableValueLike("cname", "%"+owner+"%");
		}
		
		long count = query.count();
		List<Task> tasks = query.listPage((pageNo - 1) * pageSize, pageSize);
		ListInfo<TaskInfo> infos = new ListInfo<TaskInfo>();
		infos.setCount(count);
		infos.setPageNo(pageNo);
		infos.setPageSize(pageSize);
		if(tasks != null)for(Task task : tasks){
			TaskInfo info = new TaskInfo();
			Request request = (Request)task.getProcessVariables().get("request");
			if(request == null){
				info.setOid("");
			}else{
				info.setOid(request.getOid());
			}
			info.setProcessInstanceId(task.getProcessInstanceId());
			info.setTaskId(task.getId());
			info.setTaskKey(task.getTaskDefinitionKey());
			info.setTaskName(task.getName());
			info.setTaskCreateTime(task.getCreateTime());
			Map<String, Object> taskLocalVariables = task.getTaskLocalVariables();
			Integer nrOfInstances = this.runtimeService.getVariable(task.getExecutionId(), "nrOfInstances", Integer.class);
			if(nrOfInstances == null || nrOfInstances <= 0){
				info.setEndorse(false);
			}else{
				info.setEndorse(true);
			}

			infos.getInfos().add(info);
		}
		return infos;
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
		pageNo = pageNo <= 0 ? 1 : pageNo;
		pageSize = pageSize > 0 ? pageSize : 20;
		
		HistoricTaskInstanceQuery query = this.historyService.createHistoricTaskInstanceQuery().processDefinitionKey(processKey).taskAssignee(userId);
		if(startTime != null){
			query.taskCreatedAfter(startTime);
		}
		if(endTime != null){
			query.taskCreatedBefore(endTime);
		}
		if(owner != null){
			query.processVariableValueEquals("owner", owner);
		}
		
		long count = query.count();
		List<HistoricTaskInstance> tasks = query.listPage((pageNo - 1) * pageSize, pageSize);
		ListInfo<TaskInfo> infos = new ListInfo<TaskInfo>();
		infos.setCount(count);
		infos.setPageNo(pageNo);
		infos.setPageSize(pageSize);
		if(tasks != null)for(HistoricTaskInstance task : tasks){
			TaskInfo info = new TaskInfo();
			Request request = (Request)task.getProcessVariables().get("request");
			if(request == null){
				info.setOid("");
			}else{
				info.setOid(request.getOid());
			}
			info.setProcessInstanceId(task.getProcessInstanceId());
			info.setTaskId(task.getId());
			info.setTaskKey(task.getTaskDefinitionKey());
			info.setEndTime(task.getEndTime());
			info.setTaskName(task.getName());
			System.out.println(task.getExecutionId());

			Integer nrOfInstances = null;
			try {
				nrOfInstances = this.runtimeService.getVariable(task.getExecutionId(), "nrOfInstances", Integer.class);
			}catch (ActivitiObjectNotFoundException e){
				e.printStackTrace();
				logger.warn(e.getMessage());
			}
			if(nrOfInstances == null || nrOfInstances <= 0){
				info.setEndorse(false);
			}else{
				info.setEndorse(true);
			}
			infos.getInfos().add(info);
		}
		return infos;
	}
	
	/**
	 * 审批历史流程列表
	 * @param processKey
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @param owner
	 * @param pageNo
	 * @param pageSize
	 * @return ListInfo<TaskInfo> 任务列表
	 */
	public ListInfo<ProcessInstanceInfo> historyInstList(String processKey, String userId, Date startTime, Date endTime, String owner, int pageNo, int pageSize){
		pageNo = pageNo <= 0 ? 1 : pageNo;
		pageSize = pageSize > 0 ? pageSize : 20;
		
		HistoricProcessInstanceQuery query = this.historyService
				.createHistoricProcessInstanceQuery()
				.involvedUser(userId).variableValueNotEquals("owner", userId);
		if(startTime != null){
			query.startedAfter(startTime);
		}
		if(endTime != null){
			query.startedBefore(endTime);
		}
		if(owner != null){
			query.startedBy(owner);
		}
		
		long count = query.count();
		List<HistoricProcessInstance> list = new ArrayList<HistoricProcessInstance>();
//		List<HistoricTaskInstance> tasks = query.listPage((pageNo - 1) * pageSize, pageSize);
//		if (pageSize > 0) {
		list = query.orderByProcessInstanceEndTime().desc().listPage((pageNo - 1) * pageSize, (pageNo * pageSize) - 1);
//		} else {
//			list = query.orderByProcessInstanceEndTime().desc().list();
//		}
		ListInfo<ProcessInstanceInfo> infos = new ListInfo<ProcessInstanceInfo>();
		infos.setCount(count);
		infos.setPageNo(pageNo);
		infos.setPageSize(pageSize);
		if(list != null)for(HistoricProcessInstance inst : list){
			ProcessInstanceInfo info = new ProcessInstanceInfo();
			info.setProcessInstanceId(inst.getId());
			info.setTaskInfos(getCurrentTasks(inst.getId()));
			infos.getInfos().add(info);
		}
		return infos;
	}
	
	/**
	 * 审批通过
	 * @param taskId
	 * @param userId
	 * @return List<TaskInfo> 当前任务信息
	 */
	public TaskResult pass(String taskId, String userId) throws ActivitiException{
		identityService.setAuthenticatedUserId(userId);
		Task task = this.taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(userId).singleResult();
		if(task == null) {
			logger.warn("任务没有找到{}", taskId);
			return null;
		}
		String owner = getOwner(task.getProcessInstanceId());
		String cname = getCname(task.getProcessInstanceId());
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("complete", true);
		vars.put("candidates", null);
		taskService.complete(taskId, vars);
		return new TaskResult(owner, cname, task, this.getCurrentTasks(task.getProcessInstanceId()));
	}
	
	/**
	 * 退回
	 * @param taskId
	 * @param turnback_reason
	 */
	public TaskResult back(String userId, String taskId, String turnback_reason)  throws ActivitiException {
		Task task = this.taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(userId).singleResult();
		if(task == null) {
			logger.warn("任务没有找到{}", taskId);
			return null;
		}
		List<HistoricActivityInstance> lastActs = this.findLastTasks(task);
		TaskServiceImpl taskService = (TaskServiceImpl)this.taskService;
		Map<String, String> destinationTasks = new HashMap<String, String>();
		for(HistoricActivityInstance lastAct : lastActs){
			destinationTasks.put(lastAct.getActivityId(), lastAct.getAssignee());
		}
		if(destinationTasks.isEmpty()){
			logger.warn("无法回退{}", taskId);
			throw new ActivitiIllegalArgumentException("没有前置审批节点，无法回退，请选择拒绝");
		}
		Map<String, String> findFlowActivity = this.findFlowActivity(lastActs, task.getProcessDefinitionId());
		taskService.getCommandExecutor().execute(new TurnBackTaskCmd(task.getId(), destinationTasks, findFlowActivity, turnback_reason));
		return new TaskResult(getOwner(task.getProcessInstanceId()), getCname(task.getProcessInstanceId()),task, this.getCurrentTasks(task.getProcessInstanceId()));
	}
	
	/**
	 * 加签
	 * @param taskId
	 * @param userId
	 * @param assignees
	 * @return
	 */
	public TaskResult endorse(String taskId, String userId, String assignees, Request request) throws ActivitiException {
		Task task = this.taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(userId).singleResult();
		if(task == null) {
			logger.warn("任务没有找到{}", taskId);
			return null;
		}
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("complete", false);
		vars.put("endorse_user", userId);
		vars.put("candidates", assignees);
		if(request != null){
			vars.put("request", request);
		}
		taskService.complete(taskId, vars);
		List<TaskInfo> tasks = this.getCurrentTasks(task.getProcessInstanceId());
		for(TaskInfo info : tasks){
			Task t = taskService.createTaskQuery().taskId(info.getTaskId()).singleResult();
			if(t != null){
				taskService.setAssignee(t.getId(), assignees);
				runtimeService.setVariable(t.getExecutionId(), "candidates", null);
			}
		}
		return new TaskResult(getOwner(task.getProcessInstanceId()), getCname(task.getProcessInstanceId()), task, this.getCurrentTasks(task.getProcessInstanceId()));
	}
	

	
	/**
	 * 取消申请
	 * @param processKey
	 * @param oid
	 * @param userId
	 * @param reason
	 */
	public TaskResult cancel(String processKey, String oid, String userId, String reason) throws ActivitiException {
		HistoricProcessInstance pi = historyService
				.createHistoricProcessInstanceQuery()
				.processDefinitionKey(processKey).processInstanceBusinessKey(oid).unfinished().singleResult();
		if(pi == null) {
			logger.warn("流程实例没有找到 oid={}", oid);
			return null;
		}
		String owner = (String)pi.getProcessVariables().get("owner");
		String cname = (String)pi.getProcessVariables().get("cname");
		this.runtimeService.deleteProcessInstance(pi.getId(), reason);
		this.historyService.deleteHistoricProcessInstance(pi.getId());
		return new TaskResult(owner, cname, null, null);
	}
	
	/**
	 * 取消申请
	 * @param processKey
	 * @param taskId
	 * @param userId
	 * @param reason
	 */
	public TaskResult refuse(String processKey, String taskId, String userId, String reason) throws ActivitiException {
		Task task = this.taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(userId).singleResult();
		if(task == null) {
			logger.warn("任务没有找到{}", taskId);
			return null;
		}
		String owner = getOwner(task.getProcessInstanceId());
		String cname = getCname(task.getProcessInstanceId());
		this.runtimeService.deleteProcessInstance(task.getProcessInstanceId(), reason);
		return new TaskResult(owner, cname, task, null);
	}
	
	/**
	 * 增加代理审批人
	 * @param ownerId
	 * @param userIds
	 * @return
	 */
	public boolean appendCandidate(String ownerId, List<String> userIds)  throws ActivitiException {
		if(userIds == null || userIds.isEmpty()) return false;
		List<Task> tasks = this.taskService.createTaskQuery().taskCandidateUser(ownerId).list();
		if(tasks != null)for(Task task : tasks){
			for(String userId : userIds){
				taskService.addCandidateUser(task.getId(), userId);
			}
		}
		return true;
	}
	
	/**
	 * 取消代理审批人
	 * @param ownerId
	 * @param userIds
	 * @return
	 */
	public boolean removeCandidate(String ownerId, List<String> userIds)  throws ActivitiException {
		if(userIds == null || userIds.isEmpty()) return false;
		List<Task> tasks = this.taskService.createTaskQuery().taskCandidateUser(ownerId).list();
		if(tasks != null)for(Task task : tasks){
			for(String userId : userIds){
				taskService.deleteCandidateUser(task.getId(), userId);
			}
		}
		return true;
	}
	
	/**
	 * 判断流程实例是否已经存在
	 * @param processKey
	 * @param businessKey
	 * @return
	 */
	public boolean findProcessInstanceCountByBusinessKey(String processKey,
			String businessKey) {
		if(this.historyService.createHistoricProcessInstanceQuery().processDefinitionKey(processKey).processInstanceBusinessKey(businessKey).count() > 0){
			return true;
		}
		return false;
	}
	
	public TaskResult recall(String oid, String activityId, String assignee) throws Exception {
		List<Task> tasks = this.taskService.createTaskQuery().processInstanceBusinessKey(oid).list();
		if(tasks.size() == 0){
			throw new Exception("流程已经结束，无法取回");
		}
		if(tasks.size() > 1){
			throw new Exception("此申请不支持取回，无法取回");
		}
		Task task = tasks.get(0);
		ActivityImpl currActivity = findActivitiImpl(task.getId(), null);
		if(currActivity.getId().equals(activityId) && task.getAssignee().equals(assignee)){
			throw new Exception("申请已经在您的待办工作中，无法取回");
		}
		List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);
		TransitionImpl newTransition = currActivity.createOutgoingTransition();
		ActivityImpl pointActivity = findActivitiImpl(task.getId(), activityId);
		newTransition.setDestination(pointActivity);
		taskService.complete(task.getId());
		pointActivity.getIncomingTransitions().remove(newTransition);
		restoreTransition(currActivity, oriPvmTransitionList);
		return new TaskResult(getOwner(task.getProcessInstanceId()), getCname(task.getProcessInstanceId()), task, this.getCurrentTasks(task.getProcessInstanceId()));
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
	
	private String getOwner(String processId){
		return (String)this.runtimeService.getVariable(processId, "owner");
	}
	
	private String getCname(String processId){
		return (String)this.runtimeService.getVariable(processId, "cname");
	}
	
	private List<TaskInfo> getCurrentTasks(String processInstanceId) {
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		List<TaskInfo> taskInfos = new ArrayList<TaskInfo>();
		if (tasks.size() > 0)
			for (Task _task : tasks) {
				String candidate = "";
				List<IdentityLink> ids = taskService.getIdentityLinksForTask(_task.getId());
				for (IdentityLink id : ids) {
					if (IdentityLinkType.CANDIDATE.equals(id.getType())) {
						if(id.getUserId() != null){
							candidate += id.getUserId() + ",";
						}
					}
				}
				if (candidate.length() > 0) {
					candidate = candidate.substring(0, candidate.length() - 1);
				}
				TaskInfo tinfo = new TaskInfo();
				tinfo.setTaskKey(_task.getTaskDefinitionKey());
				tinfo.setCandidate(candidate);
				tinfo.setTaskId(_task.getId());
				tinfo.setTaskName(_task.getName());
				tinfo.setTaskKey(_task.getTaskDefinitionKey());
				Integer nrOfInstances = this.runtimeService.getVariable(_task.getExecutionId(), "nrOfInstances", Integer.class);
				if(nrOfInstances == null || nrOfInstances <= 0){
					tinfo.setEndorse(false);
				}else{
					tinfo.setEndorse(true);
				}
				taskInfos.add(tinfo);
			}
		else {
			TaskInfo tinfo = new TaskInfo();
			tinfo.setCandidate(null);
			tinfo.setTaskId(null);
			tinfo.setTaskName(null);
			taskInfos.add(tinfo);
		}
		return taskInfos;
	}
	
	private List<HistoricActivityInstance> findLastTasks(Task task) {
		List<HistoricActivityInstance> res = new ArrayList<HistoricActivityInstance>();
		Set<String> acts = new TreeSet<String>();
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
			
			List<HistoricActivityInstance> list = this.historyService
					.createHistoricActivityInstanceQuery()
					.processInstanceId(task.getProcessInstanceId())
					.finished()
					.activityId(actid)
					.orderByHistoricActivityInstanceEndTime()
					.desc()
					.list();
			if (list.size() == 0) continue;
			res.add(list.get(0));
		}
		return res;
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
	
	private void findIncomingTasks(PvmActivity act, Set<String> taskKeys) {
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
				System.out.println(source.getId());
			}else if("subProcess".equals(source.getProperty("type"))){
				taskKeys.add(source.getId());
				System.out.println(source.getId());
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
	
	private TaskEntity findTaskById(String taskId) {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery()
				.taskId(taskId).singleResult();
		return task;
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
	
}
