package com.qunar.ops.recruit.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.dao.StudentMapper;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.util.OAControllerUtils;

@Controller
public class CommonController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required=true)
	private StudentMapper studentMapper;

	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index(HttpServletRequest request) {
		return "redirect:/login";
	}
	
}
