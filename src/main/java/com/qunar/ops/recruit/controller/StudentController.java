package com.qunar.ops.recruit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.service.StudentWaiter;
import com.qunar.ops.recruit.service.Studenttest;
import com.qunar.ops.recruit.service.WaitService;
import com.qunar.ops.recruit.util.RecruitConst;
import com.qunar.ops.recruit.util.RecruitControllerUtils;


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
	public BaseResult register(HttpServletRequest request, String phone, String name) {
		System.out.println(phone+"============="+name);
		Object user = request.getSession().getAttribute("user");
		if(user == null){
			if(phone != null && name != null){
				Student stu = studentService.getStudentByPhone(phone);
				if(stu == null){
					return BaseResult.getErrorResult(RecruitConst.NO_USER_ERROR, RecruitConst.NO_USER_ERROR_MSG);
				}else if(!stu.getName().equals(name)){
					return BaseResult.getErrorResult(RecruitConst.PHONE_NAME_MISS_MATCH, RecruitConst.PHONE_NAME_MISS_MATCH_MSG);
				}else if(waitService.contains(stu)){
					return BaseResult.getSuccessResult(waitService.numberInFrontOf(new StudentWaiter(stu,0)));
				}else{
					Date date = new Date();
					StudentWaiter sw = new StudentWaiter(stu, date.getTime());
					int frontWaters = waitService.add2WaitList(sw);
					request.getSession().setAttribute("user", sw);
					studentService.addStudentRealComeTimeIntoDB(date);
					return BaseResult.getSuccessResult(frontWaters);
				}
			}else{
				return BaseResult.getErrorResult(RecruitConst.PARAMETER_NULL, RecruitConst.PARAMETER_NULL_ERROR);
			}
		}else{
			if(user instanceof StudentWaiter){
				StudentWaiter sw = (StudentWaiter) user;
				int frontWaters = waitService.numberInFrontOf(sw);
				return BaseResult.getSuccessResult(frontWaters);
			}else{
				return BaseResult.getErrorResult(RecruitConst.AUTHORITY_ERROR, RecruitConst.AUTHORITY_ERROR_MSG);
			}

		}

	}
	
	@RequestMapping(value = "/student/test")
	@ResponseBody
	//public BaseResult register(HttpServletRequest request, String phone) {
	public void test(HttpServletRequest request, @RequestParam  String name,@RequestParam  String should,@RequestParam  String real) {
		System.out.println("应到时间:"+should+"++++实到时间:"+real);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//小写的mm表示的是分钟  
		String[] shouldcomeTimes = new String[]{"2015-8-21 9:00:00","2015-8-21 10:00:00","2015-8-21 9:00:00"};
		String[] realcomeTimes = new String[]{"2015-8-21 8:25:00","2015-8-21 8:00:00","2015-8-21 8:23:00"};
		
		long s;
		long r;
		long n;
		try {
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(sdf.parse(should));
			Calendar calendar1=Calendar.getInstance();
			calendar1.setTime(sdf.parse(real));
			Calendar calendar2=Calendar.getInstance();
			Date d=sdf.parse("2015-08-21 10:30:00");
			calendar2.setTime(d);
			s = calendar.getTimeInMillis();
			r = calendar1.getTimeInMillis();
			n = calendar2.getTimeInMillis();
			System.out.println("应到时间:"+s+"===实到时间:"+r+"====当前时间:"+n);
			Studenttest stu=new Studenttest(n,s,r,name);
			int frontWaters = waitService.addtest(stu);
		} catch (ParseException e) {
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
	
	/**
	 * 手机端学生登录界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/student/login")
	public String loginMobile(HttpServletRequest request,ModelMap model) {
		//String message="<span class='name'>赵英俊</span>同学 <br />在你前面还有 <span class='num'>32</span> 位同学<br />正在进行面试";
		//model.addAttribute("message",message);
		model.addAttribute("flag",0);
		return "mobile/mobile_index";
	}
}
