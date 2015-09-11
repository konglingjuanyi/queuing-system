package com.qunar.ops.recruit.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	private List<List> makeList1(
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
	
	private List<List> makeList(
			Map<PhaseInterviewer, Student> map) {
		List<List> list = new LinkedList<List>();
		Entry<PhaseInterviewer, Student>[] entries = new Entry[map.size()];
		int i = 0;
		for (Entry<PhaseInterviewer, Student> entry : map.entrySet()) {
			entries[i] = entry;
			i++;
		}
		Arrays.sort(entries, new Comparator<Entry<PhaseInterviewer, Student>>() {

			@Override
			public int compare(Entry<PhaseInterviewer, Student> o1,
					Entry<PhaseInterviewer, Student> o2) {
				PhaseInterviewer p1 = o1.getKey();
				PhaseInterviewer p2 = o2.getKey();
				Student s1 = o1.getValue();
				Student s2 = o2.getValue();
				if(s1 == null || s2 == null){
					if(s1 != null){
						return -1;
					}else if(s2 != null){
						return 1;
					}else{
						return p1.getRoom().compareTo(p2.getRoom());
					}
				}
				if(isOneView(s1) && isTwoView(s2)){
					return -1;
				}else if(isOneView(s2) && isTwoView(s1)){
					return 1;
				}else{
					if(s1.getJob().equals(s2.getJob())){
						return p1.getRoom().compareTo(p2.getRoom());
					}else{
						if(s1.getJob().equals(RecruitConst.JOB_RD)){
							return -1;
						}else if(s1.getJob().equals(RecruitConst.JOB_QA)){
							return 1;
						}else if(s2.getJob().equals(RecruitConst.JOB_RD)){
							return 1;
						}else{
							return -1;
						}
					}
				}
				
			}

			private boolean isTwoView(Student s) {
				return s.getState().equals(RecruitConst.STUDENT_STATE_GOING2TWOROOM) ||
						s.getState().equals(RecruitConst.STUDENT_STATE_TWO_VIEW);
			}

			private boolean isOneView(Student s) {
				return s.getState().equals(RecruitConst.STUDENT_STATE_GOING2ONEROOM) ||
						s.getState().equals(RecruitConst.STUDENT_STATE_ONE_VIEW);
			}
		});
//		for (Entry<PhaseInterviewer, Student> entry : entries) {
//			System.out.println(entry.getKey()+" "+entry.getValue());
//		}
		for (Entry<PhaseInterviewer, Student> entry : entries) {
			PhaseInterviewer pi = entry.getKey();
			Student stu = entry.getValue();
			PhaseInterviewer newPi=piService.getPhaseInterviewerBy(pi.getYear(), pi.getPhase(), pi.getCity(), pi.getIntervierName());
			pi.setRoom(newPi.getRoom());
			List tmp = new LinkedList();
			tmp.add(newPi);
			tmp.add(stu);
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
