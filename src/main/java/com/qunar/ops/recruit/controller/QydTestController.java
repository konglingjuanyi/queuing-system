package com.qunar.ops.recruit.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.dao.InterviewerMapper;
import com.qunar.ops.recruit.dao.StudentMapper;
import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.service.JoinService;

@Controller
public class QydTestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StudentMapper stuM;
	@Autowired
	private InterviewerMapper inM;
	@Autowired
	JoinService joinService;
	int count = 0;

	
	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/qyd")
	@ResponseBody
	public void index(HttpServletRequest request) {
		Student s = new Student();
		s.setId(1);
		String numberOfRoom = joinService.getFirstInterviewRoomNumber(s);
		System.out.println("numberofRoom---->"+numberOfRoom);
		System.out.println(request.getSession().getAttribute("i"));
		System.out.println(count--);
		System.out.println("current object-->"+this+"  currentThread-->"+Thread.currentThread().getId());
	}
	
	@RequestMapping(value = "/qydset")
	public String set(HttpServletRequest request, Interviewer v, int i) {
		/*String username = (String) request.getSession().getAttribute("username");
		String password = (String) request.getSession().getAttribute("password");
		logger.info("登录的用户名为{}====密码为{}", username,password);
		if(!OAControllerUtils.isNull(username)&&!OAControllerUtils.isNull(password)){
			return "redirect:/index";
		}*/
//		System.out.println("current access time--->"+ count++);
//		try {
//			Thread.sleep((long)(100000*Math.random()));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		request.getSession().setAttribute("i", i);
		System.out.println(count--);
		System.out.println("current object-->"+this+"  currentThread-->"+Thread.currentThread().getId());
		return "redirect:login";
	}
	
}
