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
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StudentMapper stuMapper;

	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView welcom(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/recruit/login");
		mav.addObject("debug", OAControllerUtils.isDebug());
		return mav;
	}
	
	/**
	 * index
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView toindex(HttpServletRequest request,String username,String password) {
		System.out.println("======================="+username+"+++++++++"+password);
		Student stu = stuMapper.getStudent(1);
		System.out.println(stu.getName());
		ModelAndView mav = new ModelAndView("/recruit/index");
		mav.addObject("debug", OAControllerUtils.isDebug());
		return mav;
	}

	
}
