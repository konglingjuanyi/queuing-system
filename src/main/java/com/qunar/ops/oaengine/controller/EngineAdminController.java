package com.qunar.ops.oaengine.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
public class EngineAdminController {

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
	protected ProcessEngineFactoryBean processEngine;

	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/login")
	public String login(HttpServletRequest request) {
		HttpClient client = HttpClientBuilder.create().build();
		String token = request.getParameter("token");
		HttpGet method = new HttpGet(
				"http://qsso.corp.qunar.com/api/verifytoken.php?token=" + token);
		try {
			HttpResponse response = client.execute(method);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			JSONObject parseObject = JSON.parseObject(result.toString());
			String ret = parseObject.getString("ret");
			if (ret.equals("true")) {
				String userId = parseObject.getJSONObject("data").getString(
						"userId");
				request.getSession().setAttribute("USER_ID", userId);
			} else {
				return "redirect:/admin/index.html";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("sso 验证失败", e);
		}
		return "redirect:/admin/process_list.html";
	}

	/**
	 * index
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/index.html")
	public ModelAndView welcom(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("USER_ID");
		if (userId != null) {
			return addProcess(request);
		}
		ModelAndView mav = new ModelAndView("/index");
		return mav;
	}

	/**
	 * 部署
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/add_process.html")
	public ModelAndView addProcess(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/add_process");
		return mav;
	}

	/**
	 * 部署
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/admin/deploy")
	public String deploy(
			@RequestParam(value = "file", required = false) MultipartFile file) {

		String fileName = file.getOriginalFilename();
		try {
			InputStream fileInputStream = file.getInputStream();
			Deployment deployment = null;
			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals("zip") || extension.equals("bar")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = repositoryService.createDeployment()
						.addZipInputStream(zip).deploy();
			} else {
				deployment = repositoryService.createDeployment()
						.addInputStream(fileName, fileInputStream).deploy();
			}

		} catch (Exception e) {
			logger.error(
					"error on deploy process, because of file input stream", e);
		}
		return "redirect:/admin/process_list.html";
	}

	/**
	 * 激活/挂起流程
	 * 
	 * @param state
	 * @param processDefinitionId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "admin/{state}/{processDefinitionId}")
	public String updateState(@PathVariable("state") String state,
			@PathVariable("processDefinitionId") String processDefinitionId,
			RedirectAttributes redirectAttributes) {
		ProcessDefinition def = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		if (state.equals("active")) {
			repositoryService
					.activateProcessDefinitionById(processDefinitionId);
		} else if (state.equals("suspend")) {
			repositoryService.suspendProcessDefinitionById(processDefinitionId);
		} else if (state.equals("suspend")) {
			repositoryService.deleteDeployment(def.getDeploymentId());
		}
		return "redirect:/admin/process_list.html";
	}

	/**
	 * 流程定义列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/process_list.html")
	public ModelAndView processList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("process_list");

		List<ProcessDefinition> list = repositoryService
				.createProcessDefinitionQuery().orderByDeploymentId().desc()
				.list();
		List<Object[]> infos = new ArrayList<Object[]>();
		for (ProcessDefinition def : list) {
			long c1 = runtimeService.createProcessInstanceQuery()
					.processDefinitionId(def.getId()).count();
			long c2 = taskService.createTaskQuery()
					.processDefinitionId(def.getId()).count();
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(def.getDeploymentId()).singleResult();
			infos.add(new Object[] { def, deployment, c1, c2 });
		}
		mav.addObject("page", infos);
		mav.addObject("totalCount", list.size());
		return mav;
	}

	/**
	 * 查看流程定义资源文件
	 * 
	 * @param processDefinitionId
	 * @param resourceType
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/resource/read.html")
	public void loadByDeployment(
			@RequestParam("processDefinitionId") String processDefinitionId,
			@RequestParam("resourceType") String resourceType,
			HttpServletResponse response) throws Exception {
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		String resourceName = "";
		if (resourceType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resourceType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}
		InputStream resourceAsStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(), resourceName);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 删除流程定义
	 * 
	 * @param deploymentId
	 * @return
	 */
	@RequestMapping(value = "/admin/delete")
	public String delete(@RequestParam("deploymentId") String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
		return "redirect:/admin/process_list.html";
	}

	/**
	 * 流程实例列表
	 * 
	 * @param processDefinitionId
	 * @return
	 */
	@RequestMapping(value = "/admin/{processDefinitionId}/instance_list")
	public ModelAndView findProcessInstanceList(
			@PathVariable("processDefinitionId") String processDefinitionId) {
		ModelAndView mav = new ModelAndView("/instance_list");
		List<ProcessInstance> infos = runtimeService
				.createProcessInstanceQuery()
				.processDefinitionId(processDefinitionId)
				.orderByProcessInstanceId().desc().list();
		mav.addObject("list", infos);
		mav.addObject("processDefinitionId", processDefinitionId);
		mav.addObject("processKey", processDefinitionId.split(":")[0]);
		return mav;
	}

	/**
	 * 流程任务跟踪图
	 * 
	 * @param processDefinitionId
	 * @param processInstanceId
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/{processDefinitionId}/{processInstanceId}/trace_pic.png")
	public void readResource(
			@PathVariable("processDefinitionId") String processDefinitionId,
			@PathVariable("processInstanceId") String processInstanceId,
			HttpServletResponse response) throws Exception {
		BpmnModel bpmnModel = repositoryService
				.getBpmnModel(processDefinitionId);
		List<Task> list = taskService.createTaskQuery().active()
				.processInstanceId(processInstanceId).list();
		List<String> activeActivityIds = new ArrayList<String>();
		for (Task task : list) {
			activeActivityIds.add(task.getTaskDefinitionKey());
		}
		Context.setProcessEngineConfiguration(processEngine
				.getProcessEngineConfiguration());
		// InputStream imageStream =
		// ProcessDiagramGenerator.generateDiagram(bpmnModel, "png",
		// activeActivityIds);
		InputStream imageStream = this.processEngine
				.getProcessEngineConfiguration().getProcessDiagramGenerator()
				.generateDiagram(bpmnModel, "png", activeActivityIds);
		byte[] b = new byte[1024];
		int len;
		while ((len = imageStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

	/**
	 * 删除流程
	 * 
	 * @param processDefinitionId
	 * @param processInstanceId
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/{processDefinitionId}/{processInstanceId}/delete")
	public String remove(
			@PathVariable("processDefinitionId") String processDefinitionId,
			@PathVariable("processInstanceId") String processInstanceId,
			HttpServletResponse response) throws Exception {
		runtimeService.deleteProcessInstance(processInstanceId, "admin");
		historyService.deleteHistoricProcessInstance(processInstanceId);
		return "redirect:/admin/" + processDefinitionId + "/instance_list.html";
	}

}
