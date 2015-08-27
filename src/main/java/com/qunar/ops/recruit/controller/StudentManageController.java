package com.qunar.ops.recruit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentManageController {

	/**
	 * 候选人管理
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/hr/getAllStudentInfos")
	public String getAllStudentInfos(HttpServletRequest request,  ModelMap model) {
		System.out.println("+++++++++++++++");
		return "/student_manage";
	}
}
