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
import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.service.InterviewerService;
import com.qunar.ops.recruit.util.RecruitControllerUtils;
import com.qunar.ops.recruit.util.QUtils;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterviewerService interservice;
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
		logger.info("登录的用户名为{}+++++++++++++++++密码为{}", username, password);
		if(username == null || password == null){
			ModelAndView mav = new ModelAndView("redirect:/login");
			mav.getModelMap().put("wrong", "username or password is null!");
			return mav;
		}
		Interviewer inter = interservice.getInterviewersByUserName(username);
		if(inter == null){
			request.getSession().setAttribute("user", inter);
			ModelAndView mav = new ModelAndView("redirect:/login");
			mav.getModelMap().put("wrong", "no user");
			System.out.println("no this user");
			return mav;
		}if(inter.getPassword().equals(password)){
			request.getSession().setAttribute("user", inter);
			ModelAndView mav = new ModelAndView("/recruit/index");
			return mav;
		}else{
			ModelAndView mav = new ModelAndView("redirect:/login");
			mav.getModelMap().put("wrong", "wrong password!");
			System.out.println("wrong password!");
			return mav;
			
		}
	}
	
}
