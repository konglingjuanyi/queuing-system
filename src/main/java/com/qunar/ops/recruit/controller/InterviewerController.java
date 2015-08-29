package com.qunar.ops.recruit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.StudentAssess;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.result.CommonRequest;
import com.qunar.ops.recruit.result.DataResult;
import com.qunar.ops.recruit.service.InterviewerService;
import com.qunar.ops.recruit.service.PhaseInterviewService;
import com.qunar.ops.recruit.service.PhaseService;
import com.qunar.ops.recruit.service.StudentAssessService;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.service.StudentWaiter;
import com.qunar.ops.recruit.service.WaitService;
import com.qunar.ops.recruit.util.RecruitConst;
import com.qunar.ops.recruit.util.RecruitControllerUtils;

@Controller
public class InterviewerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InterviewerService interServe;
	@Autowired
	StudentService studentService;
	@Autowired
	WaitService waitService;
	@Autowired
	PhaseService pService;
	@Autowired
	PhaseInterviewService piService;
	@Autowired
	StudentAssessService saService;
	
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
//		List<Interviewer> list = interServe.getInterviewers((pageNo - 1) * pageSize, pageSize, null,null);
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
		Interviewer inter = interServe.createInterviewer(vars);
		interServe.addInterviewer(inter);
		return BaseResult.getSuccessResult(null);
	}
	
	@RequestMapping(value = "/interview/updateInterviewers")
	@ResponseBody
	public BaseResult updateInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
