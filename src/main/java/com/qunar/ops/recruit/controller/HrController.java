package com.qunar.ops.recruit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.connect_table.InterviewerInfoToPage;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.result.CommonRequest;
import com.qunar.ops.recruit.result.ResultPlusAdditionalInfo;
import com.qunar.ops.recruit.service.InterviewerService;
import com.qunar.ops.recruit.service.JoinService;
import com.qunar.ops.recruit.service.PhaseInterviewService;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.service.StudentWaiter;
import com.qunar.ops.recruit.service.WaitService;
import com.qunar.ops.recruit.util.RecruitConst;

@Controller
public class HrController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterviewerService interService;
	@Autowired
	StudentService studentService;
	@Autowired
	WaitService waitService;
	@Autowired
	JoinService joinService;
	@Autowired
	PhaseInterviewService piService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 面试官管理 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/hr/show_list.html")
	public ModelAndView addApply(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/recruit/show_list");
		return mav;
	}
	
	@RequestMapping(value = "/hr/addInterviewers")
	@ResponseBody
	@Transactional
	public BaseResult addInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
//		System.out.println(vars);
		Interviewer inter = interService.createInterviewer(vars);
		if(interService.getInterviewerByUserName(inter.getUserName()) != null){
			return BaseResult.getErrorResult(RecruitConst.ALREADY_EXIST_USER_ERROR, RecruitConst.ALREADY_EXIST_USER_ERROR_MSG);
		}else{
			interService.addInterviewer(inter);
			return BaseResult.getSuccessResult("success");
		}
	}
	
	@RequestMapping(value = "/hr/deleteInterviewer")
	@ResponseBody
	@Transactional
	public BaseResult deleteInterviewers(HttpServletRequest request, int id) {
		interService.deleteInterviewer(id);
		return BaseResult.getSuccessResult("success");
	}
	
	@RequestMapping(value = "/hr/updateInterviewer")
	@ResponseBody
	@Transactional
	public BaseResult updateInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
