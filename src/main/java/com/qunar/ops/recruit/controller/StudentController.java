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

import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.service.JoinService;
import com.qunar.ops.recruit.service.PhaseInterviewService;
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
	@Autowired
	JoinService joinService;
	@Autowired
	PhaseInterviewService piService;
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
	public String register(HttpServletRequest request, String phone, String name, ModelMap model) {
		Object user = request.getSession().getAttribute("user");
		if(user == null){
			if(phone != null && name != null){
				Student stu = studentService.getStudentByPhone(phone);
				if(stu == null){
					String message= RecruitConst.NO_USER_ERROR_MSG;
					model.addAttribute("message",message);
					model.addAttribute("flag",0);
					System.out.println(RecruitConst.NO_USER_ERROR_MSG);
					return "mobile/mobile_login";
				}else if(!stu.getName().equals(name)){
					String message= RecruitConst.PHONE_NAME_MISS_MATCH_MSG;
					model.addAttribute("message",message);
					model.addAttribute("flag",0);
					return "mobile/mobile_login";
				}else if(stu.getTrueTime() != null){
					request.getSession().setAttribute("user", new StudentWaiter(stu));
					return "redirect:/student/refresh";
				}else{
					Date date = new Date();
					stu.setTrueTime(date);
					StudentWaiter sw = new StudentWaiter(stu);
					waitService.add2WaitList(sw);
					request.getSession().setAttribute("user", sw);
					if(!studentService.isLate(stu)){
						stu.setState(RecruitConst.STUDENT_STATE_REGIST);
					}else{
						stu.setState(RecruitConst.STUDENT_STATE_LATE);
					}
					studentService.updateStudent(stu);
					return "redirect:/student/refresh";
				}
			}else{
				model.addAttribute("flag",0);
				return "mobile/mobile_login";
			}
		}else{//session中存在学生信息
			return "redirect:/student/refresh";
		}

	}
	
	@RequestMapping(value = "/student/refresh")
	public String refresh(HttpSession session,  ModelMap model) {
		Object sw = session.getAttribute("user");
//		System.out.println(sw+"===========");
		if(sw != null){ 
			if(sw instanceof StudentWaiter){
				setModelMap(model, session, sw);
			}else{
				String message= RecruitConst.AUTHORITY_ERROR_MSG;
				model.addAttribute("message",message);
				model.addAttribute("flag",1);
			}
			return "mobile/mobile_index";
		}else{
			model.addAttribute("flag",0);
			return "mobile/mobile_login";
		}
	}
	
	private void setModelMap(ModelMap model, HttpSession session, Object sw) {

		StudentWaiter studentWaiter = (StudentWaiter) sw;
		Student student = studentWaiter.getStu();
		String name = student.getName();
		if(isFinished(student)){
			setMessage(model, student);
			model.addAttribute("flag",1);
		}else if(waitService.containsOne(studentWaiter)){
			//一面排队中
			int numberInfontOfMe = waitService.numberInFrontOfOne(studentWaiter);
			String message="<span class='name'>"+name+"</span>同学 <br />在你前面还有 <span class='num'>"+numberInfontOfMe+"</span> 位同学<br />等待面试";
			model.addAttribute("message",message);
			model.addAttribute("flag",1);
		}else if(waitService.containsTwo(studentWaiter)){
			//二面排队中
			int numberInfontOfMe = waitService.numberInFrontOfTwo(studentWaiter);
			String message="<span class='name'>"+name+"</span>同学 恭喜通过初试<br />在你前面还有 <span class='num'>"+numberInfontOfMe+"</span> 位同学<br />等待复试";
			model.addAttribute("message",message);
			model.addAttribute("flag",1);
		}else{
			Student newStudent = studentService.getStudentByPhone(student.getPhone());
			if(newStudent.getState().equals(RecruitConst.STUDENT_STATE_GOING2ONEROOM)
					|| newStudent.getState().equals(RecruitConst.STUDENT_STATE_GOING2TWOROOM)){
				//面试官取学生，通知进房间
				String interName = null;
				if(newStudent.getSecondTry() == null || newStudent.getSecondTry().equals("")){
					interName = newStudent.getFirstTry();
				}else{
					interName = newStudent.getSecondTry();
				}
				PhaseInterviewer pi = piService.getPhaseInterviewerBy(newStudent.getYear(), 
						newStudent.getPhaseNo(), newStudent.getLocation(), interName);
				String numberOfRoom = pi.getRoom();
				String message="<span class='name'>"+name+"</span>同学 <br />请你到 <span class='num'>"+numberOfRoom+"</span> 房间<br />进行面试";
				model.addAttribute("message",message);
				model.addAttribute("flag",1);
			}else{
				//直接返回当前学生的状态
				if(newStudent.getState().equals(RecruitConst.STUDENT_STATE_PASS_ME)){
					model.addAttribute("message", RecruitConst.PROMPT_PASS_ME);
				}else if(newStudent.getState().equals(RecruitConst.STUDENT_STATE_TWO_PASS)){
					model.addAttribute("message", RecruitConst.PROMPT_TWO_PASS);
				}else if(isFinished(newStudent)){
					setMessage(model, newStudent);
				}else{
					model.addAttribute("message",newStudent.getState());
				}
				model.addAttribute("flag",1);
			}
			session.setAttribute("user", new StudentWaiter(newStudent));
		}
		
	}

	private void setMessage(ModelMap model, Student student) {
		if(student.getState().equals(RecruitConst.STUDENT_STATE_FINISH) ||
				student.getState().equals(RecruitConst.STUDENT_STATE_ONE_NOT_PASS) ||
				student.getState().equals(RecruitConst.STUDENT_STATE_TWO_NOT_PASS)){
			model.addAttribute("message", RecruitConst.PROMPT_NOT_PASS);
		}else{
			model.addAttribute("message", RecruitConst.PROMPT_FINISH);
		}
		
	}

	private boolean isFinished(Student student) {
		if(student.getState().equals(RecruitConst.STUDENT_STATE_FINISH) ||
				student.getState().equals(RecruitConst.STUDENT_STATE_ONE_NOT_PASS) ||
				student.getState().equals(RecruitConst.STUDENT_STATE_TWO_NOT_PASS) ||
				student.getState().equals(RecruitConst.STUDENT_STATE_GET_OFFER) ||
				student.getState().equals(RecruitConst.STUDENT_STATE_NO_OFFER) ||
				student.getState().equals(RecruitConst.STUDENT_STATE_UNCERTAIN_OFFER)){
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/student/refresh1")
	@ResponseBody
	public BaseResult refresh1(HttpSession session,  ModelMap model) {
		Object sw = session.getAttribute("user");
//		System.out.println(sw+"===========");
		if(sw != null){ 
			if(sw instanceof StudentWaiter){
				setModelMap(model, session, sw);
			}else{
				String message= RecruitConst.AUTHORITY_ERROR_MSG;
				model.addAttribute("message",message);
				model.addAttribute("flag",1);
			}
		}else{
			model.addAttribute("flag",0);
		}
		return BaseResult.getSuccessResult(model);
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
//			int frontWaters = waitService.addtest(stu);
		} catch (ParseException e) {
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
		Object sw = request.getSession().getAttribute("user");
		if(null==sw){
			model.addAttribute("flag",0);
			return "mobile/mobile_login";
		}else{
			return "redirect:/student/refresh";
		}
	}
	
	/**
	 * PC端显示界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/student/pc_show")
	public String pc_show(HttpServletRequest request,ModelMap model) {
		return "pc/pc_index";
	}
}
