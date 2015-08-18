package com.qunar.ops.recruit.controller;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.util.OAControllerUtils;

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
	

	/**
	 * index
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "oa/index.html")
	public ModelAndView welcom(HttpServletRequest request, String message) {
		System.out.println("test");
		ModelAndView mav = new ModelAndView("/oa/index");
		mav.addObject("message", message==null?"":message);
		mav.addObject("debug", OAControllerUtils.isDebug());
		return mav;
	}

	
}
