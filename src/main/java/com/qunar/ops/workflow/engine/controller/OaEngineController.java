package com.qunar.ops.workflow.engine.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.NativeHistoricProcessInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.codehaus.janino.CodeContext.Offset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.workflow.engine.result.ApproveRequest;
import com.qunar.ops.workflow.engine.result.BaseResult;
import com.qunar.ops.workflow.engine.result.DataResult;
import com.qunar.ops.workflow.engine.result.ProcessInstanceInfo;
import com.qunar.ops.workflow.engine.result.Request;
import com.qunar.ops.workflow.engine.result.TaskInfo;
import com.qunar.ops.workflow.engine.service.EngineConst;
import com.qunar.ops.workflow.engine.service.EngineService;
import com.qunar.ops.workflow.engine.service.PostJdbcCon;
import com.qunar.ops.workflow.engine.service.ProcessDefine;
import com.qunar.ops.workflow.engine.service.ProxyService;
import com.qunar.ops.workflow.engine.stragety.HistoryHandler;

@Controller
public class OaEngineController {
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
	protected EngineService service;
	@Autowired
	protected ProcessEngineFactoryBean processEngine;
	@Autowired
	protected ProxyService proxyService;
	

	@RequestMapping(value = "/approve/index.html")
	public ModelAndView index(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		ModelAndView mav = new ModelAndView("/approve/index");
		return mav;
	}

	@RequestMapping(value = "/approve/history.html")
	public ModelAndView history(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		ModelAndView mav = new ModelAndView("/approve/history");
		return mav;
	}
	 
    /**
     * 部署
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/add_process_list.html")
    public ModelAndView addProcessList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/add_process_list");
//        String userId = (String) request.getSession().getAttribute("USER_ID");
        List<Object[]> infos = new ArrayList<Object[]>();
        List<ProcessDefine> list = new ArrayList<ProcessDefine>();
        list = PostJdbcCon.queryProcessList();
        for (ProcessDefine processDefine : list) {
        	infos.add(new Object[]{processDefine.getKey(), processDefine.getType(), processDefine.getUrl(), processDefine.getName()});
		}
        mav.addObject("page", infos);
        mav.addObject("totalCount", list.size());
        return mav;
    } 
    
    /**
     * 删除流程定义
     * @param deploymentId
     * @return
     */
    @RequestMapping(value = "/approve/delete")
    public String delete(@RequestParam("key") String key) {
    	PostJdbcCon.deleteByKey(key);
        return "redirect:/admin/add_process_list.html";
    }
    
	@RequestMapping(value = "/approve/processlist")
	@ResponseBody
	public BaseResult processlist() {
		System.out.println("进入/approve/processlist");
		List<ProcessDefine> infos = new ArrayList<ProcessDefine>();
		infos = PostJdbcCon.queryProcessList();
		return BaseResult.getSuccessResult(infos);
	}

