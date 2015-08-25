package com.qunar.ops.recruit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.result.CommonRequest;
import com.qunar.ops.recruit.result.DataResult;
import com.qunar.ops.recruit.service.InterviewerService;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.service.StudentWaiter;
import com.qunar.ops.recruit.service.WaitService;
import com.qunar.ops.recruit.util.QUtils;
import com.qunar.ops.recruit.util.RecruitConst;
import com.qunar.ops.recruit.util.RecruitControllerUtils;

@Controller
public class HrController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterviewerService interService;
	@Autowired
	StudentService studentService;
	@Autowired
	WaitService waitService;
	
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
	
	@RequestMapping(value = "/hr/getInterviewers1")
	@ResponseBody
	public List<Interviewer> getInterviewers(HttpServletRequest request) {
		System.out.println("=======");
//		Map<String, String> vars = commonRequest.getVars();
//		int noSize[] = RecruitControllerUtils.getPageNoAndSize(vars);
//		int pageSize = noSize[0];
//		int pageNo = noSize[1];
//		int pageSize = 3;
//		int pageNo = 1;
//		List<Interviewer> list = inServe.getInterviewers((pageNo - 1) * pageSize, pageSize, null,null);
//		System.out.println(list);
//		for(Interviewer i: list){
//			System.out.println(i.getName());
//		}
		return null;
	}
	
	@RequestMapping(value = "/hr/addInterviewers")
	@ResponseBody
	public BaseResult addInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
//		System.out.println("=======");
		Map<String, String> vars = commonRequest.getVars();
		System.out.println(vars);
		Interviewer inter = interService.createInterviewer(vars);
		interService.addInterviewer(inter);
		return BaseResult.getSuccessResult("success");
	}
	
	@RequestMapping(value = "/hr/updateInterviewers")
	@ResponseBody
	public BaseResult updateInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
//		System.out.println("=======");
		Map<String, String> vars = commonRequest.getVars();
//		Interviewer inter = createInterviewer(vars);
//		inServe.addInterviewer(inter);
		return BaseResult.getSuccessResult("success");
	}

	@RequestMapping(value = "/hr/getInterviewers")
	public String getInterviewers(HttpServletRequest request,  ModelMap model) {
		System.out.println("===========================");
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

		List<Interviewer> list = interService.getInterviewers();
		model.addAttribute("message", list);
		return "/viewer_manage";
	}


}
