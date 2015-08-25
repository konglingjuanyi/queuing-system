package com.qunar.ops.recruit.controller;

import java.text.SimpleDateFormat;
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
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.result.CommonRequest;
import com.qunar.ops.recruit.result.ResultPlusAdditionalInfo;
import com.qunar.ops.recruit.service.InterviewerService;
import com.qunar.ops.recruit.service.StudentService;
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
	public BaseResult addInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		System.out.println(vars);
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
	public BaseResult deleteInterviewers(HttpServletRequest request, int id) {
		interService.deleteInterviewer(id);
		return BaseResult.getSuccessResult("success");
	}
	
	@RequestMapping(value = "/hr/updateInterviewer")
	@ResponseBody
	public BaseResult updateInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		System.out.println(vars);
		Interviewer inter = interService.createUpdateInterviewer(vars);
		if(interService.getInterviewerByUserNameExceptId(inter.getUserName(), inter.getId()) != null){
			return BaseResult.getErrorResult(RecruitConst.ALREADY_EXIST_USER_ERROR, RecruitConst.ALREADY_EXIST_USER_ERROR_MSG);
		}else{
			interService.updateInterviewer(inter);
			return BaseResult.getSuccessResult("success");
		}
	}

	@RequestMapping(value = "/hr/getInterviewers")
	public String getInterviewers(HttpServletRequest request,  ModelMap model) {
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

		List<Interviewer> list = interService.getInterviewers();
		List<ResultPlusAdditionalInfo> rets = new LinkedList<ResultPlusAdditionalInfo>();
		for (Interviewer interviewer : list) {
			ResultPlusAdditionalInfo info = new ResultPlusAdditionalInfo();
			info.setObj(interviewer);
			info.addStringInfo(interviewer.getFirstFe()+interviewer.getFirstQa()+interviewer.getFirstRd()+"");
			info.addStringInfo(interviewer.getSecondFe()+interviewer.getSecondQa()+interviewer.getSecondRd()+"");
			rets.add(info);
		}
		model.addAttribute("message", rets);
		return "/viewer_manage";
	}

	@RequestMapping(value = "/hr/getInterviewerInfo")
	@ResponseBody
	public BaseResult getInterviewerInfo(HttpServletRequest request,  int id, ModelMap model) {
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

		Interviewer inter = interService.getInterviewersById(id);
		model.addAttribute("message", inter);
		return null;
	}

}
