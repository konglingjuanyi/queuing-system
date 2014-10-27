package com.qunar.ops.oaengine.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qunar.ops.oaengine.result.ActivityInfo;
import com.qunar.ops.oaengine.result.BaseResult;
import com.qunar.ops.oaengine.result.ProcessInstanceInfo;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.SendToMQInfo;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.service.EngineConst;
import com.qunar.ops.oaengine.service.EngineService;
import com.qunar.ops.oaengine.service.SendToMqService;
import com.qunar.ops.oaengine.util.QUtils;

@Controller
public class EngineController {

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
	private SendToMqService sendToMqService;

	/**
	 * 加签
	 * 
	 * @param taskId
	 * @param activityId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "{processKey}/task/{taskId}/endorse")
	@ResponseBody
	public BaseResult endorse(@PathVariable("taskId") String taskId,
			@RequestBody Request request) throws Exception {
		String userId = request.getUserId();
		Map<String, Object> vars = request.getVars();
		String assignee = (String) vars.get("assignee");
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		if (assignee == null || assignee.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_NOT_ASSIGNEE_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_NOT_ASSIGNEE,
					EngineConst.WF_ERROR_NOT_ASSIGNEE_MSG);
		}
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		try {
			if (task == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			if (!userId.equals(task.getAssignee())) {
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
			ProcessInstanceInfo info = this.service.turnTransition(task, null,
					request.getVars(), request.getForm());
			return BaseResult.getSuccessResult(info);
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "[" + taskId
					+ "]:", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "[" + taskId
							+ "]:" + e.getMessage());
		} catch (Exception e) {
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}

	/**
	 * 退回
	 * 
	 * @param taskId
	 * @param activityId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "{processKey}/task/{taskId}/turnback")
	@ResponseBody
	public BaseResult turnback(@PathVariable("taskId") String taskId,
			@RequestBody Request request) throws Exception {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		try {
			if (task == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			if (!userId.equals(task.getAssignee())) {
				long candidateUserCount = taskService.createTaskQuery()
						.taskId(taskId).taskCandidateUser(userId).count();
				if (candidateUserCount == 0) {
					logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
							+ "[" + taskId + "]!");
					return BaseResult
							.getErrorResult(
									EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE,
									EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
											+ "[" + taskId + "]!");
				}
			}
			ProcessInstanceInfo info = this.service.turnback(task,
					request.getVars(), request.getForm());
			if (info == null) {
				return BaseResult.getErrorResult(EngineConst.WF_ERROR_NOT_BACK,
						EngineConst.WF_ERROR_NOT_BACK_MSG);
			}
			return BaseResult.getSuccessResult(info);
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "[" + taskId
					+ "]:", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "[" + taskId
							+ "]:" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}

	/**
	 * 删除流程实例
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "{processKey}/remove")
	@ResponseBody
	public BaseResult remove(@PathVariable("processKey") String processKey,
			@RequestBody Request request) {
		String oid = request.getOid();
		String userId = request.getUserId();
		if (oid == null || oid.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_OID_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_OID_IS_NULL,
					EngineConst.WF_ERROR_OID_IS_NULL_MSG);
		}
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}

		ProcessInstance ins = this.service.findProcessInstanceByBusinessKey(
				processKey, oid);
		try {
			if (ins == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			Map<String, Object> vars = request.getVars();
			String isDel = (String) vars.get("is_delete");
			logger.warn(new Date() + "remove(主动删除)：" + "[" + oid + ","
					+ ins.getId() + "]:");
			this.runtimeService.deleteProcessInstance(ins.getId(), "主动delete");
			if (isDel != null && "true".equalsIgnoreCase(isDel)) {
				historyService.deleteHistoricProcessInstance(ins.getId());
			}
			ProcessInstanceInfo info = new ProcessInstanceInfo();
			info.setOid(oid);
			info.setProcessInstanceId(ins.getId());
			return BaseResult.getSuccessResult(info);
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_PROCESSINSTANCE_NOT_EXIST + "["
					+ oid + "]:", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_PROCESSINSTANCE_NOT_EXIST + "[" + oid
							+ "]:" + e.getMessage());
		} catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				logger.warn(EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST_MSG
						+ "[" + processKey + "]!", e);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST,
						EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST_MSG + "["
								+ processKey + "]!" + e.getMessage());
			} else {
				logger.error(EngineConst.WF_ERROR_REMOVE_FAIL + "["
						+ processKey + "]：", e);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_REMOVE_FAIL,
						EngineConst.WF_ERROR_REMOVE_FAIL_MSG + "[" + processKey
								+ "]：" + e.getMessage());
			}
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}

	/**
	 * 批量删除流程实例
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "all/remove")
	@ResponseBody
	public BaseResult batchRemove(@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		try {
			Map<String, Object> vars = request.getVars();
			String isDel = (String) vars.get("is_delete");
			String delete_reason = (String) vars.get("delete_reason");
			String processInstanceIds = (String) vars.get("processInstanceIds");
			if (processInstanceIds != null) {
				List<ProcessInstanceInfo> res = this.service.batchRemove(
						processInstanceIds.split(","), userId,
						"true".equals(isDel) ? true : false, delete_reason);
				return BaseResult.getSuccessResult(res);
			}
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_PROCESSINSTANCE_NOT_EXIST,
					EngineConst.WF_ERROR_PROCESSINSTANCE_NOT_EXIST_MSG);
		} catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				logger.warn(EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST_MSG
						+ ":", e);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST,
						EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST_MSG + ":"
								+ e.getMessage());
			} else {
				logger.error(EngineConst.WF_ERROR_START_FAIL_MSG + "：", e);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_REMOVE_FAIL,
						EngineConst.WF_ERROR_REMOVE_FAIL_MSG + "："
								+ e.getMessage());
			}
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}

	/**
	 * 启动流程实例
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "{processKey}/start")
	@ResponseBody
	public BaseResult processList(
			@PathVariable("processKey") String processKey,
			@RequestBody Request request) {
		String oid = request.getOid();
		String userId = request.getUserId();
		if (oid == null || oid.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_OID_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_OID_IS_NULL,
					EngineConst.WF_ERROR_OID_IS_NULL_MSG);
		}
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		if (this.service.findProcessInstanceCountByBusinessKey(processKey, oid) > 0) {
			logger.warn(EngineConst.WF_ERROR_PROCESSINSTANCE_IS_EXIST_MSG);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_PROCESSINSTANCE_IS_EXIST,
					EngineConst.WF_ERROR_PROCESSINSTANCE_IS_EXIST_MSG);
		}
		ProcessInstanceInfo info;
		try {
			info = this.service.startWorkflow(processKey, oid, userId,
					request.getVars(), request.getForm());
			return BaseResult.getSuccessResult(info);
		} catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				logger.warn(EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST_MSG
						+ "[" + processKey + "]!", e);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST,
						EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST_MSG + "["
								+ processKey + "]!" + e.getMessage());
			} else {
				logger.error(EngineConst.WF_ERROR_START_FAIL_MSG + "["
						+ processKey + "]：", e);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_START_FAIL,
						EngineConst.WF_ERROR_START_FAIL_MSG + "[" + processKey
								+ "]：" + e.getMessage());
			}
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}

	/**
	 * 批量启动流程实例
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "{processKey}/batchstart")
	@ResponseBody
	public BaseResult batchstart(@PathVariable("processKey") String processKey,
			@RequestBody Request request) {
		String oid = request.getOid();
		String userId = request.getUserId();
		if (oid == null || oid.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_OID_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_OID_IS_NULL,
					EngineConst.WF_ERROR_OID_IS_NULL_MSG);
		}
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		List<String> oids = Arrays.asList(oid.split(","));
		for (String _oid : oids) {
			if (this.service.findProcessInstanceCountByBusinessKey(processKey,
					_oid) > 0) {
				logger.warn(EngineConst.WF_ERROR_PROCESSINSTANCE_IS_EXIST_MSG);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_PROCESSINSTANCE_IS_EXIST,
						EngineConst.WF_ERROR_PROCESSINSTANCE_IS_EXIST_MSG);
			}
		}
		try {
			List<ProcessInstanceInfo> infos = this.service.batchStartWorkflow(
					processKey, oids, userId, request.getVars(),
					request.getForm());
			return BaseResult.getSuccessResult(infos);
		} catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				logger.warn(EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST_MSG
						+ "[" + processKey + "]!", e);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST,
						EngineConst.WF_ERROR_PROCESSDEFINE_NOT_EXIST_MSG + "["
								+ processKey + "]!" + e.getMessage());
			} else {
				logger.error(EngineConst.WF_ERROR_START_FAIL_MSG + "["
						+ processKey + "]：", e);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_START_FAIL,
						EngineConst.WF_ERROR_START_FAIL_MSG + "[" + processKey
								+ "]：" + e.getMessage());
			}
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}

	/**
	 * 认领任务
	 * 
	 * @param processKey
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{processKey}/task/{taskId}/claim")
	@ResponseBody
	public BaseResult claim(@PathVariable("processKey") String processKey,
			@PathVariable("taskId") String taskId, @RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		try {
			if (task == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			String assignee = task.getAssignee();
			if (assignee == null) {
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
			return BaseResult
					.getSuccessResult(this.service.claim(task, userId));
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "[" + taskId
					+ "]:", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "[" + taskId
							+ "]:" + e.getMessage());
		} catch (ActivitiTaskAlreadyClaimedException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_ALREADY_CLAIMED + "["
					+ taskId + "]:", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_ALREADY_CLAIMED,
					EngineConst.WF_ERROR_TASK_ALREADY_CLAIMED + "[" + taskId
							+ "]:" + e.getMessage());
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}

	/**
	 * 取消认领任务
	 * 
	 * @param processKey
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{processKey}/task/{taskId}/unclaim")
	@ResponseBody
	public BaseResult unclaim(@PathVariable("processKey") String processKey,
			@PathVariable("taskId") String taskId, @RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		try {
			if (task == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			ProcessInstance instance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId())
					.singleResult();
			if (instance == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			if (!userId.equalsIgnoreCase(task.getAssignee())) {
				logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED_MSG
						+ "[" + taskId + "]!");
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED,
						EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED_MSG + "["
								+ taskId + "]!");
			}
			return BaseResult.getSuccessResult(this.service.unclaim(task,
					userId));
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "[" + taskId
					+ "]:", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "[" + taskId
							+ "]:" + e.getMessage());
		} catch (ActivitiTaskAlreadyClaimedException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_ALREADY_CLAIMED + "["
					+ taskId + "]:", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_ALREADY_CLAIMED,
					EngineConst.WF_ERROR_TASK_ALREADY_CLAIMED + "[" + taskId
							+ "]:" + e.getMessage());
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
		}
	}

	/**
	 * 完成任务
	 * 
	 * @param processKey
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{processKey}/task/{taskId}/complet")
	@ResponseBody
	public BaseResult complet(@PathVariable("processKey") String processKey,
			@PathVariable("taskId") String taskId, @RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		try {
			if (task == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			ProcessInstance instance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId())
					.singleResult();
			if (instance == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			String assignee = task.getAssignee();
			if (assignee != null && assignee.length() > 0
					&& !assignee.equals(userId)) {
				logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED + "["
						+ taskId + "]，assignee=" + assignee + ", userId="
						+ userId + "!");
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
				taskService.claim(taskId, userId);
			}
			return BaseResult.getSuccessResult(this.service.complet(task,
					userId, request.getVars(), request.getForm()));
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "["
					+ processKey + "]" + "[" + taskId + "]:", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + "[" + taskId
							+ "]:" + e.getMessage());
		} catch (Exception e) {
			if (e.getMessage().contains(
					"was updated by another transaction concurrently")) {
				logger.warn(
						EngineConst.WF_ERROR_PROCESSINSTANCE_WAS_UPDATED_MSG
								+ "[" + processKey + "]" + "[" + taskId + "]:",
						e);
				return BaseResult.getErrorResult(
						EngineConst.WF_ERROR_PROCESSINSTANCE_WAS_UPDATED,
						EngineConst.WF_ERROR_PROCESSINSTANCE_WAS_UPDATED_MSG
								+ "[" + taskId + "]:" + e.getMessage());
			} else {
				logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
				return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
						EngineConst.WF_ERROR_SYS_MSG + e.getMessage());
			}
		}
	}

	/**
	 * 批量完成任务
	 * 
	 * @param processKey
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/all/task/complet")
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
		List<BaseResult> datas = new ArrayList<BaseResult>();
		List<Task> tasks = new ArrayList<Task>();
		for (String taskId : taskIds.split(",")) {
			try {
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
					datas.add(BaseResult.getErrorResult(
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED,
							EngineConst.WF_ERROR_TASK_ASSIGNER_UNMATCHED + "["
									+ taskId + "]，assignee=" + assignee
									+ ", userId=" + userId + "!"));
					continue;
				} else if (assignee == null) {
					long candidateUserCount = taskService.createTaskQuery()
							.taskId(taskId).taskCandidateUser(userId).count();
					if (candidateUserCount == 0) {
						logger.warn(EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
								+ "[" + taskId + "]，assignee=" + assignee + "!");
						datas.add(BaseResult
								.getErrorResult(
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE,
										EngineConst.WF_ERROR_TASK_ASSIGNER_NOTINCANDIDATE_MSG
												+ "["
												+ taskId
												+ "]，assignee="
												+ assignee + "!"));
						continue;
					}
				}
				tasks.add(task);
			} catch (ActivitiObjectNotFoundException e) {
				logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":", e);
				datas.add(BaseResult.getErrorResult(
						EngineConst.WF_ERROR_TASK_NOT_FOUNT,
						EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":"
								+ e.getMessage()));
				continue;
			} catch (Exception e) {
				logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
				datas.add(BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
						EngineConst.WF_ERROR_SYS_MSG + e.getMessage()));
				continue;
			}
		}
		//拼接发送到MQ的消息体
		SendToMQInfo mqInfo = new SendToMQInfo();
		Map<String, List<Map<String, Object>>> uTasks = new HashMap<String, List<Map<String, Object>>>();
		String startedUser = null;
		String processKey = null;
		List<Map<String, Object>> myTasks;
		for (Task task : tasks) {
			//拼接发送到MQ的消息体
			ProcessInstance ins = runtimeService.createProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult();
			Map<String, Object> insVars = runtimeService.getVariables(ins.getId());
			processKey = ins.getProcessDefinitionId().split(":")[0];
			startedUser = (String)insVars.get("startedUser");
			Map<String, Object> myTask = new HashMap<String, Object>();
			myTask.put("oid", ins.getBusinessKey());
			
			if (task.getAssignee() == null) {
				this.taskService.claim(task.getId(), userId);
			}
			try {
				ProcessInstanceInfo piInfo = this.service.bComplet(
						task, userId, request.getVars(), request.getForm());
				datas.add(BaseResult.getSuccessResult(piInfo));
				//拼接发送到MQ的消息体
				if(piInfo.getIsFinished().equals("true")){
					myTask.put("action", "complete");
				}else{
					if(Integer.valueOf((String)request.getVars().get("approve")) <=0 ){
						myTask.put("action", "reject");
					}else{
						myTask.put("action", "pass");
					}
				}
				if(uTasks.containsKey(startedUser)){
					uTasks.get(startedUser).add(myTask);
				}else{
					if(startedUser != null){
						myTasks = new ArrayList<Map<String, Object>>();
						myTasks.add(myTask);
						uTasks.put(startedUser, myTasks);
					}
				}
			} catch (Exception e) {
				logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
				datas.add(BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
						EngineConst.WF_ERROR_SYS_MSG + e.getMessage()));
			}
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
			logger.error(EngineConst.WF_ERROR_SEND_TO_MQ_MSG, e);
			datas.add(BaseResult.getErrorResult(EngineConst.WF_ERROR_SEND_TO_MQ,
					EngineConst.WF_ERROR_SEND_TO_MQ_MSG + e.getMessage()));
		}
		return BaseResult.getSuccessResult(datas);
	}

	/**
	 * 需要处理的流程实例列表
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{processKey}/todo")
	@ResponseBody
	public BaseResult todo(@PathVariable("processKey") String processKey,
			@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		return BaseResult.getSuccessResult(this.service
				.findeProcessInstanceByTodo(processKey, userId,
						request.getVars()));
	}

	/**
	 * 自己发起的流程实例列表
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{processKey}/owner")
	@ResponseBody
	public BaseResult owner(@PathVariable("processKey") String processKey,
			@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		return BaseResult.getSuccessResult(this.service
				.findeProcessInstanceByOwner(processKey, userId,
						request.getVars()));
	}

	/**
	 * 历史上参与的流程实例列表
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{processKey}/history/involved")
	@ResponseBody
	public BaseResult history_involved(
			@PathVariable("processKey") String processKey,
			@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		return BaseResult.getSuccessResult(this.service
				.findeHistoryProcessInstanceByInvolved(processKey, userId,
						request.getVars()));
	}

	/**
	 * 历史上发起的流程实例列表
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{processKey}/history/owner")
	@ResponseBody
	public BaseResult history_owner(
			@PathVariable("processKey") String processKey,
			@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		return BaseResult.getSuccessResult(this.service
				.findeHistoryProcessInstanceByOwner(processKey, userId,
						request.getVars()));
	}

	/**
	 * 查询历史流程实例列表
	 * 
	 * @param processKey
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{processKey}/history/query")
	@ResponseBody
	public BaseResult history_query(
			@PathVariable("processKey") String processKey,
			@RequestBody Request request) {
		String userId = request.getUserId();
		if (userId == null || userId.length() == 0) {
			logger.warn(EngineConst.WF_ERROR_USER_IS_NULL_MSG);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_USER_IS_NULL,
					EngineConst.WF_ERROR_USER_IS_NULL_MSG);
		}
		return BaseResult.getSuccessResult(this.service.findeProcessInstance(
				processKey, userId, request.getVars()));
	}

	/**
	 * 流程实例任务跟踪
	 * 
	 * @param processKey
	 * @param processInstanceId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{processKey}/trace/{processInstanceId}")
	@ResponseBody
	public BaseResult history_involved(
			@PathVariable("processKey") String processKey,
			@PathVariable("processInstanceId") String processInstanceId,
			@RequestBody Request request) {
		return BaseResult.getSuccessResult(this.service
				.traceProcess(processInstanceId));
	}

	/**
	 * 流程实例任务跟踪
	 * 
	 * @param processKey
	 * @param processInstanceId
	 * @param request
	 * @return
	 * @RequestMapping(value = "/{processKey}/trace/oid/{oid}")
	 * @ResponseBody public BaseResult
	 *               findProcessInstanceByOid(@PathVariable("processKey") String
	 *               processKey, @PathVariable("oid") String oid, @RequestBody
	 *               Request request) { return
	 *               BaseResult.getSuccessResult(this.service
	 *               .traceProcess(processInstanceId)); }
	 */

