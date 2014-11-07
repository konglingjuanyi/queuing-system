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


	@RequestMapping(value = "/oa/test.html")
	public void index(HttpServletRequest request) {
		Request req = new Request();
		req.setAmountMoney(200);
		req.setDepartment("dep");
		req.setOid("001");
		req.setReport2vp(true);
		req.setTbMoney(100);
		Object[] startWorkflow = this.manager.startWorkflow("test", "nuby.zhang", req);
		//ListInfo<TaskInfo> todoList = this.manager.todoList("test", "nuby", 0, 10);
		//List<TaskInfo> pass = this.manager.pass("58541", "nuby");
		//this.manager.endorse("58508", "nuby", "nuby,abc");
		//List<TaskInfo> back = this.manager.back("nuby", "50962", "xxx");
		//this.manager.cancel("test", "001", "nuby.zhang", "reason");
		//System.out.println("====");
	}

 
}
