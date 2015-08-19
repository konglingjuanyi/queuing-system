package com.qunar.ops.recruit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.RemoteAccessException;
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
import com.qunar.ops.recruit.util.OAControllerUtils;
import com.qunar.ops.recruit.util.QUtils;

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
	
	@RequestMapping(value = "/interview/getInterviewers")
	@ResponseBody
	public List<Interviewer> getInterviewers(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
		System.out.println("=======");
		Map<String, String> vars = commonRequest.getVars();
		int noSize[] = OAControllerUtils.getPageNoAndSize(vars);
		int pageSize = noSize[0];
		int pageNo = noSize[1];
//		int pageSize = 3;
//		int pageNo = 1;
		List<Interviewer> list = inServe.getInterviewers((pageNo - 1) * pageSize, pageSize);
		System.out.println(list);
		for(Interviewer i: list){
			System.out.println(i.getName());
		}
		return list;
	}
	
	@RequestMapping(value = "/interview/all")
	public List<Interviewer> all(HttpServletRequest request, @RequestBody CommonRequest commonRequest) {

		Map<String, String> vars = commonRequest.getVars();
		int noSize[] = OAControllerUtils.getPageNoAndSize(vars);
		int pageSize = noSize[0];
		int pageNo = noSize[1];
		String startTime = vars.get("startTime");
		String endTime = vars.get("endTime");
		Date _startTime = null;
		if (startTime != null) {
			try {
				_startTime = sdf.parse(startTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
		Date _endTime = null;
		if (endTime != null) {
			try {
				_endTime = sdf.parse(endTime);
			} catch (ParseException e) {
				logger.warn(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
		List<Interviewer> list = inServe.getInterviewers((pageNo - 1) * pageSize, pageSize);
		DataResult dataResult;
		try {
//			dataResult = getAllTableInfos(list, userId, 1);
		} catch (RemoteAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}
		for(Interviewer i: list){
			System.out.println(i.getName());
		}
		return null;
	}
}
