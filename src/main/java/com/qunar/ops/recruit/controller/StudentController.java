package com.qunar.ops.recruit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.service.StudentWaiter;
import com.qunar.ops.recruit.service.WaitService;
import com.qunar.ops.recruit.util.RecruitConst;

@Controller
public class StudentController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	WaitService waitService;
	@Autowired
	StudentService studentService;
	/**
	 * 学生登录显示
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/student/show_list.html")
	public ModelAndView addApply(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/recruit/student_show_list");
		return mav;
	}
	
	/**
	 * 学生签到
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/student/register")
	@ResponseBody
	public BaseResult register(HttpServletRequest request, String phone) {
		Student stu = studentService.getStudentByPhone(phone);
		if(waitService.contains(stu)){
			return BaseResult.getErrorResult(RecruitConst.ALREADY_REGIST_ERROR, RecruitConst.ALREADY_REGIST_ERROR_MSG);
		}else{
			StudentWaiter sw = new StudentWaiter(stu, System.currentTimeMillis());
			int frontWaters = waitService.add2WaitList(sw);
			request.getSession().setAttribute("sw", sw);
			return BaseResult.getSuccessResult(frontWaters);
		}
		
	}
	
	@RequestMapping(value = "/student/refresh")
	@ResponseBody
	public BaseResult refresh(HttpSession session) {
		StudentWaiter sw = (StudentWaiter) session.getAttribute("sw");
		if(sw != null){
			int frontWaters = waitService.numberInFrontOf(sw);
			return BaseResult.getSuccessResult(frontWaters);
		}else{
			return BaseResult.getErrorResult(RecruitConst.NOT_REGIST_ERROR, RecruitConst.NOT_REGIST_ERROR_MSG);
		}
		
	}
}
