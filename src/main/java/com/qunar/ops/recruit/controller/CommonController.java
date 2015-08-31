package com.qunar.ops.recruit.controller;

import java.util.HashSet;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qunar.ops.recruit.model.Phase;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.result.CommonRequest;
import com.qunar.ops.recruit.service.PhaseService;
import com.qunar.ops.recruit.service.StudentService;
import com.qunar.ops.recruit.service.WaitService;
import com.qunar.ops.recruit.util.QUtils;
import com.qunar.ops.recruit.util.RecruitConst;

@Controller
public class CommonController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	WaitService ws;
	@Autowired
	StudentService ss;
	@Autowired
	PhaseService ps;
	
	/**
	 * login
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index(HttpServletRequest request) {
		request.getSession().getAttribute("username");
		request.getSession().getAttribute("password");
		logger.info("登录的用户名为{}====密码为{}", request.getSession().getAttribute("username"),request.getSession().getAttribute("password"));
		return "redirect:/login";
	}
	
	
	/**
	 * logout
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		QUtils.setUsername(response, "pd", null, true);
		QUtils.setUsername(response, "un", null, true);
		QUtils.setUsername(response, "test-userid", null, false);
		return "redirect:/login";
	}
	
	
	
	@RequestMapping(value = "/restart")
	@ResponseBody
	public String restart(HttpServletRequest request,HttpServletResponse response) {
		List<Student> l1 = ss.getOneList();
		List<Student> l2 = ss.getTwoList();
		ws.recovery(l1, l2);
		return "success";
	}
	
	@RequestMapping(value = "/getAllYears")
	@ResponseBody
	public Set<String> getAllYears(HttpServletRequest request,HttpServletResponse response) {
		Set<String> set = new HashSet<String>();
		List<Phase> list = ps.getPhases();
		if(list != null){
			for (Phase phase : list) {
				set.add(phase.getYearInfo());
			}
		}
		return set;
	}
	
	@RequestMapping(value = "/getAllYears1")
	@ResponseBody
	public Set<String> getAllYears1(HttpServletRequest request,HttpServletResponse response) {
		Set<String> set = new HashSet<String>();
		List<Phase> list = ps.getPhases();
		if(list != null){
			for (Phase phase : list) {
				set.add(phase.getYearInfo());
			}
		}
		return set;
	}
	
	private void updateYearPhaseAndCity(Phase phase, HttpSession session) {
		// TODO Auto-generated method stub
		if(phase != null){
			session.setAttribute("year", phase.getYearInfo());
			session.setAttribute("phase", phase.getPhaseName());
			if(phase.getCityName() != null){
				String[] aa = phase.getCityName().split("\\|");
				if(aa.length > 0)
					session.setAttribute("city", aa[0]);
			}
		}
	}


	@RequestMapping(value = "/getAllPhases")
	@ResponseBody
	public List<Phase> getFirstPhase(HttpServletRequest request,HttpSession session) {
		List<Phase> list = ps.getPhases();
		if(list != null && list.size() > 0){
			updateYearPhaseAndCity(list.get(0), session);
		}
		return list;
	}


	@RequestMapping(value = "/getPhaseAndCityByYear")
	@ResponseBody
	public List<Phase> getPhaseAndCityByYear(HttpServletRequest request,HttpSession session, @RequestBody CommonRequest commonRequest) {
		
		Map<String, String> vars = commonRequest.getVars();
		List<Phase> phases = ps.getPhaseAndCityByYear(vars.get("year"));
		if(phases != null && phases.size() > 0){
			updateYearPhaseAndCity(phases.get(0), session);
		}
		return phases;
	}
	

	@RequestMapping(value = "/getPhaseAndCityByYear1")
	@ResponseBody
	public List<Phase> getPhaseAndCityByYear1(HttpServletRequest request,HttpSession session, @RequestBody CommonRequest commonRequest) {
		
		Map<String, String> vars = commonRequest.getVars();
		List<Phase> phases = ps.getPhaseAndCityByYear(vars.get("year"));
		return phases;
	}
	
	@RequestMapping(value = "/getCityByPhase")
	@ResponseBody
	public Phase getCityByPhase(HttpServletRequest request,HttpSession session, @RequestBody CommonRequest commonRequest) {
		
		Map<String, String> vars = commonRequest.getVars();
		Phase phase = ps.getCityByPhase(vars.get("phaseName"));
		updateYearPhaseAndCity(phase, session);
		return phase;
	}
	
	@RequestMapping(value = "/getCityByPhase1")
	@ResponseBody
	public Phase getCityByPhase1(HttpServletRequest request,HttpSession session, @RequestBody CommonRequest commonRequest) {
		
		Map<String, String> vars = commonRequest.getVars();
		Phase phase = ps.getCityByPhase(vars.get("phaseName"));
		return phase;
	}
	
	@RequestMapping(value = "/updateOprateCity")
	@ResponseBody
	public BaseResult updateOprateCity(HttpServletRequest request,HttpSession session, @RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		String city = vars.get("city");
		session.setAttribute("city", city);
		return BaseResult.getSuccessResult("success");
	}
	
	@RequestMapping(value = "/updateOprateCity1")
	@ResponseBody
	public BaseResult updateOprateCity1(HttpServletRequest request,HttpSession session, @RequestBody CommonRequest commonRequest) {
		Map<String, String> vars = commonRequest.getVars();
		return BaseResult.getSuccessResult("success");
	}

	
}
