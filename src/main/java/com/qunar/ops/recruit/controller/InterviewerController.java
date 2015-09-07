package com.qunar.ops.recruit.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.qunar.ops.recruit.model.Interviewer;
import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.model.StudentAssess;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.result.CommonRequest;
import com.qunar.ops.recruit.result.DataResult;
import com.qunar.ops.recruit.service.InterviewerService;
import com.qunar.ops.recruit.service.PcHrService;
import com.qunar.ops.recruit.service.PhaseInterviewService;
import com.qunar.ops.recruit.service.PhaseService;
import com.qunar.ops.recruit.service.StudentAssessService;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.service.StudentWaiter;
import com.qunar.ops.recruit.service.WaitService;
import com.qunar.ops.recruit.util.FileUpload;
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
	@Transactional
	public BaseResult addInterviewer(HttpServletRequest request,@RequestBody CommonRequest commonRequest) {
//		System.out.println("=======");
		Map<String, String> vars = commonRequest.getVars();
		Interviewer inter = interServe.createInterviewer(vars);
		interServe.addInterviewer(inter);
		return BaseResult.getSuccessResult(null);
	}
	
	@RequestMapping(value = "/interview/updateInterviewers")
	@ResponseBody
	@Transactional
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
	@Transactional
	public BaseResult getOneInterview(HttpServletRequest request, HttpSession session, ModelMap mm) {
		return getNextStudent(session, mm);
	}
	
	private BaseResult getNextStudent(HttpSession session, ModelMap mm) {
		Object obj = session.getAttribute("user");
		if(obj != null){
			if(obj instanceof Interviewer){
				Interviewer inter = (Interviewer) obj;
				Interviewer newInter = interServe.getInterviewerByUserId(inter.getId());
				inter = newInter;
				session.setAttribute("user", newInter);
				String[] arrs = getYearPhaseAndCity(session);
				StudentWaiter  stuW = null;
				int oneOrtwo = 1;
				if(inter.getTwoView() !=null && !"".equals(inter.getTwoView())){
					//如果面试官的二面角色不为空,先从二面队列中拿数据
					//根据面试官的城市、面试职位去二面队列里面拿一面不是自己的二面候选人
					oneOrtwo = 2;
					stuW = waitService.getHighestPriorityFromTwoList(arrs[0], arrs[1], arrs[2] ,inter.getTwoView(), inter.getUserName());
					if(stuW==null && !"".equals(inter.getOneView())){
						//如果二面候选人为空并且是一面面试官，查询一面队列候选人
						stuW = waitService.getHighestPriorityFromList(arrs[0], arrs[1], arrs[2], inter.getOneView(), inter.getUserName());
						oneOrtwo = 1;
					}
				}else{
					//只有一面角色从一面队列中拿数据
					stuW = waitService.getHighestPriorityFromList(arrs[0], arrs[1], arrs[2], inter.getOneView(), inter.getUserName());
				}
				PhaseInterviewer pi = piService.getPhaseInterviewerBy(arrs[0], arrs[1], arrs[2], inter.getUserName());
				if(stuW == null){
					mm.addAttribute("message", RecruitConst.WAITING_FOR_INTERVIEW_IS_EMPTY);
					//往全局map中放学生信息
					PcHrService.change(pi, null);
					PcHrService.changeState(pi, RecruitConst.INTERVIEWER_STATE_WAITING);
					return BaseResult.getSuccessResult(mm);
				}else{
					Student stu = stuW.getStu();
					Student newStu = new Student();
					newStu.setId(stu.getId());
					if(oneOrtwo == 1){
						stu.setState(RecruitConst.STUDENT_STATE_GOING2ONEROOM);
						stu.setFirstTry(inter.getUserName());
						newStu.setFirstTry(inter.getUserName());
						newStu.setState(RecruitConst.STUDENT_STATE_GOING2ONEROOM);
						mm.addAttribute("message", "1");
						mm.addAttribute("student", stuW.getStu());
						mm.addAttribute("phaseInterviewer", pi);
					}else{
						stu.setSecondTry(inter.getUserName());
						stu.setState(RecruitConst.STUDENT_STATE_GOING2TWOROOM);
						newStu.setSecondTry(inter.getUserName());
						newStu.setState(RecruitConst.STUDENT_STATE_GOING2TWOROOM);
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
					//往全局map中放学生信息
					PcHrService.change(pi, stu);
					PcHrService.changeState(pi, RecruitConst.INTERVIEWER_STATE_WAITING);
					//更新学生状态
					studentService.updateStudent(newStu);
					//从队列删除
					if(oneOrtwo == 1){
						waitService.removeHighestPriorityFromOneList(stuW);
					}else{
						waitService.removeHighestPriorityFromTwoList(stuW);
					}
					return BaseResult.getSuccessResult(mm);
				}
			}else{
				//不是interviewer，無權訪問
				mm.addAttribute("message", RecruitConst.AUTHORITY_ERROR_MSG);
				return BaseResult.getSuccessResult(mm);
			}
		}else{
			//无seesion, 重新登录
			mm.addAttribute("message", RecruitConst.NO_LOGIN_MSG);
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
	@Transactional
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
		if(stu.getFirstTry() != null && stu.getState().equals(RecruitConst.STUDENT_STATE_GOING2ONEROOM)){
			stu.setState(RecruitConst.STUDENT_STATE_ONE_VIEW);
			newStu.setState(RecruitConst.STUDENT_STATE_ONE_VIEW);
			newInter.setViewCount(inter.getViewCount()+1);
			inter.setViewCount(inter.getViewCount()+1);
			newPi.setOneCount(pi.getOneCount()+1);
			newPi.setStatus(RecruitConst.INTERVIEWER_STATE_VIEWING);
			PcHrService.changeStateOfStudent(pi, RecruitConst.STUDENT_STATE_ONE_VIEW);
		}else{
			stu.setState(RecruitConst.STUDENT_STATE_TWO_VIEW);
			newStu.setState(RecruitConst.STUDENT_STATE_TWO_VIEW);
			newInter.setViewCount(inter.getViewCount()+1);
			inter.setViewCount(inter.getViewCount()+1);
			newPi.setTwoCount(pi.getTwoCount()+1);
			newPi.setStatus(RecruitConst.INTERVIEWER_STATE_VIEWING);
			PcHrService.changeStateOfStudent(pi, RecruitConst.STUDENT_STATE_TWO_VIEW);
		}
		studentService.updateStudent(newStu);
		interServe.updateInterviewer(newInter);
		piService.update(newPi);
		PcHrService.changeState(pi, RecruitConst.INTERVIEWER_STATE_VIEWING);
		return BaseResult.getSuccessResult("");
	}
	
	@RequestMapping(value = "/interviewer/finishAndRest")
	@ResponseBody
	@Transactional
	public BaseResult finishAndRest(HttpServletRequest request, HttpSession session, ModelMap mm, @RequestBody CommonRequest commonRequest) {
		finishUpdateStateAndRecordAsess(session, commonRequest);
		String[] arrs = getYearPhaseAndCity(session);
		Interviewer inter = (Interviewer) session.getAttribute("user");
		PhaseInterviewer pi = piService.getPhaseInterviewerBy(arrs[0], arrs[1], arrs[2], inter.getUserName());
		PhaseInterviewer newPi = new PhaseInterviewer();
		newPi.setId(pi.getId());
		newPi.setStatus(RecruitConst.INTERVIEWER_STATE_REST);
		piService.update(newPi);
		PcHrService.changeState(pi, RecruitConst.INTERVIEWER_STATE_REST);
		PcHrService.change(pi, null);
		return BaseResult.getSuccessResult("");
	}
	
	@RequestMapping(value = "/interviewer/finishAndContinue")
	@ResponseBody
	@Transactional
	public BaseResult finishAndContinue(HttpServletRequest request, HttpSession session, ModelMap mm, @RequestBody CommonRequest commonRequest) {
		finishUpdateStateAndRecordAsess(session, commonRequest);
		return getNextStudent(session, mm);
	}
	
	private void finishUpdateStateAndRecordAsess(HttpSession session, CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		Student stu = (Student) session.getAttribute("student");
		Interviewer inter = (Interviewer) session.getAttribute("user");
		StudentAssess sa = saService.createStudentAssess(vars);
		String[] arrs = getYearPhaseAndCity(session);
		PhaseInterviewer pi = piService.getPhaseInterviewerBy(arrs[0], arrs[1], arrs[2], inter.getUserName());
		PhaseInterviewer newPi = new PhaseInterviewer();
		newPi.setId(pi.getId());
		sa.setStudenId(stu.getId());
		sa.setJob(stu.getJob());
		Student newStu = studentService.getStudentByPhone(stu.getPhone());
		boolean b = false;
		//之前是一面中
		if(newStu.getState().equals(RecruitConst.STUDENT_STATE_ONE_VIEW)){
			sa.setOneViewer(inter.getUserName());
			if(sa.getOneConclusion().equals(RecruitConst.RESULT_NOT_PASS)){
				//一面评估表未通过
				newStu.setState(RecruitConst.STUDENT_STATE_ONE_NOT_PASS);
			}else{
				b = true;
				addPhaseInterviewerResult(newStu, newPi, pi, 1);
				if(vars.get("decheckbox") != null && !vars.get("decheckbox").equals("")){
					newStu.setState(RecruitConst.STUDENT_STATE_TWO_PASS);
				}else{
					newStu.setState(RecruitConst.STUDENT_STATE_ONE_PASS);
					waitService.addTwoList(new StudentWaiter(stu));
				}
			}
			saService.add(sa);
		}else{
			sa.setTwoViewer(inter.getUserName());
			if(sa.getTwoConclusion().equals(RecruitConst.RESULT_NOT_PASS)){
				//二面评估表未通过
				newStu.setState(RecruitConst.STUDENT_STATE_TWO_NOT_PASS);
			}else{
				b = true;
				addPhaseInterviewerResult(newStu, newPi, pi, 2);
				newStu.setState(RecruitConst.STUDENT_STATE_TWO_PASS);
			}
			saService.updateByStudentId(sa);
		}
		if(b){
			piService.update(newPi);
		}
		studentService.updateStudent(newStu);
		session.setAttribute("student", null);
	}

	private void addPhaseInterviewerResult(Student newStu,
			PhaseInterviewer newPi, PhaseInterviewer pi, int i) {
		if(i == 1){
			if(newStu.getJob().equals(RecruitConst.JOB_RD)){
				newPi.setFirstRd(pi.getFirstRd()+1);
			}else if(newStu.getJob().equals(RecruitConst.JOB_FE)){
				newPi.setFirstFe(pi.getFirstFe()+1);
			}else if(newStu.getJob().equals(RecruitConst.JOB_QA)){
				newPi.setFirstQa(pi.getFirstQa()+1);
			}else{
				
			}
		}else{
			if(newStu.getJob().equals(RecruitConst.JOB_RD)){
				newPi.setSecondRd(pi.getSecondRd()+1);
			}else if(newStu.getJob().equals(RecruitConst.JOB_FE)){
				newPi.setSecondFe(pi.getSecondFe()+1);
			}else if(newStu.getJob().equals(RecruitConst.JOB_QA)){
				newPi.setSecondQa(pi.getSecondQa()+1);
			}else{
				
			}
		}

		
		
	}

	@RequestMapping(value = "/interviewer/noComeFinish")
	@ResponseBody
	@Transactional
	public BaseResult noComeFinish(HttpServletRequest request, HttpSession session, ModelMap mm) {
		Student stu = (Student) session.getAttribute("student");
		stu.setState(RecruitConst.STUDENT_STATE_PASS_ME);
		stu.setTrueTime(null);
		studentService.updateStudentNotSelective(stu);
		return getNextStudent(session, mm);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/interviewer/getCurrentViewInfo")
	@ResponseBody
	public BaseResult getCurrentViewInfo(HttpServletRequest request, HttpSession session) {
		Interviewer inter = (Interviewer) session.getAttribute("user");
		inter =interServe.getInterviewerByUserName(inter.getUserName());
		String year = (String)session.getAttribute("year");
		String phase = (String)session.getAttribute("phase");
		String city = (String)session.getAttribute("city");
		PhaseInterviewer pi = piService.getPhaseInterviewerBy(year, phase, city, inter.getUserName());
		List ret = new LinkedList();
		ret.add(inter);
		ret.add(pi);
		Student stu = PcHrService.get(pi);
		if(stu == null){
			return BaseResult.getErrorResult(-1, "failed", ret);
		}else{
			ret.add(stu);
			StudentAssess sa = saService.getStudentAssessByStudentId(stu.getId());
			ret.add(sa);
			return BaseResult.getSuccessResult(ret);
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
//		System.out.println(city);
		session.setAttribute("city", city);
		return BaseResult.getSuccessResult("success");
	}
	
	@RequestMapping(value = "/interviewer/login")
	public String toindex(HttpServletRequest request, HttpSession session,
			HttpServletResponse response, String username, String password, ModelMap model) {
		Object user = request.getSession().getAttribute("user");
		String year=(String) request.getSession().getAttribute("year");
		String city=(String) request.getSession().getAttribute("city");
		String phase=(String) request.getSession().getAttribute("phase");
//		System.out.println(year+" "+phase+" "+city+"-=-===-==-");
		
		if(user == null){
			System.out.println("user is null");
			if(username != null && password != null){
				Interviewer inter = interServe.getInterviewerByNameAndPass(username, password);
				System.out.println(inter);
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
						//添加到全局map
						pi.setStatus(RecruitConst.INTERVIEWER_STATE_WAITING);
						if(PcHrService.containsKey(pi)){
							session.setAttribute("student", PcHrService.get(pi));
						}else{
							PcHrService.put(pi, null);
						}
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
	
	//作品分享文件上传
    @RequestMapping(value = "/interviewer/upload", method = RequestMethod.POST)
    public String achiFileUpload(HttpServletRequest request, ModelMap model) {
    	ArrayList<String> imgTypes = Lists.newArrayList("jpg", "png", "bmp", "jpeg");// 支持的图片格式。
        String savePath = "/mfsdata/trainsysfile";/** 后期需要根据功能去改路径 */
        String name=FileUpload.upload(request, imgTypes, savePath);
        return "/upload";
    }
    
    /**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/toupload")
	public ModelAndView welcom(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/upload");
		return mav;
	}
}
