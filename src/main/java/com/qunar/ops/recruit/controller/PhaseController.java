package com.qunar.ops.recruit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhaseController {
	
	/**
	 * 期次管理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hr/getPhaseInfos")
	public String getInterviewers(HttpServletRequest request,  ModelMap model) {
		System.out.println("===========================");

		model.addAttribute("message", "uiui");
		return "/phase_manage";
	}
	
}
