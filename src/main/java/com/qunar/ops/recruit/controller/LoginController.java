package com.qunar.ops.recruit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.dao.InterviewerMapper;
import com.qunar.ops.recruit.util.OAControllerUtils;
import com.qunar.ops.recruit.util.QUtils;

@Controller
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterviewerMapper inM;

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
	public ModelAndView toindex(HttpServletRequest request,
			HttpServletResponse response, String username, String password) {
		System.out.println("======"+inM.getInterviewer(1).getPassword());
		logger.info("登录的用户名为{}+++++++++++++++++密码为{}", username, password);
		QUtils.setUsername(response, "pd", password, true);
		QUtils.setUsername(response, "un", username, true);
		QUtils.setUsername(response, "test-userid", username.toLowerCase(), false);
		ModelAndView mav = new ModelAndView("/recruit/index");
		mav.addObject("debug", OAControllerUtils.isDebug());
		return mav;
	}

	@RequestMapping(value = "/index1")
	public Map toindex1(HttpServletRequest request,
			HttpServletResponse response, String username, String password) {
		System.out.println("========");
		Map m = new HashMap();
		m.put("1", "1");
		return m;
	}
	
	
}
