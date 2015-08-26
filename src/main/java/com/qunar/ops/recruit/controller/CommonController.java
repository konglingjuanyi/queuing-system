package com.qunar.ops.recruit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qunar.ops.recruit.model.Phase;
import com.qunar.ops.recruit.model.Student;
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
	
	@RequestMapping(value = "/getFirstPhase")
	@ResponseBody
	public List<Phase> getFirstPhases(HttpServletRequest request,HttpServletResponse response) {
		List<Phase> list = ps.getPhases();
		if(list != null && list.size() > 0){
			updatePhaseAndCity(list.get(0));
		}
		return list;
	}

	@RequestMapping(value = "/getCityByPhase")
	@ResponseBody
	public Phase getCityByPhase(HttpServletRequest request,HttpServletResponse response, @RequestBody CommonRequest commonRequest) {
		
		Map<String, String> vars = commonRequest.getVars();
		Phase phase = ps.getCityByPhase(vars.get("phaseName"));
		updatePhaseAndCity(phase);
		return phase;
	}
	
	@RequestMapping(value = "/updateOprateCity")
	@ResponseBody
	public void updateOprateCity(HttpServletRequest request,HttpServletResponse response, @RequestBody CommonRequest commonRequest) {
		
		Map<String, String> vars = commonRequest.getVars();
		String city = vars.get("city");
		RecruitConst.city = city;
	}
	
	private void updatePhaseAndCity(Phase phase) {
//		System.out.println("update phase");
		if(phase != null){
			RecruitConst.phase = phase.getPhaseName();
			if(phase.getCityName() != null){
				String[] aa = phase.getCityName().split("\\|");
				if(aa.length > 0)
					RecruitConst.city = aa[0];
			}
		}
//		System.out.println("phase"+RecruitConst.phase);
//		System.out.println("city"+RecruitConst.city);
	}
	
}
