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

@Controller
public class PcController {

	@RequestMapping(value = "/pc/login")
	public String toPcLogin(HttpServletRequest request,ModelMap model) {
		return "/pc/pc_login";
	}
	
	@RequestMapping(value = "/pc/toindex")
	public String toPcIndex(HttpSession session, HttpServletRequest request,ModelMap model) {
		Map<PhaseInterviewer, Student> map = PcHrService.getAllInterviewers(getYearPhaseAndCity(session));
		List<Map<PhaseInterviewer, Student>> list = makeList(map);
		model.addAttribute("message", list);
		return "/pc/pc_index";
	}
	
	@RequestMapping(value = "/pc/selectViewAndStudent")
	@ResponseBody
	public BaseResult selectViewAndStudent(HttpSession session, ModelMap model) {
		Map<PhaseInterviewer, Student> map = PcHrService.getAllInterviewers(getYearPhaseAndCity(session));
		List<Map<PhaseInterviewer, Student>> list = makeList(map);
		return BaseResult.getSuccessResult(list);
	}

	private List<Map<PhaseInterviewer, Student>> makeList(
			Map<PhaseInterviewer, Student> map) {
		List<Map<PhaseInterviewer, Student>> list = new LinkedList<Map<PhaseInterviewer,Student>>();
		for (PhaseInterviewer pi : map.keySet()) {
			Map<PhaseInterviewer, Student> tmp = new HashMap<PhaseInterviewer, Student>();
			tmp.put(pi, map.get(pi));
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