//		System.out.println("=======");
		Map<String, String> vars = commonRequest.getVars();
		Interviewer inter = interServe.createInterviewer(vars);
		interServe.addInterviewer(inter);
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
		List<Interviewer> list = interServe.getInterviewers((pageNo - 1) * pageSize, pageSize, _startTime, _endTime, cityName);
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

	
	/**
	 * 面试官点击继续面试和开始面试获取下一个待面试的候选人
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "/interviewer/getOneInterview")
	@ResponseBody
	public BaseResult getOneInterview(HttpServletRequest request, HttpSession session, ModelMap mm) {
		return getNextStudent(session, mm);
	}
	
	private BaseResult getNextStudent(HttpSession session, ModelMap mm) {
		Object obj = session.getAttribute("user");
		if(obj != null){
			if(obj instanceof Interviewer){
				Interviewer inter = (Interviewer) obj;
				//Interviewer newInter = interServe.getInterviewerByUserName(inter.getUserName());
				String[] arrs = getYearPhaseAndCity(session);
				StudentWaiter  stuW = null;
				int oneOrtwo = 1;
				if(inter.getTwoView() !=null && !"".equals(inter.getTwoView())){
					//如果面试官的二面角色不为空,先从二面队列中拿数据
					//根据面试官的城市、面试职位去二面队列里面拿一面不是自己的二面候选人
					oneOrtwo = 2;
					stuW = waitService.removeHighestPriorityFromTwoList(arrs[0], arrs[1], arrs[2] ,inter.getTwoView(), inter.getUserName());
					if(stuW==null && !"".equals(inter.getOneView())){
						//如果二面候选人为空并且是一面面试官，查询一面队列候选人
						stuW = waitService.removeHighestPriorityFromList(arrs[0], arrs[1], arrs[2], inter.getOneView());
						oneOrtwo = 1;
					}
				}else{
					//只有一面角色从一面队列中拿数据
					stuW = waitService.removeHighestPriorityFromList(arrs[0], arrs[1], arrs[2], inter.getOneView());
				}
				if(stuW == null){
					System.out.println("等待队列为空");
					mm.addAttribute("message", RecruitConst.WAITING_FOR_INTERVIEW_IS_EMPTY);
					return BaseResult.getErrorResult(-1, RecruitConst.WAITING_FOR_INTERVIEW_IS_EMPTY);
				}else{
					Student stu = stuW.getStu();
					Student newStu = new Student();
					stu.setState(RecruitConst.STUDENT_STATE_GOING2ROOM);
					newStu.setId(stu.getId());
					newStu.setState(RecruitConst.STUDENT_STATE_GOING2ROOM);
					PhaseInterviewer pi = piService.getPhaseInterviewerBy(arrs[0], arrs[1], arrs[2], inter.getUserName());
					if(oneOrtwo == 1){
						stu.setFirstTry(inter.getUserName());
						newStu.setFirstTry(inter.getUserName());
						mm.addAttribute("message", "1");
						mm.addAttribute("student", stuW.getStu());
						mm.addAttribute("phaseInterviewer", pi);
					}else{
						stu.setSecondTry(inter.getUserName());
						newStu.setSecondTry(inter.getUserName());
						mm.addAttribute("message", "2");
						mm.addAttribute("student", stuW.getStu());
						mm.addAttribute("phaseInterviewer", pi);
						StudentAssess sa = saService.getStudentAssessByStudentId(stuW.getStu().getId());
						mm.addAttribute("assess", sa);
					}
					session.setAttribute("student", stu);
					//更新面试官状态
					pi.setStatus(RecruitConst.INTERVIEWER_STATE_WAITING);
					piService.update(pi);
					//更新学生状态
					studentService.updateStudent(newStu);
					return BaseResult.getSuccessResult(mm);
				}
			}else{
				//不是interviewer，無權訪問
				return BaseResult.getSuccessResult(RecruitConst.AUTHORITY_ERROR_MSG);
			}
		}else{
			//无seesion, 重新登录
			return BaseResult.getSuccessResult(RecruitConst.NO_LOGIN_MSG);
		}
	}

	private String[] getYearPhaseAndCity(HttpSession session) {
		String year = (String)session.getAttribute("year");
		String phase = (String)session.getAttribute("phase");
		String city = (String)session.getAttribute("city");
		return new String[]{year, phase, city};
	}

	/**
	 * 点击开始面试
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/interviewer/beginToInterview")
	@ResponseBody
	public BaseResult beginToInterview(HttpServletRequest request, HttpSession session) {
		Student stu = (Student) session.getAttribute("student");
		Interviewer inter = (Interviewer) session.getAttribute("user");
		String[] arrs = getYearPhaseAndCity(session);
		PhaseInterviewer pi = piService.getPhaseInterviewerBy(arrs[0], arrs[1], arrs[2], inter.getUserName());
		Student newStu = new Student();
		Interviewer newInter = new Interviewer();
		PhaseInterviewer newPi = new PhaseInterviewer();
		newStu.setId(stu.getId());
		newInter.setId(inter.getId());
		newPi.setId(pi.getId());
		if(stu.getFirstTry() != null && stu.getState().equals(RecruitConst.STUDENT_STATE_GOING2ROOM)){
			newStu.setState(RecruitConst.STUDENT_STATE_ONE_VIEW);
			newInter.setViewCount(inter.getViewCount()+1);
			newPi.setOneCount(pi.getOneCount()+1);
			newPi.setStatus(RecruitConst.INTERVIEWER_STATE_VIEWING);
		}else{
			newStu.setState(RecruitConst.STUDENT_STATE_TWO_VIEW);
			newInter.setViewCount(inter.getViewCount()+1);
			newPi.setTwoCount(pi.getTwoCount()+1);
			newPi.setStatus(RecruitConst.INTERVIEWER_STATE_VIEWING);
		}
		studentService.updateStudent(newStu);
		interServe.updateInterviewer(newInter);
		piService.update(pi);
		return BaseResult.getSuccessResult("");
	}
	
	@RequestMapping(value = "/interviewer/finishAndContinue")
	@ResponseBody
	public BaseResult finishInterview(HttpServletRequest request, HttpSession session, @RequestBody CommonRequest commonRequest) {
		Student stu = (Student) session.getAttribute("student");
		Interviewer inter = (Interviewer) session.getAttribute("user");
		String[] arrs = getYearPhaseAndCity(session);
		PhaseInterviewer pi = piService.getPhaseInterviewerBy(arrs[0], arrs[1], arrs[2], inter.getUserName());
		Student newStu = new Student();
		Interviewer newInter = new Interviewer();
		PhaseInterviewer newPi = new PhaseInterviewer();
		newStu.setId(stu.getId());
		newInter.setId(inter.getId());
		newPi.setId(pi.getId());
		if(stu.getState().equals(RecruitConst.STUDENT_STATE_ONE_VIEW)){
			newStu.setState(RecruitConst.STUDENT_STATE_ONE_VIEW);
			newInter.setViewCount(inter.getViewCount()+1);
			newPi.setOneCount(pi.getOneCount()+1);
			newPi.setStatus(RecruitConst.INTERVIEWER_STATE_VIEWING);
		}else{
			newStu.setState(RecruitConst.STUDENT_STATE_TWO_VIEW);
			newInter.setViewCount(inter.getViewCount()+1);
			newPi.setTwoCount(pi.getTwoCount()+1);
			newPi.setStatus(RecruitConst.INTERVIEWER_STATE_VIEWING);
		}
		studentService.updateStudent(newStu);
		interServe.updateInterviewer(newInter);
		piService.update(pi);
		return BaseResult.getSuccessResult("");
	}
	
	@RequestMapping(value = "/interviewer/noComeFinish")
	@ResponseBody
	public BaseResult noComeFinish(HttpServletRequest request, HttpSession session) {
		Student stu = (Student) session.getAttribute("student");
		Interviewer inter = (Interviewer) session.getAttribute("user");
		Student newStu = new Student();
		newStu.setId(stu.getId());
		newStu.setState(RecruitConst.STUDENT_STATE_FINISH);
		newStu.setFirstTry(inter.getUserName());
		
		Interviewer newInter = new Interviewer();
//		newInter.setViewCount(inter.ge);
//		interServe.updateInterviewer(newStu);
		return null;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/interviewer/getCurrentViewInfo")
	@ResponseBody
	public BaseResult getCurrentViewInfo(HttpServletRequest request, HttpSession session) {
		Student stu = (Student) session.getAttribute("student");
		Interviewer inter = (Interviewer) session.getAttribute("user");
		String year = (String)session.getAttribute("year");
		String phase = (String)session.getAttribute("phase");
		String city = (String)session.getAttribute("city");
		PhaseInterviewer pi = piService.getPhaseInterviewerBy(year, phase, city, inter.getUserName());
		Student newStu = new Student();
		List ret = new LinkedList();
		ret.add(inter);
		ret.add(pi);
		if(stu == null){
			return BaseResult.getErrorResult(-1, "failed", ret);
		}else{
			ret.add(stu);
			StudentAssess sa = saService.getStudentAssessByStudentId(stu.getId());
			ret.add(sa);
			return BaseResult.getSuccessResult(ret);
		}
	}
	
	/**
	 * 一面结束根据一面结果判断候选人是否通过是否需要进行二面
	 * @param request
	 * @param commonRequest
	 * @return
	 */
	@RequestMapping(value = "/interviewer/finishOneView")
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
	
	@RequestMapping(value = "/interviewer/getAllYears")
	@ResponseBody
	public Set<String> getAllYears(HttpServletRequest request, HttpSession session) {
		Set<String> set = new HashSet<String>();
		Interviewer inter = (Interviewer) session.getAttribute("user");
		List<PhaseInterviewer> list = piService.getPhaseIntersByName(inter.getUserName());
		if(list != null){
			for (PhaseInterviewer phase : list) {
				set.add(phase.getYear());
			}
		}
		return set;
	}

	@RequestMapping(value = "/interviewer/getPhasesByYear")
	@ResponseBody
	public Set<String> getPhasesByYear(HttpServletRequest request, HttpSession session, @RequestBody CommonRequest commonRequest) {
		Set<String> set = new HashSet<String>();
		Map<String, String> vars = commonRequest.getVars();
		Interviewer inter = (Interviewer) session.getAttribute("user");
		List<PhaseInterviewer> list = piService.getPhasesByYearAndName(vars.get("year"), inter.getUserName());
		if(list != null){
			for (PhaseInterviewer phase : list) {
				set.add(phase.getPhase());
			}
		}
		return set;
	}
	
	@RequestMapping(value = "/interviewer/getCityByYearAndPhase")
	@ResponseBody
	public Set<String> getCityByYearAndPhase(HttpServletRequest request, HttpSession session, @RequestBody CommonRequest commonRequest) {
		Set<String> set = new HashSet<String>();
		Map<String, String> vars = commonRequest.getVars();
		Interviewer inter = (Interviewer) session.getAttribute("user");
		String year = vars.get("year");
		String phase = vars.get("phase");
		session.setAttribute("year", year);
		session.setAttribute("phase", phase);
		List<PhaseInterviewer> list = piService.getCitysByYearPhaseAndName(year, phase, inter.getUserName());
		if(list != null){
			boolean b = true;
			for (PhaseInterviewer pi : list) {
				set.add(pi.getCity());
				if(b){
					session.setAttribute("city", pi.getCity());
					b = false;
				}
			}
		}
		return set;
	}
	
	@RequestMapping(value = "interviewer/updateOprateCity")
	@ResponseBody
	public BaseResult updateOprateCity(HttpServletRequest request,HttpSession session, @RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		String city = vars.get("city");
		System.out.println(city);
		session.setAttribute("city", city);
		return BaseResult.getSuccessResult("success");
	}
	
	@RequestMapping(value = "/interviewer/login")
	public String toindex(HttpServletRequest request,
			HttpServletResponse response, String username, String password, ModelMap model) {
		Object user = request.getSession().getAttribute("user");
		String year=(String) request.getSession().getAttribute("year");
		String city=(String) request.getSession().getAttribute("city");
		String phase=(String) request.getSession().getAttribute("phase");
//		System.out.println(year+" "+phase+" "+city+"-=-===-==-");
		
		if(user == null){
			if(username != null && password != null){
				Interviewer inter = interServe.getInterviewerByNameAndPass(username, password);
				if(inter == null){
					String message= RecruitConst.USERNAM_OR_PASSWORD_ERROR_MSG;
					model.addAttribute("message",message);
					model.addAttribute("flag",-1);
					return "/inter_login";
				}else{
					PhaseInterviewer pi=piService.getPhaseInterviewerBy(year, phase, city, username);
					if(pi == null){
						String message= RecruitConst.CITY_OR_PHASE_ERROR_MSG;
						model.addAttribute("message",message);
						model.addAttribute("flag",-1);
						return "/inter_login";
					}else{
						model.addAttribute("flag",1);
						request.getSession().setAttribute("user", inter);
						return "/view_index";
					}
				}
			}else{
				model.addAttribute("flag",-1);
				return "/inter_login";
			}
		}else{//session中存在用户信息
			if(user instanceof Interviewer){
				model.addAttribute("flag",1);
				return "/view_index";
			}else{
				return "/inter_login";
			}
		}
	}
}
