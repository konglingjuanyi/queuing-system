package com.qunar.ops.recruit.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
		/*String username = (String) request.getSession().getAttribute("username");
		String password = (String) request.getSession().getAttribute("password");
		logger.info("登录的用户名为{}====密码为{}", username,password);
		if(!OAControllerUtils.isNull(username)&&!OAControllerUtils.isNull(password)){
			return "redirect:/index";
		}*/
		return "redirect:/login";
	}
	
}
