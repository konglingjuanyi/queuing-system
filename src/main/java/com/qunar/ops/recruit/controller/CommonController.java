package com.qunar.ops.recruit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qunar.ops.recruit.util.QUtils;

@Controller
public class CommonController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index(HttpServletRequest request) {
		request.getSession().getAttribute("username");
		request.getSession().getAttribute("password");
		logger.info("登录的用户名为{}====密码为{}", request.getSession().getAttribute("username"),request.getSession().getAttribute("password"));
		return "redirect:/login";
	}
	
	
	/**
	 * logout
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		QUtils.setUsername(response, "pd", null, true);
		QUtils.setUsername(response, "un", null, true);
		QUtils.setUsername(response, "test-userid", null, false);
		return "redirect:/login";
	}
	
}
