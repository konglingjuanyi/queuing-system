package com.qunar.ops.recruit.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qunar.ops.recruit.model.PhaseInterviewer;
import com.qunar.ops.recruit.model.Student;
import com.qunar.ops.recruit.result.BaseResult;
import com.qunar.ops.recruit.service.PcHrService;
import com.qunar.ops.recruit.util.RecruitConst;

@Controller
public class PcController {

	@RequestMapping(value = "/pc/login")
	public String toPcLogin(HttpServletRequest request,ModelMap model) {
		return "/pc/pc_login";
	}
	
	@RequestMapping(value = "/pc/toindex")
	public String toPcIndex(HttpSession session, HttpServletRequest request,ModelMap model) {
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
		return "/pc/pc_index";
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
			List tmp = new LinkedList();
			tmp.add(pi);
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
