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
public class InterviewerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterviewerService inServe;
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
	
	/**
	 * 面试官点击继续面试和开始面试获取下一个待面试的候选人
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "/interview/getNextStudent")
	@ResponseBody
	public BaseResult getNextStudent(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		//通过ID获取面试官信息
		Interviewer inter = inServe.getInterviewersById(vars.get("id"));
		StudentWaiter  stu;
		if(!"".equals(inter.getTwoView())){
			//如果面试官的二面角色不为空,先从二面队列中拿数据
			//根据面试官的城市、面试职位去二面队列里面拿一面不是自己的二面候选人
			stu = waitService.getTwoView(inter.getCity(),inter.getTwoView(),inter.getUserName());
			if(stu==null && !"".equals(inter.getOneView())){
				//如果二面候选人为空并且是一面面试官，查询一面队列候选人
				stu = waitService.removeHighestPriorityFromList(inter.getCity(),inter.getTwoView(),inter.getUserName());
			}
		}else{
			//只有一面角色从一面队列中拿数据`
			stu = waitService.removeHighestPriorityFromList(inter.getCity(),inter.getTwoView(),inter.getUserName());
		}
		return BaseResult.getSuccessResult(stu);
	}
	
	/**
	 * 一面结束根据一面结果判断候选人是否通过是否需要进行二面
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "/interview/finishOneView")
	@ResponseBody
	public BaseResult finishOneView(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		//根据参数保存候选人的基本信息和评估表信息
		
		//end
		//根据评估表的信息判断候选人时候需要参加二面
		if(!"不通过".equals(vars.get("ID"))){
			//添加到二面队列中
			Student stu =studentService.getStudentByPhone(vars.get("phone"));
			StudentWaiter sw =new StudentWaiter(stu,0);
			waitService.addTwoList(sw);
			return BaseResult.getSuccessResult(null);
		}else{
			//结束面试
			return BaseResult.getSuccessResult(null);
		}
	}
}
