package com.qunar.ops.oaengine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qunar.ops.oaengine.manager.WorkflowManager;
import com.qunar.ops.oaengine.result.ListInfo;
import com.qunar.ops.oaengine.result.Request;
import com.qunar.ops.oaengine.result.TaskInfo;
import com.qunar.ops.oaengine.result.TaskResult;
import com.qunar.ops.oaengine.service.MailSenderService;

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
	protected WorkflowManager manager;
	@Autowired
	protected ProcessEngineFactoryBean processEngine;
	@Autowired
	private MailSenderService mailSenderService;


	@RequestMapping(value = "/oa/test.html")
	public void index(HttpServletRequest request) {
		//mailSenderService.sender("nuby.zhang@qunar.com", new String[]{"nuby.zhang@qunar.com"}, new String[]{"nuby.zhang@qunar.com"}, "", "");
		Request req = new Request();
		req.setAmountMoney(200l);
		req.setDepartment("技术部");
		req.setDepartmentII("OPS");
		req.setOid("001");
		req.setReport2vp(true);
		req.setTbMoney(100l);
//		Object[] startWorkflow = this.manager.startWorkflow("oa_common", "nuby.zhang", req);
//		ListInfo<TaskInfo> todoList = this.manager.todoList("test", "nuby", 0, 10);
		TaskResult pass = this.manager.pass("5913", "nuby.zhang");
		//this.manager.endorse("5", "nuby", "nuby,abc");
		//List<TaskInfo> back = this.manager.back("nuby", "50962", "xxx");
		//this.manager.cancel("test", "001", "nuby.zhang", "reason");
		System.out.println("====");
	}

 
}