	/**
	 * 需要处理的流程实例列表
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approve/query/todo")
	@ResponseBody
	public BaseResult todo(@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		Map<String, Object> vars = request.getVars();
		Map<String, Object[]> infos = new HashMap<String, Object[]>();
		String sstart = (String) vars.get("start");
		String slength = (String) vars.get("length");
		String approveUser = (String) vars.get("approveUser");
		String keywords = (String) vars.get("keywords");
		String processKeys = (String) vars.get("processKeys");
//		String status1 = (String) vars.get("status1");
//		String status2 = (String) vars.get("status2");
		int start = sstart == null ? 0 
				: Integer.valueOf(sstart);
		int length = slength == null ? 0 : Integer.valueOf(slength);
		Map<String, Object> params = new TreeMap<String, Object>();
		params.put("userId", userId);
		List<Task> tasks = new ArrayList<Task>();
		long count = 0;
		String sql = " from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ "
				+ "inner join ACT_RU_VARIABLE A0 on RES.PROC_INST_ID_ = A0.PROC_INST_ID_ "
				+ "inner join ACT_RU_VARIABLE A1 on RES.PROC_INST_ID_ = A1.PROC_INST_ID_ "
				+ "inner join ACT_RE_PROCDEF D on RES.PROC_DEF_ID_ = D.ID_ "
				+ "WHERE RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' and ( I.USER_ID_ = #{userId} ) and A0.TASK_ID_ is null";
		String keys = "";
		if(processKeys != null)for (String key : processKeys.split(",")) {
			keys += "'" + key + "',";
		}
		if(keys.length() > 0){
			keys = keys.substring(0, keys.length() - 1);
			sql += " and D.KEY_ in (" + keys + ")";
		}
		if (approveUser != null) {
			sql += " and A0.NAME_= 'startedUser' and A0.TYPE_ = 'string' and A0.TEXT_ = #{approveUser}";
			params.put("approveUser", approveUser);
		}
		if (keywords != null) {
			sql += " and A1.TASK_ID_ is null and A1.NAME_= 'keywords' and A1.TYPE_ = 'string' and A1.TEXT_ LIKE #{keywords}";
			params.put("keywords", "%" + keywords + "%");
		}
		
//		if (status1 == null && status2 != null) {
////			选取处理中的记录，approve
//			sql += " and A0.NAME_ = 'approve'";
//		}else if (status1 != null && status2 == null) {
////			变量表中还没有approve
//			sql += " and A0.proc_inst_id_ not in (select proc_inst_id_ from ACT_RU_VARIABLE where NAME_ = 'approve')";
//		}
		String queryRecord = "select distinct RES.*" + sql
				+ " order by RES.ID_ desc";
		System.out.println(queryRecord);
		String queryCount = "select count(distinct RES.ID_)" + sql;
		NativeTaskQuery query = this.taskService.createNativeTaskQuery();
		query.sql(queryRecord);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.parameter(entry.getKey(), entry.getValue());
		}
		tasks = query.sql(queryRecord).listPage(start, length);
		query = this.taskService.createNativeTaskQuery();
		query.sql(queryCount);
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			query.parameter(entry.getKey(), entry.getValue());
		}
		count = query.count();
		DataResult result = new DataResult();
		result.setCount(count);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (Task task : tasks) {
			Object[] arr = infos.get(task.getProcessInstanceId());
			if (arr == null) {
				ProcessInstance _pi = runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(task.getProcessInstanceId())
						.singleResult();
				ProcessDefinition _pd = this.repositoryService
						.getProcessDefinition(_pi.getProcessDefinitionId());
				Map<String, Object> _var = this.runtimeService.getVariables(_pi
						.getId());
				arr = new Object[] { _pi, _pd, _var };
				infos.put(_pi.getId(), arr);
			}
			ProcessInstance pi = (ProcessInstance) arr[0];
			ProcessDefinition pd = (ProcessDefinition) arr[1];
			Map<String, Object> var = (Map<String, Object>) arr[2];
			String summary = (String) var.get("summary");
			String startedUser = (String) var.get("startedUser");
			if (startedUser == null || "".equals(startedUser)) {
				startedUser = (String) var.get("initiator");
			}
			String statusName = "";
//			int status = this.proxyService.getStatusByTaskId(task.getId());
//			if(status == -1){
//				statusName = "处理失败";
//			}if(status == 0){
//				statusName = "处理中";
//			}
			summary = summary == null ? "" : summary + " ";
			List<HistoricActivityInstance> res = service.findLastTasks(task);
			if (res.size() == 0) { //如果没有上一个节点，说明是第一个审批人。
				summary += "第一审批人，请审批";
			}else {
				HistoricActivityInstance historicActivityInstance = res.get(res.size()-1);
				summary += historicActivityInstance.getAssignee()+" 同意 "+ sdf.format(historicActivityInstance.getEndTime());
			}
			
			result.append(new String[] { 
					pi.getId(), 
					task.getId(),
					pd.getName(), 
					startedUser, 
					summary,
					sdf.format(task.getCreateTime()), 
					pd.getKey(),
					pi.getBusinessKey(),
					statusName});
		}
		return BaseResult.getSuccessResult(result);
	}

	/**
	 * 历史流程实例列表
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approve/query/history")
	@ResponseBody
	public BaseResult history(@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		Map<String, Object> vars = request.getVars();
		HistoryHandler historyHandler = new HistoryHandler();
		System.out.println("historyController");
		return historyHandler.buildSqlResult(taskService, historyService, repositoryService, service, vars, userId);
	}

	/**
	 * 批量完成任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approve/task/complet")
	@ResponseBody
	public BaseResult batchComplet(@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		String taskIds = (String) request.getVars().get("taskIds");
		taskIds = taskIds.replaceAll(" ", "");
		try {
			List<Task> tasks = new ArrayList<Task>();
			for (String taskId : taskIds.split(",")) {
				if ("".equals(taskId))
					continue;
				Task task = taskService.createTaskQuery().taskId(taskId)
						.singleResult();
				if (task == null) {
					throw new ActivitiObjectNotFoundException(this.getClass());
				}
				String assignee = task.getAssignee();
				if (assignee != null && assignee.length() > 0
						&& !assignee.equals(userId)) {
					logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED
							+ "[" + taskId + "]，assignee=" + assignee
							+ ", userId=" + userId + "!");
					return BaseResult.getErrorResult(
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED,
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED + "["
									+ taskId + "]，assignee=" + assignee
									+ ", userId=" + userId + "!");
				} else if (assignee == null) {
					long candidateUserCount = taskService.createTaskQuery()
							.taskId(taskId).taskCandidateUser(userId).count();
					if (candidateUserCount == 0) {
						logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
								+ "[" + taskId + "]，assignee=" + assignee + "!");
						return BaseResult
								.getErrorResult(
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE,
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
												+ "["
												+ taskId
												+ "]，assignee="
												+ assignee + "!");
					}
				}
				tasks.add(task);
			}
			List<ProcessInstanceInfo> res = this.service.batchComplet(tasks, userId, request.getVars(), request.getForm());
			return BaseResult.getSuccessResult(res);
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":"
							+ e.getMessage());
		} catch (Exception e) {
			if(e.getMessage().equals("Send_To_MQ_Error")){
				logger.error(EngineConst.WF_ERROR_SEND_TO_MQ_MSG, e);
				return BaseResult.getErrorResult(EngineConst.WF_ERROR_SEND_TO_MQ,
						EngineConst.WF_ERROR_SEND_TO_MQ_MSG + e.getMessage());
			}
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}
	
	/**
	 * 批量完成任务-异步
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approve/task/async_complet")
	@ResponseBody
	public BaseResult asyncBatchComplet(@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		String taskIds = (String) request.getVars().get("taskIds");
		taskIds = taskIds.replaceAll(" ", "");
		try {
			List<Task> tasks = new ArrayList<Task>();
			for (String taskId : taskIds.split(",")) {
				if ("".equals(taskId))
					continue;
				Task task = taskService.createTaskQuery().taskId(taskId)
						.singleResult();
				if (task == null) {
					throw new ActivitiObjectNotFoundException(this.getClass());
				}
				String assignee = task.getAssignee();
				if (assignee != null && assignee.length() > 0
						&& !assignee.equals(userId)) {
					logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED
							+ "[" + taskId + "]，assignee=" + assignee
							+ ", userId=" + userId + "!");
					return BaseResult.getErrorResult(
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED,
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED + "["
									+ taskId + "]，assignee=" + assignee
									+ ", userId=" + userId + "!");
				} else if (assignee == null) {
					long candidateUserCount = taskService.createTaskQuery()
							.taskId(taskId).taskCandidateUser(userId).count();
					if (candidateUserCount == 0) {
						logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
								+ "[" + taskId + "]，assignee=" + assignee + "!");
						return BaseResult
								.getErrorResult(
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE,
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
												+ "["
												+ taskId
												+ "]，assignee="
												+ assignee + "!");
					}
				}
				if(!this.proxyService.hasKey(task.getId())){
					tasks.add(task);
				}
			}
			this.proxyService.addTasks(tasks, userId, request.getVars(), request.getForm());
			//List<ProcessInstanceInfo> res = this.service.batchComplet(tasks, userId, request.getVars(), request.getForm());
			return BaseResult.getSuccessResult(new DataResult());
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":"
							+ e.getMessage());
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}

	/**
	 * 批量驳回任务
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approve/task/reject")
	@ResponseBody
	public BaseResult batchReject(@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		String taskIds = (String) request.getVars().get("taskIds");
		String reason = (String) request.getVars().get("reason");
		if (reason == null)
			reason = "";
		taskIds = taskIds.replaceAll(" ", "");
		try {
			Set<String> pids = new HashSet<String>();
			for (String taskId : taskIds.split(",")) {
				if ("".equals(taskId))
					continue;
				Task task = taskService.createTaskQuery().taskId(taskId)
						.singleResult();
				if (task == null) {
					throw new ActivitiObjectNotFoundException(this.getClass());
				}
				String assignee = task.getAssignee();
				if (assignee != null && assignee.length() > 0
						&& !assignee.equals(userId)) {
					logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED
							+ "[" + taskId + "]，assignee=" + assignee
							+ ", userId=" + userId + "!");
					return BaseResult.getErrorResult(
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED,
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED + "["
									+ taskId + "]，assignee=" + assignee
									+ ", userId=" + userId + "!");
				} else if (assignee == null) {
					long candidateUserCount = taskService.createTaskQuery()
							.taskId(taskId).taskCandidateUser(userId).count();
					if (candidateUserCount == 0) {
						logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
								+ "[" + taskId + "]，assignee=" + assignee + "!");
						return BaseResult
								.getErrorResult(
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE,
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
												+ "["
												+ taskId
												+ "]，assignee="
												+ assignee + "!");
					}
				}
				pids.add(task.getProcessInstanceId());
			}
			List<ProcessInstanceInfo> res = this.service.batchRemove(
					pids.toArray(new String[] {}), userId, false, reason);
			return BaseResult.getSuccessResult(res);
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":"
							+ e.getMessage());
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}
	
	/**
	 * 批量驳回任务 - 异步
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approve/task/async_reject")
	@ResponseBody
	public BaseResult asyncBatchReject(@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		String taskIds = (String) request.getVars().get("taskIds");
		String reason = (String) request.getVars().get("reason");
		if (reason == null)
			reason = "";
		taskIds = taskIds.replaceAll(" ", "");
		try {
			List<Task> tasks = new ArrayList<Task>();
			for (String taskId : taskIds.split(",")) {
				if ("".equals(taskId))
					continue;
				Task task = taskService.createTaskQuery().taskId(taskId)
						.singleResult();
				if (task == null) {
					throw new ActivitiObjectNotFoundException(this.getClass());
				}
				String assignee = task.getAssignee();
				if (assignee != null && assignee.length() > 0
						&& !assignee.equals(userId)) {
					logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED
							+ "[" + taskId + "]，assignee=" + assignee
							+ ", userId=" + userId + "!");
					return BaseResult.getErrorResult(
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED,
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED + "["
									+ taskId + "]，assignee=" + assignee
									+ ", userId=" + userId + "!");
				} else if (assignee == null) {
					long candidateUserCount = taskService.createTaskQuery()
							.taskId(taskId).taskCandidateUser(userId).count();
					if (candidateUserCount == 0) {
						logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
								+ "[" + taskId + "]，assignee=" + assignee + "!");
						return BaseResult
								.getErrorResult(
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE,
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
												+ "["
												+ taskId
												+ "]，assignee="
												+ assignee + "!");
					}
				}
				if(!this.proxyService.hasKey(task.getId())){
					tasks.add(task);
				}
			}
			this.proxyService.addTasks(tasks, userId, request.getVars(), request.getForm());
			//List<ProcessInstanceInfo> res = this.service.batchRemove(pids.toArray(new String[] {}), userId, false, reason);
			return BaseResult.getSuccessResult(new DataResult());
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":"
							+ e.getMessage());
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}
	
	/**
	 * 添加process_list
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/approve/add/process")
	@ResponseBody
	public BaseResult addProcess(@RequestBody ApproveRequest request, HttpServletRequest httprequest,HttpServletResponse response) {
		System.out.println("进入添加process_list");
		String userId = (String) httprequest.getSession().getAttribute("USER_ID");
		String key = (String) request.getKey();
		String url = (String) request.getUrl();
		String type = (String) request.getType();
		String name = (String) request.getName();
		System.out.println(userId);
		String msg = PostJdbcCon.insertProcessList(key, type, url, name,userId);
		BaseResult baseResult = new BaseResult();
		baseResult.setData(msg);
		return baseResult;
	}
	
	/**
     * 获取流程定义列表，展示在前端
     * @param request
     * @return
     */
    @RequestMapping(value = "/approve/get_process")
    @ResponseBody
    public List<String> processList(HttpServletRequest request) {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc().list();
        List<String> infos = new ArrayList<String>();
        for(ProcessDefinition def : list){
        	String key = def.getKey();
        	if(!infos.contains(key)){
        		infos.add(key);
        	}
        }
        return infos;
    }
}
