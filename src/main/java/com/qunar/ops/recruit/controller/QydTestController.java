package com.qunar.ops.recruit.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qunar.ops.recruit.dao.InterviewerMapper;
import com.qunar.ops.recruit.dao.StudentMapper;
import com.qunar.ops.recruit.model.Interviewer;

@Controller
public class QydTestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StudentMapper stuM;
	@Autowired
	private InterviewerMapper inM;

	
	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/qyd")
	public String index(HttpServletRequest request) {
		/*String username = (String) request.getSession().getAttribute("username");
		String password = (String) request.getSession().getAttribute("password");
		logger.info("登录的用户名为{}====密码为{}", username,password);
		if(!OAControllerUtils.isNull(username)&&!OAControllerUtils.isNull(password)){
			return "redirect:/index";
		}*/
		Interviewer in = new Interviewer();
		in.setEndTime(new Date());
		in.setName("qyd");
		in.setCity("jinan");
		in.setUserName("qinyadong");
		in.setPassword("123456");
		in.setState(1);
		in.setStartDate(new Date());
		in.setJob("");
		in.setRole("");
		in.setOneCount(0);
		in.setTwoCount(0);
		inM.insert(in);
		System.out.println("===");
		return "redirect:/login";
	}
	
}