//		System.out.println(vars);
		Interviewer inter = interService.createUpdateInterviewer(vars);
		if(interService.getInterviewerByUserNameExceptId(inter.getUserName(), inter.getId()) != null){
			return BaseResult.getErrorResult(RecruitConst.ALREADY_EXIST_USER_ERROR, RecruitConst.ALREADY_EXIST_USER_ERROR_MSG);
		}else{
			interService.updateInterviewer(inter);
			return BaseResult.getSuccessResult("success");
		}
	}

	@RequestMapping(value = "/hr/getInterviewersForMonitor")
	public String getInterviewersForMonitor(HttpServletRequest request, HttpSession session,  ModelMap model) {
		System.out.println(session.getAttribute("year")+" "+session.getAttribute("phase")+" "+session.getAttribute("city")); 
//		Map<String, String> vars = commonRequest.getVars();
//		int noSize[] = RecruitControllerUtils.getPageNoAndSize(vars);
//		int pageSize = noSize[0];
//		int pageNo = noSize[1];
//		int pageSize = 10;
//		int pageNo = 1;
//		pageNo = pageNo <= 0 ? 1 : pageNo;
//		pageSize = pageSize > 0 ? pageSize : 20;
		String year = (String)session.getAttribute("year");
		String phase = (String)session.getAttribute("phase");
		String city = (String)session.getAttribute("city");
		List<InterviewerInfoToPage> list = joinService.getInterviewerInfoToPages(year, phase, city);
		List<ResultPlusAdditionalInfo> rets = new LinkedList<ResultPlusAdditionalInfo>();
		for (InterviewerInfoToPage interviewer : list) {
			ResultPlusAdditionalInfo info = new ResultPlusAdditionalInfo();
			info.setObj(interviewer);
			info.addStringInfo(interviewer.getPi().getFirstFe()+interviewer.getPi().getFirstQa()+interviewer.getPi().getFirstRd()+"");
			if(interviewer.getCount() == null)
				info.addStringInfo(0+"");
			else
				info.addStringInfo(interviewer.getCount()+"");
			rets.add(info);
		}
		model.addAttribute("message", rets);
		return "/viewer_monitor";
	}
	
	@RequestMapping(value = "/hr/getInterviewersForManage")
	public String getInterviewersForManage(HttpServletRequest request,  ModelMap model) {
		List<Interviewer> list =  interService.getInterviewers();
		List<ResultPlusAdditionalInfo> rets = new LinkedList<ResultPlusAdditionalInfo>();
		for (Interviewer interviewer : list) {
			ResultPlusAdditionalInfo info = new ResultPlusAdditionalInfo();
			info.setObj(interviewer);
			rets.add(info);
		}
		model.addAttribute("message", rets);
		return "/viewer_manage";
	}

	@RequestMapping(value = "/hr/getInterviewerInfo")
	@ResponseBody
	public BaseResult getInterviewerInfo(HttpServletRequest request, HttpSession session, int id, ModelMap model) {
//		System.out.println("===========================");
//		Map<String, String> vars = commonRequest.getVars();
//		int noSize[] = RecruitControllerUtils.getPageNoAndSize(vars);
//		int pageSize = noSize[0];
//		int pageNo = noSize[1];
//		int pageSize = 10;
//		int pageNo = 1;
//		pageNo = pageNo <= 0 ? 1 : pageNo;
//		pageSize = pageSize > 0 ? pageSize : 20;
//		String phase = vars.get("phase");
//		String city = vars.get("city");
//		List<Interviewer> list = inServe.getInterviewers((pageNo - 1) * pageSize, pageSize, phase, city);
//		List<String[]> retList = tranfer2stringArray(list);
//		DataResult dataResult = new DataResult();
//		dataResult.setCount(list.size());
//		dataResult.setTableInfos(retList);
		logger.debug("getInterviewerInfo");
		String year = (String)session.getAttribute("year");
		String phase = (String)session.getAttribute("phase");
		String city = (String)session.getAttribute("city");
//		System.out.println(id+"===9009");
		Interviewer inter = interService.getInterviewersById(id);
		PhaseInterviewer pi = piService.getPhaseInterviewerBy(year, phase, city, inter.getUserName());
		List<Student> list = studentService.getStudentsByFirstTry(inter.getUserName());
		int[] nums = getNums(list);
		List ret = new LinkedList();
		ret.add(inter);
		ret.add(pi);
		ret.add(nums);
//		model.addAttribute("message", interFe);
		return BaseResult.getSuccessResult(ret);
	}

	@RequestMapping(value = "/hr/assignInterviewer")
	@Transactional
	public String getInterviewersForManage(HttpServletRequest request, ModelMap model, Integer id, String name) {
		if(id == null || name == null){
			return "forward:/hr/getAllStudentInfos";
		}else{
			Student stu = studentService.getStudentById(id);
			StudentWaiter sw = new StudentWaiter(stu);
			if(waitService.containsOne(sw)){
				waitService.removeHighestPriorityFromOneList(sw);
				sw.getStu().setFirstTry(name);
				waitService.add2AssianList(sw);
			}else{
				sw.getStu().setFirstTry(name);
				waitService.add2AssianList(sw);
			}
			Student newStu = new Student();
			newStu.setId(stu.getId());
			newStu.setState(RecruitConst.STUDENT_STATE_REGIST);
			if(stu.getTrueTime() == null)
				newStu.setTrueTime(new Date());
			studentService.updateStudent(newStu);
		}
		
		return "forward:/hr/getAllStudentInfos";
	}
	
	private int[] getNums(List<Student> list) {
		int secondRd = 0;
		int secondFe = 0;
		int secondQa = 0;
		for (Student student : list) {
			if(student.getState().equals(RecruitConst.STUDENT_STATE_FINISH) || 
					student.getState().equals(RecruitConst.STUDENT_STATE_TWO_PASS)){
				if(student.getJob().equals(RecruitConst.JOB_FE)){
					secondFe ++;
				}else if(student.getJob().equals(RecruitConst.JOB_QA)){
					secondQa ++;
				}else if(student.getJob().equals(RecruitConst.JOB_RD)){
					secondRd ++;
				}
			}
		}
		return new int[]{secondRd, secondFe, secondQa};
	}

}