	/**
	 * 添加候选人
	 * 
	 * @param processKey
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "{processKey}/{processInstanceId}/{type}_candidate")
	@ResponseBody
	public BaseResult addCandidate(
			@PathVariable("processInstanceId") String processInstanceId,
			@PathVariable("type") String type, @RequestBody Request request) {
		try {
			ProcessInstance instance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			if (instance == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			Task task = taskService.createTaskQuery()
					.processInstanceId(instance.getId())
					.taskDefinitionKey(instance.getActivityId()).singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			Object value = request.getVars().get("candidate");
			for (String user : QUtils.getUsers(value)) {
				if ("add".equalsIgnoreCase(type)) {
					taskService.addCandidateUser(task.getId(), user);
				} else {
					taskService.deleteCandidateUser(task.getId(), user);
				}
			}
			TaskInfo tinfo = new TaskInfo();
			tinfo = new TaskInfo();
			tinfo.setAssign(task.getAssignee());
			tinfo.setStartTime(task.getCreateTime());
			tinfo.setEndTime(null);
			tinfo.setTaskId(task.getId());
			tinfo.setTaskName(task.getName());
			tinfo.setType("待处理");
			ProcessInstanceInfo info = new ProcessInstanceInfo();
			info.setOid(instance.getBusinessKey());
			info.setProcessInstanceId(task.getProcessInstanceId());
			info.addCurrentTasks(tinfo);
			return BaseResult.getSuccessResult(info);
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":"
							+ e.getMessage());
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + ":" + e.getMessage());
		}
	}

	/**
	 * 指定操作人
	 * 
	 * @param processKey
	 * @param taskId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "{processKey}/{processInstanceId}/assigned")
	@ResponseBody
	public BaseResult assigned(
			@PathVariable("processInstanceId") String processInstanceId,
			@RequestBody Request request) {
		try {
			ProcessInstance instance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			if (instance == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			Task task = taskService.createTaskQuery()
					.processInstanceId(instance.getId())
					.taskDefinitionKey(instance.getActivityId()).singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			String assignee = (String) request.getVars().get("assignee");
			task.setAssignee(assignee);
			TaskInfo tinfo = new TaskInfo();
			tinfo = new TaskInfo();
			tinfo.setAssign(assignee);
			tinfo.setStartTime(task.getCreateTime());
			tinfo.setEndTime(null);
			tinfo.setTaskId(task.getId());
			tinfo.setTaskName(task.getName());
			tinfo.setType("待处理");
			ProcessInstanceInfo info = new ProcessInstanceInfo();
			info.setOid(instance.getBusinessKey());
			info.setProcessInstanceId(task.getProcessInstanceId());
			info.addCurrentTasks(tinfo);
			return BaseResult.getSuccessResult(info);
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":", e);
			return BaseResult.getErrorResult(
					EngineConst.WF_ERROR_TASK_NOT_FOUNT,
					EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":"
							+ e.getMessage());
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return BaseResult.getErrorResult(EngineConst.WF_ERROR_SYS,
					EngineConst.WF_ERROR_SYS_MSG + ":" + e.getMessage());
		}
	}

	/**
	 * 流程任务跟踪图
	 * 
	 * @param processDefinitionId
	 * @param processInstanceId
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "{processKey}/{processInstanceId}/trace_pic.png")
	public void readResource(@PathVariable("processKey") String processKey,
			@PathVariable("processInstanceId") String processInstanceId,
			HttpServletResponse response) throws Exception {
		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processKey).latestVersion()
				.singleResult();
		BpmnModel bpmnModel = repositoryService.getBpmnModel(pd.getId());
		List<Task> list = taskService.createTaskQuery().active()
				.processInstanceId(processInstanceId).list();
		List<String> activeActivityIds = new ArrayList<String>();
		for (Task task : list) {
			activeActivityIds.add(task.getTaskDefinitionKey());
		}
		Context.setProcessEngineConfiguration(processEngine
				.getProcessEngineConfiguration());
		InputStream imageStream = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator().generateDiagram(
				bpmnModel, "png", activeActivityIds);
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 查看流程定义资源文件
	 * 
	 * @param processDefinitionId
	 * @param resourceType
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "resource/read.html")
	public void loadByDeployment(@RequestParam("processKey") String processKey,
			HttpServletResponse response) throws Exception {
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(processKey).latestVersion()
				.singleResult();
		String resourceName = processDefinition.getDiagramResourceName();
		InputStream resourceAsStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(), resourceName);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 获得全部activity
	 * 
	 * @param processInstanceId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "{processDefinitionId}/{processInstanceId}/activity", method = RequestMethod.GET)
	@ResponseBody
	public Object[] findActivityPosition(
			@PathVariable("processDefinitionId") String processDefinitionId,
			@PathVariable("processInstanceId") String processInstanceId) {
		Map<String, ActivityInfo> infos = new HashMap<String, ActivityInfo>();
		try {
			ProcessInstance instance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			if (instance == null) {
				throw new ActivitiObjectNotFoundException(this.getClass());
			}
			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
					.getDeployedProcessDefinition(processDefinitionId);
			List<TaskInfo> list = this.service.traceProcess(processInstanceId)
					.getTaskTrace();
			for (ActivityImpl act : processDefinition.getActivities()) {
				findAllActivity(act, infos);
			}
			for (TaskInfo task : list) {
				ActivityInfo act = infos.get(task.getTaskKey());
				if (act == null) {
					continue;
				}
				if (act.isCurrent()) {
					continue;
				}
				if (task.getEndTime() == null) {
					act.setCurrent(true);
				} else {
					act.setHistory(true);
				}
				act.setInfo(task);
			}
			return infos.values().toArray();
		} catch (ActivitiObjectNotFoundException e) {
			logger.warn(EngineConst.WF_ERROR_TASK_NOT_FOUNT_MSG + ":", e);
			return infos.values().toArray();
		} catch (Exception e) {
			logger.error(EngineConst.WF_ERROR_SYS_MSG, e);
			return infos.values().toArray();
		}
	}

	private void findAllActivity(ActivityImpl act,
			Map<String, ActivityInfo> infos) {
		List<ActivityImpl> activities = act.getActivities();
		if (activities.isEmpty()) {
			String type = (String) act.getProperty("type");
			if ("usertask".equals(type.toLowerCase())) {
				ActivityInfo info = new ActivityInfo();
				info.setX(act.getX());
				info.setY(act.getY());
				info.setWidth(act.getWidth());
				info.setHeight(act.getHeight());
				info.setId(act.getId());
				infos.put(act.getId(), info);
			}
		} else {
			for (ActivityImpl _act : act.getActivities()) {
				this.findAllActivity(_act, infos);
			}
		}
	}
}
