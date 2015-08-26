package com.qunar.ops.recruit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.service.WaitService;
import com.qunar.ops.recruit.util.QUtils;

@Controller
public class CommonController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	WaitService ws;
	@Autowired
	StudentService ss;
	
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
	
	
	
	@RequestMapping(value = "/restart")
	@ResponseBody
	public String restart(HttpServletRequest request,HttpServletResponse response) {
		List<Student> l1 = ss.getOneList();
		List<Student> l2 = ss.getTwoList();
		ws.recovery(l1, l2);
		return "success";
	}
	
	@RequestMapping(value = "/getFirstPhases")
	@ResponseBody
	public String getFirstPhases(HttpServletRequest request,HttpServletResponse response) {
		List<Student> l1 = ss.getOneList();
		List<Student> l2 = ss.getTwoList();
		ws.recovery(l1, l2);
		return "success";
	}
	
}
