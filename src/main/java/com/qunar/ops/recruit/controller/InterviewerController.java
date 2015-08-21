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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.result.CommonRequest;
import com.qunar.ops.recruit.result.DataResult;
import com.qunar.ops.recruit.service.InterviewerService;
import com.qunar.ops.recruit.util.QUtils;
import com.qunar.ops.recruit.util.RecruitConst;
import com.qunar.ops.recruit.util.RecruitControllerUtils;

@Controller
public class InterviewerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterviewerService inServe;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 面试官管理 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/interview/show_list.html")
	public ModelAndView addApply(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/recruit/show_list");
		return mav;
	}
	
	@RequestMapping(value = "/interview/getInterviewers1")
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
	
	@RequestMapping(value = "/interview/addInterviewers")
	@ResponseBody
	public BaseResult addInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
//		System.out.println("=======");
		Map<String, String> vars = commonRequest.getVars();
		Interviewer inter = createInterviewer(vars);
		inServe.addInterviewer(inter);
		return BaseResult.getSuccessResult(null);
	}
	
	@RequestMapping(value = "/interview/updateInterviewers")
	@ResponseBody
	public BaseResult updateInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
//		System.out.println("=======");
		Map<String, String> vars = commonRequest.getVars();
		Interviewer inter = createInterviewer(vars);
		inServe.addInterviewer(inter);
		return BaseResult.getSuccessResult(null);
	}

	@RequestMapping(value = "/interview/getInterviewers")
	@ResponseBody
	public BaseResult getInterviewers(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		int noSize[] = RecruitControllerUtils.getPageNoAndSize(vars);
		int pageSize = noSize[0];
		int pageNo = noSize[1];
		pageNo = pageNo <= 0 ? 1 : pageNo;
		pageSize = pageSize > 0 ? pageSize : 20;
		String startTime = vars.get("startTime");
		String endTime = vars.get("endTime");
		Date _startTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return BaseResult.getErrorResult(RecruitConst.DATE_FORMAT_ERROR, RecruitConst.DATE_FORMAT_ERROR_MSG);
			}
		}
		Date _endTime = null;
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return BaseResult.getErrorResult(RecruitConst.DATE_FORMAT_ERROR, RecruitConst.DATE_FORMAT_ERROR_MSG);
			}
		}		
		String cityName = vars.get("cityName");
		List<Interviewer> list = inServe.getInterviewers((pageNo - 1) * pageSize, pageSize, _startTime, _endTime, cityName);
		List<String[]> retList = tranfer2stringArray(list);
		DataResult dataResult = new DataResult();
		dataResult.setCount(list.size());
		dataResult.setTableInfos(retList);
		return BaseResult.getSuccessResult(dataResult);
	}

	private List<String[]> tranfer2stringArray(List<Interviewer> list) {
		List<String[]> retList = new LinkedList<String[]>();
//		for(Interviewer inter : list){
//			String[] tmp = new String[]{inter.getId()+"", inter.getName(), inter.getUserName(),
//					inter.getPassword(), inter.getCity(), inter.getJob(), inter.getRole(),
//					inter.getOneCount()+"", inter.getTwoCount()+"", inter.getState()+"", QUtils.date2str(inter.getStartDate()),
//					QUtils.date2str(inter.getEndTime()), QUtils.date2str(inter.getCreateTime())};
//			for(String s : tmp){
//				System.out.print(s+" ");
//			}
//			System.out.println();
//			retList.add(tmp);
//		}
		return retList;
	}
	
	private Interviewer createInterviewer(Map<String, String> vars) {
		Interviewer inter = new Interviewer();
		String new_city = vars.get("new_city");
		String new_name = vars.get("new_name");
		String new_job = vars.get("new_job");
		String new_user = vars.get("new_user");
		String new_password = vars.get("new_password");
		String new_startDate = vars.get("new_startDate");
		String new_endDate = vars.get("new_endDate");
		String new_role = vars.get("new_role");
		inter.setCity(new_city);
		inter.setUserName(new_user);
		inter.setPassword(new_password);
		Date startDate = QUtils.formatDate(new_startDate);
		Date endDate = QUtils.formatDate(new_endDate);
		inter.setStartDate(startDate);
		inter.setOneCount(0);
		inter.setTwoCount(0);
		inter.setCreateTime(new Date());
		return inter;
	}
}
