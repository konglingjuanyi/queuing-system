package com.qunar.ops.recruit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.dao.InterviewerMapper;
import com.qunar.ops.recruit.dao.StudentMapper;
import com.qunar.ops.recruit.util.RecruitControllerUtils;
import com.qunar.ops.recruit.util.QUtils;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView welcom(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/recruit/login");
		return mav;
	}

	/**
	 * index
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView toindex(HttpServletRequest request,
			HttpServletResponse response, String username, String password) {
		System.out.println("-----============================================++++");
		logger.info("登录的用户名为{}+++++++++++++++++密码为{}", username, password);
		request.getSession().setAttribute("username", username);
		request.getSession().setAttribute("password", password);
		ModelAndView mav = new ModelAndView("/recruit/index");
		System.out.println("***************");
		return mav;
	}
	
}
