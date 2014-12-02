package com.qunar.ops.oaengine.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.qunar.ops.oaengine.command.TurnBackTaskCmd;
import com.qunar.ops.oaengine.result.ListInfo;
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
	
	/**
	 * 启动申请流程
	 * @param processKey
	 * @param userId
	 * @param request
	 * @return Object[]; 0-流程ID；1-当前任务信息
	 */
	public Object[] startWorkflow(String processKey, String userId, Request request) throws ActivitiException{
		this.identityService.setAuthenticatedUserId(userId);
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("request", request);
		vars.put("owner", userId);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, request.getOid(), vars);
		if(processInstance == null){
			logger.error("start process fail! {key={}, bkey={}}", new Object[]{processKey, request.getOid()});
			return null;
		}
		logger.debug(
				"start process of {key={}, bkey={}, pid={}}",
				new Object[] { processKey, request.getOid(), processInstance.getId()});
		TaskResult tr = new TaskResult(userId, null, this.getCurrentTasks(processInstance.getId()));
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
		
		TaskQuery query = this.taskService.createTaskQuery().processDefinitionKey(processKey).taskCandidateUser(userId);
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
			info.setTaskName(task.getName());
			
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
	 * 审批通过
	 * @param taskIds
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
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("complete", true);
		taskService.complete(taskId, vars);
		return new TaskResult(owner, task, this.getCurrentTasks(task.getProcessInstanceId()));
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
		return new TaskResult(getOwner(task.getProcessInstanceId()), task, this.getCurrentTasks(task.getProcessInstanceId()));
	}
	
	/**
	 * 加签
	 * @param taskId
	 * @param userId
	 * @param assignees
	 * @return
	 */
	public TaskResult endorse(String taskId, String userId, String assignees) throws ActivitiException {
		Task task = this.taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(userId).singleResult();
		if(task == null) {
			logger.warn("任务没有找到{}", taskId);
			return null;
		}
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("complete", false);
		vars.put("candidates", assignees);
		taskService.complete(taskId, vars);
		return new TaskResult(getOwner(task.getProcessInstanceId()), task, this.getCurrentTasks(task.getProcessInstanceId()));
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
				.processDefinitionKey(processKey).processInstanceBusinessKey(oid).startedBy(userId).unfinished().singleResult();
		if(pi == null) {
			logger.warn("流程实例没有找到 oid={}", oid);
			return null;
		}
		String owner = (String)pi.getProcessVariables().get("owner");
		this.runtimeService.deleteProcessInstance(pi.getId(), reason);
		return new TaskResult(owner, null, null);
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
	
	private String getOwner(String processId){
		return (String)this.runtimeService.getVariable(processId, "owner");
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
