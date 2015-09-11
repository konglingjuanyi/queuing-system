package com.qunar.ops.recruit.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.service.PcHrService;
import com.qunar.ops.recruit.service.PhaseInterviewService;
import com.qunar.ops.recruit.util.RecruitConst;

@Controller
public class PcController {

	@Autowired
	PhaseInterviewService piService;
	
	@RequestMapping(value = "/pc/login")
	public String toPcLogin(HttpServletRequest request,ModelMap model) {
		return "/pc/pc_login";
	}
	
	@RequestMapping(value = "/pc/toindex")
	public String toPcIndex(HttpSession session, HttpServletRequest request, String j_code, ModelMap model) {
		String kaptcha = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		System.out.println(j_code);
		if(request.getSession().getAttribute("success") != null){
			Map<PhaseInterviewer, Student> map = PcHrService.getAllInterviewers(getYearPhaseAndCity(session));
			List<List> list = makeList(map);
			for (List list2 : list) {
				PhaseInterviewer pi = (PhaseInterviewer) list2.get(0);
				if(pi.getStatus().equals(RecruitConst.INTERVIEWER_STATE_WAITING) && list2.get(1) != null){
					Student s = (Student) list2.get(1);
					model.addAttribute("room", pi.getRoom());
					model.addAttribute("name", s.getName());
					break;
				}
			}
			if(!model.containsKey("room")){
				model.addAttribute("room", "");
				model.addAttribute("name", "");
			}
			model.addAttribute("message", list);
			request.getSession().setAttribute("success", "success");
			return "/pc/pc_index";
		}else{
			if(kaptcha != null && kaptcha.equals(j_code)){
				Map<PhaseInterviewer, Student> map = PcHrService.getAllInterviewers(getYearPhaseAndCity(session));
				List<List> list = makeList(map);
				for (List list2 : list) {
					PhaseInterviewer pi = (PhaseInterviewer) list2.get(0);
					if(pi.getStatus().equals(RecruitConst.INTERVIEWER_STATE_WAITING) && list2.get(1) != null){
						Student s = (Student) list2.get(1);
						model.addAttribute("room", pi.getRoom());
						model.addAttribute("name", s.getName());
						break;
					}
				}
				if(!model.containsKey("room")){
					model.addAttribute("room", "");
					model.addAttribute("name", "");
				}
				model.addAttribute("message", list);
				request.getSession().setAttribute("success", "success");
				return "/pc/pc_index";
			}else{
				return "/pc/pc_login";
			}
		}
		
		
	}
	
	@RequestMapping(value = "/pc/selectViewAndStudent")
	@ResponseBody
	public BaseResult selectViewAndStudent(HttpSession session, ModelMap model) {
		Map<PhaseInterviewer, Student> map = PcHrService.getAllInterviewers(getYearPhaseAndCity(session));
		List<List> list = makeList(map);
		return BaseResult.getSuccessResult(list);
	}

	private List<List> makeList(
			Map<PhaseInterviewer, Student> map) {
		List<List> list = new LinkedList<List>();
		for (PhaseInterviewer pi : map.keySet()) {
			PhaseInterviewer newPi=piService.getPhaseInterviewerBy(pi.getYear(), pi.getPhase(), pi.getCity(), pi.getIntervierName());
			List tmp = new LinkedList();
			tmp.add(newPi);
			tmp.add(map.get(pi));
			list.add(tmp);
		}
		return list;
	}

	private String[] getYearPhaseAndCity(HttpSession session) {
		String year = (String)session.getAttribute("year");
		String phase = (String)session.getAttribute("phase");
		String city = (String)session.getAttribute("city");
		return new String[]{year, phase, city};
	}
}
